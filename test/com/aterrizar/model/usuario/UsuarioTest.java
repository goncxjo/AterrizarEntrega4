package com.aterrizar.model.usuario;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.util.date.DateHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioTest {

    @Test
    public void actualizarTipo_UsuarioNoRegistrado_SeVuelve_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario = usuario.actualizarTipo(new Estandar());
        assertEquals("No es usuario Estandar", Estandar.class, usuario.getClass());
    }

    @Test(expected = TipoUsuarioNoDisponibleException.class)
    public void actualizarTipo_UsuarioEstandar_NoPuedeSer_UsuarioVIP() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario.actualizarTipo(new VIP());
    }

    @Test
    public void actualizarTipo_UsuarioEstandar_Puede_Y_Se_Vuelve_UsuarioVIP() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

        VueloAsiento vueloAsiento = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 12.0, 0.0)
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        usuario.comprar(vueloAsiento);
        usuario.comprar(vueloAsiento);
        usuario.comprar(vueloAsiento);

        usuario = usuario.actualizarTipo(new VIP());

        assertEquals("No es usuario VIP", VIP.class, usuario.getClass());
    }

    @Test(expected = TipoUsuarioNoDisponibleException.class)
    public void actualizarTipo_UsuarioVIP_NoPuedeSer_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

        VueloAsiento vueloAsiento = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 12.0, 0.0)
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        usuario.comprar(vueloAsiento);
        usuario.comprar(vueloAsiento);
        usuario.comprar(vueloAsiento);

        usuario.actualizarTipo(new Estandar());
    }

    @Test
    public void actualizarTipo_UsuarioVIP_SeVuelve_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new VIP("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario = usuario.actualizarTipo(new Estandar());

        assertEquals("No es usuario Estandar", Estandar.class, usuario.getClass());
    }

    @Test
    public void comprarAsiento_UnUsuarioCompraUnAsiento() {
        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        VueloAsiento vueloAsiento = new VueloAsiento(
                "LCH"
                , "Lanchita"
                , new Vuelo(Destino.BUE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
                , new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
        );

        usuario.comprar(vueloAsiento);

        assertTrue("El usuario no ha podido comprar el asiento.", usuario.getHistorialCompras().contains(vueloAsiento));
    }
}
