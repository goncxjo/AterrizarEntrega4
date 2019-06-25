package com.aterrizar.dummydata;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoLanchitaYaReservadoException;
import com.aterrizar.model.aerolinea.AerolineaLanchita;

import java.util.*;
import java.util.stream.Collectors;

public class LanchitaDummy implements AerolineaLanchita {
    private Map<String, String> diccionarioDestinos;
    private List<List<String>> asientos;

    public LanchitaDummy() {

        this.diccionarioDestinos = new HashMap();
        diccionarioDestinos.put("BCN", "079");
        diccionarioDestinos.put("MEX", "037");
        diccionarioDestinos.put("MIA", "177");
        diccionarioDestinos.put("LA", "266");
        diccionarioDestinos.put("XXX", "000");

        this.asientos = new ArrayList<>();
        asientos.add(Arrays.asList("LCH 079-42","10000.00","T","V","D", "12.0", "6.0"));
        asientos.add(Arrays.asList("LCH 079-46","14000.00","T","C","D", "12.0", "4.0"));
        asientos.add(Arrays.asList("LCH 079-12","20000.00","P","C","D", "10.0", "4.0"));
        asientos.add(Arrays.asList("LCH 079-37","20100.00","E","C","R", "11.0", "2.0"));
        asientos.add(Arrays.asList("LCH 037-35","120000.00","P","P","D", "1.0", "1.0"));
        asientos.add(Arrays.asList("LCH 177-42","1000.00","T","V","D", "12.0", "6.0"));
        asientos.add(Arrays.asList("LCH 177-46","1400.00","T","C","D", "12.0", "4.0"));
        asientos.add(Arrays.asList("LCH 177-12","2000.00","P","C","R", "10.0", "4.0"));
        asientos.add(Arrays.asList("LCH 177-35","1800.00","P","P","D", "13.0", "1.0"));
        asientos.add(Arrays.asList("LCH 177-37","1700.00","E","C","D", "11.0", "2.0"));
        asientos.add(Arrays.asList("LCH 266-46","14000.00","T","C","D", "12.0", "4.0"));
        asientos.add(Arrays.asList("LCH 266-12","2000.00","P","C","D", "10.0", "4.0"));
    }

    @Override
    public List<List<String>> asientosDisponibles(String origen, String fechaSalida, String destino, String fechaLlegada) {
        return this.asientos
                .stream()
                .filter(x -> contiene(x, destino) && (x.get(4).equals("D") || x.get(4).equals("R")))
                .collect(Collectors.toList());
    }

    private boolean contiene(List<String> asiento, String destino) {
        return asiento.get(0).contains("LCH " + this.diccionarioDestinos.getOrDefault(destino, "XXX"));
    }

    @Override
    public void comprar(String codigoAsiento) throws AsientoLanchitaNoDisponibleException {
        List<String> asiento = getAsiento(codigoAsiento);
        if(estaDisponible(asiento)) {
            actualizarRegistro(asiento, "C");
        } else {
            throw new AsientoLanchitaNoDisponibleException("Asiento no disponible");
        }
    }

    private void actualizarRegistro(List<String> asiento, String nuevoEstado) {
        List<String> asientoActualizado = Arrays.asList(
                asiento.get(0)
                , asiento.get(1)
                , asiento.get(2)
                , asiento.get(3)
                , nuevoEstado
                , asiento.get(5)
                , asiento.get(6));

        this.asientos.remove(asiento);
        this.asientos.add(asientoActualizado);
    }

    private boolean estaDisponible(List<String> asiento) {
        return asiento.get(4).equals("D");
    }

    private List<String> getAsiento(String codigoAsiento) {
        return this.asientos
                    .stream()
                    .filter(x -> x.get(0).equals(codigoAsiento))
                    .collect(Collectors.toList())
                    .get(0);
    }

    @Override
    public void reservar(String codigoAsiento, String dni) throws AsientoLanchitaYaReservadoException, AsientoLanchitaNoDisponibleException {
        List<String> asiento = getAsiento(codigoAsiento);
        if(estaReservado(asiento)) {
            throw new AsientoLanchitaYaReservadoException("Asiento ya se encuentra reservado");
        }
        if(estaDisponible(asiento)) {
            actualizarRegistro(asiento, "R");
        } else {
            throw new AsientoLanchitaNoDisponibleException("Asiento no disponible");
        }
    }

    private boolean estaReservado(List<String> asiento) {
        return asiento.get(4).equals("R");
    }

    @Override
    public boolean estaReservado(String codigoAsiento) {
        List<String> asiento = getAsiento(codigoAsiento);
        return estaReservado(asiento);
    }
}
