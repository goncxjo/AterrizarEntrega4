package com.aterrizar.model.aterrizar;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.enumerator.asiento.Estado;
import com.aterrizar.exception.AsientoLanchitaYaReservadoException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.model.Vuelo;
import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.aerolinea.AerolineaLanchita;
import com.aterrizar.model.aerolinea.AerolineaLanchitaProxy;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.usuario.Estandar;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.Reserva;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.util.date.DateHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class RepositorioTest {
	private Repositorio repositorio;
	@Mock AerolineaLanchita mockLanchita;
	AerolineaLanchitaProxy aerolineaLanchitaProxy;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		aerolineaLanchitaProxy = new AerolineaLanchitaProxy(mockLanchita);
		List<Aerolinea> aerolineasProxy = Arrays.asList(aerolineaLanchitaProxy);
		repositorio = new Repositorio(aerolineasProxy);
	}

	@Test
	public void reservar_ReservaUnAsientoDisponible() throws AsientoNoDisponibleException, AsientoLanchitaYaReservadoException, AsientoYaReservadoException {
		Usuario usuario = new Estandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

		VueloAsiento vueloAsiento = new VueloAsiento(
				aerolineaLanchitaProxy
				, new Vuelo(Destino.EZE, Destino.MIA, DateHelper.parseToDate("13/05/2019"), 10.0, 5.0)
				, new Ejecutivo("LCH 005-40", 50000, Ubicacion.Centro, Estado.Disponible)
				);

		when(mockLanchita.estaReservado(anyString())).thenReturn(false);
		boolean estaReservadoAntesDeReservar = repositorio.estaReservado(vueloAsiento);

		doNothing().when(mockLanchita).reservar(anyObject(), anyString());
		repositorio.reservar(vueloAsiento, usuario);

		when(mockLanchita.estaReservado(anyString())).thenReturn(true);
        boolean estaReservadoDespuesDeReservar = repositorio.estaReservado(vueloAsiento);
        
		assertTrue("No se pudo reservar el asiento", !estaReservadoAntesDeReservar && estaReservadoDespuesDeReservar);
	}
	
	
	@Test
	public void reservar_ReservaUnAsientoNoDisponible() throws AsientoNoDisponibleException, AsientoLanchitaYaReservadoException, AsientoYaReservadoException {
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
	public void transferir_CaeLaReservaDeUnAsientoYEsTransferidaAlPrimeroDeLaListaDeEspera() throws AsientoLanchitaYaReservadoException, AsientoNoDisponibleException, AsientoYaReservadoException {
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
			repositorio.reservar(vueloAsiento, usuario);
		} catch (AsientoYaReservadoException e) {
			repositorio.sobrereservar(vueloAsiento, usuario);
		}
		boolean listaDeEsperaAntesDeTransferir = repositorio.getListaEspera(vueloAsiento.getAsiento().getCodigoAsiento()).isEmpty();
		
		repositorio.transferir(asientoReservado);
		boolean listaDeEsperaDespuesDeTransferir = repositorio.getListaEspera(vueloAsiento.getAsiento().getCodigoAsiento()).isEmpty();	
		
		assertTrue("No se pudo transferir la reserva", !listaDeEsperaAntesDeTransferir && listaDeEsperaDespuesDeTransferir);
	}
	
	
	@Test
	public void transferir_CaeLaReservaDeUnAsientoYLaListaDeEsperaEstaVacia() throws AsientoLanchitaYaReservadoException, AsientoNoDisponibleException, AsientoYaReservadoException {
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