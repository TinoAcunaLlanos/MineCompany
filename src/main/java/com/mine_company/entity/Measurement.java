package com.mine_company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_Sensor", nullable = false, foreignKey = @ForeignKey(name = "FK_Measurement_Sensor"))
    private Sensor sensor;

    private LocalDateTime measuredAt;

    private Double value;
}
