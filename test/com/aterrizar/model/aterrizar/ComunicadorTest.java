package com.aterrizar.model.aterrizar;

public class ComunicadorTest {
//	private Comunicador comunicador;
//	@Mock AerolineaLanchita mockLanchita;
//
//	@Before
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//
//		when(mockLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
//				.thenReturn(Arrays.asList(
//						Arrays.asList("LCH 344-42","1000.00","E","C","D", "12.0", "0.0")
//				));
//
//		AerolineaLanchitaProxy aerolineaLanchitaProxy = new AerolineaLanchitaProxy(mockLanchita);
//		comunicador = new Comunicador(aerolineaLanchitaProxy);
//	}
//
//	@Test
//	public void filtrarAsientos_UnUsuarioBuscaAsientosYEncuentra() throws ParametroVacioException, DestinosIgualesException {
//		Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
//
//		VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
//				.agregarOrigen(Destino.BUE)
//				.agregarDestino(Destino.MIA)
//				.agregarFecha("20190510")
//				.agregarTipoAsiento(new Turista())
//				.agregarUbicacion(Ubicacion.Ventanilla)
//				.build();
//
//		List<VueloAsiento> vueloAsientos = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos();
//
//		assertFalse(vueloAsientos.isEmpty());
//	}
//
//	@Test
//	public void comprarAsiento_UnUsuarioCompraUnAsiento() throws AsientoNoDisponibleException, ParametroVacioException, DestinosIgualesException {
//
//		when(mockLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
//				.thenReturn(Arrays.asList(
//						Arrays.asList("LCH 344-46","400.00","T","V","D", "15.0", "0.0")
//				));
//
//		doAnswer(invocationOnMock -> {
//			when(mockLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
//					.thenAnswer(i -> Arrays.asList());
//
//			AerolineaLanchitaProxy aerolineaLanchitaProxy = new AerolineaLanchitaProxy(mockLanchita);
//			this.comunicador = new Comunicador(aerolineaLanchitaProxy);
//			return null;
//		}).when(mockLanchita).comprar(anyObject());
//
//		Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
//
//		VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
//				.agregarOrigen(Destino.BUE)
//				.agregarDestino(Destino.MIA)
//				.agregarFecha("20190510")
//				.agregarTipoAsiento(new Turista())
//				.agregarUbicacion(Ubicacion.Ventanilla)
//				.build();
//
//		VueloAsiento vueloAsientoSeleccionado = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos()
//				.get(0);
//
//		this.comunicador.comprar(vueloAsientoSeleccionado, usuario);
//
//		List<VueloAsiento> asientosLuegoDeComprar = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos();
//
//		assertFalse("El usuario no ha podido comprar el asiento.", asientosLuegoDeComprar.contains(vueloAsientoSeleccionado));
//	}
//
//	@Test
//	public void comprar_Usuario_ReservaUnAsientoDisponibleYSeEliminaDelVuelo() throws AsientoNoDisponibleException, ParametroVacioException, DestinosIgualesException {
//
//		doAnswer(invocationOnMock -> {
//			when(mockLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
//					.thenAnswer(i -> Arrays.asList());
//
//			AerolineaLanchitaProxy aerolineaLanchitaProxy = new AerolineaLanchitaProxy(mockLanchita);
//			this.comunicador = new Comunicador(aerolineaLanchitaProxy);
//			return null;
//		}).when(mockLanchita).comprar(anyObject());
//
//		Usuario usuario = new NoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
//
//		VueloAsientoFiltro filtro = new VueloAsientoFiltroBuilder()
//				.agregarOrigen(Destino.BUE)
//				.agregarDestino(Destino.MIA)
//				.agregarFecha("20190510")
//				.agregarTipoAsiento(new Turista())
//				.agregarUbicacion(Ubicacion.Ventanilla)
//				.build();
//
//		VueloAsiento vueloAsientoSeleccionado = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos()
//				.get(0);
//
//		List<VueloAsiento> vueloAsientosAntesDeComprar = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos();
//
//		comunicador.comprar(vueloAsientoSeleccionado, usuario);
//
//		List<VueloAsiento> vueloAsientosDespuesDeComprar = comunicador
//				.filtrarAsientos(filtro, usuario)
//				.getVueloAsientos();
//
//		assertTrue("El asiento aún existe", !vueloAsientosAntesDeComprar.isEmpty() && vueloAsientosDespuesDeComprar.isEmpty());
//	}
}
