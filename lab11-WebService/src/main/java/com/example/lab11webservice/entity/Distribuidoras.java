package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddistribuidora", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "fundacion", nullable = false)
    private Integer fundacion;

    @Column(name = "web", length = 200)
    private String web;

    @ManyToOne
    @JoinColumn(name = "idsede")
    private Paises idsede;

}