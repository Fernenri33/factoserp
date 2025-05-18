package com.app.factoserp.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "venderor", referencedColumnName = "id")
    private Usuarios vendedor;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDate fechaModificacion;

    @Column(name = "fechaEntrega")
    private LocalDate fechaEntrega;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaDetalle> ventaDetalle = new ArrayList<>();

    public Venta() {
    }

    public Venta(int id, Usuarios vendedor, LocalDate fechaCreacion, LocalDate fechaModificacion,
            LocalDate fechaEntrega, double total, List<VentaDetalle> envioDetalle) {
        this.id = id;
        this.vendedor = vendedor;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
        this.ventaDetalle = envioDetalle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Usuarios vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaEntrega() {
        return this.fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<VentaDetalle> getEnvioDetalle() {
        return this.ventaDetalle;
    }

    public void setEnvioDetalle(List<VentaDetalle> envioDetalle) {
        this.ventaDetalle = envioDetalle;
    }
}
