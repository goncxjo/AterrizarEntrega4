package com.aterrizar.enumerator.asiento;

public enum Estado {
    Disponible
    , Reservado
    , Comprado
    ;

    public boolean estaDisponible() {
        return this.name().equals(Disponible.name());
    }
}
