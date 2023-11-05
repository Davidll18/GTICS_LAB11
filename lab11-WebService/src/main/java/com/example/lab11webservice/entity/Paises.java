package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paises")
public class Paises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpais", nullable = false)
    private Integer id;

    @Column(name = "iso", length = 2)
    private String iso;

    @Column(name = "nombre", length = 80)
    private String nombre;

}