package com.aterrizar.model.aerolinea;

import com.aterrizar.exception.AsientoOceanicNoDisponibleException;
import com.aterrizar.model.asiento.AsientoDTO;

import java.util.List;

public interface AerolineaOceanic {
    List<AsientoDTO> asientosDisponiblesParaOrigen(String codigoOrigenOceanic, String fechaSalida);
    List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic, String fechaSalida, String codigoDestinoOceanic);
    boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento);
    boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento) throws AsientoOceanicNoDisponibleException;
    boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento);
}
