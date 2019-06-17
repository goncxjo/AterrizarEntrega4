package com.aterrizar.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LayoutView extends JFrame {

    protected static final int WIDTH = 500;
    protected static final int HEIGHT = 300;
    protected static final int PADDING_0 = 15;
    protected static final int PADDING_1 = 25;
    protected static final int PADDING_2 = 50;
    protected static final EmptyBorder EMPTY_BORDER = new EmptyBorder(PADDING_0, PADDING_0, PADDING_0, PADDING_0);

    protected final JPanel contentPane;

    public LayoutView(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        centrar();

        contentPane = new JPanel();
        contentPane.setBorder(EMPTY_BORDER);
        contentPane.setLayout(new BorderLayout());

        setContentPane(contentPane);
    }

    private void centrar() {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
    }
}
