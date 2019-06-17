package com.aterrizar.enumerator.asiento;

public enum Estado {
    Disponible
    , Reservado
    ;

    public boolean estaDisponible() {
        return this.name().equals(Disponible.name());
    }
}
