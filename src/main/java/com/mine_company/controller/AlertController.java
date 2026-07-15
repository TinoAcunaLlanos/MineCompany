
package com.mine_company.controller;

import com.mine_company.dto.AlertDTO;
import com.mine_company.entity.Alert;
import com.mine_company.exception.ModelNotFoundException;
import com.mine_company.service.IAlertService;
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
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private IAlertService alertSerivce;

    @Autowired
    @Qualifier("alertMapper")
    private ModelMapper mapper;
    private Alert obj;

    @GetMapping
    public ResponseEntity<Page<AlertDTO>> readAll(AlertDTO filter, Pageable pageable) throws Exception{

        Page<Alert> page = alertSerivce.search(
                filter,
                pageable
        );

        Page<AlertDTO> dtoPage = page.map(m ->
                mapper.map(m, AlertDTO.class)
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Alert obj = alertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, AlertDTO.class), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<AlertDTO> create(@Valid @RequestBody AlertDTO alert) throws Exception {
        alert.setMeasuredAt(LocalDateTime.now());
        Alert obj = alertSerivce.create(mapper.map(alert, Alert.class));
        return new ResponseEntity<>(mapper.map(obj, AlertDTO.class), HttpStatus.CREATED);
    }*/

    /*@PutMapping
    public ResponseEntity<AlertDTO> update(@Valid @RequestBody AlertDTO dto) throws Exception {
        Alert obj = alertSerivce.readById(dto.getId());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND" + dto.getId());
        }
        Alert mine = alertSerivce.update(mapper.map(dto, Alert.class));
        return new ResponseEntity<>(mapper.map(mine, AlertDTO.class), HttpStatus.OK);
    }*/


    /*@PatchMapping("/{id}/disable")
    public ResponseEntity<AlertDTO> disabled(@PathVariable Integer id) throws Exception{
        Alert obj = alertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        Alert alert = alertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(alert, AlertDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<AlertDTO> enabled(@PathVariable Integer id) throws Exception{
        Alert obj = alertSerivce.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        Alert alert = alertSerivce.update(obj);
        return new ResponseEntity<>(mapper.map(alert, AlertDTO.class), HttpStatus.OK);
    }*/

}


