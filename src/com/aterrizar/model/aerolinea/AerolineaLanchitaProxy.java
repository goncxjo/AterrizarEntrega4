package com.aterrizar.model.aerolinea;

import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoLanchitaYaReservadoException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.model.asiento.*;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;

import java.util.*;

public class AerolineaLanchitaProxy extends Aerolinea {
    private AerolineaLanchita aerolineaLanchita;

    public AerolineaLanchitaProxy(AerolineaLanchita aerolineaLanchita) {
        super("LCH", "Lanchita");
        this.aerolineaLanchita = aerolineaLanchita;
    }

    @Override
    protected double getTiempoVuelo(Object asiento) {
        List<String> asientoGenerado = (List<String>) asiento;

        return Double.parseDouble(asientoGenerado.get(5));
    }

    @Override
    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
        try {
            super.comprar(vueloAsiento, usuario);
            this.aerolineaLanchita.comprar(vueloAsiento.getAsiento().getCodigoAsiento());
        } catch (AsientoLanchitaNoDisponibleException e) {
            throw new AsientoNoDisponibleException(this.nombre + ": " + e.getMessage());
        }
    }

    @Override
    public void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoYaReservadoException {    	
        try {
            this.aerolineaLanchita.reservar(vueloAsiento.getAsiento().getCodigoAsiento(), Integer.toString(usuario.getDNI()));
        } catch(AsientoLanchitaYaReservadoException e){
            throw new AsientoYaReservadoException(this.nombre + ": " + "El asiento ya se encuentra reservado");
        }
    }
    
    protected double getPopularidad(Object asiento) {
        List<String> asientoGenerado = (List<String>) asiento;

        return Double.parseDouble(asientoGenerado.get(6));
    }

    @Override
    public boolean estaReservado(String codigoAsiento) {
        return this.aerolineaLanchita.estaReservado(codigoAsiento);
    }

    @Override
    protected List getAsientosDisponiblesPorAerolinea(VueloAsientoFiltro filtro) {
        return this.aerolineaLanchita.asientosDisponibles(
                filtro.getOrigen().name()
                , filtro.getFecha()
                , filtro.getDestino().name()
                , null
        );
    }

    protected Asiento generarAsiento(Object asiento, Usuario usuario) {
        List<String> asientoGenerado = (List<String>) asiento;

        switch (asientoGenerado.get(2)) {
            case "E":
                return new Ejecutivo(
                        asientoGenerado.get(0)
                        , Double.parseDouble(asientoGenerado.get(1)) + usuario.getRecargo()
                        , getUbicacionPorCodigo(asientoGenerado.get(3))
                        , getEstadoPorCodigo(asientoGenerado.get(4))
                );
            case "P":
                return new PrimeraClase(
                        asientoGenerado.get(0)
                        , Double.parseDouble(asientoGenerado.get(1)) + usuario.getRecargo()
                        , getUbicacionPorCodigo(asientoGenerado.get(3))
                        , getEstadoPorCodigo(asientoGenerado.get(4))
                );
            case "T":
                return new Turista(
                        asientoGenerado.get(0)
                        , Double.parseDouble(asientoGenerado.get(1)) + usuario.getRecargo()
                        , getUbicacionPorCodigo(asientoGenerado.get(3))
                        , getEstadoPorCodigo(asientoGenerado.get(4))
                );
            default:
                return null;
        }
    }

    private Estado getEstadoPorCodigo(String inicial) {
        switch (inicial) {
            case "D":
                return Estado.Disponible;
            case "R":
                return Estado.Reservado;
            default:
                return Estado.valueOf(inicial);
        }
    }

    private Ubicacion getUbicacionPorCodigo(String inicial) {
        switch (inicial) {
            case "C":
                return Ubicacion.Centro;
            case "P":
                return Ubicacion.Pasillo;
            case "V":
                return Ubicacion.Ventanilla;
            default:
                return Ubicacion.valueOf(inicial);
        }
    }
}
