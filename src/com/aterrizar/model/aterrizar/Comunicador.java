package com.aterrizar.model.aterrizar;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.exception.DestinosIgualesException;
import com.aterrizar.exception.ParametroVacioException;
import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.aerolinea.AerolineaLanchitaProxy;
import com.aterrizar.model.asiento.Asiento;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;

import java.util.List;

public class Comunicador extends Aerolinea {
    AerolineaLanchitaProxy aerolineaLanchitaProxy;

    public Comunicador(AerolineaLanchitaProxy aerolineaLanchitaProxy) {
        this.aerolineaLanchitaProxy = aerolineaLanchitaProxy;
    }

    @Override
    public Aerolinea filtrarAsientos(VueloAsientoFiltro filtro, Usuario usuario) throws ParametroVacioException, DestinosIgualesException {
        return agregarAsientosLanchita(filtro, usuario);
    }

    @Override
    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
	    String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();
        Aerolinea aerolineaProxy = detectarAerolinea(codigoAsiento);
        aerolineaProxy.comprar(vueloAsiento, usuario);
    }

    @Override
    public void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoYaReservadoException, AsientoNoDisponibleException {
	    String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();
        Aerolinea aerolineaProxy = detectarAerolinea(codigoAsiento);
        aerolineaProxy.reservar(vueloAsiento, usuario);
    }

    private Aerolinea detectarAerolinea(String codigoAsiento) throws AsientoNoDisponibleException {
        if(codigoAsiento.contains(this.aerolineaLanchitaProxy.getCodigo())) {
            return this.aerolineaLanchitaProxy;
        } else {
            throw new AsientoNoDisponibleException("El asiento no existe");
        }
    }

    @Override
    protected List getAsientosDisponiblesPorAerolinea(VueloAsientoFiltro filtro) {
        return null;
    }

    @Override
    protected double getTiempoVuelo(Object asiento) {
        return 0;
    }
    
    @Override
    protected double getPopularidad(Object asiento) {
        return 0;
    }

    @Override
    protected Asiento generarAsiento(Object asiento, Usuario usuario) {
        return null;
    }

    private Aerolinea agregarAsientosLanchita(VueloAsientoFiltro filtro, Usuario usuario) throws ParametroVacioException, DestinosIgualesException {
        this.vueloAsientos.addAll(
                aerolineaLanchitaProxy
                        .filtrarAsientos(filtro, usuario)
                        .buscarSuperOfertas(usuario)
                        .getVueloAsientos()
        );

        return this;
    }

    @Override
    public boolean estaReservado(String codigoAsiento) {
        try {
            Aerolinea aerolineaProxy = detectarAerolinea(codigoAsiento);
            return aerolineaProxy.estaReservado(codigoAsiento);
        } catch (AsientoNoDisponibleException e) {
            return true;
        }
    }
}
