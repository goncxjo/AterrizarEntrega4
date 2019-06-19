package com.aterrizar.view;

import com.aterrizar.DummyData;
import com.aterrizar.controller.BienvenidaController;
import com.aterrizar.viewmodel.BienvenidaViewModel;
import com.aterrizar.viewmodel.UsuarioComprasViewModel;
import com.aterrizar.viewmodel.UsuarioReservasViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BienvenidaView extends LayoutView {

    private BienvenidaController controller;
    private BienvenidaViewModel vm;

    private final JPanel textPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JLabel usuarioLabel;
    private final JLabel consultaLabel = new JLabel("¿Qué desea hacer?");;
    private final JButton verComprasButton = new JButton("Ver Compras");
    private final JButton verReservasButton = new JButton("Ver Reservas");
    private final JButton buscarAsientosButton = new JButton("Buscar Asientos");

    public BienvenidaView() throws HeadlessException {
        super(WIDTH, HEIGHT - 100);
        crearController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,25,0));
        contentPane.add(textPanel);

        usuarioLabel = new JLabel("Hola, " + vm.getUsuario().getNombreCompleto() + "!");
        textPanel.add(usuarioLabel);
        textPanel.add(consultaLabel);

        buttonPanel.setLayout(new GridLayout(0,3,15,0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(verComprasButton);
        buttonPanel.add(verReservasButton);
        buttonPanel.add(buscarAsientosButton);

        verComprasButton.addActionListener(e -> onVerCompras());
        verReservasButton.addActionListener(e -> onVerReservas());
        buscarAsientosButton.addActionListener(e -> onBuscarAsientos());
    }

    private void onVerCompras() {
        VerOperacionView verComprasFrame = new VerOperacionView(new UsuarioComprasViewModel(vm.getUsuario()));
        verComprasFrame.setVisible(true);
    }

    private void onVerReservas() {
        VerOperacionView verReservasFrame = new VerOperacionView(new UsuarioReservasViewModel(vm.getUsuario()));
        verReservasFrame.setVisible(true);
    }

    private void onBuscarAsientos() {
        BuscarAsientoView buscarAsientoFrame= new BuscarAsientoView(vm.getUsuario());
        buscarAsientoFrame.setVisible(true);
    }

    private void crearController() {
        this.controller = new BienvenidaController();
        this.vm = controller.getModelo();
        this.vm.setUsuario(DummyData.getUsuarioEstandar());
    }
}
