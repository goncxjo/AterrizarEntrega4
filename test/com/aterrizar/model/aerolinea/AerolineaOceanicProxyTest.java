package com.aterrizar.model.aerolinea;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.exception.*;
import com.aterrizar.model.asiento.AsientoDTO;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;
import com.aterrizar.model.asiento.Turista;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.NoRegistrado;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltroBuilder;
import com.aterrizar.util.date.DateHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class AerolineaOceanicProxyTest {
    private AerolineaOceanicProxy aerolineaOceanicProxy;

    @Mock private AerolineaOceanic mockOceanic;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void asientosDisponiblesParaOrigen_UsuarioNoRegistrado_ObtenerTodosAsientosDesdeBUE() throws ParametroVacioException, DestinosIgualesException {
        //Asientos disponibles con vuelos desde Buenos Aires
        when(mockOceanic.asientosDisponiblesParaOrigen("BUE", "31/12/1990"))
                .thenReturn(this.generarAsientosOrigen());
        
        //Cargo datos de aerolinea oceanic al proxy
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
        
        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        assertTrue("No contiene 4 vuelos", vueloAsientos.size() == 4);
    }

    @Test
    public void asientosDisponiblesParaOrigenYDestino_UsuarioNoRegistrado_ObtenerAsientosDesdeBUEaMEX() throws ParametroVacioException, DestinosIgualesException {
        //Asientos disponibles con vuelos desde Buenos Aires a Mexico
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","MEX"))
                .thenReturn(this.generarAsientosDeBUEaMEX());
        
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.MEX)
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        assertTrue("No contiene 2 vuelos", vueloAsientos.size() == 2);
    }

    @Test
    public void asientosDisponiblesParaOrigenYDestino_UsuarioNoRegistrado_ObtenerAsientosDesdeBUEaSLA() throws ParametroVacioException, DestinosIgualesException {

        //Asientos disponibles con vuelos desde Buenos Aires a Los Angeles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());
        
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.LA)
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        assertTrue("No se encontraron vuelos",!vueloAsientos.isEmpty());
    }

    @Test
    public void asientosDisponiblesParaOrigenYDestino_UsuarioNoRegistrado_NoObtenerAsientosDesdeBUEaTOK() throws ParametroVacioException, DestinosIgualesException {

        //Asientos disponibles con vuelos desde Buenos Aires a Los Angeles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());
        
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.TOK)
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        assertTrue("Se encontraron vuelos",vueloAsientos.isEmpty());
    }
 
    @Test(expected = ParametroVacioException.class)
    public void asientosDisponiblesParaOrigenYDestino_UsuarioNoRegistrado_NoSeAceptaOrigenNulo() throws ParametroVacioException, DestinosIgualesException {

        //Asientos disponibles con vuelos desde Buenos Aires a Los Angeles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());
        
       
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

    }

    @Test(expected = ParametroVacioException.class)
    public void asientosDisponiblesParaOrigenYDestino_UsuarioNoRegistrado_NoSeAceptaFechaNulo() throws ParametroVacioException, DestinosIgualesException {

        //Asientos disponibles con vuelos desde Buenos Aires a Los Angeles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());
        
       
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.TOK)
                .build();

        Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

    }
    
    @Test
    public void estaReservado_asientoNoEstaReservado() {
        //Se carga asiento no reservado
    	when(mockOceanic.estaReservado("OCE 001", 1))
        .thenReturn(false);
    	
    	aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
    	
        //El asiento OCE 001 1 esta disponible
        assertFalse("El asiento OCE 001-1 esta reservado",aerolineaOceanicProxy.estaReservado("OCE 001", 1));
    }

    @Test
    public void estaReservado_asientoEstaReservado() {
    	//Se carga asiento reservado
        when(mockOceanic.estaReservado("OCE 010", 1))
        .thenReturn(true);
        
    	aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
        
        //El asiento OCE 010 1 esta reservado
        assertTrue("El asiento OCE 010-1 no esta reservado",aerolineaOceanicProxy.estaReservado("OCE 010", 1));
    }

    @Test
    public void comprarSiHayDisponibilidad_UsuarioEstandard_SeCompraAsientoDisponible() throws AsientoOceanicNoDisponibleException
            , AsientoNoDisponibleException
            , ParametroVacioException, DestinosIgualesException {
        String codigoAsiento = "OCE 001-1";
    	//Se cargan vuelos disponibles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());
        
        //Cuando se compra un asiento se resetea los asientos del mockOceanic quitando el asiento que se compro
       doAnswer(invocationOnMock -> {
           //Se obtiene la lista de asientos disponibles
           List<AsientoDTO> asientosDisponibles = generarAsientosDeBUEaSLA();

            //Se quita el asiento OCE 001-1
           asientosDisponibles.remove(0);

           when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                    .thenAnswer(i -> asientosDisponibles );
           this.aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
           //Se devuelve true indicando que se pudo comprar el asiento
            return true;
        }).when(mockOceanic).comprarSiHayDisponibilidad(anyString(), eq("OCE 001"), eq(1));

    	aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

    	//Se crea filtro de busqueda
        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.LA)
                .agregarFecha("31/12/1990")
                .build();

        //Se crea el usuario
        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);
        
        //Se crea el vueloAsiento
        VueloAsiento vueloAsiento = aerolineaOceanicProxy
				.filtrarAsientos(filtro, usuario)
				.getVueloAsientos()
				.get(0);

        //Se guardan los asientos antes de comprar
        List<VueloAsiento> vueloAsientosAntesDeComprar = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        //Se compra asiento
        aerolineaOceanicProxy.comprar(vueloAsiento, usuario);

        //Se guardan los asientos despues de comprar
        List<VueloAsiento> vueloAsientosDespuesDeComprar = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        //El asiento OCE 001 1 se pudo comprar
        assertTrue("El asiento OCE 001-1 no se pudo comprar",
                vueloAsientosAntesDeComprar.size() == 2 && vueloAsientosDespuesDeComprar.size() == 1);
    }

    @Test(expected = AsientoNoDisponibleException.class)
    public void comprarSiHayDisponibilidad_UsuarioEstandar_NoPuedeComprarUnAsientoReservado() throws AsientoNoDisponibleException
            , AsientoOceanicNoDisponibleException
            , ParametroVacioException, DestinosIgualesException {
    	when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
		        .thenReturn(this.generarAsientosDeBUEaSLA());		
		
        doThrow(new AsientoOceanicNoDisponibleException("El asiento ya se encuentra reservado"))
                .when(mockOceanic)
                .comprarSiHayDisponibilidad(anyString(), eq("OCE 001"), eq(1));
        //Se resetean los asientos disponibles
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
        
        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.LA)
                .agregarFecha("31/12/1990")
                .build();

        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);
        
        VueloAsiento vueloAsiento = aerolineaOceanicProxy
				.filtrarAsientos(filtro, usuario)
				.getVueloAsientos()
				.get(0);

        this.aerolineaOceanicProxy.comprar(vueloAsiento, usuario);
    }

    @Test
    public void reservar_UsuarioEstandar_SeReservaAsientoDisponible() throws AsientoOceanicNoDisponibleException
            , AsientoYaReservadoException
            , ParametroVacioException, DestinosIgualesException {
        String codigoAsiento = "OCE 001-1";
        //Se cargan vuelos disponibles
        when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                .thenReturn(this.generarAsientosDeBUEaSLA());


        //Cuando se reserva un asiento se resetea los asientos del mockOceanic quitando el asiento que se reservó
        doAnswer(invocationOnMock -> {
            //Se obtiene la lista de asientos disponibles
            List<AsientoDTO> asientosDisponibles = generarAsientosDeBUEaSLA();

            //Se quita el asiento OCE 001-1
            asientosDisponibles.remove(0);

            when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
                    .thenAnswer(i -> asientosDisponibles );
            //Se resetean los asientos disponibles
            this.aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
            //Se devuelve true indicando que se pudo reservar asiento
            return true;
        }).when(mockOceanic).reservar(anyString(), eq("OCE 001"), eq(1));

        //Se crea proxy con el mock
        aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

        //Se crea filtro de busqueda
        VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.LA)
                .agregarFecha("31/12/1990")
                .build();

        //Se crea el usuario
        Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);
        
      //Se crea el vueloAsiento
        VueloAsiento vueloAsiento = aerolineaOceanicProxy
				.filtrarAsientos(filtro, usuario)
				.getVueloAsientos()
				.get(0);

        //Se guardan los asientos antes de reservar
        List<VueloAsiento> vueloAsientosAntesDeReservar = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        //Se reserva asiento
        aerolineaOceanicProxy.reservar(vueloAsiento, usuario);

        //Se guardan los asientos despues de reservar
        List<VueloAsiento> vueloAsientosDespuesDeReservar = aerolineaOceanicProxy
                .filtrarAsientos(filtro, usuario)
                .getVueloAsientos();

        //El asiento OCE 001 1 se pudo reservar
        assertTrue("El asiento OCE 001-1 no se pudo reservar",
                vueloAsientosAntesDeReservar.size() == 2 && vueloAsientosDespuesDeReservar.size() == 1);
    }
    
    //Generar asientos  Origen de Buenos Aires a Los Angeles
    public List<AsientoDTO> generarAsientosDeBUEaSLA(){
        List<AsientoDTO> asientos = new ArrayList();
        Date fechaSalida = DateHelper.parseFromISO8601("31/12/1990");

        asientos.add(new AsientoDTO("OCE 001", 1, fechaSalida, null, 100, new Ejecutivo(), Ubicacion.Centro));
        asientos.add(new AsientoDTO("OCE 002", 1, fechaSalida, null, 110, new Turista(), Ubicacion.Pasillo));
        return asientos;
    }

    //Generar asientos  Origen de Buenos Aires a Mexico
    public List<AsientoDTO> generarAsientosDeBUEaMEX(){
        List<AsientoDTO> asientos = new ArrayList();
        Date fechaSalida = DateHelper.parseFromISO8601("31/12/1990");

        asientos.add(new AsientoDTO("OCE 003", 1, fechaSalida, null, 340, new PrimeraClase(), Ubicacion.Pasillo));
        asientos.add(new AsientoDTO("OCE 004", 1, fechaSalida, null, 200, new Ejecutivo(), Ubicacion.Ventanilla));
        return asientos;
    }

    //Generar asientos  Origen desde Buenos Aires con fecha "31/12/1990"
    public List<AsientoDTO> generarAsientosOrigen(){
        List<AsientoDTO> asientos = new ArrayList();
        asientos.addAll(this.generarAsientosDeBUEaSLA());
        asientos.addAll(this.generarAsientosDeBUEaMEX());
        return asientos;
    }
}
