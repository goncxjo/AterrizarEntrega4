package com.aterrizar.model.vueloasiento;

import com.aterrizar.model.usuario.Usuario;

import java.util.Date;

public class Reserva {
    private Date fechaReserva;
    private Usuario usuario;
    private VueloAsiento vueloAsiento;

    public Reserva(VueloAsiento vueloAsiento, Usuario usuario) {
        this.fechaReserva = new Date();
        this.usuario = usuario;
        this.vueloAsiento = vueloAsiento;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VueloAsiento getVueloAsiento() {
        return vueloAsiento;
    }

    public void setVueloAsiento(VueloAsiento vueloAsiento) {
        this.vueloAsiento = vueloAsiento;
    }
}
