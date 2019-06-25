package com.aterrizar.model.usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;

public class NoRegistrado extends Usuario {

    public NoRegistrado() {
    }

    public NoRegistrado(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public NoRegistrado(Usuario usuario) {
        super(usuario);
    }

    @Override
    public float getRecargo() { return 20; }

    @Override
    public Estandar actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {
        if(!nuevoTipoUsuario.getClass().equals(Estandar.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        }
        return new Estandar(this);
    }

    @Override
    public boolean puedeSerUsuarioVIP() {
        return false;
    }
}
