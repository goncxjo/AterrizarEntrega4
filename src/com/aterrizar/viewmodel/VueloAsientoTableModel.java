package com.aterrizar.viewmodel;

import com.aterrizar.model.vueloasiento.VueloAsiento;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VueloAsientoTableModel extends AbstractTableModel {
    List<VueloAsiento> asientosList;
    String[] headerList = {
            "Aerolinea"
            , "Vuelo"
            , "Asiento"
            , "Precio"
            , "Ubicación"
            , "Clase"
    };

    Class[] classes = { String.class, String.class, String.class, Double.class, String.class, String.class };

    public VueloAsientoTableModel() {
        asientosList = new ArrayList<>();
     }

    public VueloAsientoTableModel(List<VueloAsiento> list) {
        asientosList = list;
    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        VueloAsiento entity = getRowAt(row);

        switch (column) {
            case 0:
                return entity.getAerolinea().getNombre();
            case 1:
                String codigoVuelo = entity.getAsiento().getCodigoAsiento().split("-")[0];
                return codigoVuelo;
            case 2:
                String nroAsiento = entity.getAsiento().getCodigoAsiento().split("-")[1];
                return nroAsiento;
            case 3:
                return entity.getAsiento().getPrecio();
            case 4:
                return entity.getAsiento().getUbicacion();
            case 5:
                return entity.getAsiento().getNombreTipoAsiento();
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

    public VueloAsiento getRowAt(int index) {
        try {
            return asientosList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void reset() {
        if(!asientosList.isEmpty()) {
            asientosList.clear();
            fireTableDataChanged();
        }
    }

}
