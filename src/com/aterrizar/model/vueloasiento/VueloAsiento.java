package com.aterrizar.model.vueloasiento;

import com.aterrizar.model.asiento.Asiento;
import com.aterrizar.model.Vuelo;

public class VueloAsiento {
    private String nombreAerolinea;
    private String codigoAerolinea;
    private Vuelo vuelo;
    private Asiento asiento;

    public VueloAsiento(String nombreAerolinea, String codigoAerolinea, Vuelo vuelo, Asiento asiento) {
        this.nombreAerolinea = nombreAerolinea;
        this.codigoAerolinea = codigoAerolinea;
        this.vuelo = vuelo;
        this.asiento = asiento;
    }

    public VueloAsiento() {
        this.vuelo = new Vuelo();
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

}