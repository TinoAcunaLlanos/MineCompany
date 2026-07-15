package com.mine_company.dto;

import com.mine_company.entity.Measurement;
import com.mine_company.enums.AlertStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertDTO {

    private Integer id;
    private Integer idMeasurement;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private LocalDateTime dateTimeCreatedBeginAt;
    private LocalDateTime dateTimeCreatedEndAt;
    private LocalDateTime dateTimeResolvedBeginAt;
    private LocalDateTime dateTimeResolvedEndAt;
    private AlertStatus status;
}
