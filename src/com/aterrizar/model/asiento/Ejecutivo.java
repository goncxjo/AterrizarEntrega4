package com.aterrizar.model.asiento;

import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;

public class Ejecutivo extends Asiento {

    public Ejecutivo() {
    }

    public Ejecutivo(String codigoAsiento, double precio, Ubicacion ubicacion, Estado estado) {
        super(codigoAsiento, precio, ubicacion, estado);
    }

    @Override
    public String getNombreTipoAsiento() {
        return "Ejecutivo";
    }
}
