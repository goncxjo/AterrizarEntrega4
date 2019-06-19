package com.aterrizar.viewmodel;

import com.aterrizar.model.util.date.DateHelper;
import com.aterrizar.model.vueloasiento.VueloAsiento;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OperacionTableModel extends AbstractTableModel {

    List<VueloAsiento> operacionesList;
    String[] headerList = {
            "Salida"
            , "Aerolinea"
            , "Vuelo"
            , "Asiento"
            , "Precio"
    };

    Class[] classes = { String.class, String.class, String.class, String.class, Double.class };

    public OperacionTableModel(UsuarioOperacionViewModel vm) {
        vm.buscarOperacionesUsuario();
        operacionesList = vm.getVueloAsientos();
    }

    public VueloAsiento obtener(int index) {
        try {
            return operacionesList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        VueloAsiento entity = null;
        entity = operacionesList.get(row);

        switch (column) {
            case 0:
                return DateHelper.parseToString(entity.getVuelo().getFecha(), "dd/MM/yyyy");
            case 1:
                return entity.getNombreAerolinea();
            case 2:
                String codigoVuelo = entity.getAsiento().getCodigoAsiento().split("-")[0];
                return codigoVuelo;
            case 3:
                String nroAsiento = entity.getAsiento().getCodigoAsiento().split("-")[1];
                return nroAsiento;
            case 4:
                return entity.getAsiento().getPrecio();
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
