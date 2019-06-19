package com.aterrizar.controller;

import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.view.AsientosView;
import com.aterrizar.viewmodel.BusquedaAsientoViewModel;

public class AsientosController extends Controller {

    public BusquedaAsientoViewModel modelo;
    public

    public AsientosController(BusquedaAsientoViewModel modelo, Usuario usuario) {
        this.modelo = modelo;
    }

    public void iniciar() {
        this.vista = new AsientosView(this, this.modelo);
        mostrarVista();
    }
}
