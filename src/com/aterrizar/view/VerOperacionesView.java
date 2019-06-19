package com.aterrizar.view;

import com.aterrizar.controller.OperacionController;
import com.aterrizar.viewmodel.OperacionTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class OperacionView extends LayoutView {

    protected OperacionController controller;

    protected final JPanel textPanel = new JPanel();
    protected final JPanel operacionesPanel = new JPanel();
    protected final JPanel buttonPanel = new JPanel();
    protected final JLabel usuarioLabel = new JLabel();
    protected final JTable operacionesTabla = new JTable();;
    protected final JButton cerrarButton = new JButton("Cerrar");

    public OperacionView() throws HeadlessException {
        super();

        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,0,0));
        contentPane.add(textPanel, BorderLayout.NORTH);

        textPanel.add(usuarioLabel);

        operacionesPanel.setLayout(new GridLayout(0, 1));
        operacionesPanel.setBorder(new EmptyBorder(25,0,25,0));
        contentPane.add(operacionesPanel, BorderLayout.CENTER);

        operacionesPanel.add(new JScrollPane(operacionesTabla));
        operacionesTabla.setModel(new OperacionTableModel(controller.getOperacionesUsuario()));

        buttonPanel.setLayout(new GridLayout(0,3,15,0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        cerrarButton.addActionListener(e -> onCerrar());
    }
}
