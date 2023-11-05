package com.example.lab11webservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paises")
public class Paises {
    @Id
    @Column(name = "idpais", nullable = false)
    private Integer id;

    @Column(name = "iso", length = 2)
    private String iso;

    @Column(name = "nombre", length = 80)
    private String nombre;

}