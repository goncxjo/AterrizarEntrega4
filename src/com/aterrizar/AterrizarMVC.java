package com.aterrizar;

import com.aterrizar.controller.BienvenidaController;
import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;
import com.aterrizar.model.asiento.Turista;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.util.date.DateHelper;
import com.aterrizar.model.vueloasiento.VueloAsiento;

import java.awt.*;

public class AterrizarMVC {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BienvenidaController controller = new BienvenidaController(generarDummyData());

                controller.iniciar();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static Usuario generarDummyData() {
        Usuario usuario = new Estandar("Darío", "Sztajnszrajber", 17123456);

        VueloAsiento va1 = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"))
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        VueloAsiento va2 = new VueloAsiento(
                "OCE"
                , "Oceanic"
                , new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"))
                , new Turista("LCH 005-22", 25000, Ubicacion.Ventanilla, Estado.Disponible)
        );

        VueloAsiento va3 = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.BAR, DateHelper.parseToDate("13/05/2019"))
                , new PrimeraClase("LCH 005-43", 40000, Ubicacion.Pasillo, Estado.Disponible)
        );


        usuario.comprar(va1);
        usuario.comprar(va2);

        usuario.reservar(va3.getAsiento().getCodigoAsiento());

        return usuario;
    }
}
