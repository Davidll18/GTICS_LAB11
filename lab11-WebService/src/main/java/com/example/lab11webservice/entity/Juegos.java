package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "juegos")
public class Juegos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 448)
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "image", length = 400)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgenero")
    private Generos idgenero;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    private Plataformas idplataforma;

    @ManyToOne
    @JoinColumn(name = "ideditora")
    private Editoras ideditora;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    private Distribuidoras iddistribuidora;

}