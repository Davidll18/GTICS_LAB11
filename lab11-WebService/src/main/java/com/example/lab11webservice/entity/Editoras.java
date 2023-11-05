package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "editoras")
public class Editoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideditora", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

}