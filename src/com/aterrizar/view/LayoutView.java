package com.aterrizar.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LayoutView extends JFrame {

    protected static final String TITLE = "Aterrizar.com";
    protected static final int WIDTH = 500;
    protected static final int HEIGHT = 300;
    protected static final int PADDING_0 = 15;
    protected static final int PADDING_1 = 25;
    protected static final int PADDING_2 = 50;
    protected static final EmptyBorder NO_BORDER = new EmptyBorder(0,0,0,0);
    protected static final EmptyBorder EMPTY_BORDER = new EmptyBorder(PADDING_0, PADDING_0, PADDING_0, PADDING_0);
    protected static final EmptyBorder EMPTY_BORDER_TOP_BOTTOM = new EmptyBorder(PADDING_0, 0, PADDING_0, 0);
    protected static final GridLayout BUTTON_1_BORDER = new GridLayout(0,1,PADDING_0,0);
    protected static final GridLayout BUTTON_2_BORDER = new GridLayout(0,2,PADDING_0,0);
    protected static final GridLayout BUTTON_3_BORDER = new GridLayout(0,3,PADDING_0,0);
    protected static final ImageIcon DEFAULT_ICON = new ImageIcon("global.png");

    protected JPanel contentPane;
    protected final JButton cerrarButton = new JButton("Cerrar");

    public LayoutView() throws HeadlessException {
        configFrame(WIDTH, HEIGHT);
    }

    public LayoutView(int width, int height) throws HeadlessException {
        configFrame(width, height);
    }

    private void configFrame(int width, int height) {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setIconImage(DEFAULT_ICON.getImage());

        centerFrame();

        contentPane = new JPanel();
        contentPane.setBorder(EMPTY_BORDER);
        contentPane.setLayout(new BorderLayout());

        setContentPane(contentPane);
    }

    public void centerFrame() {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
    }

    public void onCerrar() {
        dispose();
    }

    public ImageIcon getResizedImage(String filePath, int width, int height) {
        ImageIcon originalImageIcon = new ImageIcon(filePath);
        Image img = originalImageIcon.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
