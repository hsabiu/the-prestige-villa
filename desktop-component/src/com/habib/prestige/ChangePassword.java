package com.habib.prestige;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class ChangePassword extends JFrame {
	private static final long serialVersionUID = 1L;

	Color roomsColor = new Color(212, 216, 162);

	ChangePassword() {
		setSize(500, 350);
		setTitle("Change Password");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setShape(new RoundRectangle2D.Double(20, 20, 460, 320, 20, 20));

		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/backGround.png"));

		setContentPane(new ImagePanel(backGround.getImage()));

		JPanel imagePanel = new JPanel();
		imagePanel.setMaximumSize(new Dimension(550, 90));
		imagePanel.setPreferredSize(new Dimension(550, 90));
		imagePanel.setMaximumSize(new Dimension(550, 90));
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setOpaque(false);

		JLabel smallLogo = new JLabel(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/myLogo1.png")));

		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 0;
		imagePanel.add(smallLogo, c5);

		JPanel roomInfo = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " CHANGE PASSWORD ");
		border.setTitleJustification(TitledBorder.CENTER);
		roomInfo.setBorder(border);
		roomInfo.setLayout(new GridBagLayout());
		roomInfo.setMaximumSize(new Dimension(400, 200));
		roomInfo.setPreferredSize(new Dimension(400, 200));
		roomInfo.setMaximumSize(new Dimension(400, 200));
		roomInfo.setOpaque(false);

		JLabel newPassword = new JLabel("New Password :");
		newPassword.setFont(MainFrame.addInfoFont);
		newPassword.setForeground(Color.RED);
		newPassword.setMaximumSize(new Dimension(150, 20));
		newPassword.setPreferredSize(new Dimension(150, 20));
		newPassword.setMaximumSize(new Dimension(150, 20));

		final JPasswordField newPasswordValue = new JPasswordField(8);
		newPasswordValue.setPreferredSize(new Dimension(130, 25));

		JLabel confirmPassword = new JLabel("Confirm Password :");
		confirmPassword.setFont(MainFrame.addInfoFont);
		confirmPassword.setForeground(Color.RED);
		confirmPassword.setMaximumSize(new Dimension(150, 20));
		confirmPassword.setPreferredSize(new Dimension(150, 20));
		confirmPassword.setMaximumSize(new Dimension(150, 20));

		final JPasswordField confirmPasswordValue = new JPasswordField(8);
		confirmPasswordValue.setPreferredSize(new Dimension(100, 25));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		c.gridx = 0;
		c.gridy = 1;
		roomInfo.add(newPassword, c);

		c.gridx = 1;
		c.gridy = 1;
		roomInfo.add(newPasswordValue, c);

		c.gridx = 0;
		c.gridy = 2;
		roomInfo.add(confirmPassword, c);

		c.gridx = 1;
		c.gridy = 2;
		roomInfo.add(confirmPasswordValue, c);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.setMaximumSize(new Dimension(400, 70));
		buttonPanel.setPreferredSize(new Dimension(400, 70));
		buttonPanel.setMaximumSize(new Dimension(400, 70));
		buttonPanel.setOpaque(false);

		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice;
				choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
				if (choice == 0) dispose();
			}
		});

		JButton add_Button = new JButton("Change");
		add_Button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (newPasswordValue.getText().equals("") || confirmPasswordValue.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Plese fill all the fields");
				} else {

					if (newPasswordValue.getText().equals(confirmPasswordValue.getText())) {

						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

							Statement stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
							ResultSet rs = stmt.executeQuery("SELECT * FROM `users` WHERE `Staff ID`='" + LoginWindow.user + "'");

							rs.first();

							rs.updateString("Password", newPasswordValue.getText());

							rs.updateRow();

							JOptionPane.showMessageDialog(null, "Password changed");

							dispose();

							MainFrame.userAccount();

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Password doesn't match");
					}

				}
			}
		});

		buttonPanel.add(add_Button);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(cancel_Button);

		add(Box.createRigidArea(new Dimension(0, 30)));
		add(imagePanel);
		add(roomInfo);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(buttonPanel);

		setVisible(true);

	}
}