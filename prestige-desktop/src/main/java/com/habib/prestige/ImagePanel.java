package com.habib.prestige;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Image img;

	ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

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
		g.drawImage(img, 0, 0,getWidth(), getHeight(), null);
	}
}

