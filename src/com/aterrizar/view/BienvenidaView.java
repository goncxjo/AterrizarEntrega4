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

    private final JPanel usuarioPanel = new JPanel();
    private final JPanel textPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JLabel usuarioLabel;
    private final JLabel bienvenidaLabel = new JLabel();
    private final JLabel preguntaLabel = new JLabel();
    private final JButton verComprasButton = new JButton("Ver Compras");
    private final JButton verReservasButton = new JButton("Ver Reservas");
    private final JButton buscarAsientosButton = new JButton("Buscar Asientos");

    public BienvenidaView() throws HeadlessException {
        super(WIDTH, HEIGHT - 100);
        crearController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.add(usuarioPanel, BorderLayout.WEST);

        usuarioLabel = new JLabel(getResizedImage("usuario.png", 100, 100));
        usuarioPanel.add(usuarioLabel);

        textPanel.setLayout(new GridLayout(2, 0));
        textPanel.setBorder(new EmptyBorder(PADDING_1,PADDING_1,PADDING_1 + 10,0));
        contentPane.add(textPanel, BorderLayout.CENTER);

        bienvenidaLabel.setText("Hola, " + vm.getUsuario().getNombreCompleto() + "!");
        preguntaLabel.setText("¿Qué desea hacer?");
        textPanel.add(bienvenidaLabel);
        textPanel.add(preguntaLabel);

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
        DummyData data = new DummyData();
        this.vm.setUsuario(data.getUsuarioEstandar());
    }
}
