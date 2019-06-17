package com.aterrizar.model;

import com.aterrizar.enumerator.Destino;

import java.util.Date;

public class Vuelo {
    private Destino origen;
    private Destino destino;
    private Date fecha;

    public Vuelo() {}

    public Vuelo(Destino origen, Destino destino, Date fecha) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public Destino getOrigen() {
        return origen;
    }

    public void setOrigen(Destino origen) {
        this.origen = origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha= fecha;
    }
}
