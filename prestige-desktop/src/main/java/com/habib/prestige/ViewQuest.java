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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class ViewQuest{
	static String viewColumnData = null;

	public JFrame ViewQuest;
	JLabel quest_Number;
	JLabel reservation_Number;
	JLabel fnameValue;
	JLabel lnameValue;
	JLabel genderValues;
	JLabel phoneNumber;
	JLabel addressValue;
	JLabel countryValues;
	JLabel idNumber_Value;
	JLabel idType_Value;
	JLabel arrivalDate_Value;
	JLabel departureDate_Value;
	JLabel noOfDays_Value;
	JLabel roomNumber_Value;
	JLabel roomType_Value;
	JLabel roomRate_Value;
	JLabel totalPrizeValue;
	JLabel resDate_Value;
	JButton closeButton;
	JButton printButton;
	
	Color viewQuestColor = new Color(212, 216, 162);

	public ViewQuest() {
		
		ViewQuest = new JFrame();

		ViewQuest.setSize(750, 650);
		ViewQuest.setTitle("View Details");
		ViewQuest.setLocationRelativeTo(null);
		ViewQuest.setResizable(false);
		ViewQuest.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ViewQuest.setLayout(new BoxLayout(ViewQuest.getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) ViewQuest.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		ViewQuest.setUndecorated(true);
		ViewQuest.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		ViewQuest.setShape(new RoundRectangle2D.Double(20, 20, 710, 620, 20, 20));
		
		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/icons/backGround.png"));
		
		ViewQuest.setContentPane(new ImagePanel(backGround.getImage()));
		
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
		
		JPanel questNumberPanel = new JPanel();
		questNumberPanel.setLayout(new GridBagLayout());
		questNumberPanel.setMaximumSize(new Dimension(690, 70));
		questNumberPanel.setPreferredSize(new Dimension(690, 70));
		questNumberPanel.setMaximumSize(new Dimension(690, 70));
		questNumberPanel.setOpaque(false);

		JLabel questNo = new JLabel("RES NO: ");
		questNo.setFont(new Font("Costantia", Font.BOLD, 18));

		quest_Number = new JLabel();
		quest_Number.setForeground(new Color(163, 175, 39));
		quest_Number.setFont(new Font("Costantia", Font.BOLD, 18));;

		JPanel personelInfo = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " PERSONAL INFORMATION ");
		border.setTitleJustification(TitledBorder.CENTER);
		personelInfo.setBorder(border);
		personelInfo.setMaximumSize(new Dimension(690, 400));
		personelInfo.setPreferredSize(new Dimension(690, 400));
		personelInfo.setMaximumSize(new Dimension(690, 400));
		personelInfo.setLayout(new GridBagLayout());
		personelInfo.setOpaque(false);

		JLabel departureDate = new JLabel("Departure Date :");
		departureDate.setFont(MainFrame.addInfoFont);

		addressValue = new JLabel();
		addressValue.setPreferredSize(new Dimension(200, 20));
		addressValue.setFont(MainFrame.addInfoFont);
		addressValue.setForeground(Color.RED);

		departureDate_Value = new JLabel();
		departureDate_Value.setPreferredSize(new Dimension(200, 20));
		departureDate_Value.setFont(MainFrame.addInfoFont);
		departureDate_Value.setForeground(Color.RED);

		JLabel firstName = new JLabel("First Name :");
		firstName.setPreferredSize(departureDate.getPreferredSize());
		firstName.setFont(MainFrame.addInfoFont);

		fnameValue = new JLabel();
		fnameValue.setPreferredSize(new Dimension(200, 20));
		fnameValue.setFont(MainFrame.addInfoFont);
		fnameValue.setForeground(Color.RED);

		JLabel lastName = new JLabel("Last Name :");
		lastName.setPreferredSize(departureDate.getPreferredSize());
		lastName.setFont(MainFrame.addInfoFont);

		lnameValue = new JLabel();
		lnameValue.setPreferredSize(new Dimension(200, 20));
		lnameValue.setFont(MainFrame.addInfoFont);
		lnameValue.setForeground(Color.RED);

		JLabel gender = new JLabel("Gender :");
		gender.setPreferredSize(departureDate.getPreferredSize());
		gender.setFont(MainFrame.addInfoFont);

		genderValues = new JLabel();
		genderValues.setPreferredSize(new Dimension(200, 20));
		genderValues.setFont(MainFrame.addInfoFont);
		genderValues.setForeground(Color.RED);

		JLabel phone = new JLabel("Phone No. :");
		phone.setPreferredSize(departureDate.getPreferredSize());
		phone.setFont(MainFrame.addInfoFont);

		phoneNumber = new JLabel();
		phoneNumber.setPreferredSize(new Dimension(200, 20));
		phoneNumber.setFont(MainFrame.addInfoFont);
		phoneNumber.setForeground(Color.RED);

		JLabel address = new JLabel("Address :");
		address.setPreferredSize(departureDate.getPreferredSize());
		address.setFont(MainFrame.addInfoFont);

		JLabel country = new JLabel("Country :");
		country.setPreferredSize(departureDate.getPreferredSize());
		country.setFont(MainFrame.addInfoFont);

		countryValues = new JLabel();
		countryValues.setPreferredSize(new Dimension(200, 20));
		countryValues.setFont(MainFrame.addInfoFont);
		countryValues.setForeground(Color.RED);

		JLabel idNumber = new JLabel("ID Number :");
		idNumber.setPreferredSize(departureDate.getPreferredSize());
		idNumber.setFont(MainFrame.addInfoFont);

		idNumber_Value = new JLabel();
		idNumber_Value.setPreferredSize(new Dimension(200, 20));
		idNumber_Value.setFont(MainFrame.addInfoFont);
		idNumber_Value.setForeground(Color.RED);

		JLabel id = new JLabel("ID Type :");
		id.setPreferredSize(departureDate.getPreferredSize());
		id.setFont(MainFrame.addInfoFont);

		idType_Value = new JLabel();
		idType_Value.setPreferredSize(new Dimension(200, 20));
		idType_Value.setFont(MainFrame.addInfoFont);
		idType_Value.setForeground(Color.RED);

		JLabel arrivalDate = new JLabel("Arrival Date :");
		arrivalDate.setPreferredSize(departureDate.getPreferredSize());
		arrivalDate.setFont(MainFrame.addInfoFont);

		arrivalDate_Value = new JLabel();
		arrivalDate_Value.setPreferredSize(new Dimension(200, 20));
		arrivalDate_Value.setFont(MainFrame.addInfoFont);
		arrivalDate_Value.setForeground(Color.RED);

		JLabel noOfDays = new JLabel("No. Of Days :");
		noOfDays.setPreferredSize(departureDate.getPreferredSize());
		noOfDays.setFont(MainFrame.addInfoFont);

		noOfDays_Value = new JLabel();
		noOfDays_Value.setPreferredSize(new Dimension(200, 20));
		noOfDays_Value.setFont(MainFrame.addInfoFont);
		noOfDays_Value.setForeground(Color.RED);

		JLabel roomNumber = new JLabel("Room No :");
		roomNumber.setPreferredSize(departureDate.getPreferredSize());
		roomNumber.setFont(MainFrame.addInfoFont);
		
		roomNumber_Value = new JLabel();
		roomNumber_Value.setPreferredSize(new Dimension(200, 20));
		roomNumber_Value.setFont(MainFrame.addInfoFont);
		roomNumber_Value.setForeground(Color.RED);

		JLabel roomType = new JLabel("Room Type :");
		roomType.setPreferredSize(departureDate.getPreferredSize());
		roomType.setFont(MainFrame.addInfoFont);

		roomType_Value = new JLabel();
		roomType_Value.setPreferredSize(new Dimension(200, 20));
		roomType_Value.setFont(MainFrame.addInfoFont);
		roomType_Value.setForeground(Color.RED);

		JLabel roomRate = new JLabel("Rate Per Night :");
		roomRate.setPreferredSize(departureDate.getPreferredSize());
		roomRate.setFont(MainFrame.addInfoFont);

		roomRate_Value = new JLabel();
		roomRate_Value.setPreferredSize(new Dimension(200, 20));
		roomRate_Value.setFont(MainFrame.addInfoFont);
		roomRate_Value.setForeground(Color.RED);

		JLabel totalPrize = new JLabel("Total Prize :");
		totalPrize.setPreferredSize(departureDate.getPreferredSize());
		totalPrize.setFont(MainFrame.addInfoFont);

		totalPrizeValue = new JLabel();
		totalPrizeValue.setPreferredSize(new Dimension(200, 20));
		totalPrizeValue.setFont(MainFrame.addInfoFont);
		totalPrizeValue.setForeground(Color.RED);

		JLabel resDate = new JLabel("Res Date :");
		resDate.setPreferredSize(departureDate.getPreferredSize());
		resDate.setFont(MainFrame.addInfoFont);

		resDate_Value = new JLabel();
		resDate_Value.setPreferredSize(new Dimension(200, 20));
		resDate_Value.setFont(MainFrame.addInfoFont);
		resDate_Value.setForeground(Color.RED);

		try {
			Connection con;
			Statement st;
			ResultSet rs;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + viewColumnData + "' ORDER BY `Res`");

			rs.first();

			quest_Number.setText(rs.getString("Res"));
			fnameValue.setText(rs.getString("First Name"));
			lnameValue.setText(rs.getString("Last Name"));
			genderValues.setText(rs.getString("Gender"));
			phoneNumber.setText(rs.getString("Phone No"));
			countryValues.setText(rs.getString("Country"));
			addressValue.setText(rs.getString("Address"));
			idType_Value.setText(rs.getString("ID Type"));
			idNumber_Value.setText(rs.getString("ID No"));
			arrivalDate_Value.setText(rs.getString("Arrival"));
			departureDate_Value.setText(rs.getString("Departure"));
			noOfDays_Value.setText(rs.getString("Days"));
			roomNumber_Value.setText(rs.getString("Room No"));
			roomType_Value.setText(rs.getString("Room Type"));
			roomRate_Value.setText("=N= " + rs.getString("Room Rate"));
			totalPrizeValue.setText("=N= " + rs.getString("Total Prize"));
			resDate_Value.setText(rs.getString("RDate"));

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		questNumberPanel.add(questNo, c);

		c.gridx = 1;
		c.gridy = 0;
		questNumberPanel.add(quest_Number, c);

		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(5, 5, 5, 5);
		c1.gridx = 0;
		c1.gridy = 0;
		personelInfo.add(firstName, c1);

		c1.gridx = 1;
		c1.gridy = 0;
		personelInfo.add(fnameValue, c1);

		c1.gridx = 2;
		c1.gridy = 0;
		personelInfo.add(lastName, c1);

		c1.gridx = 3;
		c1.gridy = 0;
		personelInfo.add(lnameValue, c1);

		c1.gridx = 0;
		c1.gridy = 1;
		personelInfo.add(gender, c1);

		c1.gridx = 1;
		c1.gridy = 1;
		personelInfo.add(genderValues, c1);

		c1.gridx = 2;
		c1.gridy = 1;
		personelInfo.add(phone, c1);

		c1.gridx = 3;
		c1.gridy = 1;
		personelInfo.add(phoneNumber, c1);

		c1.gridx = 0;
		c1.gridy = 2;
		personelInfo.add(country, c1);

		c1.gridx = 1;
		c1.gridy = 2;
		personelInfo.add(countryValues, c1);

		c1.gridx = 2;
		c1.gridy = 2;
		personelInfo.add(address, c1);

		c1.gridx = 3;
		c1.gridy = 2;
		personelInfo.add(addressValue, c1);

		c1.gridx = 0;
		c1.gridy = 3;
		personelInfo.add(id, c1);

		c1.gridx = 1;
		c1.gridy = 3;
		personelInfo.add(idType_Value, c1);

		c1.gridx = 2;
		c1.gridy = 3;
		personelInfo.add(idNumber, c1);

		c1.gridx = 3;
		c1.gridy = 3;
		personelInfo.add(idNumber_Value, c1);

		c1.gridx = 0;
		c1.gridy = 4;
		personelInfo.add(arrivalDate, c1);

		c1.gridx = 1;
		c1.gridy = 4;
		personelInfo.add(arrivalDate_Value, c1);

		c1.gridx = 2;
		c1.gridy = 4;
		personelInfo.add(departureDate, c1);

		c1.gridx = 3;
		c1.gridy = 4;
		personelInfo.add(departureDate_Value, c1);

		c1.gridx = 0;
		c1.gridy = 5;
		personelInfo.add(roomNumber, c1);

		c1.gridx = 1;
		c1.gridy = 5;
		personelInfo.add(roomNumber_Value, c1);

		c1.gridx = 2;
		c1.gridy = 5;
		personelInfo.add(roomType, c1);

		c1.gridx = 3;
		c1.gridy = 5;
		personelInfo.add(roomType_Value, c1);

		c1.gridx = 0;
		c1.gridy = 6;
		personelInfo.add(noOfDays, c1);

		c1.gridx = 1;
		c1.gridy = 6;
		personelInfo.add(noOfDays_Value, c1);

		c1.gridx = 2;
		c1.gridy = 6;
		personelInfo.add(roomRate, c1);

		c1.gridx = 3;
		c1.gridy = 6;
		personelInfo.add(roomRate_Value, c1);

		c1.gridx = 0;
		c1.gridy = 7;
		personelInfo.add(totalPrize, c1);

		c1.gridx = 1;
		c1.gridy = 7;
		personelInfo.add(totalPrizeValue, c1);

		c1.gridx = 2;
		c1.gridy = 7;
		personelInfo.add(resDate, c1);

		c1.gridx = 3;
		c1.gridy = 7;
		personelInfo.add(resDate_Value, c1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(690,50));
		buttonPanel.setPreferredSize(new Dimension(690, 50));
		buttonPanel.setMaximumSize(new Dimension(690, 50));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setOpaque(false);
		
		printButton = new JButton("Print");
		printButton.setMinimumSize(new Dimension(100, 30));
		printButton.setPreferredSize(new Dimension(100, 30));
		printButton.setMaximumSize(new Dimension(100, 30));
		printButton.addActionListener(new Print(ViewQuest));
		

		closeButton = new JButton("Close");
		closeButton.setMinimumSize(new Dimension(100, 30));
		closeButton.setPreferredSize(new Dimension(100, 30));
		closeButton.setMaximumSize(new Dimension(100, 30));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewQuest.dispose();
			}
		});
		
		buttonPanel.add(printButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(closeButton);

		ViewQuest.add(Box.createRigidArea(new Dimension(0, 30)));
		ViewQuest.add(imagePanel);
		ViewQuest.add(questNumberPanel);
		ViewQuest.add(Box.createRigidArea(new Dimension(0, 5)));
		ViewQuest.add(personelInfo);
		ViewQuest.add(Box.createRigidArea(new Dimension(0, 10)));
		ViewQuest.add(buttonPanel);
		ViewQuest.add(Box.createRigidArea(new Dimension(0, 30)));

		ViewQuest.setVisible(true);
	}
}
