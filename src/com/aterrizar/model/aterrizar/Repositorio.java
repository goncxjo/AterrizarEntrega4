package com.aterrizar.model.aterrizar;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.exception.DestinosIgualesException;
import com.aterrizar.exception.ParametroVacioException;
import com.aterrizar.model.aerolinea.Aerolinea;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.Reserva;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;
import com.aterrizar.util.asiento.ReservasHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repositorio {
    private ReservasHelper reservasHelper;
    private List<Reserva> listaEspera =  new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Aerolinea> aerolineasProxy;
    private List<VueloAsiento> vueloAsientos = new ArrayList<>();

    public Repositorio(List<Aerolinea> aerolineasProxy) {
        this.aerolineasProxy = aerolineasProxy;
    }

    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
        Aerolinea aerolinea = vueloAsiento.getAerolinea();

        aerolinea.comprar(vueloAsiento, usuario);
        usuario.comprar(vueloAsiento);

        eliminarSobreReservas(vueloAsiento);
    }

    public void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException, AsientoYaReservadoException {
        Aerolinea aerolinea = vueloAsiento.getAerolinea();

        aerolinea.reservar(vueloAsiento, usuario);
        usuario.reservar(vueloAsiento);
    }

    public void sobrereservar(VueloAsiento vueloAsiento, Usuario usuario) {
        agregarSobreReserva(vueloAsiento, usuario);
        usuario.reservar(vueloAsiento);
    }

    public List<Reserva> getListaEspera(String codigoAsiento) {
        return listaEspera
                .stream()
                .filter(x -> x.getVueloAsiento().getAsiento().getCodigoAsiento().equals(codigoAsiento))
                .sorted(Comparator.comparing(Reserva::getFechaReserva))
                .collect(Collectors.toList());
    }

    private void agregarSobreReserva(VueloAsiento vueloAsiento, Usuario usuario) {
        listaEspera.add(new Reserva(vueloAsiento, usuario));
    }

    private void eliminarSobreReserva(Reserva reserva) {
        listaEspera.removeIf(x -> x.getVueloAsiento().getAsiento().getCodigoAsiento().equals(reserva.getVueloAsiento().getAsiento().getCodigoAsiento()));
    }

    private void eliminarSobreReservas(VueloAsiento vueloAsiento) {
        String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();
        listaEspera.removeAll(getListaEspera(codigoAsiento));
    }

    public void transferir(Reserva reserva) {
        Usuario usuario = reserva.getUsuario();
        List<Reserva> listaEsperaPorCodigoAsiento = getListaEspera(reserva.getVueloAsiento().getAsiento().getCodigoAsiento());

        if(!listaEsperaPorCodigoAsiento.isEmpty()) {
            Reserva reservaEnEspera = listaEsperaPorCodigoAsiento.get(0);
            Usuario otroUsuario = reservaEnEspera.getUsuario();
            usuario.transferir(reserva, otroUsuario);
            eliminarSobreReserva(reserva);
        } else {
            usuario.eliminar(reserva);
        }
    }

    public void verificarReservasExpiradas() {
        for (Usuario usuario : usuarios) {
            for (Reserva reserva : usuario.getReservas()) {
                if(reservasHelper.expiro(reserva)) {
                    transferir(reserva);
                }
            }
        }
    }

    public boolean estaReservado(VueloAsiento vueloAsiento) {
        Aerolinea aerolinea = vueloAsiento.getAerolinea();

        return aerolinea.estaReservado(vueloAsiento);
    }

    public List<VueloAsiento> getVueloAsientos(VueloAsientoFiltro filtro, Usuario usuario) throws ParametroVacioException, DestinosIgualesException {
        this.vueloAsientos.clear();

        for (Aerolinea aerolineaProxy : this.aerolineasProxy) {
            this.vueloAsientos.addAll(
                    aerolineaProxy
                            .filtrarAsientos(filtro, usuario)
                            .buscarSuperOfertas(usuario)
                            .getVueloAsientos()
            );
        }

        usuario.agregarFiltroAlHistorial(filtro);

        return this.vueloAsientos;
    }
}
