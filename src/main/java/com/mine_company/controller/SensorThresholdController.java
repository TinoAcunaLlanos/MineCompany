
package com.mine_company.controller;

import com.mine_company.dto.SensorThresholdDTO;
import com.mine_company.entity.SensorThreshold;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.ISensorThresholdService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensorThresholds")
public class SensorThresholdController {

    @Autowired
    private ISensorThresholdService plantSerivce;

    @Autowired
    @Qualifier("SensorThresholdMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<SensorThresholdDTO>> readAll(SensorThresholdDTO filter, Pageable pageable) throws Exception{
        Page<SensorThreshold> page = plantSerivce.search(
                mapper.map(filter, SensorThreshold.class),
                pageable
        );

        Page<SensorThresholdDTO> dtoPage = page.map(m ->
                mapper.map(m, SensorThresholdDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorThresholdDTO> readById(@PathVariable("id") Integer id) throws Exception {
        SensorThreshold obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, SensorThresholdDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorThresholdDTO> create(@Valid @RequestBody SensorThresholdDTO plant) throws Exception {
        SensorThreshold obj =plantSerivce.create(mapper.map(plant, SensorThreshold.class));
        return new ResponseEntity<>(mapper.map(obj, SensorThresholdDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SensorThresholdDTO> update(@Valid @RequestBody SensorThresholdDTO dto) throws Exception{
        SensorThreshold obj = plantSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        SensorThreshold mine = plantSerivce.update(mapper.map(dto, SensorThreshold.class));
        return new ResponseEntity<>(mapper.map(mine, SensorThresholdDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<SensorThresholdDTO> disabled(@PathVariable Integer id) throws Exception{
        SensorThreshold obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        SensorThreshold plant = plantSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(plant, SensorThresholdDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<SensorThresholdDTO> enabled(@PathVariable Integer id) throws Exception{
        SensorThreshold obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        SensorThreshold plant = plantSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(plant, SensorThresholdDTO.class), HttpStatus.OK);
    }

}


