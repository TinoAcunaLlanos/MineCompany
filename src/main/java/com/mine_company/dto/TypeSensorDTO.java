package com.mine_company.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeSensorDTO {

    private Integer id;
    private String name;
    private String description;
    private String measureUnit;
}
