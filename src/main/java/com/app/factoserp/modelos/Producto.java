package com.app.factoserp.modelos;

import com.app.factoserp.modelos.Enum.Unidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false) // Nombre del producto, no puede ser nulo
    private String nombre; // Nombre del producto

    @Column(name = "descripcion")
    private String descripcion; // Descripción del producto

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (Kilo, Libra, etc.)
    @Column(name = "unidad", nullable = false)
    private Unidad unidadMedida;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad; // Cantidad del producto en inventario

    @Column(name ="precioUnitario", nullable = false)
    private double precioUnitario; // Precio unitario del producto

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, Unidad unidadMedida, int cantidad,
            double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Unidad getUnidadMedida() {
        return this.unidadMedida;
    }

    public void setUnidadMedida(Unidad unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
}
