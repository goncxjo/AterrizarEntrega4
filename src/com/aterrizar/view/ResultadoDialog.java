package com.aterrizar.view;

import com.aterrizar.mode.util.operation.ResultadoOperacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultadoDialog extends JDialog {

    private JLabel consultaLabel;
    private JButton okButton;
    private JButton sobreReservarButton;

    public ResultadoDialog(Frame owner, String title, boolean modal, ResultadoOperacion resultadoOperacion, boolean estaReservado) {
        super(owner, title, modal);
        setBounds(200, 200, 500, 150);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,15,0));
        contentPane.add(textPanel);

        JLabel resultadoLabel = new JLabel(resultadoOperacion.getResultado());
        textPanel.add(resultadoLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3,15,0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        if(resultadoOperacion.OK()) {
            okButton = new JButton("Seguir Buscando");
            buttonPanel.add(okButton);
        } else {
            if(estaReservado) {
                sobreReservarButton = new JButton("Sobrereservar");
                consultaLabel = new JLabel("Por favor, intente nuevamente.");
                buttonPanel.add(sobreReservarButton);
            } else {
                consultaLabel = new JLabel("¿Qué desea hacer?");
            }
            okButton = new JButton("Aceptar");
            textPanel.add(consultaLabel);
            buttonPanel.add(okButton);
        }
    }
}
