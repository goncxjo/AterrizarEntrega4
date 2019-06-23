package com.aterrizar;

import com.aterrizar.exception.AsientoOceanicNoDisponibleException;
import com.aterrizar.model.aerolinea.AerolineaOceanic;
import com.aterrizar.model.asiento.AsientoDTO;

import java.util.List;

public class OceanicDummy implements AerolineaOceanic {
    @Override
    public List<AsientoDTO> asientosDisponiblesParaOrigen(String codigoOrigenOceanic, String fechaSalida) {
        return null;
    }

    @Override
    public List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic, String fechaSalida, String codigoDestinoOceanic) {
        return null;
    }

    @Override
    public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
        return false;
    }

    @Override
    public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento) throws AsientoOceanicNoDisponibleException {
        return false;
    }

    @Override
    public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        return false;
    }
}
