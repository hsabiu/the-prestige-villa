package com.habib.prestige;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class AddRooms extends JFrame {
	private static final long serialVersionUID = 1L;

	Color roomsColor = new Color(212, 216, 162);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	AddRooms() {
		setSize(400, 450);
		setTitle("New Room");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		getContentPane().setBackground(roomsColor);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setShape(new RoundRectangle2D.Double(20, 20, 360, 400, 20, 20));

		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/backGround.png"));

		setContentPane(new ImagePanel(backGround.getImage()));

		JPanel imagePanel = new JPanel();
		imagePanel.setMaximumSize(new Dimension(300, 90));
		imagePanel.setPreferredSize(new Dimension(300, 90));
		imagePanel.setMaximumSize(new Dimension(300, 90));
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setOpaque(false);

		JLabel smallLogo = new JLabel(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/myLogo1.png")));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		imagePanel.add(smallLogo, c2);

		JPanel roomInfo = new JPanel();
		roomInfo.setMaximumSize(new Dimension(350, 250));
		roomInfo.setPreferredSize(new Dimension(350, 250));
		roomInfo.setMaximumSize(new Dimension(350, 250));
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ROOM INFORMATION ");
		border.setTitleJustification(TitledBorder.CENTER);
		roomInfo.setBorder(border);
		roomInfo.setLayout(new GridBagLayout());
		roomInfo.setOpaque(false);

		JLabel roomType = new JLabel("Room Type");
		roomType.setFont(Reservation.formFont);
		roomType.setForeground(Reservation.textColor);
		roomType.setPreferredSize(new Dimension(100, 20));

		String[] roomTypes = { "Diplomatic", "Executive", "Super Executive" };
		final JComboBox roomTypeValues = new JComboBox(roomTypes);
		roomTypeValues.setPreferredSize(new Dimension(100, 30));
		roomTypeValues.setSelectedItem(null);

		JLabel roomNo = new JLabel("Room No");
		roomNo.setFont(Reservation.formFont);
		roomNo.setForeground(Reservation.textColor);
		roomNo.setPreferredSize(new Dimension(100, 20));

		final JLabel roomNoValue = new JLabel();
		roomNoValue.setPreferredSize(new Dimension(100, 20));
		roomNoValue.setEnabled(false);

		JLabel roomPrize = new JLabel("Prize (=N=)");
		roomPrize.setFont(Reservation.formFont);
		roomPrize.setForeground(Reservation.textColor);
		roomPrize.setPreferredSize(new Dimension(100, 20));

		final JLabel roomPrizeValue = new JLabel();
		roomPrizeValue.setPreferredSize(new Dimension(100, 20));
		roomPrizeValue.setEnabled(false);

		roomTypeValues.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {

					Connection con;
					Statement st;
					ResultSet rs;

					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery("SELECT * FROM `rooms` WHERE `Room Type`='" + roomTypeValues.getSelectedItem().toString() + "' ORDER BY `Room No` DESC");

					rs.first();

					DecimalFormat df = new DecimalFormat("000");

					String s = rs.getString("Room No");
					String s1 = s.substring(0, 3);
					int intValue = Integer.parseInt(s1) + 1;
					String s2 = df.format(intValue);

					roomNoValue.setText(s2);
					roomPrizeValue.setText(rs.getString("Prize (=N=)"));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		c.gridx = 0;
		c.gridy = 0;
		roomInfo.add(roomType, c);

		c.gridx = 1;
		c.gridy = 0;
		roomInfo.add(roomTypeValues, c);

		c.gridx = 0;
		c.gridy = 1;
		roomInfo.add(roomNo, c);

		c.gridx = 1;
		c.gridy = 1;
		roomInfo.add(roomNoValue, c);

		c.gridx = 0;
		c.gridy = 2;
		roomInfo.add(roomPrize, c);

		c.gridx = 1;
		c.gridy = 2;
		roomInfo.add(roomPrizeValue, c);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(350, 50));
		buttonPanel.setPreferredSize(new Dimension(350, 50));
		buttonPanel.setMaximumSize(new Dimension(350, 50));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setOpaque(false);

		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice;
				choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
				if (choice == 0) dispose();
			}
		});

		JButton add_Button = new JButton("Add");
		add_Button.setPreferredSize(cancel_Button.getPreferredSize());
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (roomTypeValues.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "You must select a room type before you can add.");
				} else {

					try {

						Class.forName("com.mysql.jdbc.Driver");
						Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

						Statement stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = stmt.executeQuery("SELECT * FROM `rooms` ORDER BY `Room No`");

						rs.moveToInsertRow();

						rs.updateString("Room Type", roomTypeValues.getSelectedItem().toString());
						rs.updateString("Prize (=N=)", roomPrizeValue.getText());
						rs.updateString("Status", "Available");

						if (roomTypeValues.getSelectedItem().toString().equals("Diplomatic")) {
							rs.updateString("Room No", roomNoValue.getText() + "D");
							rs.updateString("Available Services", "2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV");
						} else if (roomTypeValues.getSelectedItem().toString().equals("Super Executive")) {
							rs.updateString("Room No", roomNoValue.getText() + "S");
							rs.updateString("Available Services", "1 Room, Living Room, Bathroom, AC, Fridge, DSTV");
						} else if (roomTypeValues.getSelectedItem().toString().equals("Executive")) {
							rs.updateString("Room No", roomNoValue.getText() + "E");
							rs.updateString("Available Services", "1 Room, Bathroom, AC, Fridge, DSTV");
						}

						rs.insertRow();

						JOptionPane.showMessageDialog(null, "New room added");
						dispose();

						rs.close();
						stmt.close();

						MainFrame.centerPaneContainer.removeAll();
						MainFrame.centerPaneContainer.add(MainFrame.adminCenterPane());

						stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						rs = stmt.executeQuery("SELECT * FROM `rooms` ORDER BY `Room No`");

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		buttonPanel.add(add_Button);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(cancel_Button);

		add(Box.createRigidArea(new Dimension(0, 10)));
		add(imagePanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(roomInfo);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(buttonPanel);

		setVisible(true);

	}
}