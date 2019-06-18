package com.aterrizar;

import com.aterrizar.controller.BienvenidaController;

import java.awt.*;

public class AterrizarMVC {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BienvenidaController controller = new BienvenidaController(DummyData.getUsuarioEstandar());

                controller.iniciar();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
