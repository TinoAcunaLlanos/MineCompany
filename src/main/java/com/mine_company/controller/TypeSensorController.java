
package com.mine_company.controller;

import com.mine_company.dto.TypeSensorDTO;
import com.mine_company.entity.TypeSensor;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.ITypeSensorService;
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
@RequestMapping("/typeSensors")
public class TypeSensorController {

    @Autowired
    private ITypeSensorService typeSensorSerivce;

    @Autowired
    @Qualifier("typeSensorMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TypeSensorDTO>> readAll(TypeSensorDTO filter, Pageable pageable) throws Exception{
        Page<TypeSensor> page = typeSensorSerivce.search(
                mapper.map(filter, TypeSensor.class),
                pageable
        );

        Page<TypeSensorDTO> dtoPage = page.map(m ->
                mapper.map(m, TypeSensorDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeSensorDTO> readById(@PathVariable("id") Integer id) throws Exception {
        TypeSensor obj = typeSensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, TypeSensorDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TypeSensorDTO> create(@Valid @RequestBody TypeSensorDTO typeSensor) throws Exception {
        TypeSensor obj =typeSensorSerivce.create(mapper.map(typeSensor, TypeSensor.class));
        return new ResponseEntity<>(mapper.map(obj, TypeSensorDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TypeSensorDTO> update(@Valid @RequestBody TypeSensorDTO dto) throws Exception{
        TypeSensor obj = typeSensorSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        TypeSensor mine = typeSensorSerivce.update(mapper.map(dto, TypeSensor.class));
        return new ResponseEntity<>(mapper.map(mine, TypeSensorDTO.class), HttpStatus.OK);
    }

    /*

    @PatchMapping("/{id}/disable")
    public ResponseEntity<TypeSensorDTO> disabled(@PathVariable Integer id) throws Exception{
        TypeSensor obj = typeSensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        TypeSensor typeSensor = typeSensorSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeSensor, TypeSensorDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<TypeSensorDTO> enabled(@PathVariable Integer id) throws Exception{
        TypeSensor obj = typeSensorSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        TypeSensor typeSensor = typeSensorSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeSensor, TypeSensorDTO.class), HttpStatus.OK);
    }

     */

}


