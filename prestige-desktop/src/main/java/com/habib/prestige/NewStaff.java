package com.habib.prestige;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;

class NewStaff extends JFrame {
    private static final long serialVersionUID = 1L;

    private static String newStaffID;

    JPanel buttonPanel;
    JLabel staffID_Value;
    JLabel userNameValue;
    JLabel passwordValue;

    JTextField fnameValue;
    JTextField lnameValue;

    JButton addButton;
    JButton cancel_Button;

    JCheckBox mon;
    JCheckBox tue;
    JCheckBox wed;
    JCheckBox thu;
    JCheckBox fri;
    JCheckBox sat;
    JCheckBox sun;
    JComboBox userType;
    JCheckBox allDays;
    JComboBox genderValues;

    JTextArea addressValue;

    JFormattedTextField phoneNumber;

    JPanel userTypePanel = new JPanel();

    String strWorkDays;

    NewStaff() {

        DBConnection dbConnection = new DBConnection();

        setSize(600, 700);
        setTitle("Add New Staff");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        Color addStaffColor = new Color(212, 216, 162);
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

            ResultSet rs = dbConnection.getStatement().executeQuery("SELECT `Staff ID` FROM `users` ORDER BY `Staff ID` DESC");

            rs.next();

            int staffIdNumber = Integer.parseInt(rs.getString("Staff ID")) + 1;

            if (staffIdNumber < 10) newStaffID = df.format(staffIdNumber);
            else if (staffIdNumber < 100) newStaffID = df.format(staffIdNumber);
            rs.close();
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

        JPanel staffPersonalInfo = new JPanel();
        staffPersonalInfo.setMaximumSize(new Dimension(550, 200));
        staffPersonalInfo.setPreferredSize(new Dimension(550, 200));
        staffPersonalInfo.setMaximumSize(new Dimension(550, 200));
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " PERSONAL INFORMATION ");
        border.setTitleJustification(TitledBorder.CENTER);
        staffPersonalInfo.setBorder(border);
        staffPersonalInfo.setLayout(new GridBagLayout());
        staffPersonalInfo.setOpaque(false);

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

        String[] g_Values = {"Male", "Female"};
        genderValues = new JComboBox(g_Values);

        JLabel phone = new JLabel("Phone No.");
        phone.setFont(Reservation.formFont);
        phone.setForeground(Reservation.textColor);
        phone.setMinimumSize(firstName.getPreferredSize());
        phone.setPreferredSize(firstName.getPreferredSize());
        phone.setMaximumSize(firstName.getPreferredSize());

        MaskFormatter mask = null;

        try {
            mask = new MaskFormatter("###-###-####");
        } catch (ParseException e) {
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

        String[] userTypeValues = {"Manager", "Receptionist"};

        userType = new JComboBox(userTypeValues);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 5, 5, 5);
        c1.gridx = 0;
        c1.gridy = 0;
        staffPersonalInfo.add(firstName, c1);

        c1.gridx = 1;
        c1.gridy = 0;
        staffPersonalInfo.add(fnameValue, c1);

        c1.gridx = 2;
        c1.gridy = 0;
        staffPersonalInfo.add(lastName, c1);

        c1.gridx = 3;
        c1.gridy = 0;
        staffPersonalInfo.add(lnameValue, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        staffPersonalInfo.add(gender, c1);

        c1.gridx = 1;
        c1.gridy = 1;
        staffPersonalInfo.add(genderValues, c1);

        c1.gridx = 2;
        c1.gridy = 1;
        staffPersonalInfo.add(phone, c1);

        c1.gridx = 3;
        c1.gridy = 1;
        staffPersonalInfo.add(phoneNumber, c1);

        c1.gridx = 0;
        c1.gridy = 2;
        staffPersonalInfo.add(address, c1);

        c1.gridx = 1;
        c1.gridy = 2;
        staffPersonalInfo.add(addressValue, c1);

        c1.gridx = 2;
        c1.gridy = 2;
        staffPersonalInfo.add(user, c1);

        c1.gridx = 3;
        c1.gridy = 2;
        staffPersonalInfo.add(userType, c1);

        userTypePanel.setMaximumSize(new Dimension(550, 150));
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        userTypePanel.add(mon, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        userTypePanel.add(tue, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        userTypePanel.add(wed, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        userTypePanel.add(thu, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        userTypePanel.add(fri, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        userTypePanel.add(sat, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        userTypePanel.add(sun, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        userTypePanel.add(allDays, constraints);

        JPanel userAccoutPanel = new JPanel();
        userAccoutPanel.setMaximumSize(new Dimension(550, 100));
        userAccoutPanel.setPreferredSize(new Dimension(550, 100));
        userAccoutPanel.setMaximumSize(new Dimension(550, 100));
        TitledBorder userAccountPanelBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " USER ACCOUNT ");
        userAccountPanelBorder.setTitleJustification(TitledBorder.CENTER);
        userAccoutPanel.setBorder(userAccountPanelBorder);
        userAccoutPanel.setLayout(new GridBagLayout());
        userAccoutPanel.setOpaque(false);

        JLabel userName = new JLabel("User ID");
        userName.setFont(Reservation.formFont);

        JLabel password = new JLabel("Password");
        password.setFont(Reservation.formFont);

        userNameValue = new JLabel(newStaffID);
        userNameValue.setForeground(Reservation.textColor);
        userNameValue.setFont(new Font("Costantia", Font.BOLD, 18));

        passwordValue = new JLabel(RandomPassword.generateRandomPassword());
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
        buttonPanel.setMaximumSize(new Dimension(550, 50));
        buttonPanel.setPreferredSize(new Dimension(550, 50));
        buttonPanel.setMaximumSize(new Dimension(550, 50));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.setOpaque(false);

        cancel_Button = new JButton("Cancel");
        cancel_Button.addActionListener(e -> {
            int choice;
            choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
            if (choice == 0) dispose();
        });

        addButton = new JButton("Add");
        addButton.setPreferredSize(cancel_Button.getPreferredSize());
        addButton.addActionListener(e -> {

            if ((fnameValue.getText()).equals("") || (lnameValue.getText()).equals("") || (phoneNumber.getText()).equals("") || (addressValue.getText()).equals("")) {
                JOptionPane.showMessageDialog(null, "Form must be filled with all values before it can be submitted");
            } else {

                try {

                    ResultSet rs = dbConnection.getStatement().executeQuery("SELECT * FROM `users` ORDER BY `Staff ID`");

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

                    rs.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(cancel_Button);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(imagePanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(staffNumberPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(staffPersonalInfo);
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
