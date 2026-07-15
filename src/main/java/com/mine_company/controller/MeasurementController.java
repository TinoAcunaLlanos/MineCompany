
package com.mine_company.controller;

import com.mine_company.dto.MeasurementDTO;
import com.mine_company.entity.Measurement;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IMeasurementService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private IMeasurementService measurementSerivce;

    @Autowired
    @Qualifier("measurementMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<MeasurementDTO>> readAll(MeasurementDTO filter, Pageable pageable) throws Exception{

        Page<Measurement> page = measurementSerivce.search(
                filter,
                pageable
        );

        Page<MeasurementDTO> dtoPage = page.map(m ->
                mapper.map(m, MeasurementDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Measurement obj = measurementSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, MeasurementDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MeasurementDTO> create(@Valid @RequestBody MeasurementDTO measurement) throws Exception {
        measurement.setMeasuredAt(LocalDateTime.now());
        Measurement obj =measurementSerivce.create(mapper.map(measurement, Measurement.class));
        return new ResponseEntity<>(mapper.map(obj, MeasurementDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MeasurementDTO> update(@Valid @RequestBody MeasurementDTO dto) throws Exception{
        Measurement obj = measurementSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }
        Measurement mine = measurementSerivce.update(mapper.map(dto, Measurement.class));
        return new ResponseEntity<>(mapper.map(mine, MeasurementDTO.class), HttpStatus.OK);
    }


    /*@PatchMapping("/{id}/disable")
    public ResponseEntity<MeasurementDTO> disabled(@PathVariable Integer id) throws Exception{
        Measurement obj = measurementSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        Measurement measurement = measurementSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(measurement, MeasurementDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<MeasurementDTO> enabled(@PathVariable Integer id) throws Exception{
        Measurement obj = measurementSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        Measurement measurement = measurementSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(measurement, MeasurementDTO.class), HttpStatus.OK);
    }*/

}


