/*
package com.mine_company.controller;

import com.mine_company.dto.PlantDTO;
import com.mine_company.entity.Plant;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IPlantService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private IPlantService plantSerivce;

    @Autowired
    @Qualifier("plantMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PlantDTO>> readAll() throws Exception{
        List<PlantDTO> list = plantSerivce.readAll().stream()
                            .map(plant -> mapper.map(plant, PlantDTO.class))
                            .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Plant obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, PlantDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlantDTO> create(@Valid @RequestBody PlantDTO plant) throws Exception {
        Plant obj =plantSerivce.create(mapper.map(plant, Plant.class));
        return new ResponseEntity<>(mapper.map(obj, PlantDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PlantDTO> update(@Valid @RequestBody PlantDTO dto) throws Exception{
        Plant obj = plantSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        Plant mine = plantSerivce.update(mapper.map(dto, Plant.class));
        return new ResponseEntity<>(mapper.map(mine, PlantDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<PlantDTO> disabled(@PathVariable Integer id) throws Exception{
        Plant obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        Plant plant = plantSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(plant, PlantDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<PlantDTO> enabled(@PathVariable Integer id) throws Exception{
        Plant obj = plantSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        Plant plant = plantSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(plant, PlantDTO.class), HttpStatus.OK);
    }

}

 */
