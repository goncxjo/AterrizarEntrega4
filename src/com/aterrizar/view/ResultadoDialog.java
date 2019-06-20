package com.aterrizar.view;

import com.aterrizar.model.util.operation.ResultadoOperacion;
import com.aterrizar.model.vueloasiento.VueloAsiento;

import javax.swing.*;

import static com.aterrizar.view.LayoutView.TITLE;

public class ResultadoDialog {
    public static int mostrarResultadoOperacion(ResultadoOperacion resultado) {
        return JOptionPane.showOptionDialog(
                null
                , resultado.getResultado()
                , TITLE
                , resultado.getTipoOpciones()
                , resultado.getTipoMensaje()
                , null
                , resultado.getOpciones()
                , resultado.getOpciones()[0]);
    }

    public static int preguntarSiSobrereservar(String errorMensaje, VueloAsiento vueloAsiento) {
        Object[] options = { "Sobrereservar", "Seguir buscando" };
        return JOptionPane.showOptionDialog(
                null
                , errorMensaje + ". \n ¿Qué desea hacer?"
                , TITLE
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.INFORMATION_MESSAGE
                , null
                , options
                , options[0]);
    }
}
