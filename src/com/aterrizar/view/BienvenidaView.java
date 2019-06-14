package com.aterrizar.view;

import com.aterrizar.mode.util.operation.ResultadoCompra;
import com.aterrizar.mode.util.operation.ResultadoReserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BienvenidaView extends JFrame {

    private final String title = "Aterrizar.com";
    private final JPanel contentPane;
    private final JPanel textPanel;
    private final JPanel buttonPanel;
    private final JLabel usuarioLabel;
    private final JLabel consultaLabel;
    private final JButton verComprasButton;
    private final JButton verReservasButton;
    private final JButton buscarAsientosButton;

    public BienvenidaView() throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 150);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setBorder(new EmptyBorder(0,0,15,0));
        contentPane.add(textPanel);

        usuarioLabel = new JLabel("Hola, XXXXXX");
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

        // TODO: Pasar a modulo de compras/reservas
        ResultadoDialog compraDialog = new ResultadoDialog(
                this
                , title
                , true
                , new ResultadoCompra("TE ANOTASTE EN OBJETOS DEMASIADO TARDE!!")
                , false
        );

        ResultadoDialog reservaDialog = new ResultadoDialog(
                this
                , title
                , true
                , new ResultadoReserva(123456)
                , false
        );

        verComprasButton.addActionListener(e -> compraDialog.setVisible(true));
        verReservasButton.addActionListener(e -> reservaDialog.setVisible(true));

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BienvenidaView frame = new BienvenidaView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
