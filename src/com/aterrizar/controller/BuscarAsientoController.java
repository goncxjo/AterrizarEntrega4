package com.aterrizar.controller;

import com.aterrizar.viewmodel.BuscarAsientoViewModel;

public class BuscarAsientoController {

    public BuscarAsientoViewModel modelo;

    public BuscarAsientoController() {
        this.modelo = new BuscarAsientoViewModel();
    }

    public BuscarAsientoViewModel getModelo() {
        return modelo;
    }

    public void setModelo(BuscarAsientoViewModel modelo) {
        this.modelo = modelo;
    }
}
