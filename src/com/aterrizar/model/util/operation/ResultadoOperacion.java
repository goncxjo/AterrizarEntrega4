package com.aterrizar.model.util.operation;

public abstract class ResultadoOperacion {

    protected boolean estaOK;
    protected String mensajeError;
    protected Object resultado;

    public ResultadoOperacion(String error) {
        this.estaOK = false;
        this.mensajeError = error;
    }

    public ResultadoOperacion(Object resultado) {
        this.estaOK = true;
        this.resultado = resultado;
    }

    public boolean OK() {
        return this.estaOK;
    }

    public String getResultado() {
        if (this.estaOK) {
            return getMensajeOK();
        } else {
            return getMensajeError();
        }
    }

    protected abstract String getMensajeOK();

    protected abstract String getMensajeError();
}
