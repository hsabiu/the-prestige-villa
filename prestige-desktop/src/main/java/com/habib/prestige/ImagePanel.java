package com.habib.prestige;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    Image img;

    ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}

