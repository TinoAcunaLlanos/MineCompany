
package com.mine_company.controller;

import com.mine_company.dto.MineDTO;
import com.mine_company.entity.Mine;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IMineService;
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
@RequestMapping("/mines")
public class MineController {

    @Autowired
    private IMineService mineSerivce;

    @Autowired
    @Qualifier("mineMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<MineDTO>> search(
            MineDTO filter,
            Pageable pageable) throws Exception {
        System.out.println(filter.getStatus());
        Page<Mine> page = mineSerivce.search(
                mapper.map(filter, Mine.class),
                pageable
        );

        Page<MineDTO> dtoPage = page.map(m ->
                mapper.map(m, MineDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MineDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Mine obj = mineSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, MineDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MineDTO> create(@Valid @RequestBody MineDTO mine) throws Exception {
        Mine obj =mineSerivce.create(mapper.map(mine, Mine.class));
        return new ResponseEntity<>(mapper.map(obj, MineDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MineDTO> update(@Valid @RequestBody MineDTO dto) throws Exception{
        Mine obj = mineSerivce.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }

        Mine mine = mineSerivce.update(mapper.map(dto, Mine.class));
        return new ResponseEntity<>(mapper.map(mine, MineDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<MineDTO> disabled(@PathVariable Integer id) throws Exception{
        Mine obj = mineSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(false);

        Mine mine = mineSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(mine, MineDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<MineDTO> enabled(@PathVariable Integer id) throws Exception{
        Mine obj = mineSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        obj.setStatus(true);

        Mine mine = mineSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(mine, MineDTO.class), HttpStatus.OK);
    }


}
