
package com.habib.prestige;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;

class MyDate extends JLabel {

	private static final long serialVersionUID = 1L;

	MyDate() {
		Date today = new Date();
		DateFormat myFormat = DateFormat.getDateInstance(DateFormat.FULL);
		setForeground(new Color(133, 5, 20));
		setFont(new Font("Costantia", Font.BOLD, 14));
		setText(myFormat.format(today));
	}
}
