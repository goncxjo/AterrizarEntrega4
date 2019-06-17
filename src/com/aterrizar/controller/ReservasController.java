package com.aterrizar.controller;

import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.viewmodel.OperacionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReservasController extends OperacionController {
    public ReservasController(Usuario modelo) {
        super(modelo);
    }

    public List<OperacionViewModel> getOperacionesUsuario() {
        return new ArrayList<>();
//        return modelo.getReservas().stream().map(x -> new OperacionViewModel(
//                x.getVuelo().getFecha()
//                , x.getNombreAerolinea()
//                , x.getAsiento().getCodigoAsiento()
//                , x.getAsiento().getNumeroAsiento()
//                , x.getAsiento().getPrecio()
//        )).collect(Collectors.toList());
    }
}
