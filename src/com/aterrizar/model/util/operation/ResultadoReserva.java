package com.aterrizar.model.util.operation;

import com.aterrizar.model.vueloasiento.VueloAsiento;

public class ResultadoReserva extends ResultadoOperacion {

    public ResultadoReserva(String error) {
        super(error);
    }

    public ResultadoReserva(VueloAsiento vueloAsiento) {
        super(vueloAsiento);
    }

    protected String getMensajeOK() {
        return "El asiento " + vueloAsiento.getAsiento().getCodigoAsiento() + " ha sido reservado exitosamente!";
    }

    protected String getMensajeError() {
        return "Ha ocurrido un error en su reserva: " + mensajeError + ".";
    }

}
