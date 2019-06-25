package com.aterrizar.model.usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;

public class VIP extends Usuario {

    public VIP() {
    }

    public VIP(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public VIP(Usuario usuario) {
        super(usuario);
    }

    @Override
    public Estandar actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {
        Estandar usuario = new Estandar(this);

        if(!nuevoTipoUsuario.getClass().equals(Estandar.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        } else if(usuario.puedeSerUsuarioVIP()) {
            throw new TipoUsuarioNoDisponibleException("No usuario no puede ser degredado a Estandar");
        }

        return usuario;
    }

    @Override
    public boolean puedeSerUsuarioVIP() {
        return false;
    }

    @Override
    public boolean esVIP() { return true; }
}
