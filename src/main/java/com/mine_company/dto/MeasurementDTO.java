package com.mine_company.dto;

import com.mine_company.entity.Sensor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementDTO {

    private Integer id;
    private Integer idSensor;
    private LocalDateTime measuredAt;
    private LocalDateTime dateTimeBeginAt;
    private LocalDateTime dateTimeEndAt;
    private Double value;
}
