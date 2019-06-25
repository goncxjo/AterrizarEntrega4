package com.aterrizar.model.aerolinea;

import com.aterrizar.enumerator.vueloasiento.TipoOrden;
import com.aterrizar.enumerator.Destino;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.exception.DestinosIgualesException;
import com.aterrizar.exception.ParametroVacioException;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.asiento.Asiento;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.util.date.DateHelper;
import com.aterrizar.util.date.PatternDoesntMatchException;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Aerolinea {
    protected String codigo;
    protected String nombre;
    protected List<VueloAsiento> vueloAsientos = new ArrayList();

    protected Aerolinea() {}

    Aerolinea(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }

    public List<VueloAsiento> getVueloAsientos() { return vueloAsientos; }

    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException{
        vueloAsiento.getVuelo().increasePopularidad();
    }

    public abstract void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoYaReservadoException, AsientoNoDisponibleException;

    public Aerolinea filtrarAsientos(VueloAsientoFiltro filtro, Usuario usuario) throws ParametroVacioException, DestinosIgualesException {
        this.vueloAsientos.clear();
        validarParametros(filtro);

        List asientosDisponibles = getAsientosDisponiblesPorAerolinea(filtro);

        if(!asientosDisponibles.isEmpty()) {
            this.vueloAsientos = mapear(asientosDisponibles, filtro, usuario);
        }

        return this;
    }

    protected abstract double getTiempoVuelo(Object asiento);
    
    protected abstract double getPopularidad(Object asiento);

    protected List getAsientosDisponiblesPorAerolinea(VueloAsientoFiltro filtro) {
        return new ArrayList();
    }

    protected void validarParametros(VueloAsientoFiltro filtro) throws ParametroVacioException, PatternDoesntMatchException, DestinosIgualesException {
        Destino origen = filtro.getOrigen();
        Destino destino = filtro.getDestino();
        String fecha = filtro.getFecha();

        if(origen == null) {
            throw new ParametroVacioException("El origen no puede estar vacío");
        }
        if(destino == null) {
            throw new ParametroVacioException("El destino no puede estar vacío");
        }
        if(fecha == null || fecha.equals("")) {
            throw new ParametroVacioException("La fecha no puede estar vacía");
        }
        if(origen.equals(destino)) {
            throw new DestinosIgualesException("El origen y el destino deben ser distintos.");
        }

        // Probamos si arroja excepción
        DateHelper.parseToDate(filtro.getFecha());
    }

    private List<VueloAsiento> mapear(List<Object> asientosDisponibles, VueloAsientoFiltro filtro, Usuario usuario) {
        return asientosDisponibles
                .stream()
                .map(asiento -> new VueloAsiento(
                                this
                                , new Vuelo(filtro.getOrigen(), filtro.getDestino(), DateHelper.parseToDate(filtro.getFecha()), getTiempoVuelo(asiento), getPopularidad(asiento))
                                , generarAsiento(asiento, usuario)
                        )
                )
                .collect(Collectors.toList());
    }

    protected Asiento generarAsiento(Object asiento, Usuario usuario) {
        return null;
    }

    public Aerolinea buscarSuperOfertas(Usuario usuario) {
        for (VueloAsiento vueloAsiento : this.vueloAsientos) {
            Asiento asiento = vueloAsiento.getAsiento();
            if (asiento.esSuperOferta(usuario)) {
                asiento.marcarComoSuperOferta();
            }
        }

        this.vueloAsientos.removeIf(va -> va.getAsiento().esSuperOferta() && !usuario.esVIP());

        return this;
    }

    protected VueloAsiento getVueloAsiento(String codigoAsiento) throws AsientoNoDisponibleException {
        Optional<VueloAsiento> vueloAsiento = this.vueloAsientos
                .stream()
                .filter(x -> x.getAsiento().getCodigoAsiento().equals(codigoAsiento))
                .findFirst();

        if(vueloAsiento.isPresent()) {
            return vueloAsiento.get();
        } else {
            throw new AsientoNoDisponibleException("El asiento no existe");
        }
    }

    public Aerolinea ordenarAsientosPor(TipoOrden tipoOrden) {
        if(tipoOrden == null) {
            tipoOrden = TipoOrden.superOferta;
        }
        this.vueloAsientos.sort(tipoOrden::sort);

        return this;
    }

    public abstract boolean estaReservado(VueloAsiento vueloAsiento);
}
