package com.aterrizar;

import com.aterrizar.view.BienvenidaView;

import java.awt.*;

public class AterrizarMVC {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BienvenidaView frame = new BienvenidaView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
