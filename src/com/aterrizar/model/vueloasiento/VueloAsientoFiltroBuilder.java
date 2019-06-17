package com.aterrizar.model.vueloasiento;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.model.asiento.Asiento;

public class VueloAsientoFiltroBuilder {
    VueloAsientoFiltro filtro;

    public VueloAsientoFiltroBuilder() {
        this.filtro = new VueloAsientoFiltro();
    }

    public VueloAsientoFiltroBuilder agregarTipoAsiento(Asiento asiento) {
        filtro.setTipoAsiento(asiento);
        return this;
    }

    public VueloAsientoFiltroBuilder agregarOrigen(Destino origen) {
        filtro.setOrigen(origen);
        return this;
    }

    public VueloAsientoFiltroBuilder agregarDestino(Destino  destino) {
        filtro.setDestino(destino);
        return this;
    }

    public VueloAsientoFiltroBuilder agregarFecha(String fecha) {
        filtro.setFecha(fecha);
        return this;
    }

    public VueloAsientoFiltroBuilder agregarUbicacion(Ubicacion ubicacion) {
        filtro.setUbicacion(ubicacion);
        return this;
    }
    
    public VueloAsientoFiltro build() {
        return filtro;
    }
}
