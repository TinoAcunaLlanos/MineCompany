package com.mine_company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SensorThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idSensor")
    private Sensor sensor;

    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private Double minValue;

    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private Double maxValue;

    @Column(nullable = false)
    private Boolean status;
}
