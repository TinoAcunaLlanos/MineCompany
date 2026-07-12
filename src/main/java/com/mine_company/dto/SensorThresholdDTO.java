package com.mine_company.dto;

import com.mine_company.entity.Sensor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorThresholdDTO {

    private Integer id;
    private Integer idSensor;
    private Integer minValue;
    private Integer maxValue;
    private Boolean status;
}
