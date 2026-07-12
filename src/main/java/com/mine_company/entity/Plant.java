package com.mine_company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_mine", foreignKey = @ForeignKey(name="FK_plant_mine"))
    private Mine mine;

    @Column(length = 50)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private Boolean status;
}
