package com.mine_company.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeAssertDTO {

    private Integer id;
    private String name;
    private String description;

}
