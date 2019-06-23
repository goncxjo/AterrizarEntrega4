package com.aterrizar;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.aerolinea.AerolineaLanchitaProxy;
import com.aterrizar.model.aerolinea.AerolineaOceanicProxy;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;
import com.aterrizar.model.asiento.Turista;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.util.date.DateHelper;

import java.util.Arrays;
import java.util.List;

public class DummyData {
    private static AerolineaLanchitaProxy aerolineaLanchitaProxy = new AerolineaLanchitaProxy(new LanchitaDummy());
    private static AerolineaOceanicProxy aerolineaOceanicProxy = new AerolineaOceanicProxy(new OceanicDummy());

    public static Usuario getUsuarioEstandar() {
        Usuario usuario = new Estandar("Florencia", "Bonsegundo", 37201911);

        VueloAsiento va1 = new VueloAsiento(
                aerolineaLanchitaProxy
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"),12.0,0.0)
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        VueloAsiento va2 = new VueloAsiento(
                aerolineaOceanicProxy
                , new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"),11.0,2.0)
                , new Turista("OCE 023-22", 25000, Ubicacion.Ventanilla, Estado.Disponible)
        );

        VueloAsiento va3 = new VueloAsiento(
                aerolineaLanchitaProxy
                , new Vuelo(Destino.BUE, Destino.BCN, DateHelper.parseToDate("13/05/2019"),13.0,1.0)
                , new PrimeraClase("LCH 005-43", 40000, Ubicacion.Pasillo, Estado.Disponible)
        );


        usuario.comprar(va1);
        usuario.comprar(va2);

        usuario.reservar(va3);

        return usuario;
    }

    public static Repositorio getRepositorio() {
        List<Aerolinea> aerolineasProxy = Arrays.asList(aerolineaLanchitaProxy, aerolineaOceanicProxy);
        Repositorio repositorio = new Repositorio(aerolineasProxy);

        return repositorio;
    }
}
