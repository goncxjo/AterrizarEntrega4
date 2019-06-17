package com.aterrizar.controller;

import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.view.OperacionView;
import com.aterrizar.viewmodel.OperacionViewModel;

import java.util.List;

public abstract class OperacionController extends Controller {
    protected Usuario modelo;

    public OperacionController(Usuario modelo) {
        this.modelo = modelo;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public abstract List<OperacionViewModel> getOperacionesUsuario();

    @Override
    public void iniciar() {
        this.vista = new OperacionView(this);
        mostrarVista();
    }
}
