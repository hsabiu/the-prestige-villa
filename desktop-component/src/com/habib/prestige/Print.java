package com.habib.prestige;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Print implements Printable, ActionListener {

    JFrame frameToPrint;

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        double scale = Math.min(pf.getImageableWidth()/frameToPrint.getWidth(), pf.getImageableWidth()/frameToPrint.getHeight());
        
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

    public Print(JFrame f) {
        frameToPrint = f;
    }
}