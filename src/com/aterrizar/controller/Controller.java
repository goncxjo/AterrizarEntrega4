package com.aterrizar.controller;

import com.aterrizar.view.BienvenidaView;

import javax.swing.*;

public abstract class Controller {
    protected final String titulo = "Aterrizar.com";
    public JFrame vista;

    public String getTitulo() {
        return titulo;
    }

    public void mostrarVista() {
        this.vista.setVisible(true);
    }

    public void ocultarVista() { this.vista.setVisible(false); }

    public abstract void iniciar();
}
