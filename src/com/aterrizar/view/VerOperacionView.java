package com.aterrizar.view;

import com.aterrizar.viewmodel.OperacionTableModel;
import com.aterrizar.viewmodel.UsuarioOperacionViewModel;

import javax.swing.*;
import java.awt.*;

public class VerOperacionView extends LayoutView {

    protected final JPanel textPanel = new JPanel();
    protected final JPanel operacionesPanel = new JPanel();
    protected final JPanel buttonPanel = new JPanel();
    protected final JLabel usuarioLabel = new JLabel();
    protected final JTable operacionesTabla = new JTable();

    public VerOperacionView(UsuarioOperacionViewModel vm) throws HeadlessException {
        super();

        textPanel.setLayout(new GridLayout(0, 2));
        textPanel.setBorder(NO_BORDER);
        contentPane.add(textPanel, BorderLayout.NORTH);

        usuarioLabel.setText(vm.getTipoOperacion() + " de " + vm.getUsuario().getNombreCompleto());
        textPanel.add(usuarioLabel);
        textPanel.add(new JLabel(getResizedImage(vm.getTipoOperacion() + ".png", 50, 50), JLabel.RIGHT));

        operacionesPanel.setLayout(new GridLayout(0, 1));
        operacionesPanel.setBorder(EMPTY_BORDER_TOP_BOTTOM);
        contentPane.add(operacionesPanel, BorderLayout.CENTER);

        operacionesPanel.add(new JScrollPane(operacionesTabla));
        operacionesTabla.setModel(new OperacionTableModel(vm));

        buttonPanel.setLayout(BUTTON_3_BORDER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(cerrarButton);

        cerrarButton.addActionListener(e -> onCerrar());
    }
}
