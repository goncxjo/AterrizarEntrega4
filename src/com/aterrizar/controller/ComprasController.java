package com.aterrizar.controller;

import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.viewmodel.OperacionViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class ComprasController extends OperacionController {
    public ComprasController(Usuario modelo) {
        super(modelo);
    }

    public List<OperacionViewModel> getOperacionesUsuario() {
        return modelo.getHistorialCompras().stream().map(x -> new OperacionViewModel(
                x.getVuelo().getFecha()
                , x.getNombreAerolinea()
                , x.getAsiento().getCodigoAsiento()
                , x.getAsiento().getNumeroAsiento()
                , x.getAsiento().getPrecio()
        )).collect(Collectors.toList());
    }
}
