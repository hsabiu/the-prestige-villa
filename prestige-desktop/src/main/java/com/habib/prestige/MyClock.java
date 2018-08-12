package com.habib.prestige;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

class MyClock extends JLabel implements Runnable {

    private static final long serialVersionUID = 1L;

    MyClock() {
        this.setForeground(new Color(133, 5, 20));
        this.setFont(new Font("DS-Digital", Font.BOLD, 20));

        Thread the_thread = new Thread(this);
        the_thread.start();
    }

    public void run() {
        try {
            while (true) {
                Date time = new Date();
                DateFormat myFormat = DateFormat.getTimeInstance(DateFormat.LONG);
                setText(myFormat.format(time));
                Thread.sleep(1000);
            }

        } catch (InterruptedException exp) {
            this.setText(exp.getLocalizedMessage());
        }
    }
}
