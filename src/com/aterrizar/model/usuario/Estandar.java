package com.aterrizar.model.usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;

public class Estandar extends Usuario {

    public Estandar() {
    }

    public Estandar(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public Estandar(Usuario usuario) {
        super(usuario);
    }

    public boolean puedeSerUsuarioVIP() {
        boolean esVIP = false;
        if(!this.historialCompras.isEmpty()) {
            esVIP = this.historialCompras.stream().mapToDouble(va -> va.getAsiento().getPrecio() + this.getRecargo()).sum() > 100000;
        }
        return esVIP;
    }

    @Override
    public VIP actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {

        if(!nuevoTipoUsuario.getClass().equals(VIP.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        } else if(!puedeSerUsuarioVIP()) {
            throw new TipoUsuarioNoDisponibleException("No usuario no cumple con las condiciones para ser VIP");
        }

        return new VIP(this);
    }
}
