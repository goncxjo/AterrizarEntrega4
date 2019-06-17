package com.aterrizar.controller;

import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.view.BienvenidaView;

public class BienvenidaController extends Controller {
    private Usuario modelo;

    public BienvenidaController(Usuario modelo) {
        this.modelo = modelo;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }

    @Override
    public void iniciar() {
        this.vista = new BienvenidaView(this);
        mostrarVista();
    }

    public void mostrarCompras() {
        OperacionController operacionController = new ComprasController(this.modelo);
        operacionController.iniciar();
    }

    public void mostrarReservas() {
        OperacionController operacionController = new ReservasController(this.modelo);
        operacionController.iniciar();
    }

    public void mostrarBusquedaAsientos() {
        AsientosController asientosController = new AsientosController(this.modelo);
        asientosController.iniciar();
    }
}
