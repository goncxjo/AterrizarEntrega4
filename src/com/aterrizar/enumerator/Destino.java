package com.aterrizar.enumerator;

public enum Destino {
    BUE("Buenos Aires", "Argentina")
    , EZE("Ezeiza", "Argentina")
    , LA("Los Angeles", "EE.UU.")
    , BCN("Barcelona", "España")
    , MAD("Madrid", "España")
    , TOK("Tokio", "Japón")
    , BSB("Brasilia", "Brasil")
    , MIA("Miami", "EE.UU.")
    , MEX("Ciudad de México", "México");

    String nombre;
    String pais;

    Destino(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getCodigoYNombre() {
        return this.name() + " - " + nombre + " (" + pais + ")";
    }

    public static Destino[] getOrigenes() {
        return new Destino[] { Destino.BUE, Destino.EZE };
    }

    public static Destino[] getDestinos() {
        return new Destino[] { Destino.LA, Destino.BCN, Destino.MAD, Destino.TOK, Destino.BSB, Destino.MIA, Destino.MEX };
    }
}
