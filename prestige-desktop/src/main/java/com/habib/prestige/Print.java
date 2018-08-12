package com.habib.prestige;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Print implements Printable, ActionListener {

    private JFrame frameToPrint;

    Print(JFrame f) {
        frameToPrint = f;
    }

    public int print(Graphics g, PageFormat pf, int page) {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        double scale = Math.min(pf.getImageableWidth() / frameToPrint.getWidth(), pf.getImageableWidth() / frameToPrint.getHeight());

        g2d.scale(scale, scale);

        frameToPrint.printAll(g);

        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "An error occurs, printing not completed.");
            }
        }
    }
}