package com.aterrizar.model.util.operation;

public class ResultadoCompra extends ResultadoOperacion {

    public ResultadoCompra(String error) {
        super(error);
    }

    public ResultadoCompra(Object resultado) {
        super(resultado);
    }

    protected String getMensajeOK() {
        return "El asiento " + resultado.toString() + " ha sido comprado exitosamente!";
    }

    protected String getMensajeError() {
        return "Ha ocurrido un error en su compra: " + mensajeError + ".";
    }
}
