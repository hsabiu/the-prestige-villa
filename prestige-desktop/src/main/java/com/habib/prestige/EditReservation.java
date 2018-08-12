package com.habib.prestige;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class EditReservation {

    static String resColumnData = null;
    private Reservation editRes = new Reservation();
    private DBConnection dbConnection;

    EditReservation() {

        dbConnection = new DBConnection();

        editRes.setTitle("Update Reservation");

        try {
            Connection con;
            Statement st;
            ResultSet rs;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

            rs.first();

            editRes.reservation_Number.setText(rs.getString("Res"));

            editRes.fnameValue.setText(rs.getString("First Name"));
            editRes.lnameValue.setText(rs.getString("Last Name"));
            editRes.genderValues.setSelectedItem(rs.getString("Gender"));
            editRes.phoneNumber.setText(rs.getString("Phone No"));
            editRes.countryValues.setSelectedItem(rs.getString("Country"));
            editRes.addressValue.setText(rs.getString("Address"));
            editRes.idType_Value.setSelectedItem(rs.getString("ID Type"));
            editRes.idNumber_Value.setText(rs.getString("ID No"));

            Date arr_Date = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("Arrival"));
            Date dep_Date = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("Departure"));

            editRes.arrivalDate_Value.setDate(arr_Date);
            editRes.departureDate_Value.setDate(dep_Date);

            editRes.noOfDays_Value.setText(rs.getString("Days"));

            editRes.roomType_Value.setSelectedItem(rs.getString("Room Type"));
            editRes.roomType_Value.setEnabled(false);

            Vector strRoomNo = new Vector();
            strRoomNo.addElement(rs.getString("Room No"));


            editRes.roomNumber_Value.setModel(new DefaultComboBoxModel(strRoomNo));

            editRes.roomRate_Value.setText(rs.getString("Room Rate"));
            editRes.totalPrizeValue.setText(rs.getString("Total Prize"));

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        JPanel down_ButtonPanel = new JPanel();
        down_ButtonPanel.setMaximumSize(new Dimension(550, 70));
        down_ButtonPanel.setPreferredSize(new Dimension(550, 70));
        down_ButtonPanel.setMinimumSize(new Dimension(550, 70));
        down_ButtonPanel.setLayout(new BoxLayout(down_ButtonPanel, BoxLayout.LINE_AXIS));
        down_ButtonPanel.add(Box.createHorizontalGlue());
        down_ButtonPanel.setOpaque(false);

        JButton add_Button = new JButton("Update");
        JButton cancel_Button = new JButton("Cancel");

        add_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    Connection con;
                    Statement st;
                    ResultSet rs;

                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

                    rs.first();

                    rs.updateString("Res", editRes.reservation_Number.getText());
                    rs.updateString("First Name", editRes.fnameValue.getText().toUpperCase());
                    rs.updateString("Last Name", editRes.lnameValue.getText().toUpperCase());
                    rs.updateString("Gender", editRes.genderValues.getSelectedItem().toString());
                    rs.updateString("Phone No", editRes.phoneNumber.getText());
                    rs.updateString("Country", editRes.countryValues.getSelectedItem().toString());
                    rs.updateString("Address", editRes.addressValue.getText().toUpperCase());
                    rs.updateString("ID Type", editRes.idType_Value.getSelectedItem().toString());
                    rs.updateString("ID No", editRes.idNumber_Value.getText().toUpperCase());

                    rs.updateString("Arrival", editRes.arrivalDate_Value.returnStringDate());
                    rs.updateString("Departure", editRes.departureDate_Value.returnStringDate());

                    rs.updateString("Days", editRes.noOfDays_Value.getText());
                    rs.updateString("Room Type", editRes.roomType_Value.getSelectedItem().toString());
                    rs.updateString("Room No", editRes.roomNumber_Value.getSelectedItem().toString());
                    rs.updateString("Room Rate", editRes.roomRate_Value.getText());
                    rs.updateString("Total Prize", editRes.totalPrizeValue.getText());

                    rs.updateRow();

                    JOptionPane.showMessageDialog(null, "Record Updated");

                    editRes.dispose();

                    MainFrame.centerPaneContainer.removeAll();
                    MainFrame.centerPaneContainer.add(MainFrame.allReservations());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancel_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == 0) editRes.dispose();
            }
        });

        down_ButtonPanel.add(add_Button);
        down_ButtonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        down_ButtonPanel.add(cancel_Button);

        editRes.add(down_ButtonPanel);
        editRes.add(Box.createRigidArea(new Dimension(0, 10)));
        editRes.setVisible(true);
    }
}
