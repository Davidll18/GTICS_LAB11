package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "juegosxusuario")
public class Juegosxusuario {
    @Id
    @Column(name = "idjuegosxusuario", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idjuego", nullable = false)
    private Juegos idjuego;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuarios idusuario;

    @Column(name = "cantidad")
    private Integer cantidad;

}