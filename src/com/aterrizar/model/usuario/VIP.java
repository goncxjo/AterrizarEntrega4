package com.aterrizar.model.usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.model.asiento.Asiento;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;

public class VIP extends Usuario {

    public VIP() {
    }

    public VIP(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public VIP(Usuario usuario) {
        super(usuario);
    }

    public boolean puedeVerSuperOferta(Asiento asiento) {
        boolean puedeVerSuperOferta = false;

        if((asiento instanceof PrimeraClase && asiento.getPrecio() + this.getRecargo() < 8000) || (asiento instanceof Ejecutivo && asiento.getPrecio() + this.getRecargo() < 4000)) {
            puedeVerSuperOferta = true;
        }

        return puedeVerSuperOferta;
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
}
