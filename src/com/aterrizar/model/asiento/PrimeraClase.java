package com.aterrizar.model.asiento;

import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;

public class PrimeraClase extends Asiento {

    public PrimeraClase() {
    }

    public PrimeraClase(String codigoAsiento, double precio, Ubicacion ubicacion, Estado estado) {
        super(codigoAsiento, precio, ubicacion, estado);
    }

    @Override
    public String toString() {
        return "Primera Clase";
    }
}


