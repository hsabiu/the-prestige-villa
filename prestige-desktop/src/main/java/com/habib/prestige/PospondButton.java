package com.habib.prestige;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

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

class PospondButton extends JFrame {
	private static final long serialVersionUID = 1L;

	JLabel reservation_NumberValue;
	JLabel roomNoValue;
	JLabel roomCostValue;
	static DatePicker arriveDateValue;
	DatePicker departureDateValue;
	JButton add_Button;
	JButton cancel_Button;
	Color postpondColor = new Color(212, 216, 162);

	PospondButton() {
		setSize(550, 410);
		setTitle("Pospond Reservation");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		getContentPane().setBackground(postpondColor);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setShape(new RoundRectangle2D.Double(20, 20, 510, 380, 20, 20));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setMaximumSize(new Dimension(580, 70));
		imagePanel.setPreferredSize(new Dimension(580, 70));
		imagePanel.setMaximumSize(new Dimension(580, 70));
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setOpaque(false);

		JLabel smallLogo = new JLabel(new ImageIcon(LoginWindow.class.getResource("/icons/myLogo1.png")));

		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 0;
		imagePanel.add(smallLogo, c5);
		
		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/icons/backGround.png"));
		
		setContentPane(new ImagePanel(backGround.getImage()));
		
		JPanel reservationNumberPanel = new JPanel();
		reservationNumberPanel.setLayout(new GridBagLayout());
		reservationNumberPanel.setOpaque(false);
		reservationNumberPanel.setMinimumSize(new Dimension(480, 60));
		reservationNumberPanel.setPreferredSize(new Dimension(480, 60));
		reservationNumberPanel.setMaximumSize(new Dimension(480, 60));
		
		
		JLabel reservation_Number = new JLabel("RES NO: ");
		reservation_Number.setFont(new Font("Costantia", Font.BOLD, 18));

		reservation_NumberValue = new JLabel();
		reservation_NumberValue.setForeground(new Color(163, 175, 39));
		reservation_NumberValue.setFont(new Font("Costantia", Font.BOLD, 18));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		reservationNumberPanel.add(reservation_Number, c);

		c.gridx = 1;
		c.gridy = 0;
		reservationNumberPanel.add(reservation_NumberValue, c);

		JPanel reservationInfo = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " RESERVATION INFORMATION ");
		border.setTitleJustification(TitledBorder.CENTER);
		reservationInfo.setBorder(border);
		reservationInfo.setLayout(new GridBagLayout());
		reservationInfo.setOpaque(false);
		reservationInfo.setMinimumSize(new Dimension(480, 70));
		reservationInfo.setPreferredSize(new Dimension(480, 70));
		reservationInfo.setMaximumSize(new Dimension(480, 70));

		JLabel roomNo = new JLabel("Room No :");
		roomNo.setFont(MainFrame.addInfoFont);

		roomNoValue = new JLabel();
		roomNoValue.setFont(MainFrame.addInfoFont);
		roomNoValue.setForeground(Color.RED);

		JLabel roomCost = new JLabel("Cost :");
		roomCost.setFont(MainFrame.addInfoFont);

		roomCostValue = new JLabel();
		roomCostValue.setFont(MainFrame.addInfoFont);
		roomCostValue.setForeground(Color.RED);

		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(5, 5, 5, 5);

		c1.gridx = 0;
		c1.gridy = 0;
		reservationInfo.add(roomNo, c1);

		c1.gridx = 1;
		c1.gridy = 0;
		reservationInfo.add(roomNoValue, c1);

		c1.gridx = 3;
		c1.gridy = 0;
		reservationInfo.add(roomCost, c1);

		c1.gridx = 4;
		c1.gridy = 0;
		reservationInfo.add(roomCostValue, c1);

		JPanel newReservationInfo = new JPanel();
		TitledBorder border1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " NEW DATES ");
		border1.setTitleJustification(TitledBorder.CENTER);
		newReservationInfo.setBorder(border);
		newReservationInfo.setLayout(new GridBagLayout());
		newReservationInfo.setOpaque(false);
		newReservationInfo.setMinimumSize(new Dimension(480, 90));
		newReservationInfo.setPreferredSize(new Dimension(480, 90));
		newReservationInfo.setMaximumSize(new Dimension(480, 90));

		JLabel newArriveDate = new JLabel("Arrival Date :");
		newArriveDate.setFont(MainFrame.addInfoFont);

		arriveDateValue = new DatePicker();
		arriveDateValue.setMinimumSize(new Dimension(120, 30));
		arriveDateValue.setPreferredSize(new Dimension(120, 30));
		arriveDateValue.setMaximumSize(new Dimension(120, 30));

		JLabel newDepartureDate = new JLabel("Departure Date :");
		newDepartureDate.setFont(MainFrame.addInfoFont);

		departureDateValue = new DatePicker();
		departureDateValue.setMinimumSize(new Dimension(120, 30));
		departureDateValue.setPreferredSize(new Dimension(120, 30));
		departureDateValue.setMaximumSize(new Dimension(120, 30));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5, 5, 5, 5);

		c2.gridx = 0;
		c2.gridy = 0;
		newReservationInfo.add(newArriveDate, c2);

		c2.gridx = 1;
		c2.gridy = 0;
		newReservationInfo.add(arriveDateValue, c2);

		c2.gridx = 2;
		c2.gridy = 0;
		newReservationInfo.add(newDepartureDate, c2);

		c2.gridx = 3;
		c2.gridy = 0;
		newReservationInfo.add(departureDateValue, c2);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setOpaque(false);
		buttonPanel.setMinimumSize(new Dimension(480, 30));
		buttonPanel.setPreferredSize(new Dimension(480, 30));
		buttonPanel.setMaximumSize(new Dimension(480, 30));


		add_Button = new JButton("Pospond");

		cancel_Button = new JButton("Cancel");
		cancel_Button.setPreferredSize(add_Button.getPreferredSize());

		buttonPanel.add(add_Button);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(cancel_Button);
		
		add(Box.createRigidArea(new Dimension(0, 30)));
		add(imagePanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(reservationNumberPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(reservationInfo);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(newReservationInfo);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(buttonPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));

		setVisible(false);

	}

}