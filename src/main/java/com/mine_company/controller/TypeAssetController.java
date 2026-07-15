
package com.mine_company.controller;

import com.mine_company.dto.TypeAssetDTO;
import com.mine_company.entity.TypeAsset;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.ITypeAssetService;
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
@RequestMapping("/typeAssets")
public class TypeAssetController {

    @Autowired
    private ITypeAssetService typeAssetSerivce;

    @Autowired
    @Qualifier("typeAssetMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TypeAssetDTO>> readAll(TypeAssetDTO filter, Pageable pageable) throws Exception{
        Page<TypeAsset> page = typeAssetSerivce.search(
                mapper.map(filter, TypeAsset.class),
                pageable
        );

        Page<TypeAssetDTO> dtoPage = page.map(m ->
                mapper.map(m, TypeAssetDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAssetDTO> readById(@PathVariable("id") Integer id) throws Exception {
        TypeAsset obj = typeAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, TypeAssetDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TypeAssetDTO> create(@Valid @RequestBody TypeAssetDTO typeAsset) throws Exception {
        TypeAsset obj =typeAssetSerivce.create(mapper.map(typeAsset, TypeAsset.class));
        return new ResponseEntity<>(mapper.map(obj, TypeAssetDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TypeAssetDTO> update(@Valid @RequestBody TypeAssetDTO dto) throws Exception{
        TypeAsset obj = typeAssetSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        TypeAsset mine = typeAssetSerivce.update(mapper.map(dto, TypeAsset.class));
        return new ResponseEntity<>(mapper.map(mine, TypeAssetDTO.class), HttpStatus.OK);
    }

    /*

    @PatchMapping("/{id}/disable")
    public ResponseEntity<TypeAssetDTO> disabled(@PathVariable Integer id) throws Exception{
        TypeAsset obj = typeAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        TypeAsset typeAsset = typeAssetSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeAsset, TypeAssetDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<TypeAssetDTO> enabled(@PathVariable Integer id) throws Exception{
        TypeAsset obj = typeAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        TypeAsset typeAsset = typeAssetSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(typeAsset, TypeAssetDTO.class), HttpStatus.OK);
    }

     */

}


