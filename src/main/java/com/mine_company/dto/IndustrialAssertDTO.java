package com.mine_company.dto;

import com.mine_company.entity.Area;
import com.mine_company.entity.TypeAssert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IndustrialAssertDTO {

    private Integer id;
    private Integer idTypeAssert;
    private Integer idArea;
    private String name;
    private String assetCode;
    private String serialNumber;
    private String description;
    private LocalDateTime installedAt;
    private LocalDateTime dateTimeBegin;
    private LocalDateTime dateTimeEnd;
    private Boolean status;
}
