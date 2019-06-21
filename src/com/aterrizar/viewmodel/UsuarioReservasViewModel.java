package com.aterrizar.viewmodel;

import com.aterrizar.model.usuario.Usuario;

import java.util.stream.Collectors;

public class UsuarioReservasViewModel extends UsuarioOperacionViewModel {
    public UsuarioReservasViewModel(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void buscarOperacionesUsuario() {
        vueloAsientos = usuario.getReservas()
                .stream()
                .map(x -> x.getVueloAsiento())
                .collect(Collectors.toList());
    }

    @Override
    public String getTipoOperacion() {
        return "Reservas";
    }
}
