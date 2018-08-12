package com.habib.prestige;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

class PostponeReservation {

    static String resColumnData = null;

    PostponeReservation() {
        final PostponeButton postpone = new PostponeButton();
        DBConnection dbConnection = new DBConnection();

        try {

            ResultSet selectRs = dbConnection.getStatement().executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

            selectRs.first();

            postpone.reservation_NumberValue.setText(selectRs.getString("Res"));
            postpone.roomNoValue.setText(selectRs.getString("Room No"));
            postpone.roomCostValue.setText("=N= " + selectRs.getString("Room Rate"));

            Date dep_Date = new SimpleDateFormat("dd/MM/yyyy").parse(selectRs.getString("Departure"));

            postpone.departureDateValue.setDate(dep_Date);

            postpone.setVisible(true);

            postpone.add_Button.addActionListener(e -> {
                try {
                    ResultSet updateRs = dbConnection.getStatement().executeQuery("SELECT * FROM 'reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

                    updateRs.first();

                    DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
                    LocalDate departDate = dtf.parseLocalDate(updateRs.getString("Departure"));

                    int dateBetween = Days.daysBetween(departDate, new DateTime(postpone.departureDateValue.returnDate()).toLocalDate()).getDays();
                    int intPrize = Integer.parseInt(updateRs.getString("Room Rate"));
                    int inResult = intPrize * dateBetween;
                    Integer updatedDays = Integer.parseInt(updateRs.getString("Days")) + dateBetween;
                    Integer updatePrize = Integer.parseInt(updateRs.getString("Total Prize")) + inResult;

                    updateRs.updateString("Departure", postpone.departureDateValue.returnStringDate());
                    updateRs.updateString("Days", updatedDays.toString());
                    updateRs.updateString("Total Prize", updatePrize.toString());

                    updateRs.updateRow();

                    int choice;
                    choice = JOptionPane.showConfirmDialog(null, "Balace : =N=" + inResult + " Do you want to pospond?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        JOptionPane.showMessageDialog(null, "Reservation pospond");
                        postpone.dispose();
                    }

                    MainFrame.centerPaneContainer.removeAll();
                    MainFrame.centerPaneContainer.add(MainFrame.expected_Arrive_Depart());

                    selectRs.close();
                    updateRs.close();
                    dbConnection.getStatement().close();
                    dbConnection.getConnection().close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            postpone.cancel_Button.addActionListener(e -> {
                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    postpone.dispose();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}