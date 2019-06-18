package com.aterrizar.model.asiento;

import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;

public class Turista extends Asiento {
    public Turista() {
    }

    public Turista(String codigoAsiento, double precio, Ubicacion ubicacion, Estado estado) {
        super(codigoAsiento, precio, ubicacion, estado);
    }

    @Override
    public String toString() {
        return "Turismo";
    }
}
