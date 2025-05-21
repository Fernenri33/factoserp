package com.app.factoserp.modelos.Enum;

public enum Rol {
    ADMIN("ADMIN"),          // Acceso a todo y a generar usuarios
    VENDEDOR("VENDEDOR"),    // Acceso a ventas, pero no a compras
    COMPRADOR("COMPRADOR");  // Acceso a compras, pero no a ventas

    private final String displayName;

    Rol(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}

