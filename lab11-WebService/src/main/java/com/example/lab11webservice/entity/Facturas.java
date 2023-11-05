package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Facturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura", nullable = false)
    private Integer id;

    @Column(name = "fechaEnvio", nullable = false, length = 50)
    private String fechaEnvio;

    @Column(name = "tarjeta", nullable = false, length = 50)
    private String tarjeta;

    @Column(name = "codigoVerificacion", nullable = false, length = 5)
    private String codigoVerificacion;

    @Column(name = "direccion", nullable = false, length = 500)
    private String direccion;

    @Column(name = "monto", nullable = false)
    private Float monto;

    @ManyToOne
    @JoinColumn(name = "idjuegosxusuario")
    private Juegosxusuario idjuegosxusuario;

}