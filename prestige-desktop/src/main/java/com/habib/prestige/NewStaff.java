package com.habib.prestige;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

class NewStaff extends JFrame {
	private static final long serialVersionUID = 1L;

	public JPanel buttonPanel;
	public JLabel staffID_Value = null;
	public JTextField fnameValue = null;
	public JTextField lnameValue = null;
	public JTextArea addressValue = null;
	public JFormattedTextField phoneNumber = null;
	public JLabel userNameValue;
	public JLabel passwordValue;
	@SuppressWarnings("rawtypes")
	public JComboBox userType;
	@SuppressWarnings("rawtypes")
	public JComboBox genderValues = null;
	public JButton add_Button;
	public JButton cancel_Button;
	public GridBagConstraints c2 = new GridBagConstraints();
	public JPanel userTypePanel = new JPanel();
	public Color addStaffColor = new Color(212, 216, 162);
	public JCheckBox allDays;
	public JCheckBox mon;
	public JCheckBox tue;
	public JCheckBox wed;
	public JCheckBox thu;
	public JCheckBox fri;
	public JCheckBox sat;
	public JCheckBox sun;
	public String strWorkDays;

	static String newStaffID;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	NewStaff() {
		setSize(600, 700);
		setTitle("Add New Staff");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		getContentPane().setBackground(addStaffColor);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setShape(new RoundRectangle2D.Double(20, 20, 570, 680, 20, 20));
		
		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/icons/backGround.png"));
		
		setContentPane(new ImagePanel(backGround.getImage()));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setMaximumSize(new Dimension(550, 90));
		imagePanel.setPreferredSize(new Dimension(550, 90));
		imagePanel.setMaximumSize(new Dimension(550, 90));
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setOpaque(false);

		JLabel smallLogo = new JLabel(new ImageIcon(LoginWindow.class.getResource("/icons/myLogo1.png")));

		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 0;
		imagePanel.add(smallLogo, c5);

		JPanel staffNumberPanel = new JPanel();
		staffNumberPanel.setLayout(new GridBagLayout());
		staffNumberPanel.setOpaque(false);

		try {

			DecimalFormat df = new DecimalFormat("0000");

			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `Staff ID` FROM `users` ORDER BY `Staff ID` DESC");

			rs.next();

			int staffIdNumber = Integer.parseInt(rs.getString("Staff ID")) + 1;

			if (staffIdNumber < 10) newStaffID = df.format(staffIdNumber).toString();
			else if ((staffIdNumber >= 10) && (staffIdNumber < 100)) newStaffID = df.format(staffIdNumber).toString();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JLabel staffID = new JLabel("STAFF ID ");
		staffID.setFont(new Font("Costantia", Font.BOLD, 18));

		staffID_Value = new JLabel(newStaffID);
		staffID_Value.setForeground(new Color(163, 175, 39));
		staffID_Value.setFont(new Font("Costantia", Font.BOLD, 18));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		staffNumberPanel.add(staffID, c);

		c.gridx = 1;
		c.gridy = 0;
		staffNumberPanel.add(staffID_Value, c);

		JPanel staffPersonelInfo = new JPanel();
		staffPersonelInfo.setMaximumSize(new Dimension(550, 200));
		staffPersonelInfo.setPreferredSize(new Dimension(550, 200));
		staffPersonelInfo.setMaximumSize(new Dimension(550, 200));
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " PERSONAL INFORMATION ");
		border.setTitleJustification(TitledBorder.CENTER);
		staffPersonelInfo.setBorder(border);
		staffPersonelInfo.setLayout(new GridBagLayout());
		staffPersonelInfo.setOpaque(false);

		JLabel firstName = new JLabel("First Name");
		firstName.setFont(Reservation.formFont);
		firstName.setForeground(Reservation.textColor);

		fnameValue = new JTextField(12);
		fnameValue.setBackground(MainFrame.textFieldColor);

		JLabel lastName = new JLabel("Last Name");
		lastName.setFont(Reservation.formFont);
		lastName.setForeground(Reservation.textColor);
		lastName.setMinimumSize(firstName.getPreferredSize());
		lastName.setPreferredSize(firstName.getPreferredSize());
		lastName.setMaximumSize(firstName.getPreferredSize());

		lnameValue = new JTextField(12);
		lnameValue.setBackground(MainFrame.textFieldColor);

		JLabel gender = new JLabel("Gender");
		gender.setFont(Reservation.formFont);
		gender.setForeground(Reservation.textColor);
		gender.setMinimumSize(firstName.getPreferredSize());
		gender.setPreferredSize(firstName.getPreferredSize());
		gender.setMaximumSize(firstName.getPreferredSize());

		String[] g_Values = { "Male", "Female" };
		genderValues = new JComboBox(g_Values);

		JLabel phone = new JLabel("Phone No.");
		phone.setFont(Reservation.formFont);
		phone.setForeground(Reservation.textColor);
		phone.setMinimumSize(firstName.getPreferredSize());
		phone.setPreferredSize(firstName.getPreferredSize());
		phone.setMaximumSize(firstName.getPreferredSize());
		
		MaskFormatter mask = null;
		
		try{
			mask = new MaskFormatter("###########");			
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		phoneNumber = new JFormattedTextField(mask);
		phoneNumber.setPreferredSize(lnameValue.getPreferredSize());
		phoneNumber.setBackground(MainFrame.textFieldColor);

		JLabel address = new JLabel("Address");
		address.setFont(Reservation.formFont);
		address.setForeground(Reservation.textColor);
		address.setMinimumSize(firstName.getPreferredSize());
		address.setPreferredSize(firstName.getPreferredSize());
		address.setMaximumSize(firstName.getPreferredSize());

		addressValue = new JTextArea(5, 12);
		addressValue.setLineWrap(true);
		addressValue.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		addressValue.setBackground(MainFrame.textFieldColor);

		JLabel user = new JLabel("User Type");
		user.setFont(Reservation.formFont);
		user.setForeground(Reservation.textColor);
		user.setMinimumSize(firstName.getPreferredSize());
		user.setPreferredSize(firstName.getPreferredSize());
		user.setMaximumSize(firstName.getPreferredSize());

		String[] userTypeValues = { "Manager", "Receptionist" };

		userType = new JComboBox(userTypeValues);

		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(5, 5, 5, 5);
		c1.gridx = 0;
		c1.gridy = 0;
		staffPersonelInfo.add(firstName, c1);

		c1.gridx = 1;
		c1.gridy = 0;
		staffPersonelInfo.add(fnameValue, c1);

		c1.gridx = 2;
		c1.gridy = 0;
		staffPersonelInfo.add(lastName, c1);

		c1.gridx = 3;
		c1.gridy = 0;
		staffPersonelInfo.add(lnameValue, c1);

		c1.gridx = 0;
		c1.gridy = 1;
		staffPersonelInfo.add(gender, c1);

		c1.gridx = 1;
		c1.gridy = 1;
		staffPersonelInfo.add(genderValues, c1);

		c1.gridx = 2;
		c1.gridy = 1;
		staffPersonelInfo.add(phone, c1);

		c1.gridx = 3;
		c1.gridy = 1;
		staffPersonelInfo.add(phoneNumber, c1);

		c1.gridx = 0;
		c1.gridy = 2;
		staffPersonelInfo.add(address, c1);

		c1.gridx = 1;
		c1.gridy = 2;
		staffPersonelInfo.add(addressValue, c1);

		c1.gridx = 2;
		c1.gridy = 2;
		staffPersonelInfo.add(user, c1);

		c1.gridx = 3;
		c1.gridy = 2;
		staffPersonelInfo.add(userType, c1);

		userTypePanel.setMaximumSize(new Dimension(550,150));
		userTypePanel.setPreferredSize(new Dimension(550, 150));
		userTypePanel.setMaximumSize(new Dimension(550, 150));
		TitledBorder userTypePanelBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " WORK DAYS ");
		userTypePanelBorder.setTitleJustification(TitledBorder.CENTER);
		userTypePanel.setBorder(userTypePanelBorder);
		userTypePanel.setLayout(new GridBagLayout());
		userTypePanel.setOpaque(false);

		wed = new JCheckBox("Wednesday");
		wed.setFont(Reservation.formFont);
		wed.setBackground(addStaffColor);

		mon = new JCheckBox("Monday");
		mon.setFont(Reservation.formFont);
		mon.setPreferredSize(wed.getPreferredSize());
		mon.setBackground(addStaffColor);

		tue = new JCheckBox("Tuesday");
		tue.setFont(Reservation.formFont);
		tue.setPreferredSize(wed.getPreferredSize());
		tue.setBackground(addStaffColor);

		thu = new JCheckBox("Thursday");
		thu.setFont(Reservation.formFont);
		thu.setPreferredSize(wed.getPreferredSize());
		thu.setBackground(addStaffColor);

		fri = new JCheckBox("Friday");
		fri.setFont(Reservation.formFont);
		fri.setPreferredSize(wed.getPreferredSize());
		fri.setBackground(addStaffColor);

		sat = new JCheckBox("Saturday");
		sat.setFont(Reservation.formFont);
		sat.setPreferredSize(wed.getPreferredSize());
		sat.setBackground(addStaffColor);

		sun = new JCheckBox("Sunday");
		sun.setFont(Reservation.formFont);
		sun.setPreferredSize(wed.getPreferredSize());
		sun.setBackground(addStaffColor);

		allDays = new JCheckBox("All Days");
		allDays.setFont(Reservation.formFont);
		allDays.setPreferredSize(wed.getPreferredSize());
		allDays.setBackground(addStaffColor);

		c2.insets = new Insets(5, 5, 5, 5);

		c2.gridx = 0;
		c2.gridy = 0;
		userTypePanel.add(mon, c2);

		c2.gridx = 1;
		c2.gridy = 0;
		userTypePanel.add(tue, c2);

		c2.gridx = 2;
		c2.gridy = 0;
		userTypePanel.add(wed, c2);

		c2.gridx = 0;
		c2.gridy = 1;
		userTypePanel.add(thu, c2);

		c2.gridx = 1;
		c2.gridy = 1;
		userTypePanel.add(fri, c2);

		c2.gridx = 2;
		c2.gridy = 1;
		userTypePanel.add(sat, c2);

		c2.gridx = 0;
		c2.gridy = 2;
		userTypePanel.add(sun, c2);

		c2.gridx = 1;
		c2.gridy = 2;
		userTypePanel.add(allDays, c2);

		JPanel userAccoutPanel = new JPanel();
		userAccoutPanel.setMaximumSize(new Dimension(550,100));
		userAccoutPanel.setPreferredSize(new Dimension(550, 100));
		userAccoutPanel.setMaximumSize(new Dimension(550, 100));
		TitledBorder userAccoutPanelBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " USER ACCOUNT ");
		userAccoutPanelBorder.setTitleJustification(TitledBorder.CENTER);
		userAccoutPanel.setBorder(userAccoutPanelBorder);
		userAccoutPanel.setLayout(new GridBagLayout());
		userAccoutPanel.setOpaque(false);

		JLabel userName = new JLabel("User ID");
		userName.setFont(Reservation.formFont);
		
		JLabel password = new JLabel("Password");
		password.setFont(Reservation.formFont);

		userNameValue = new JLabel(newStaffID);
		userNameValue.setForeground(Reservation.textColor);
		userNameValue.setFont(new Font("Costantia", Font.BOLD, 18));

		passwordValue = new JLabel(RandomPassword.generateRondomPassword());
		passwordValue.setForeground(Reservation.textColor);
		passwordValue.setFont(new Font("Costantia", Font.BOLD, 18));

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(5, 5, 5, 5);

		c3.gridx = 0;
		c3.gridy = 0;
		userAccoutPanel.add(userName, c3);

		c3.gridx = 1;
		c3.gridy = 0;
		userAccoutPanel.add(userNameValue, c3);

		c3.gridx = 2;
		c3.gridy = 0;
		userAccoutPanel.add(password, c3);

		c3.gridx = 3;
		c3.gridy = 0;
		userAccoutPanel.add(passwordValue, c3);

		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(550,50));
		buttonPanel.setPreferredSize(new Dimension(550, 50));
		buttonPanel.setMaximumSize(new Dimension(550, 50));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setOpaque(false);

		cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice;
				choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
				if (choice == 0) dispose();
			}
		});

		add_Button = new JButton("Add");
		add_Button.setPreferredSize(cancel_Button.getPreferredSize());
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((fnameValue.getText()).equals("") || (lnameValue.getText()).equals("") || (phoneNumber.getText()).equals("") || (addressValue.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "Form must be filled with all values before it can be submitted");
				} else {

					try {

						Class.forName("com.mysql.jdbc.Driver");
						Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

						Statement stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = stmt.executeQuery("SELECT * FROM `users` ORDER BY `Staff ID`");

						rs.moveToInsertRow();

						rs.updateString("Staff ID", userNameValue.getText());
						rs.updateString("Password", passwordValue.getText());
						rs.updateString("First Name", fnameValue.getText().toUpperCase());
						rs.updateString("Last Name", lnameValue.getText().toUpperCase());
						rs.updateString("Address", addressValue.getText().toUpperCase());
						rs.updateString("Phone No", phoneNumber.getText());
						rs.updateString("Gender", genderValues.getSelectedItem().toString());
						rs.updateString("User Type", userType.getSelectedItem().toString());

						strWorkDays = "";
						if (mon.isSelected()) {
							strWorkDays = strWorkDays + "Mon, ";
						}
						if (tue.isSelected()) {
							strWorkDays = strWorkDays + "Tue, ";
						}
						if (wed.isSelected()) {
							strWorkDays = strWorkDays + "Wed, ";
						}
						if (thu.isSelected()) {
							strWorkDays = strWorkDays + "Thu, ";
						}
						if (fri.isSelected()) {
							strWorkDays = strWorkDays + "Fri, ";
						}
						if (sat.isSelected()) {
							strWorkDays = strWorkDays + "Sat, ";
						}
						if (sun.isSelected()) {
							strWorkDays = strWorkDays + "Sun, ";
						}
						if (allDays.isSelected()) {
							strWorkDays = strWorkDays + "All Days, ";
						}

						rs.updateString("Work Days", strWorkDays.substring(0, strWorkDays.length() - 2));

						rs.insertRow();

						JOptionPane.showMessageDialog(null, "New user added");
						
						dispose();

						MainFrame.centerPaneContainer.removeAll();
						MainFrame.centerPaneContainer.add(MainFrame.adminCenterPane());

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		buttonPanel.add(add_Button);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(cancel_Button);

		add(Box.createRigidArea(new Dimension(0, 20)));
		add(imagePanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(staffNumberPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(staffPersonelInfo);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(userTypePanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(userAccoutPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(buttonPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));

		setVisible(false);

	}
}
