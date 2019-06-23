package com.aterrizar.model.aerolinea;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.exception.*;
import com.aterrizar.model.asiento.*;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;


import java.util.ArrayList;
import java.util.List;

public class AerolineaOceanicProxy extends Aerolinea {
    private AerolineaOceanic aerolineaOceanic;

    public AerolineaOceanicProxy(AerolineaOceanic aerolineaOceanic) {
        super("OCE", "Oceanic");
        this.aerolineaOceanic = aerolineaOceanic;
    }

    @Override
    protected void validarParametros(VueloAsientoFiltro filtro) throws ParametroVacioException {
        Destino origen = filtro.getOrigen();
        String fecha = filtro.getFecha();

        if(origen == null) {
            throw new ParametroVacioException("El origen no puede estar vacío");
        }

        if(fecha == null || fecha.equals("")) {
            throw new ParametroVacioException("La fecha no puede estar vacía");
        }
    }

    @Override
    protected List getAsientosDisponiblesPorAerolinea(VueloAsientoFiltro filtro) {
        List asientosDisponibles = new ArrayList();

        if(filtro.getDestino() == null) {
           //Se obtienen asientos  con origen
            asientosDisponibles.addAll(this.aerolineaOceanic.asientosDisponiblesParaOrigen(
                    getCodigoCiudadFormatoOceanic(filtro.getOrigen())
                    , filtro.getFecha()
            ));
        } else {
            //Se obtienen asientos con origen y destino
            asientosDisponibles.addAll(this.aerolineaOceanic.asientosDisponiblesParaOrigenYDestino(
                    getCodigoCiudadFormatoOceanic(filtro.getOrigen())
                    , filtro.getFecha()
                    , getCodigoCiudadFormatoOceanic(filtro.getDestino())
            ));
        }

        return asientosDisponibles;
    }

    private String getCodigoCiudadFormatoOceanic(Destino destino) {
        String codigo = destino.name();

        if(destino.equals(Destino.LA)) {
            codigo = "SLA";
        } else if (codigo.length() < 2) {
            codigo.concat("_");
        }

        return codigo;
    }

    @Override
    protected Asiento generarAsiento(Object asiento, Usuario usuario) {
	//Se formatea el objeto asiento para usar sus metodos y propiedades
        AsientoDTO asientoGenerado = (AsientoDTO) asiento;

        Asiento nuevoAsiento = asientoGenerado.getClaseAsiento();
        nuevoAsiento.setEstado(Estado.Disponible);
        nuevoAsiento.setUbicacion(asientoGenerado.getUbicacion());
        nuevoAsiento.setPrecio(asientoGenerado.getPrecio() + usuario.getRecargo());
        nuevoAsiento.setCodigoAsiento(asientoGenerado.getCodigoVuelo() + "-" + asientoGenerado.getNumeroAsiento());

        return nuevoAsiento;
    }

	@Override
    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
		int dni = usuario.getDNI();
		String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();

		boolean sePudoComprar = this.aerolineaOceanic.comprarSiHayDisponibilidad(
		        getDniFormateado(dni)
                , getCodigoVuelo(codigoAsiento)
                , getNumeroDeAsiento(codigoAsiento)
        );

		if(!sePudoComprar) {
            throw new AsientoNoDisponibleException(this.nombre + ": el asiento no está disponible");
        }
    }

    @Override
    public void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoYaReservadoException{
    	String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();

    	boolean sePudoReservar = aerolineaOceanic.reservar(
    	        getDniFormateado(usuario.getDNI()),
                getCodigoVuelo(codigoAsiento),
                getNumeroDeAsiento(codigoAsiento));

        //Se comprueba si no se puede reservar
        if(!sePudoReservar){
           //Se dispara excepcion informando asiento ya reservado
           throw new AsientoYaReservadoException(this.nombre + ": " + "el asiento ya está reservado");
       }

    }

	public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
		return this.aerolineaOceanic.estaReservado(codigoDeVuelo, numeroDeAsiento);
	}

	private String getCodigoVuelo(String codigoAsiento){
       return codigoAsiento.split("-")[0];
    }

    private int getNumeroDeAsiento(String codigoAsiento){
        return Integer.parseInt(codigoAsiento.split("-")[1]);
    }

    private String getDniFormateado (int dni){
        return  Integer.toString(dni);

    }

	@Override
	protected double getTiempoVuelo(Object asiento) {
        AsientoDTO asientoGenerado = (AsientoDTO) asiento;

        return asientoGenerado.getTiempoDeVuelo();
	}

	@Override
	protected double getPopularidad(Object asiento) {
        AsientoDTO asientoGenerado = (AsientoDTO) asiento;

        return asientoGenerado.getPopularidad();
	}

	@Override
    public boolean estaReservado(VueloAsiento vueloAsiento) {
        String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();
        return this.aerolineaOceanic.estaReservado(getCodigoVuelo(codigoAsiento), getNumeroDeAsiento(codigoAsiento));
	}

}
