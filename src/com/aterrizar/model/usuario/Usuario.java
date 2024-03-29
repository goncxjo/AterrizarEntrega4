package com.aterrizar.model.usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.model.asiento.Asiento;
import com.aterrizar.model.vueloasiento.Reserva;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.model.vueloasiento.VueloAsientoFiltro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected int DNI;
    protected List<VueloAsientoFiltro> historialBusquedas = new ArrayList<>();
    protected List<VueloAsiento> historialCompras = new ArrayList<>();
    protected List<Reserva> reservas = new ArrayList<>();

    public Usuario(String nombre, String apellido, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
    }

    public Usuario(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.DNI = usuario.getDNI();
        this.historialBusquedas = usuario.getHistorialBusquedas();
        this.historialCompras = usuario.getHistorialCompras();
        this.reservas = usuario.getReservas();
    }

    public Usuario() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() { return nombre + " " + this.apellido; }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public List<VueloAsientoFiltro> getHistorialBusquedas() {
        return this.historialBusquedas;
    }

    public void agregarFiltroAlHistorial(VueloAsientoFiltro vuelo) { this.historialBusquedas.add(vuelo); }

    public List<VueloAsiento> getHistorialCompras() { return this.historialCompras; }

    public void comprar(VueloAsiento vueloAsiento) {
        this.historialCompras.add(vueloAsiento);
    }

    public float getRecargo() { return 0; }

    public boolean esSuperOferta(Asiento asiento) { return false; }

    public Usuario actualizarTipo(Usuario nuevoUsuario) throws TipoUsuarioNoDisponibleException {
        throw new TipoUsuarioNoDisponibleException("No existe el usuario solicitado");
    }

    public abstract boolean puedeSerUsuarioVIP();

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void reservar(VueloAsiento vueloAsiento) {
        this.reservas.add(new Reserva(vueloAsiento, this));
    }

    private void eliminarReserva(String codigoAsiento) {
        this.reservas.removeIf(x -> x.getVueloAsiento().getAsiento().getCodigoAsiento().equals(codigoAsiento));
    }

    public void eliminar(Reserva reserva) {
        eliminarReserva(reserva.getVueloAsiento().getAsiento().getCodigoAsiento());
    }

    public void transferir(Reserva reserva, Usuario otroUsuario) {
        eliminarReserva(reserva.getVueloAsiento().getAsiento().getCodigoAsiento());
        otroUsuario.reservar(reserva.getVueloAsiento());
    }

    public boolean yaHizoReserva(VueloAsiento vueloAsiento) {
        Optional<Reserva> reserva = this.reservas
                .stream()
                .filter(r -> r.getVueloAsiento().getAsiento().getCodigoAsiento().equals(vueloAsiento.getAsiento().getCodigoAsiento()))
                .findAny();

        return reserva.isPresent();
    }

    public boolean esVIP() { return false; }
}
