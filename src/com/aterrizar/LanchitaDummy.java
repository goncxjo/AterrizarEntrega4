package com.aterrizar;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoLanchitaYaReservadoException;
import com.aterrizar.model.aerolinea.AerolineaLanchita;

import java.util.List;

public class LanchitaDummy implements AerolineaLanchita {
    @Override
    public List<List<String>> asientosDisponibles(String origen, String fechaSalida, String destino, String fechaLlegada) {
        return null;
    }

    @Override
    public void comprar(String codigoAsiento) throws AsientoLanchitaNoDisponibleException {

    }

    @Override
    public void reservar(String codigoAsiento, String dni) throws AsientoLanchitaYaReservadoException {

    }

    @Override
    public boolean estaReservado(String codigoAsiento) {
        return false;
    }
}
