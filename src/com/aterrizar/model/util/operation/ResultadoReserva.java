package com.aterrizar.model.util.operation;

public class ResultadoReserva extends ResultadoOperacion {

    public ResultadoReserva(String error) {
        super(error);
    }

    public ResultadoReserva(Object resultado) {
        super(resultado);
    }

    protected String getMensajeOK() {
        return "El asiento " + resultado.toString() + " ha sido reservado exitosamente!";
    }

    protected String getMensajeError() {
        return "Ha ocurrido un error en su reserva: " + mensajeError + ".";
    }

}
