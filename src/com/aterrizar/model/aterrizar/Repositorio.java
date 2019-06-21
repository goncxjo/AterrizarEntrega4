package com.aterrizar.model.aterrizar;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.exception.AsientoYaReservadoException;
import com.aterrizar.exception.DestinosIgualesException;
import com.aterrizar.exception.ParametroVacioException;
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
    private Comunicador comunicador;
    private ReservasHelper reservasHelper;
    private List<Reserva> listaEspera =  new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<VueloAsiento> vueloAsientos = new ArrayList<>();

    public Repositorio(Comunicador comunicador) {
        this.comunicador = comunicador;
    }

    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    public void comprar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
        String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();

        comunicador.comprar(codigoAsiento);
        usuario.comprar(vueloAsiento);
        eliminarSobreReservas(codigoAsiento);
    }

    public void reservar(VueloAsiento vueloAsiento, Usuario usuario) throws AsientoNoDisponibleException {
        String codigoAsiento = vueloAsiento.getAsiento().getCodigoAsiento();

        try {
            comunicador.reservar(codigoAsiento, usuario.getDNI());
            usuario.reservar(vueloAsiento);
        } catch (AsientoYaReservadoException e) {
            agregarSobreReserva(vueloAsiento, usuario);
        }
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

    private void eliminarSobreReservas(String codigoAsiento) {
        listaEspera.removeAll(getListaEspera(codigoAsiento));
    }

    public void transferir(Reserva reserva) {
        Usuario usuario = reserva.getUsuario();
        List<Reserva> listaEsperaPorCodigoAsiento = getListaEspera(reserva.getVueloAsiento().getAsiento().getCodigoAsiento());

        if(!listaEsperaPorCodigoAsiento.isEmpty()) {
            Reserva reservaEnEspera = listaEsperaPorCodigoAsiento.get(0);
            Usuario otroUsuario = reservaEnEspera.getUsuario();
            usuario.transferir(reserva, otroUsuario);
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

    public boolean estaReservado(String codigoAsiento) {
        return comunicador.estaReservado(codigoAsiento);
    }

    public List<VueloAsiento> getVueloAsientos(VueloAsientoFiltro filtro, Usuario usuario) throws ParametroVacioException, DestinosIgualesException {
        return comunicador.filtrarAsientos(filtro, usuario)
                .buscarSuperOfertas(usuario)
                .getVueloAsientos();
    }
}
