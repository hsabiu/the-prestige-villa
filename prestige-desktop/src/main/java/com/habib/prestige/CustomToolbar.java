package com.habib.prestige;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

//This class override the default tool bars paintComponent method to produce a toolBar with a background image
public class CustomToolbar extends JToolBar {
    private static final long serialVersionUID = 1L;

    CustomToolbar() {
        super();
        setOpaque(true);
        setBorder(new BevelBorder(BevelBorder.RAISED, new Color(239, 241, 236), new Color(239, 241, 236), null, null));
        setFloatable(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = this.getSize();
        ImageIcon image = new ImageIcon(LoginWindow.class.getResource("/icons/upperFrame.PNG"));
        g.drawImage(image.getImage(), 0, 0, size.width, size.height, this);
    }
}