
package com.mine_company.controller;

import com.mine_company.dto.SensorDTO;
import com.mine_company.entity.Sensor;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.ISensorService;
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
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private ISensorService sensorSerivce;

    @Autowired
    @Qualifier("sensorMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<SensorDTO>> readAll(SensorDTO filter, Pageable pageable) throws Exception{

        Page<Sensor> page = sensorSerivce.search(
                filter,
                pageable
        );

        Page<SensorDTO> dtoPage = page.map(m ->
                mapper.map(m, SensorDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Sensor obj = sensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, SensorDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorDTO> create(@Valid @RequestBody SensorDTO sensor) throws Exception {
        Sensor obj =sensorSerivce.create(mapper.map(sensor, Sensor.class));
        return new ResponseEntity<>(mapper.map(obj, SensorDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SensorDTO> update(@Valid @RequestBody SensorDTO dto) throws Exception{
        Sensor obj = sensorSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        Sensor mine = sensorSerivce.update(mapper.map(dto, Sensor.class));
        return new ResponseEntity<>(mapper.map(mine, SensorDTO.class), HttpStatus.OK);
    }


    @PatchMapping("/{id}/disable")
    public ResponseEntity<SensorDTO> disabled(@PathVariable Integer id) throws Exception{
        Sensor obj = sensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        Sensor sensor = sensorSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(sensor, SensorDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<SensorDTO> enabled(@PathVariable Integer id) throws Exception{
        Sensor obj = sensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        Sensor sensor = sensorSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(sensor, SensorDTO.class), HttpStatus.OK);
    }

}


