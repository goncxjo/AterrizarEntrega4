package com.aterrizar.model.asiento;

import com.aterrizar.enumerator.Ubicacion;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AsientoDTO {
    private String codigoVuelo;
    private Integer numeroAsiento;
    private Date fechaSalida;
    private Time horaSalida;
    private double precio;
    private Asiento claseAsiento;
    private Ubicacion ubicacion;

    public AsientoDTO(String codigoVuelo, Integer numeroAsiento, Date fechaSalida, Time horaSalida, double precio, Asiento claseAsiento, Ubicacion ubicacion) {

        this.codigoVuelo = codigoVuelo;
        this.numeroAsiento = numeroAsiento;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.precio = precio;
        this.claseAsiento = claseAsiento;
        this.ubicacion = ubicacion;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public Integer getNumeroAsiento() {
        return numeroAsiento;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public double getPrecio() {
        return precio;
    }

    public Asiento getClaseAsiento() {
        return claseAsiento;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
