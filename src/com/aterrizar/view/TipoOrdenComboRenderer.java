package com.aterrizar.view;

import com.aterrizar.enumerator.vueloasiento.TipoOrden;

import javax.swing.*;
import java.awt.*;

public class TipoOrdenComboRenderer extends JLabel implements ListCellRenderer<TipoOrden> {
    @Override
    public Component getListCellRendererComponent(JList<? extends TipoOrden> list, TipoOrden value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getNombre());
        return this;
    }
}
