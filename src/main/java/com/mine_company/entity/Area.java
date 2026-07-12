package com.mine_company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_plant", nullable = false, foreignKey = @ForeignKey(name="FK_area_plant"))
    private Plant plant;

    @Column(length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean status;
}
