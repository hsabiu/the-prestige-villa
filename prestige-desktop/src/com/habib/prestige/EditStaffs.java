package com.habib.prestige;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class EditStaffs {

	static String staffsColumnData;
	JLabel workDaysValue = new JLabel();
	String strWorkDaysValue;
	String s;
	NewStaff editStaff = new NewStaff();

	EditStaffs() {

		editStaff.setTitle("Update Staff Information");

		try {
			Connection con;
			Statement st;
			ResultSet rs;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
			
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM `users` WHERE `Staff ID`='" + staffsColumnData + "'");

			rs.first();

			s = rs.getString("Staff ID");

			editStaff.staffID_Value.setText(s);
			editStaff.fnameValue.setText(rs.getString("First Name"));
			editStaff.lnameValue.setText(rs.getString("Last Name"));
			editStaff.genderValues.setSelectedItem(rs.getString("Gender"));
			editStaff.phoneNumber.setText(rs.getString("Phone No"));
			editStaff.addressValue.setText(rs.getString("Address"));
			editStaff.userType.setSelectedItem(rs.getString("User Type"));
			editStaff.userNameValue.setText(s);

			strWorkDaysValue = rs.getString("Work Days");
			workDaysValue.setText(strWorkDaysValue);

			editStaff.passwordValue.setText(rs.getString("Password"));

			editStaff.strWorkDays = "";

			int i = 0;
			while (i < strWorkDaysValue.length()) {

				if (strWorkDaysValue.length() == 8) {
					editStaff.allDays.setSelected(true);
				}

				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Mon")) {
					editStaff.mon.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Tue")) {
					editStaff.tue.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Wed")) {
					editStaff.wed.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Thu")) {
					editStaff.thu.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Fri")) {
					editStaff.fri.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Sat")) {
					editStaff.sat.setSelected(true);
				}
				if (strWorkDaysValue.substring(i, (i + strWorkDaysValue.indexOf(","))).equals("Sun")) {
					editStaff.sun.setSelected(true);
				}

				i += strWorkDaysValue.indexOf(",") + 2;
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		JButton update_Button = new JButton("Update");
		update_Button.setMinimumSize(new Dimension(75, 26));
		update_Button.setPreferredSize(new Dimension(75, 26));
		update_Button.setMaximumSize(new Dimension(75, 26));
		update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Connection con;
					Statement st;
					ResultSet rs;

					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
					
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery("SELECT * FROM `users` WHERE `Staff ID`='" + staffsColumnData + "'");

					rs.first();

					rs.updateString("Staff ID", s);
					rs.updateString("Password", editStaff.passwordValue.getText());
					rs.updateString("First Name", editStaff.fnameValue.getText());
					rs.updateString("Last Name", editStaff.lnameValue.getText());
					rs.updateString("Gender", editStaff.genderValues.getSelectedItem().toString());
					rs.updateString("Phone No", editStaff.phoneNumber.getText());
					rs.updateString("Address", editStaff.addressValue.getText());
					rs.updateString("User Type", editStaff.userType.getSelectedItem().toString());

					if (editStaff.mon.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Mon, ";
					}
					if (editStaff.tue.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Tue, ";
					}
					if (editStaff.wed.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Wed, ";
					}
					if (editStaff.thu.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Thu, ";
					}
					if (editStaff.fri.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Fri, ";
					}
					if (editStaff.sat.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Sat, ";
					}
					if (editStaff.sun.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "Sun, ";
					}
					if (editStaff.allDays.isSelected()) {
						editStaff.strWorkDays = editStaff.strWorkDays + "All Days, ";
					}
					rs.updateString("Work Days", editStaff.strWorkDays.substring(0, editStaff.strWorkDays.length() - 2));

					rs.updateRow();

					JOptionPane.showMessageDialog(null, "Record Updated");

					editStaff.dispose();

					MainFrame.centerPaneContainer.removeAll();
					MainFrame.centerPaneContainer.add(MainFrame.adminCenterPane());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		editStaff.buttonPanel.removeAll();
		editStaff.buttonPanel.add(Box.createHorizontalGlue());
		editStaff.buttonPanel.add(update_Button);
		editStaff.buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		editStaff.buttonPanel.add(editStaff.cancel_Button);

		editStaff.setVisible(true);
	}
}
