
package com.mine_company.controller;

import com.mine_company.dto.IndustrialAssertDTO;
import com.mine_company.entity.IndustrialAssert;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IIndustrialAssertService;
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
@RequestMapping("/industrialAsserts")
public class IndustrialAssertController {

    @Autowired
    private IIndustrialAssertService industrialAssertSerivce;

    @Autowired
    @Qualifier("industrialAssertMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<IndustrialAssertDTO>> readAll(IndustrialAssertDTO filter, Pageable pageable) throws Exception{

        Page<IndustrialAssert> page = industrialAssertSerivce.search(
                filter,
                pageable
        );

        Page<IndustrialAssertDTO> dtoPage = page.map(m ->
                mapper.map(m, IndustrialAssertDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustrialAssertDTO> readById(@PathVariable("id") Integer id) throws Exception {
        IndustrialAssert obj = industrialAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, IndustrialAssertDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IndustrialAssertDTO> create(@Valid @RequestBody IndustrialAssertDTO industrialAssert) throws Exception {
        IndustrialAssert obj =industrialAssertSerivce.create(mapper.map(industrialAssert, IndustrialAssert.class));
        return new ResponseEntity<>(mapper.map(obj, IndustrialAssertDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IndustrialAssertDTO> update(@Valid @RequestBody IndustrialAssertDTO dto) throws Exception{
        IndustrialAssert obj = industrialAssertSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        IndustrialAssert mine = industrialAssertSerivce.update(mapper.map(dto, IndustrialAssert.class));
        return new ResponseEntity<>(mapper.map(mine, IndustrialAssertDTO.class), HttpStatus.OK);
    }


    @PatchMapping("/{id}/disable")
    public ResponseEntity<IndustrialAssertDTO> disabled(@PathVariable Integer id) throws Exception{
        IndustrialAssert obj = industrialAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        IndustrialAssert industrialAssert = industrialAssertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(industrialAssert, IndustrialAssertDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<IndustrialAssertDTO> enabled(@PathVariable Integer id) throws Exception{
        IndustrialAssert obj = industrialAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        IndustrialAssert industrialAssert = industrialAssertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(industrialAssert, IndustrialAssertDTO.class), HttpStatus.OK);
    }

}


