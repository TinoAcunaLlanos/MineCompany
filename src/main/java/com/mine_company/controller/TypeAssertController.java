
package com.mine_company.controller;

import com.mine_company.dto.TypeAssertDTO;
import com.mine_company.entity.TypeAssert;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.ITypeAssertService;
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
@RequestMapping("/typeAsserts")
public class TypeAssertController {

    @Autowired
    private ITypeAssertService typeAssertSerivce;

    @Autowired
    @Qualifier("typeAssertMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TypeAssertDTO>> readAll(TypeAssertDTO filter, Pageable pageable) throws Exception{
        Page<TypeAssert> page = typeAssertSerivce.search(
                mapper.map(filter, TypeAssert.class),
                pageable
        );

        Page<TypeAssertDTO> dtoPage = page.map(m ->
                mapper.map(m, TypeAssertDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAssertDTO> readById(@PathVariable("id") Integer id) throws Exception {
        TypeAssert obj = typeAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, TypeAssertDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TypeAssertDTO> create(@Valid @RequestBody TypeAssertDTO typeAssert) throws Exception {
        TypeAssert obj =typeAssertSerivce.create(mapper.map(typeAssert, TypeAssert.class));
        return new ResponseEntity<>(mapper.map(obj, TypeAssertDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TypeAssertDTO> update(@Valid @RequestBody TypeAssertDTO dto) throws Exception{
        TypeAssert obj = typeAssertSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        TypeAssert mine = typeAssertSerivce.update(mapper.map(dto, TypeAssert.class));
        return new ResponseEntity<>(mapper.map(mine, TypeAssertDTO.class), HttpStatus.OK);
    }

    /*

    @PatchMapping("/{id}/disable")
    public ResponseEntity<TypeAssertDTO> disabled(@PathVariable Integer id) throws Exception{
        TypeAssert obj = typeAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        TypeAssert typeAssert = typeAssertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeAssert, TypeAssertDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<TypeAssertDTO> enabled(@PathVariable Integer id) throws Exception{
        TypeAssert obj = typeAssertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        TypeAssert typeAssert = typeAssertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeAssert, TypeAssertDTO.class), HttpStatus.OK);
    }

     */

}


