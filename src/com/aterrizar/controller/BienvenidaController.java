package com.aterrizar.controller;

import com.aterrizar.viewmodel.BienvenidaViewModel;

public class BienvenidaController {
    private BienvenidaViewModel modelo;

    public BienvenidaController() {
        this.modelo = new BienvenidaViewModel();
    }

    public BienvenidaViewModel getModelo() {
        return modelo;
    }

    public void setModelo(BienvenidaViewModel modelo) {
        this.modelo = modelo;
    }
}
