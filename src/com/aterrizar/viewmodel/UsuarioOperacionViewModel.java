package com.aterrizar.viewmodel;

import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;

import java.util.List;

public abstract class UsuarioOperacionViewModel {
    protected Usuario usuario;
    protected List<VueloAsiento> vueloAsientos;

    public UsuarioOperacionViewModel(Usuario usuario) {
        this.usuario = usuario;
    }

    public abstract void buscarOperacionesUsuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<VueloAsiento> getVueloAsientos() {
        return vueloAsientos;
    }

    public void setVueloAsientos(List<VueloAsiento> vueloAsientos) {
        this.vueloAsientos = vueloAsientos;
    }
}
