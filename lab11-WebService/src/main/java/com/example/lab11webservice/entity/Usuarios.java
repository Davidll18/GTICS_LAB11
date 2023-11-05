package com.example.lab11webservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false)
    private Integer id;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "nombres", length = 45)
    private String nombres;

    @Column(name = "correo", length = 45)
    private String correo;

    @Column(name = "password", length = 65)
    private String password;

    @Column(name = "autorizacion", length = 20)
    private String autorizacion;

    @Column(name = "enabled")
    private Boolean enabled;

}