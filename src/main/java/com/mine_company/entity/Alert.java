package com.mine_company.entity;

import com.mine_company.enums.AlertStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "measurement_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_alert_measurement")
    )
    private Measurement measurement;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;
}
