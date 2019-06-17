package com.aterrizar.view;

import com.aterrizar.controller.AsientosController;
import com.aterrizar.viewmodel.AsientoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AsientosView extends LayoutView {

    private final JPanel errorPanel = new JPanel();
    private final JPanel asientosPanel = new JPanel();
    private final JPanel filtrosPanel = new JPanel();
    private final JPanel resultadosPanel = new JPanel();
    private final JPanel botonesPanel = new JPanel();

    private final JLabel errorLabel = new JLabel("");
    private final JLabel origenLabel = new JLabel("Origen: ", JLabel.TRAILING);
    private final JLabel destinoLabel = new JLabel("Destino: ", JLabel.TRAILING);
    private final JLabel fechaLabel = new JLabel("Fecha: ", JLabel.TRAILING);
    private final JTextField origenTextField = new JTextField();
    private final JTextField destinoTextField = new JTextField();
    private final JTextField fechaTextField = new JTextField();

    private final JTable resultadosTabla = new JTable();

    private final JButton buscarButton = new JButton("Buscar");
    private final JButton comprarButton = new JButton("Comprar");
    private final JButton reservarButton = new JButton("Reservar");
    private final JButton cerrarButton = new JButton("Cerrar");

    public AsientosView(AsientosController controller) throws HeadlessException {
        super(controller.getTitulo());
        setSize(WIDTH, HEIGHT + 200);

        setErrorPanel();
        setAsientosPanel();
        setBotonesPanel();

        cerrarButton.addActionListener(e -> onCerrar());
    }

    private void setBotonesPanel() {
        botonesPanel.setLayout(new GridLayout(0,3,PADDING_0,0));
        contentPane.add(botonesPanel, BorderLayout.SOUTH);

        botonesPanel.add(comprarButton);
        botonesPanel.add(reservarButton);
        botonesPanel.add(cerrarButton);
    }

    private void setAsientosPanel() {
        asientosPanel.setLayout(new GridLayout(0, 1));
        asientosPanel.setBorder(new EmptyBorder(PADDING_0,0,PADDING_1,0));
        contentPane.add(asientosPanel, BorderLayout.CENTER);

        setFiltrosPanel();

        asientosPanel.add(resultadosPanel, BorderLayout.CENTER);

        resultadosPanel.add(new JScrollPane(resultadosTabla));
        resultadosTabla.setModel(new AsientoTableModel(new ArrayList<>()));
    }

    private void setFiltrosPanel() {
        filtrosPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" Búsqueda ")
                , EMPTY_BORDER
        ));

        filtrosPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(origenLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        filtrosPanel.add(origenTextField, c);
        origenTextField.setColumns(20);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(destinoLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        filtrosPanel.add(destinoTextField, c);
        destinoTextField.setColumns(20);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(fechaLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        filtrosPanel.add(fechaTextField, c);
        fechaTextField.setColumns(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;                            //reset to default
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.gridx = 0;                            //aligned
        c.gridy = 3;
        filtrosPanel.add(buscarButton, c);

        asientosPanel.add(filtrosPanel);
    }

    private void setErrorPanel() {
        errorPanel.setLayout(new GridLayout(0, 1));
        errorPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
                , EMPTY_BORDER
        ));
        contentPane.add(errorPanel, BorderLayout.NORTH);

        errorPanel.add(errorLabel);
    }

    private void onCerrar() {
        dispose();
    }
}
