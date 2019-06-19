package com.aterrizar.viewmodel;

import com.aterrizar.DummyData;
import com.aterrizar.enumerator.Destino;
import com.aterrizar.exception.ParametroVacioException;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltroBuilder;

import java.util.ArrayList;
import java.util.List;

public class BuscarAsientoViewModel {
    public Usuario usuario;
    public VueloAsientoFiltro filtro;
    public List<VueloAsiento> vueloAsientos = new ArrayList<>();

    private Repositorio repositorio = DummyData.getRepositorio();

    public BuscarAsientoViewModel() {
    }

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

    public void setFiltro(Destino origen, Destino destino, String fecha) {
        this.filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(origen)
                .agregarDestino(destino)
                .agregarFecha(fecha)
                .build();
    }

    public void buscarAsientosDisponibles() throws ParametroVacioException {
        this.vueloAsientos = repositorio.getVueloAsientos(filtro, usuario);
    }

    public List<VueloAsiento> getVueloAsientos() {
        return vueloAsientos;
    }

    public void setVueloAsientos(List<VueloAsiento> vueloAsientos) {
        this.vueloAsientos = vueloAsientos;
    }
}
