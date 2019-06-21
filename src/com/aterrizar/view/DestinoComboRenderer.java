package com.aterrizar.view;

import com.aterrizar.enumerator.Destino;

import javax.swing.*;
import java.awt.*;

public class DestinoComboRenderer extends JLabel implements ListCellRenderer<Destino> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Destino> list, Destino value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getCodigoYNombre());
        return this;
    }
}
