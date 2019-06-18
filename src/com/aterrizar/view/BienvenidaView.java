package com.aterrizar.view;

import com.aterrizar.controller.BienvenidaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BienvenidaView extends LayoutView {

    private final BienvenidaController controller;
    private final JPanel textPanel;
    private final JPanel buttonPanel;
    private final JLabel usuarioLabel;
    private final JLabel consultaLabel;
    private final JButton verComprasButton;
    private final JButton verReservasButton;
    private final JButton buscarAsientosButton;

    public BienvenidaView(BienvenidaController controller) throws HeadlessException {
        super(controller.getTitulo(), WIDTH, HEIGHT - 100);
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,25,0));
        contentPane.add(textPanel);

        usuarioLabel = new JLabel("Hola, " + controller.getModelo().getNombreCompleto() + ".") ;
        consultaLabel = new JLabel("¿Qué desea hacer?");
        textPanel.add(usuarioLabel);
        textPanel.add(consultaLabel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3,15,0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        verComprasButton = new JButton("Ver Compras");
        verReservasButton = new JButton("Ver Reservas");
        buscarAsientosButton = new JButton("Buscar Asientos");
        buttonPanel.add(verComprasButton);
        buttonPanel.add(verReservasButton);
        buttonPanel.add(buscarAsientosButton);

        verComprasButton.addActionListener(e -> controller.mostrarCompras());
        verReservasButton.addActionListener(e -> controller.mostrarReservas());
        buscarAsientosButton.addActionListener(e -> controller.mostrarBusquedaAsientos());
    }
}
