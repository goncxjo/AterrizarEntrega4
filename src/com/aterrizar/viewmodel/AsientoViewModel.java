package com.aterrizar.viewmodel;

public class AsientoViewModel {
    private String nombreAerolinea;
    private String codigoVuelo;
    private int nroAsiento;
    private double precioAsiento;
    private String ubicacion;
    private String clase;

    public AsientoViewModel(String nombreAerolinea, String codigoVuelo, int nroAsiento, double precioAsiento, String ubicacion, String clase) {
        this.nombreAerolinea = nombreAerolinea;
        this.codigoVuelo = codigoVuelo;
        this.nroAsiento = nroAsiento;
        this.precioAsiento = precioAsiento;
        this.ubicacion = ubicacion;
        this.clase = clase;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
