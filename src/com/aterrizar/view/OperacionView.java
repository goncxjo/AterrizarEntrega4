package com.aterrizar.view;

import com.aterrizar.controller.OperacionController;
import com.aterrizar.viewmodel.OperacionTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperacionView extends LayoutView {

    private final OperacionController controller;
    private final JPanel textPanel;
    private final JPanel operacionesPanel;
    private final JPanel buttonPanel;
    private final JLabel usuarioLabel;
    private final JTable operacionesTabla;
    private final JButton cerrarButton = new JButton("Cerrar");

    public OperacionView(OperacionController controller) throws HeadlessException {
        super(controller.getTitulo(), WIDTH, HEIGHT + 200);
        this.controller = controller;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,0,0));
        contentPane.add(textPanel, BorderLayout.NORTH);

        usuarioLabel = new JLabel("Operaciones de " + controller.getModelo().getNombreCompleto() + ":");
        textPanel.add(usuarioLabel);

        operacionesPanel = new JPanel();
        operacionesPanel.setLayout(new GridLayout(0, 1));
        operacionesPanel.setBorder(new EmptyBorder(25,0,25,0));
        contentPane.add(operacionesPanel, BorderLayout.CENTER);

        operacionesTabla = new JTable();
        operacionesPanel.add(new JScrollPane(operacionesTabla));
        operacionesTabla.setModel(new OperacionTableModel(controller.getOperacionesUsuario()));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3,15,0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        cerrarButton.addActionListener(e -> onCerrar());
    }

    private void onCerrar() {
        dispose();
    }
}
