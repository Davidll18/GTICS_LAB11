package edu.pucp.gtics.lab11_gtics_20232.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "juegos")
public class Juegos {

    @Id
    private Integer idjuego;

    @Size(min = 3, max = 45, message = "Debe contener entre 3 y 45 caracteres")
    private String nombre;

    @Size(min = 3, max = 400, message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;

    @DecimalMin(value = "10" , message = "Valor mínimo 10")
    @DecimalMax(value = "500" , message = "Valor máximo 500")
    private double precio;

    private String image;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    @Valid
    private Plataformas plataforma;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    @Valid
    private Distribuidoras distribuidora;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    @Valid
    private Generos genero;


}
