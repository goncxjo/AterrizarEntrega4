package com.aterrizar;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.aerolinea.AerolineaLanchita;
import com.aterrizar.model.aerolinea.AerolineaLanchitaProxy;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;
import com.aterrizar.model.asiento.Turista;
import com.aterrizar.model.aterrizar.Comunicador;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.util.date.DateHelper;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class DummyData {

    public Usuario getUsuarioEstandar() {
        Usuario usuario = new Estandar("Darío", "Sztajnszrajber", 17123456);

        VueloAsiento va1 = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"),12.0,0.0)
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        VueloAsiento va2 = new VueloAsiento(
                "OCE"
                , "Oceanic"
                , new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"),11.0,2.0)
                , new Turista("OCE 023-22", 25000, Ubicacion.Ventanilla, Estado.Disponible)
        );

        VueloAsiento va3 = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.BAR, DateHelper.parseToDate("13/05/2019"),13.0,1.0)
                , new PrimeraClase("LCH 005-43", 40000, Ubicacion.Pasillo, Estado.Disponible)
        );


        usuario.comprar(va1);
        usuario.comprar(va2);

        usuario.reservar(va3);

        return usuario;
    }

    public Repositorio getRepositorio() {
        AerolineaLanchita mockLanchita = Mockito.mock(AerolineaLanchita.class);
        Repositorio repositorio = new Repositorio(new Comunicador(new AerolineaLanchitaProxy(mockLanchita)));

        when(mockLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(
                    Arrays.asList(
                        Arrays.asList("LCH 344-42","1000.00","T","V","D")
                        , Arrays.asList("LCH 344-46","1400.00","T","C","D")
                        , Arrays.asList("LCH 344-12","2000.00","P","C","D")
                        , Arrays.asList("LCH 344-35","1800.00","P","P","D")
                        , Arrays.asList("LCH 344-37","1700.00","E","C","D")
                    )
                );

        return repositorio;
    }
}
