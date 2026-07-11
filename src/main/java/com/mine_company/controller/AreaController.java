package com.mine_company.controller;

import com.mine_company.dto.AreaDTO;
import com.mine_company.dto.PlantDTO;
import com.mine_company.entity.Area;
import com.mine_company.entity.Plant;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IAreaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private IAreaService areaSerivce;

    @Autowired
    @Qualifier("areaMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<AreaDTO>> readAll(AreaDTO filter, Pageable pageable) throws Exception{
        Page<Plant> page = areaSerivce.search(
                mapper.map(filter, Plant.class),
                pageable
        );

        Page<PlantDTO> dtoPage = page.map(m ->
                mapper.map(m, PlantDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Area obj = areaSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, AreaDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> create(@Valid @RequestBody AreaDTO area) throws Exception {
        Area obj =areaSerivce.create(mapper.map(area, Area.class));
        return new ResponseEntity<>(mapper.map(obj, AreaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AreaDTO> update(@Valid @RequestBody AreaDTO dto) throws Exception{
        Area obj = areaSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        Area mine = areaSerivce.update(mapper.map(dto, Area.class));
        return new ResponseEntity<>(mapper.map(mine, AreaDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<AreaDTO> disabled(@PathVariable Integer id) throws Exception{
        Area obj = areaSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        Area area = areaSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(area, AreaDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<AreaDTO> enabled(@PathVariable Integer id) throws Exception{
        Area obj = areaSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        Area area = areaSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(area, AreaDTO.class), HttpStatus.OK);
    }

}

