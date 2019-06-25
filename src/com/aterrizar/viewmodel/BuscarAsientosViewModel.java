package com.aterrizar.viewmodel;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.vueloasiento.TipoOrden;
import com.aterrizar.exception.*;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.usuario.VIP;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltroBuilder;
import com.aterrizar.util.date.PatternDoesntMatchException;

import java.util.ArrayList;
import java.util.List;

public class BuscarAsientosViewModel {
    public Usuario usuario;
    public VueloAsientoFiltro filtro;
    public List<VueloAsiento> vueloAsientos = new ArrayList<>();
    public VueloAsiento vueloAsientoSeleccionado;

    private Repositorio repositorio;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VueloAsientoFiltro getFiltro() {
        return filtro;
    }

    public void setFiltro(VueloAsientoFiltro filtro) {
        this.filtro = filtro;
    }

    public void setFiltro(Destino origen, Destino destino, String fecha, TipoOrden tipoOrden) {
        this.filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(origen)
                .agregarDestino(destino)
                .agregarFecha(fecha)
                .agregarTipoOrden(tipoOrden)
                .build();
    }

    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void buscarAsientosDisponibles() throws ParametroVacioException, PatternDoesntMatchException, DestinosIgualesException, NoHayAsientosDisponiblesException {
        this.vueloAsientos = repositorio.getVueloAsientos(filtro, usuario);
        if (this.vueloAsientos.isEmpty()) {
            throw new NoHayAsientosDisponiblesException("No hay asientos disponibles según los filtros ingresados");
        }
    }

    public List<VueloAsiento> getVueloAsientos() {
        return vueloAsientos;
    }

    public void setVueloAsientos(List<VueloAsiento> vueloAsientos) {
        this.vueloAsientos = vueloAsientos;
    }

    public VueloAsiento getVueloAsientoSeleccionado() {
        return vueloAsientoSeleccionado;
    }

    public void setVueloAsientoSeleccionado(VueloAsiento vueloAsientoSeleccionado) {
        this.vueloAsientoSeleccionado = vueloAsientoSeleccionado;
    }

    public void comprarVueloAsientoSeleccionado() throws AsientoNoDisponibleException {
        repositorio.comprar(vueloAsientoSeleccionado, usuario);
    }

    public void reservarVueloAsientoSeleccionado() throws AsientoNoDisponibleException, AsientoYaReservadoException, UsuarioEnListaEsperaException, UsuarioYaHizoReservaException {
        repositorio.reservar(vueloAsientoSeleccionado, usuario);
    }

    public void sobrereservarVueloAsientoSeleccionado() {
        repositorio.sobrereservar(vueloAsientoSeleccionado, usuario);
    }

    public void actualizarUsuarioSiCumpleCondiciones() {
        try {
            if(usuario.puedeSerUsuarioVIP()) {
                usuario = usuario.actualizarTipo(new VIP());
            }
        } catch (TipoUsuarioNoDisponibleException e) {
            // TODO: ver qué hacer con esta excepción
        }
    }
}
