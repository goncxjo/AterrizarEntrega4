package com.aterrizar.model.aterrizar;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.exception.*;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.aerolinea.*;
import com.aterrizar.model.asiento.AsientoDTO;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.Reserva;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltroBuilder;
import com.aterrizar.util.date.DateHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class RepositorioTest {
	private Repositorio repositorio;
	@Mock AerolineaLanchita mockLanchita;
	@Mock AerolineaOceanic mockOceanic;
	AerolineaLanchitaProxy aerolineaLanchitaProxy;
	AerolineaOceanicProxy aerolineaOceanicProxy;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		aerolineaLanchitaProxy = new AerolineaLanchitaProxy(mockLanchita);
		aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
		List<Aerolinea> aerolineasProxy = Arrays.asList(aerolineaLanchitaProxy, aerolineaOceanicProxy);
		repositorio = new Repositorio(aerolineasProxy);
	}

	@Test
	public void comprar_CompraUnAsientoDisponible() throws AsientoNoDisponibleException, DestinosIgualesException, ParametroVacioException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

		when(mockOceanic.asientosDisponiblesParaOrigenYDestino(anyString(), anyString(), anyString()))
				.thenReturn(Arrays.asList(
						new AsientoDTO("OCE 001", 1, DateHelper.parseFromISO8601("31/12/2019"), null, 1000000, new Ejecutivo(), Ubicacion.Centro, 10.0, 3.0)
				));

		aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

		//Se crea filtro de busqueda
		VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
				.agregarOrigen(Destino.BUE)
				.agregarDestino(Destino.LA)
				.agregarFecha("31/12/2019")
				.build();

		doAnswer(invocationOnMock -> {
			when(mockOceanic.asientosDisponiblesParaOrigenYDestino(anyString(), anyString(), anyString()))
					.thenAnswer(i -> Arrays.asList());
			this.aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);
			return true; // ESTA DISPONIBLE
		}).when(mockOceanic).comprarSiHayDisponibilidad(anyString(), anyString(), anyInt());

		List<VueloAsiento> vueloAsientosAntesDeComprar = new ArrayList<>();
		vueloAsientosAntesDeComprar.addAll(repositorio.getVueloAsientos(filtro, usuario));
		VueloAsiento vueloAsiento = vueloAsientosAntesDeComprar.get(0);
		repositorio.comprar(vueloAsiento, usuario);
		List<VueloAsiento> vueloAsientosDespuesDeComprar = repositorio.getVueloAsientos(filtro, usuario);

		assertTrue("No se pudo comprar el asiento", !vueloAsientosAntesDeComprar.isEmpty() && vueloAsientosDespuesDeComprar.isEmpty());
	}

	@Test(expected = AsientoNoDisponibleException.class)
	public void comprar_NoSePuedeComprarAsientoQueNoExiste() throws AsientoNoDisponibleException, DestinosIgualesException, ParametroVacioException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

		when(mockOceanic.asientosDisponiblesParaOrigenYDestino(anyString(), anyString(), anyString()))
				.thenReturn(Arrays.asList(
						new AsientoDTO("OCE 001", 1, DateHelper.parseFromISO8601("31/12/2019"), null, 1000000, new Ejecutivo(), Ubicacion.Centro, 10.0, 3.0)
				));

		aerolineaOceanicProxy = new AerolineaOceanicProxy(mockOceanic);

		//Se crea filtro de busqueda
		VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
				.agregarOrigen(Destino.BUE)
				.agregarDestino(Destino.LA)
				.agregarFecha("31/12/2019")
				.build();

		when(mockOceanic.comprarSiHayDisponibilidad(anyString(), anyString(), anyInt())).thenReturn(false);

		List<VueloAsiento> vueloAsientos = repositorio.getVueloAsientos(filtro, usuario);
		VueloAsiento vueloAsiento = vueloAsientos.get(0);
		repositorio.comprar(vueloAsiento, usuario);
	}

	@Test
	public void reservar_ReservaUnAsientoDisponible() throws AsientoNoDisponibleException, AsientoYaReservadoException, UsuarioEnListaEsperaException, UsuarioYaHizoReservaException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

		VueloAsiento vueloAsiento = new VueloAsiento(
				aerolineaOceanicProxy
				, new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
				, new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
		);

		when(mockOceanic.estaReservado(anyString(), anyInt())).thenReturn(false);
		boolean estaReservadoAntesDeReservar = repositorio.estaReservado(vueloAsiento);

		when((mockOceanic.reservar(anyObject(), anyString(), anyInt()))).thenReturn(true);
		repositorio.reservar(vueloAsiento, usuario);

		when(mockOceanic.estaReservado(anyString(), anyInt())).thenReturn(true);
        boolean estaReservadoDespuesDeReservar = repositorio.estaReservado(vueloAsiento);
        
		assertTrue("No se pudo reservar el asiento", !estaReservadoAntesDeReservar && estaReservadoDespuesDeReservar);
	}
	
	
	@Test
	public void reservar_SeSobrereservaUnAsientoYaReservado() throws AsientoNoDisponibleException, AsientoLanchitaYaReservadoException, UsuarioYaHizoReservaException, UsuarioEnListaEsperaException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

		VueloAsiento vueloAsiento = new VueloAsiento(
				aerolineaLanchitaProxy
				, new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
				, new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Reservado)
				);

		try {
			Mockito.doThrow(AsientoYaReservadoException.class).when(mockLanchita).reservar(anyString(),anyString());
			repositorio.reservar(vueloAsiento, usuario);
		} catch (AsientoYaReservadoException e) {
			repositorio.sobrereservar(vueloAsiento, usuario);
		}

		assertTrue("No se pudo sobre reservar el asiento", !repositorio.getListaEspera(vueloAsiento.getAsiento().getCodigoAsiento()).isEmpty());
	}
	
	
	@Test
	public void transferir_CaeLaReservaDeUnAsientoYEsTransferidaAlPrimeroDeLaListaDeEspera() throws AsientoLanchitaYaReservadoException, AsientoNoDisponibleException, AsientoYaReservadoException, UsuarioEnListaEsperaException, UsuarioYaHizoReservaException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		Usuario usuario2 = new Estandar("Jessica", "Jones", 30303456);
		
		VueloAsiento vueloAsiento = new VueloAsiento(
				aerolineaLanchitaProxy
				, new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
				, new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
				);
		
		doNothing().when(mockLanchita).reservar(anyObject(), anyString());
		repositorio.reservar(vueloAsiento, usuario);
		Reserva asientoReservado = new Reserva(vueloAsiento, usuario);

		try {
			Mockito.doThrow(AsientoYaReservadoException.class).when(mockLanchita).reservar(anyString(),anyString());
			repositorio.reservar(vueloAsiento, usuario2);
		} catch (AsientoYaReservadoException e) {
			repositorio.sobrereservar(vueloAsiento, usuario2);
		}
		boolean listaDeEsperaAntesDeTransferir = repositorio.getListaEspera(vueloAsiento.getAsiento().getCodigoAsiento()).isEmpty();
		
		repositorio.transferir(asientoReservado);
		boolean listaDeEsperaDespuesDeTransferir = repositorio.getListaEspera(vueloAsiento.getAsiento().getCodigoAsiento()).isEmpty();	
		
		assertTrue("No se pudo transferir la reserva", !listaDeEsperaAntesDeTransferir && listaDeEsperaDespuesDeTransferir);
	}
	
	
	@Test
	public void transferir_CaeLaReservaDeUnAsientoYLaListaDeEsperaEstaVacia() throws AsientoLanchitaYaReservadoException, AsientoNoDisponibleException, AsientoYaReservadoException, UsuarioEnListaEsperaException, UsuarioYaHizoReservaException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		
		VueloAsiento vueloAsiento = new VueloAsiento(
				aerolineaLanchitaProxy
				, new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
				, new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
				);
		
		doNothing().when(mockLanchita).reservar(anyObject(), anyString());
		repositorio.reservar(vueloAsiento, usuario);
		Reserva asientoReservado = new Reserva(vueloAsiento, usuario);
		when(mockLanchita.estaReservado(anyString())).thenReturn(true);
		boolean estaReservadoAntesDeTransferir = repositorio.estaReservado(vueloAsiento);
		
		repositorio.transferir(asientoReservado);
		when(mockLanchita.estaReservado(anyString())).thenReturn(false);
		boolean estaReservadoDespuesDeTransferir = repositorio.estaReservado(vueloAsiento);
		
		assertTrue("La reserva fue transferida correctamente", estaReservadoAntesDeTransferir && !estaReservadoDespuesDeTransferir);
	}
}