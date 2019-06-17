package com.aterrizar.viewmodel;

import com.aterrizar.model.usuario.Usuario;

import java.util.Date;

public class OperacionViewModel {
    private Date fechaSalida;
    private String nombreAerolinea;
    private String codigoVuelo;
    private int nroAsiento;
    private double precioAsiento;

    public OperacionViewModel(Date fechaSalida, String nombreAerolinea, String codigoVuelo, int nroAsiento, double precioAsiento) {
        this.fechaSalida = fechaSalida;
        this.nombreAerolinea = nombreAerolinea;
        this.codigoVuelo = codigoVuelo;
        this.nroAsiento = nroAsiento;
        this.precioAsiento = precioAsiento;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public int getNroAsiento() {
        return nroAsiento;
    }

    public void setNroAsiento(int nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    public double getPrecioAsiento() {
        return precioAsiento;
    }

    public void setPrecioAsiento(double precioAsiento) {
        this.precioAsiento = precioAsiento;
    }
}
