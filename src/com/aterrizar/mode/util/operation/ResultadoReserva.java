package com.aterrizar.mode.util.operation;

public class ResultadoReserva extends ResultadoOperacion {

    public ResultadoReserva(String error) {
        super(error);
    }

    public ResultadoReserva(Object resultado) {
        super(resultado);
    }

    @Override
    public String getResultado() {
        if (this.estaOK) {
            return "El asiento " + resultado.toString() + " ha sido reservado exitosamente!";
        } else {
            return "Ha ocurrido un error en su reserva: " + mensajeError + ".";
        }
    }
}
