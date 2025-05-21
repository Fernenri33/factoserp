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
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "comprador", referencedColumnName = "id")
    private Usuarios comprador;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDate fechaModificacion;

    @Column(name = "fechaEntrega")
    private LocalDate fechaEntrega;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<CompraDetalle> compraDetalle = new ArrayList<>();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<HistorialCompra> historialCompra = new ArrayList<>();


    public Compra() {
    }

    public Compra(int id, Usuarios comprador, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEntrega, double total, List<CompraDetalle> compraDetalle) {
        this.id = id;
        this.comprador = comprador;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
        this.compraDetalle = compraDetalle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getComprador() {
        return this.comprador;
    }

    public void setComprador(Usuarios comprador) {
        this.comprador = comprador;
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

    public List<CompraDetalle> getCompraDetalle() {
        return this.compraDetalle;
    }

    public void setCompraDetalle(List<CompraDetalle> compraDetalle) {
        this.compraDetalle = compraDetalle;
    }
}
