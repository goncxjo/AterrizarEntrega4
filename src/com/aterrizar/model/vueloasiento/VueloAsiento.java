package com.aterrizar.model.vueloasiento;

import com.aterrizar.model.Vuelo;
import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.asiento.Asiento;

public class VueloAsiento {
    private Aerolinea aerolinea;
    private Vuelo vuelo;
    private Asiento asiento;

    public VueloAsiento(Aerolinea aerolinea, Vuelo vuelo, Asiento asiento) {
        this.aerolinea = aerolinea;
        this.vuelo = vuelo;
        this.asiento = asiento;
    }

    public VueloAsiento() {
        this.vuelo = new Vuelo();
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
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
