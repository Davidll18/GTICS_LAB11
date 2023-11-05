package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "fechaEnvio", nullable = false, length = 50)
    private String fechaEnvio;

    @Size(max = 50)
    @Column(name = "tarjeta", length = 50)
    private String tarjeta;

    @Size(max = 5)
    @Column(name = "codigoVerificacion", length = 5)
    private String codigoVerificacion;

    @Size(max = 500)
    @Column(name = "direccion", length = 500)
    private String direccion;

    @NotNull
    @Column(name = "monto", nullable = false)
    private Float monto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idjuegosxusuario")
    private JuegosxUsuario idjuegosxusuario;

    @Column(name = "celular")
    private Integer celular;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public JuegosxUsuario getIdjuegosxusuario() {
        return idjuegosxusuario;
    }

    public void setIdjuegosxusuario(JuegosxUsuario idjuegosxusuario) {
        this.idjuegosxusuario = idjuegosxusuario;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

}