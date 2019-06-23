package com.aterrizar;

import com.aterrizar.enumerator.Ubicacion;
import com.aterrizar.model.aerolinea.AerolineaOceanic;
import com.aterrizar.model.asiento.AsientoDTO;
import com.aterrizar.model.asiento.Ejecutivo;
import com.aterrizar.model.asiento.PrimeraClase;
import com.aterrizar.model.asiento.Turista;

import java.util.*;
import java.util.stream.Collectors;

public class OceanicDummy implements AerolineaOceanic {
    private Map<String, String> diccionarioDestinos;
    private List<AsientoDTO> asientos;
    private List<AsientoDTO> asientosComprados;
    private List<AsientoDTO> asientosReservados;

    public OceanicDummy() {
        this.diccionarioDestinos = new HashMap();
        diccionarioDestinos.put("BCN", "271");
        diccionarioDestinos.put("MEX", "079");
        diccionarioDestinos.put("MIA", "505");
        diccionarioDestinos.put("LA_", "179");
        diccionarioDestinos.put("XXX", "000");

        this.asientos = new ArrayList<>();
        asientos.add(new AsientoDTO("OCE 271", 42, new Date(), null, 1000.00, new Turista(), Ubicacion.Ventanilla, 70, 13.0));
        asientos.add(new AsientoDTO("OCE 271", 46, new Date(), null, 1400.00, new Turista(), Ubicacion.Centro, 20, 10.0));
        asientos.add(new AsientoDTO("OCE 271", 12, new Date(), null, 2000.00, new PrimeraClase(), Ubicacion.Centro, 10, 11.0));
        asientos.add(new AsientoDTO("OCE 271", 37, new Date(), null, 1700.00, new Ejecutivo(), Ubicacion.Centro, 30, 9.0));
        asientos.add(new AsientoDTO("OCE 079", 35, new Date(), null, 1800.00, new PrimeraClase(), Ubicacion.Pasillo, 40, 8.0));
        asientos.add(new AsientoDTO("OCE 079", 42, new Date(), null, 1000.00, new Turista(), Ubicacion.Ventanilla, 10, 5.0));
        asientos.add(new AsientoDTO("OCE 079", 46, new Date(), null, 1400.00, new Turista(), Ubicacion.Centro, 15, 1.0));
        asientos.add(new AsientoDTO("OCE 505", 12, new Date(), null, 2000.00, new PrimeraClase(), Ubicacion.Centro, 60, 0.0));
        asientos.add(new AsientoDTO("OCE 505", 35, new Date(), null, 1800.00, new PrimeraClase(), Ubicacion.Pasillo, 55, 2.0));
        asientos.add(new AsientoDTO("OCE 505", 37, new Date(), null, 1700.00, new Ejecutivo(), Ubicacion.Centro, 40, 7.0));
        asientos.add(new AsientoDTO("OCE 179", 46, new Date(), null, 1400.00, new Turista(), Ubicacion.Centro, 25, 5.0));
        asientos.add(new AsientoDTO("OCE 179", 12, new Date(), null, 2000.00, new PrimeraClase(), Ubicacion.Centro, 10, 3.0));

        this.asientosComprados = new ArrayList();
        this.asientosReservados = new ArrayList();
    }

    @Override
    public List<AsientoDTO> asientosDisponiblesParaOrigen(String codigoOrigenOceanic, String fechaSalida) {
        return this.asientos;
    }

    @Override
    public List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic, String fechaSalida, String codigoDestinoOceanic) {
        return this.asientos
                .stream()
                .filter(x -> x.getCodigoVuelo().contains("OCE " + this.diccionarioDestinos.getOrDefault(codigoDestinoOceanic, "XXX")))
                .collect(Collectors.toList());
    }

    @Override
    public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
        Optional<AsientoDTO> asientoReservado = this.asientosReservados
                .stream()
                .filter(x -> x.getCodigoVuelo().equals(codigoDeVuelo) && x.getNumeroAsiento().equals(numeroDeAsiento))
                .findFirst();

        if(asientoReservado.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        Optional<AsientoDTO> asientoComprado = this.asientosComprados
                .stream()
                .filter(x -> x.getCodigoVuelo().equals(codigoVuelo) && x.getNumeroAsiento().equals(numeroDeAsiento))
                .findFirst();

        if(asientoComprado.isPresent()) {
            return false;
        }

        AsientoDTO asiento = getAsientoDTO(codigoVuelo, numeroDeAsiento);

        this.asientos.remove(asiento);
        this.asientosComprados.add(asiento);
        return true;
    }

    private AsientoDTO getAsientoDTO(String codigoVuelo, Integer numeroDeAsiento) {
        return this.asientos
                    .stream()
                    .filter(x -> x.getCodigoVuelo().equals(codigoVuelo) && x.getNumeroAsiento().equals(numeroDeAsiento))
                    .findFirst()
                    .get();
    }

    @Override
    public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        Optional<AsientoDTO> asientoReservado = this.asientosReservados
                .stream()
                .filter(x -> x.getCodigoVuelo().equals(codigoVuelo) && x.getNumeroAsiento().equals(numeroDeAsiento))
                .findFirst();

        if(asientoReservado.isPresent()) {
            return false;
        }

        AsientoDTO asiento = getAsientoDTO(codigoVuelo, numeroDeAsiento);

        this.asientos.remove(asiento);
        this.asientosReservados.add(asiento);

        return true;
    }
}
