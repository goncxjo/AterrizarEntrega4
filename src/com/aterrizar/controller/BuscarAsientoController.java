package com.aterrizar.controller;

import com.aterrizar.viewmodel.BuscarAsientosViewModel;

public class BuscarAsientoController {

    public BuscarAsientosViewModel modelo;

    public BuscarAsientoController() {
        this.modelo = new BuscarAsientosViewModel();
    }

    public BuscarAsientosViewModel getModelo() {
        return modelo;
    }

    public void setModelo(BuscarAsientosViewModel modelo) {
        this.modelo = modelo;
    }
}
