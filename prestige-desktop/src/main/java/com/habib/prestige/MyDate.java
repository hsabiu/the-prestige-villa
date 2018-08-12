
package com.habib.prestige;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

class MyDate extends JLabel {

    private static final long serialVersionUID = 1L;

    MyDate() {
        Date today = new Date();
        DateFormat myFormat = DateFormat.getDateInstance(DateFormat.FULL);
        setForeground(new Color(133, 5, 20));
        setFont(new Font("Constantia", Font.BOLD, 14));
        setText(myFormat.format(today));
    }
}
