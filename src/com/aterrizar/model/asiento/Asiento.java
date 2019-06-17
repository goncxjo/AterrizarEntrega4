package com.aterrizar.model.asiento;

import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;

public abstract class Asiento {
    protected String codigoAsiento;
    protected double precio;
    protected Ubicacion ubicacion;
    protected Estado estado;
    protected boolean esSuperOferta = false;

    public Asiento() {}

    public Asiento(String codigoAsiento, double precio, Ubicacion ubicacion, Estado estado) {
        this.codigoAsiento = codigoAsiento;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    public void setCodigoAsiento(String codigoAsiento) {
        this.codigoAsiento = codigoAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public boolean esSuperOferta() { return esSuperOferta; }

    public void marcarComoSuperOferta() {
        this.esSuperOferta = true;
    }

    public int getNumeroAsiento() {
        String numeroAsiento = codigoAsiento.split("-")[1];
        return Integer.parseInt(numeroAsiento);
    }
}
