package com.mine_company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorDTO {

    private Integer id;
    private Integer idIndustrialAssert;
    private Integer idTypeSensor;
    private String name;
    private String assetCode;
    private String serialNumber;
    private String description;
    private LocalDateTime installedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateTimeBegin;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateTimeEnd;
    private Boolean status;
}
