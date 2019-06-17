package com.aterrizar.model.vueloasiento;

import com.aterrizar.model.usuario.Usuario;

import java.util.Date;

public class Reserva {
    private Date fechaReserva;
    private String codigoAsiento;
    private Usuario usuario;

    public Reserva(String codigoAsiento, Usuario usuario) {
        this.fechaReserva = new Date();
        this.usuario = usuario;
        this.codigoAsiento = codigoAsiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }
}
