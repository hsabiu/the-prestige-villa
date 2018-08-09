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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class LoginWindow {

	Connection con;
	Statement st;
	ResultSet rs;

	JFrame loginFrame;
	JLabel userName;
	JLabel passWord;
	JTextField userNameValue;
	JPasswordField passWordValue;
	JButton login;
	JButton exit;
	public static String user;

	public LoginWindow() {

		loginFrame = new JFrame();
		loginFrame.setSize(500, 460);
		loginFrame.setTitle("Login Window");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setLayout(new BoxLayout(loginFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		loginFrame.setResizable(false);
		loginFrame.setUndecorated(true);
		loginFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		loginFrame.setShape(new RoundRectangle2D.Double(20, 20, 450, 400, 20, 20));

		ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/backGround.png"));

		ImagePanel loginPaneContainer = new ImagePanel(backGround.getImage());
		loginPaneContainer.setLayout(new GridBagLayout());

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/myLogo.png")));

		JLabel loginLogo = new JLabel("");
		loginLogo.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/loginLogo.PNG")));

		JPanel loginPane = new JPanel();
		loginPane.setOpaque(false);
		loginPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(231, 247, 201), new Color(231, 247, 201), null, null));
		loginPane.setLayout(new BoxLayout(loginPane, BoxLayout.PAGE_AXIS));

		passWord = new JLabel("Password ");
		passWord.setForeground(Color.WHITE);
		passWord.setFont(new Font("Andalus", Font.BOLD, 18));

		userName = new JLabel("User ID ");
		userName.setForeground(Color.WHITE);
		userName.setFont(new Font("Andalus", Font.BOLD, 18));
		userName.setPreferredSize(passWord.getPreferredSize());

		userNameValue = new JTextField(12);
		userNameValue.setBackground(new Color(234, 238, 186));
		userNameValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction();
			}
		});

		passWordValue = new JPasswordField(12);
		passWordValue.setBackground(new Color(234, 238, 186));
		passWordValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction();
			}
		});

		JPanel upPane = new JPanel();
		upPane.setOpaque(false);
		upPane.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 10);

		c.gridx = 0;
		c.gridy = 1;
		upPane.add(userName, c);

		c.gridx = 1;
		c.gridy = 1;
		upPane.add(userNameValue, c);

		c.gridx = 0;
		c.gridy = 2;
		upPane.add(passWord, c);

		c.gridx = 1;
		c.gridy = 2;
		upPane.add(passWordValue, c);

		JPanel downPane = new JPanel();
		downPane.setOpaque(false);
		downPane.setLayout(new BoxLayout(downPane, BoxLayout.LINE_AXIS));
		downPane.add(Box.createHorizontalGlue());

		login = new JButton("Login");
		login.setMinimumSize(new Dimension(65, 30));
		login.setPreferredSize(new Dimension(65, 30));
		login.setMaximumSize(new Dimension(65, 30));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction();
			}
		});

		exit = new JButton("Exit");
		exit.setMinimumSize(new Dimension(65, 30));
		exit.setPreferredSize(new Dimension(65, 30));
		exit.setMaximumSize(new Dimension(65, 30));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		downPane.add(login);
		downPane.add(Box.createRigidArea(new Dimension(15, 5)));
		downPane.add(exit);
		downPane.add(Box.createRigidArea(new Dimension(10, 0)));

		loginPane.add(Box.createRigidArea(new Dimension(0, 10)));
		loginPane.add(upPane);
		loginPane.add(Box.createRigidArea(new Dimension(0, 5)));
		loginPane.add(downPane);
		loginPane.add(Box.createRigidArea(new Dimension(0, 10)));

		GridBagConstraints c2 = new GridBagConstraints();

		c2.gridx = 0;
		c2.gridy = 0;
		loginPaneContainer.add(logo, c2);

		c2.gridx = 0;
		c2.gridy = 1;
		loginPaneContainer.add(loginLogo, c2);

		c2.gridx = 0;
		c2.gridy = 2;
		loginPaneContainer.add(loginPane, c2);

		loginFrame.add(loginPaneContainer);
		loginFrame.setVisible(true);
	}

	public void buttonAction() {

		try {

			user = userNameValue.getText().trim();
			char[] x = passWordValue.getPassword();

			String pass = new String(x);

			if (user.equals("")) {
				JOptionPane.showMessageDialog(null, "Plese provide your Staff ID");
			} else if (pass.equals("")) {
				JOptionPane.showMessageDialog(null, "Plese provide your Password");
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

				String sql = "SELECT * FROM `users` WHERE `users`.`Staff ID` = '" + user + "' AND `users`.`Password` = '" + pass + "'";
				st = con.createStatement();
				rs = st.executeQuery(sql);

				if (rs.first()) {
					if (rs.getString("User Type").equals("Receptionist")) {

						new MainFrame();

						loginFrame.dispose();
						MainFrame.admin.setEnabled(false);
						MainFrame.credit_card.setEnabled(false);
						MainFrame.welcomeLabel.setText(rs.getString("First Name") + " " + rs.getString("Last Name"));
						MainFrame.logo.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/waiter1.png")));

					} else if (rs.getString("User Type").equals("Manager")) {
						new MainFrame();

						loginFrame.dispose();
						MainFrame.welcomeLabel.setText(rs.getString("First Name") + " " + rs.getString("Last Name"));
						MainFrame.logo.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/administrator1.png")));
					} else {
						new MainFrame();

						loginFrame.dispose();
						MainFrame.welcomeLabel.setText("ADMIN");
						MainFrame.logo.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/habib/prestige/icons/administrator1.png")));
					}

				} else {
					JOptionPane.showMessageDialog(null, "Invalid User ID or Password");
					userNameValue.setText("");
					passWordValue.setText("");
				}
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot connect to the database, makesure the server at localhost is up and running");
		}
	}

	public static void main(String[] args) {
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

		try {
			String strTheDay = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();

			LocalDate theDay = LocalDate.parse(strTheDay, DateTimeFormat.forPattern("dd/MM/yyyy"));
			LocalDate dayBefore = theDay.minusDays(1);

			String str4 = new SimpleDateFormat("dd/MM/yyyy").format(dayBefore.toDate()).toString();

			SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = dtFormat.parse(str4);

			Connection con;
			Statement st;
			ResultSet rs;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");

			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Status`= 'Waiting'");

			while (rs.next()) {
				String strDate = rs.getString("Arrival");
				Date date2 = dtFormat.parse(strDate);

				if (date2.before(date1)) {
					rs.updateString("Status", "Canceled");
					rs.updateString("CancelDate", (new SimpleDateFormat("dd/MM/yyyy").format(new Date())).toString());

					rs.updateRow();

					String str3 = rs.getString("Room No");

					Statement st1 = con.createStatement();
					st1.executeUpdate("UPDATE `rooms` SET `Status` = 'Available' WHERE `Room No`='" + str3 + "'");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		new LoginWindow();
	}
}