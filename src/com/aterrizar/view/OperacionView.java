package com.aterrizar.view;

import com.aterrizar.controller.OperacionController;
import com.aterrizar.viewmodel.OperacionTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperacionView extends JFrame {

    private final JPanel contentPane;
    private final JPanel textPanel;
    private final JPanel operacionesPanel;
    private final JPanel buttonPanel;
    private final JLabel usuarioLabel;
    private final JTable operacionesTabla;
    private final JButton cerrarButton;

    public OperacionView(OperacionController controller) throws HeadlessException {
        super(controller.getTitulo());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 500, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

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

        cerrarButton = new JButton("Cerrar");
        buttonPanel.add(cerrarButton);

        cerrarButton.addActionListener(e -> onCerrar());
    }

    private void onCerrar() {
        dispose();
    }
}
