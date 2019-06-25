package com.aterrizar;

import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.aerolinea.AerolineaLanchitaProxy;
import com.aterrizar.model.aerolinea.AerolineaOceanicProxy;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;

import java.util.Arrays;
import java.util.List;

public class DummyData {
    private static AerolineaLanchitaProxy aerolineaLanchitaProxy = new AerolineaLanchitaProxy(new LanchitaDummy());
    private static AerolineaOceanicProxy aerolineaOceanicProxy = new AerolineaOceanicProxy(new OceanicDummy());

    public static Usuario getUsuarioEstandar() {
        return new Estandar("Florencia", "Bonsegundo", 37201911);
    }

    public static Repositorio getRepositorio() {
        List<Aerolinea> aerolineasProxy = Arrays.asList(aerolineaLanchitaProxy, aerolineaOceanicProxy);
        Repositorio repositorio = new Repositorio(aerolineasProxy);

        return repositorio;
    }
}
