package com.aterrizar.util.operation;

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

    public String getResultado() {
        if (this.estaOK) {
            return getMensajeOK();
        } else {
            return getMensajeError();
        }
    }

    protected abstract String getMensajeOK();

    protected abstract String getMensajeError();

    protected String getTituloOperacion() {
        return "Resultado de la operación";
    }

    public void mostrarResultadoOperacion() {
        JOptionPane.showOptionDialog(
                null
                , getResultado()
                , getTituloOperacion()
                , tipoOpciones
                , tipoMensaje
                , null
                , opciones
                , opciones[0]);
    }

    public static int preguntarPorResultadoOperacion(String pregunta) {
        return JOptionPane.showConfirmDialog(
                null
                , pregunta
                , "Pregunta"
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.INFORMATION_MESSAGE
        );
    }
}
