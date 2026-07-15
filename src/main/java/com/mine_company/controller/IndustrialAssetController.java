
package com.mine_company.controller;

import com.mine_company.dto.IndustrialAssetDTO;
import com.mine_company.entity.IndustrialAsset;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IIndustrialAssetService;
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
@RequestMapping("/industrialAssets")
public class IndustrialAssetController {

    @Autowired
    private IIndustrialAssetService industrialAssetSerivce;

    @Autowired
    @Qualifier("industrialAssetMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<IndustrialAssetDTO>> readAll(IndustrialAssetDTO filter, Pageable pageable) throws Exception{

        Page<IndustrialAsset> page = industrialAssetSerivce.search(
                filter,
                pageable
        );

        Page<IndustrialAssetDTO> dtoPage = page.map(m ->
                mapper.map(m, IndustrialAssetDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustrialAssetDTO> readById(@PathVariable("id") Integer id) throws Exception {
        IndustrialAsset obj = industrialAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, IndustrialAssetDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IndustrialAssetDTO> create(@Valid @RequestBody IndustrialAssetDTO industrialAsset) throws Exception {
        industrialAsset.setInstalledAt(LocalDateTime.now());
        IndustrialAsset obj =industrialAssetSerivce.create(mapper.map(industrialAsset, IndustrialAsset.class));
        return new ResponseEntity<>(mapper.map(obj, IndustrialAssetDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IndustrialAssetDTO> update(@Valid @RequestBody IndustrialAssetDTO dto) throws Exception{
        IndustrialAsset obj = industrialAssetSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }
        dto.setModifiedAt(LocalDateTime.now());
        IndustrialAsset mine = industrialAssetSerivce.update(mapper.map(dto, IndustrialAsset.class));
        return new ResponseEntity<>(mapper.map(mine, IndustrialAssetDTO.class), HttpStatus.OK);
    }


    @PatchMapping("/{id}/disable")
    public ResponseEntity<IndustrialAssetDTO> disabled(@PathVariable Integer id) throws Exception{
        IndustrialAsset obj = industrialAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        obj.setModifiedAt(LocalDateTime.now());

        obj.setStatus(false);

        IndustrialAsset industrialAsset = industrialAssetSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(industrialAsset, IndustrialAssetDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<IndustrialAssetDTO> enabled(@PathVariable Integer id) throws Exception{
        IndustrialAsset obj = industrialAssetSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        obj.setModifiedAt(LocalDateTime.now());
        obj.setStatus(true);

        IndustrialAsset industrialAsset = industrialAssetSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(industrialAsset, IndustrialAssetDTO.class), HttpStatus.OK);
    }

}


