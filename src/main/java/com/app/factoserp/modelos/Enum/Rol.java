package com.app.factoserp.modelos.Enum;

public enum Rol {
    
    Admin("Admin"),
    Vendedor("Vendedor"),
    Comprador("Comprador");

    private final String displayName;

    Rol(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
