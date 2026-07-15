package com.mine_company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IndustrialAssetDTO {

    private Integer id;
    private Integer idTypeAsset;
    private Integer idArea;
    private String name;
    private String assetCode;
    private String serialNumber;
    private String description;
    private LocalDateTime installedAt;
    private LocalDateTime modifiedAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateTimeBegin;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateTimeEnd;
    private Boolean status;
}
