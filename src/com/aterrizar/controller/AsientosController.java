package com.aterrizar.controller;

import com.aterrizar.DummyData;
import com.aterrizar.exception.ParametroVacioException;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.util.date.PatternDoesntMatchException;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.view.AsientosView;
import com.aterrizar.viewmodel.AsientoViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AsientosController extends Controller {

    public Usuario modelo;
    public Repositorio repositorio;

    public AsientosController(Usuario modelo) {
        this.modelo = modelo;
        this.repositorio = DummyData.getRepositorio();
    }

    public void iniciar() {
        this.vista = new AsientosView(this);
        mostrarVista();
    }

    public List<AsientoViewModel> getAsientoDisponibles(VueloAsientoFiltro filtro) throws ParametroVacioException, PatternDoesntMatchException {
        List<AsientoViewModel> vuelosDisponibles = new ArrayList<>();

        vuelosDisponibles.addAll(repositorio.getVueloAsientos(filtro, this.modelo).stream().map(x -> new AsientoViewModel(
                x.getNombreAerolinea()
                , x.getAsiento().getCodigoAsiento()
                , x.getAsiento().getNumeroAsiento()
                , x.getAsiento().getPrecio()
                , x.getAsiento().getUbicacion().toString()
                , x.getAsiento().toString()
        )).collect(Collectors.toList()));

        return vuelosDisponibles;
    }
}
