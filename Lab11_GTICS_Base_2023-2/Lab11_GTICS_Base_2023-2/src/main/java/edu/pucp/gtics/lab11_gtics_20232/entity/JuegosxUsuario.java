package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "juegosxusuario")
public class JuegosxUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuegosxusuario", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjuego", nullable = false)
    private Juegos idjuego;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idusuario", nullable = false)
    private User idusuario;

    @Column(name = "cantidad")
    private Integer cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Juegos getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(Juegos idjuego) {
        this.idjuego = idjuego;
    }

    public User getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(User idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}