package com.habib.prestige;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class PospondReservation {

	static String resColumnData = null;

	PospondReservation() {
		final PospondButton postpond = new PospondButton();

		try {

			Connection con;
			Statement st;
			final ResultSet rs;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
			
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

			rs.first();

			postpond.reservation_NumberValue.setText(rs.getString("Res"));
			postpond.roomNoValue.setText(rs.getString("Room No"));
			postpond.roomCostValue.setText("=N= " + rs.getString("Room Rate"));

			Date dep_Date = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("Departure"));

			postpond.departureDateValue.setDate(dep_Date);

			postpond.setVisible(true);

			postpond.add_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Connection con;
						Statement st;
						final ResultSet rs;

						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
						
						st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						rs = st.executeQuery("SELECT * FROM 'reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

						rs.first();

						DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
						LocalDate departDate = dtf.parseLocalDate(rs.getString("Departure"));

						int dateBetween = Days.daysBetween(departDate, new DateTime(postpond.departureDateValue.returnDate()).toLocalDate()).getDays();
						int intPrize = Integer.parseInt(rs.getString("Room Rate"));
						int inResult = intPrize * dateBetween;
						Integer updatedDays = Integer.parseInt(rs.getString("Days")) + dateBetween;
						Integer updatePrize = Integer.parseInt(rs.getString("Total Prize")) + inResult;

						rs.updateString("Departure", postpond.departureDateValue.returnStringDate());
						rs.updateString("Days", updatedDays.toString());
						rs.updateString("Total Prize", updatePrize.toString());

						rs.updateRow();

						int choice;
						choice = JOptionPane.showConfirmDialog(null, "Balace : =N=" + inResult + " Do you want to pospond?", "Warning", JOptionPane.YES_NO_OPTION);
						if (choice == 0) {
							JOptionPane.showMessageDialog(null, "Reservation pospond");
							postpond.dispose();
						}

						MainFrame.centerPaneContainer.removeAll();
						MainFrame.centerPaneContainer.add(MainFrame.expected_Arrive_Depart());

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			postpond.cancel_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int choice;
					choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
					if (choice == 0) {
						postpond.dispose();
					}
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}