package com.mine_company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class IndustrialAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_TypeAsset", nullable = false, foreignKey = @ForeignKey(name="FK_IndustrialAsset_TypeAsset"))
    private TypeAsset typeAsset;

    @ManyToOne
    @JoinColumn(name = "id_Area", nullable = false, foreignKey = @ForeignKey(name="FK_IndustrialAsset_Area"))
    private Area area;

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
