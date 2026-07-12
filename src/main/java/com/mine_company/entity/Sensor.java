package com.mine_company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_IndustrialAssert", nullable = false, foreignKey = @ForeignKey(name="FK_Sensor_IndustrialAssert"))
    private IndustrialAssert industrialAssert;

    @ManyToOne
    @JoinColumn(name = "id_TypeSensor", nullable = false, foreignKey = @ForeignKey(name="FK_Sensor_TypeSensor"))
    private TypeSensor typeSensor;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String assetCode;

    @Column(length = 30, nullable = false)
    private String serialNumber;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime installedAt;

    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Boolean status;
}
