package com.habib.prestige;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

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