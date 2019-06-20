package com.aterrizar.model.util.operation;

import com.aterrizar.model.vueloasiento.VueloAsiento;

import javax.swing.*;

public abstract class ResultadoOperacion {

    protected boolean estaOK;
    protected String mensajeError;
    protected VueloAsiento vueloAsiento;
    protected int tipoOpciones;
    protected int tipoMensaje;
    protected Object[] opciones;

    public ResultadoOperacion(String error) {
        this.estaOK = false;
        this.mensajeError = error;
        this.tipoOpciones = JOptionPane.YES_OPTION;
        this.tipoMensaje = JOptionPane.ERROR_MESSAGE;
        this.opciones = new Object[]{"Aceptar"};
    }

    public ResultadoOperacion(VueloAsiento vueloAsiento) {
        this.estaOK = true;
        this.vueloAsiento = vueloAsiento;
        this.tipoOpciones = JOptionPane.YES_OPTION;
        this.tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
        this.opciones = new Object[]{"Seguir buscando"};
    }

    public boolean OK() {
        return this.estaOK;
    }

    public int getTipoOpciones() {
        return tipoOpciones;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public Object[] getOpciones() {
        return opciones;
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
