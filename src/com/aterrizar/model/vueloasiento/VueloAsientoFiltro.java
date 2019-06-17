package com.aterrizar.model.vueloasiento;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.model.asiento.Asiento;

public class VueloAsientoFiltro {
    private Destino origen;
    private Destino destino;
    private String fecha;
    private Asiento tipoAsiento;
    private Ubicacion ubicacion;

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Asiento getTipoAsiento() { return tipoAsiento; }

    public void setTipoAsiento(Asiento tipoAsiento) { this.tipoAsiento = tipoAsiento; }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
