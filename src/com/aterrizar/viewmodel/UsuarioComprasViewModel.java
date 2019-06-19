package com.aterrizar.viewmodel;

import com.aterrizar.model.usuario.Usuario;

public class UsuarioComprasViewModel extends UsuarioOperacionViewModel {
    public UsuarioComprasViewModel(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void buscarOperacionesUsuario() {
        vueloAsientos = usuario.getHistorialCompras();
    }
}
