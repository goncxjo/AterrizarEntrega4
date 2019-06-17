package com.aterrizar.viewmodel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AsientoTableModel extends AbstractTableModel {
    List<AsientoViewModel> asientosList;
    String[] headerList = {
            "Aerolinea"
            , "Vuelo"
            , "Asiento"
            , "Precio"
            , "Ubicación"
            , "Clase"
    };

    Class[] classes = { String.class, String.class, String.class, Double.class, String.class, String.class };

    public AsientoTableModel(List<AsientoViewModel> list) {
        asientosList = list;
    }

    public AsientoViewModel obtener(int index) {
        try {
            return asientosList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        AsientoViewModel entity = null;
        entity = asientosList.get(row);

        switch (column) {
            case 0:
                return entity.getNombreAerolinea();
            case 1:
                return entity.getCodigoVuelo();
            case 2:
                return entity.getNroAsiento();
            case 3:
                return entity.getPrecioAsiento();
            case 4:
                return entity.getUbicacion();
            case 5:
                return entity.getClase();
            default:
                return "";
        }
    }

    @Override
    public int getColumnCount() {
        return headerList.length;
    }

    @Override
    public int getRowCount() {
        return asientosList.size();
    }

    // This method will be used to display the name of columns
    public String getColumnName(int col) {
        return headerList[col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    }
}
