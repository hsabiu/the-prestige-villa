package com.habib.prestige;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class DatePicker extends JXDatePicker {

    private static final long serialVersionUID = 1L;

    DatePicker() {

        setDate(Calendar.getInstance().getTime());
        setFormats(new SimpleDateFormat("dd/MM/yyyy"));

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    Date returnDate() {
        return getDate();
    }

    String returnStringDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dt = getDate();
        return dateFormat.format(dt);
    }
}
