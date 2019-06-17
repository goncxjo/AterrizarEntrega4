package com.aterrizar.viewmodel;

import com.aterrizar.model.util.date.DateHelper;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OperacionTableModel extends AbstractTableModel {

    List<OperacionViewModel> operacionesList;
    String[] headerList = {
            "Salida"
            , "Aerolinea"
            , "Vuelo"
            , "Asiento"
            , "Precio"
    };

    Class[] classes = { String.class, String.class, String.class, String.class, Double.class };

    public OperacionTableModel(List<OperacionViewModel> list) {
        operacionesList = list;
    }

    public OperacionViewModel obtener(int index) {
        try {
            return operacionesList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        OperacionViewModel entity = null;
        entity = operacionesList.get(row);

        switch (column) {
            case 0:
                return DateHelper.parseToString(entity.getFechaSalida(), "dd/MM/yyyy");
            case 1:
                return entity.getNombreAerolinea();
            case 2:
                return entity.getCodigoVuelo();
            case 3:
                return entity.getNroAsiento();
            case 4:
                return entity.getPrecioAsiento();
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
        return operacionesList.size();
    }

    // This method will be used to display the name of columns
    public String getColumnName(int col) {
        return headerList[col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    }
}
