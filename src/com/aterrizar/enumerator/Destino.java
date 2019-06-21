package com.aterrizar.enumerator;

public enum Destino {
    BUE("Buenos Aires")
    , EZE("Ezeiza")
    , LA("Los Angeles")
    , BAR("Barcelona")
    , MAD("Madrid")
    , TOK("Tokio")
    , BRA("Brasilia")
    , MIA("Miami");

    String nombre;

    Destino(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoYNombre() {
        return this.name() + " - " + nombre;
    }
}
