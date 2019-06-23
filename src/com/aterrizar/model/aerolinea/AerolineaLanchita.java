package com.aterrizar.model.aerolinea;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoLanchitaYaReservadoException;

import java.util.List;

public interface AerolineaLanchita {
    List<List<String>> asientosDisponibles(String origen, String fechaSalida, String destino, String fechaLlegada);
    void comprar(String codigoAsiento) throws AsientoLanchitaNoDisponibleException;
    void reservar(String codigoAsiento, String dni) throws AsientoLanchitaYaReservadoException, AsientoLanchitaNoDisponibleException;
    boolean estaReservado(String codigoAsiento);
}
