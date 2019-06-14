package com.aterrizar.mode.util.operation;

public class ResultadoCompra extends ResultadoOperacion {

    public ResultadoCompra(String error) {
        super(error);
    }

    public ResultadoCompra(Object resultado) {
        super(resultado);
    }

    @Override
    public String getResultado() {
        if (this.estaOK) {
            return "El asiento " + resultado.toString() + " ha sido comprado exitosamente";
        } else {
            return "Ha ocurrido un error en su compra: " + mensajeError + ".";
        }
    }
}
