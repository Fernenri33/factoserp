package com.app.factoserp.modelos.Enum;

public enum Rol {
    
    Admin("Admin"), // Acceso a todo y a generar usuarios
    Vendedor("Vendedor"), // Acceso a ventas, pero no a compras
    Comprador("Comprador"); // Acceso a compras, pero no a ventas

    private final String displayName;

    Rol(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
