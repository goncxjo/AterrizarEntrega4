package com.aterrizar.util.operation;

import com.aterrizar.model.vueloasiento.VueloAsiento;

public class ResultadoCompra extends ResultadoOperacion {

    public ResultadoCompra(String error) {
        super(error);
    }

    public ResultadoCompra(VueloAsiento vueloAsiento) {
        super(vueloAsiento);
    }

    protected String getMensajeOK() {
        return "El asiento " + vueloAsiento.getAsiento().getCodigoAsiento() + " ha sido comprado exitosamente!";
    }

    protected String getMensajeError() {
        return "Ha ocurrido un error en su compra: " + mensajeError + ".";
    }
}
