package com.habib.prestige;

import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

class Reservation extends JFrame {
    private static final long serialVersionUID = 1L;

    public static int dateBetween;
    public static int reservationCounter;
    public static Color textFieldColor = new Color(234, 238, 186);
    public static Color textColor = new Color(70, 75, 14);
    public static Font formFont = new Font("Courier New", Font.BOLD, 12);

    JLabel reservation_Number;
    JTextField fnameValue;
    JTextField lnameValue;
    JComboBox<?> genderValues;
    JFormattedTextField phoneNumber;
    JTextArea addressValue;
    JComboBox<?> countryValues;
    JTextField idNumber_Value;
    JComboBox<?> idType_Value;
    DatePicker arrivalDate_Value;
    DatePicker departureDate_Value;
    JLabel noOfDays_Value;
    JComboBox<?> roomType_Value;
    JComboBox<?> roomNumber_Value;
    JLabel roomRate_Value;
    JLabel totalPrizeValue;

    @SuppressWarnings("unchecked")
    Reservation() {

        DBConnection dbConnection = new DBConnection();

        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String fDate = dateFormat.format(dt);

        String dtStr1 = fDate.substring(0, 2);
        String dtStr2 = fDate.substring(3, 5);
        String dtStr3 = dtStr1 + dtStr2;

        try {

            String sql = "SELECT `Res` FROM `reservations` ORDER BY `Res` DESC";
            ResultSet rs = dbConnection.getStatement().executeQuery(sql);

            rs.next();

            String lastRes = rs.getString("Res");
            String string1 = lastRes.substring(0, 4);
            String string2 = lastRes.substring(4, 8);

            if (dtStr3.equals(string1)) {
                reservationCounter = Integer.parseInt(string2) + 1;
            } else {
                reservationCounter = 1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(630, 750);
        setTitle("New Reservation");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setShape(new RoundRectangle2D.Double(20, 20, 590, 700, 20, 20));

        ImageIcon backGround = new ImageIcon(LoginWindow.class.getResource("/icons/backGround.png"));

        setContentPane(new ImagePanel(backGround.getImage()));

        DecimalFormat df = new DecimalFormat("0000");

        String s1 = fDate.substring(0, 2) + fDate.substring(3, 5);
        String s2 = s1 + (df.format(reservationCounter));


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

        JPanel reservationNumberPanel = new JPanel();
        reservationNumberPanel.setMaximumSize(new Dimension(550, 70));
        reservationNumberPanel.setPreferredSize(new Dimension(550, 70));
        reservationNumberPanel.setMaximumSize(new Dimension(550, 70));
        reservationNumberPanel.setLayout(new GridBagLayout());
        reservationNumberPanel.setOpaque(false);

        JLabel reservationNo = new JLabel("RES NO: ");
        reservationNo.setFont(new Font("Costantia", Font.BOLD, 18));

        reservation_Number = new JLabel();
        reservation_Number.setText(s2);
        reservation_Number.setForeground(new Color(163, 175, 39));
        reservation_Number.setFont(new Font("Costantia", Font.BOLD, 18));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        reservationNumberPanel.add(reservationNo, c);

        c.gridx = 1;
        c.gridy = 0;
        reservationNumberPanel.add(reservation_Number, c);

        JPanel personalInfo = new JPanel();
        personalInfo.setMaximumSize(new Dimension(550, 200));
        personalInfo.setPreferredSize(new Dimension(550, 200));
        personalInfo.setMaximumSize(new Dimension(550, 200));
        personalInfo.setOpaque(false);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " PERSONAL INFORMATION ");
        border.setTitleJustification(TitledBorder.CENTER);
        personalInfo.setBorder(border);
        personalInfo.setLayout(new GridBagLayout());

        JLabel firstName = new JLabel("First Name");
        firstName.setFont(formFont);
        firstName.setForeground(textColor);

        fnameValue = new JTextField(12);
        fnameValue.setBackground(textFieldColor);

        JLabel lastName = new JLabel("Last Name");
        lastName.setFont(formFont);
        lastName.setForeground(textColor);
        lastName.setPreferredSize(firstName.getPreferredSize());

        lnameValue = new JTextField(12);
        lnameValue.setBackground(textFieldColor);

        JLabel gender = new JLabel("Gender");
        gender.setFont(formFont);
        gender.setForeground(textColor);
        gender.setPreferredSize(firstName.getPreferredSize());

        String[] g_Values = {"Male", "Female"};
        genderValues = new JComboBox<Object>(g_Values);

        JLabel phone = new JLabel("Phone No.");
        phone.setFont(formFont);
        phone.setForeground(textColor);
        phone.setPreferredSize(firstName.getPreferredSize());

        MaskFormatter mask = null;

        try {
            mask = new MaskFormatter("###-###-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        phoneNumber = new JFormattedTextField(mask);
        phoneNumber.setPreferredSize(lnameValue.getPreferredSize());
        phoneNumber.setBackground(textFieldColor);

        JLabel address = new JLabel("Address");
        address.setFont(formFont);
        address.setForeground(textColor);
        address.setPreferredSize(firstName.getPreferredSize());

        addressValue = new JTextArea(5, 12);
        addressValue.setBackground(textFieldColor);
        addressValue.setMinimumSize(new Dimension(140, 85));
        addressValue.setPreferredSize(new Dimension(140, 85));
        addressValue.setMaximumSize(new Dimension(140, 85));
        addressValue.setLineWrap(true);
        addressValue.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        addressValue.setOpaque(false);

        JLabel country = new JLabel("Country");
        country.setFont(formFont);
        country.setForeground(textColor);
        country.setPreferredSize(firstName.getPreferredSize());

        String[] countries = {"Belarus", "Canada", "China", "England", "Germany", "Ghana", "Hungary", "Nigeria", "Niger", "Poland", "Ukraine", "USA"};
        countryValues = new JComboBox<Object>(countries);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 5, 5, 5);
        c1.gridx = 0;
        c1.gridy = 0;
        personalInfo.add(firstName, c1);

        c1.gridx = 1;
        c1.gridy = 0;
        personalInfo.add(fnameValue, c1);

        c1.gridx = 2;
        c1.gridy = 0;
        personalInfo.add(lastName, c1);

        c1.gridx = 3;
        c1.gridy = 0;
        personalInfo.add(lnameValue, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        personalInfo.add(gender, c1);

        c1.gridx = 1;
        c1.gridy = 1;
        personalInfo.add(genderValues, c1);

        c1.gridx = 2;
        c1.gridy = 1;
        personalInfo.add(phone, c1);

        c1.gridx = 3;
        c1.gridy = 1;
        personalInfo.add(phoneNumber, c1);

        c1.gridx = 0;
        c1.gridy = 2;
        personalInfo.add(country, c1);

        c1.gridx = 1;
        c1.gridy = 2;
        personalInfo.add(countryValues, c1);

        c1.gridx = 2;
        c1.gridy = 2;
        personalInfo.add(address, c1);

        c1.gridx = 3;
        c1.gridy = 2;
        personalInfo.add(addressValue, c1);

        JPanel idInfo = new JPanel();
        idInfo.setMaximumSize(new Dimension(550, 100));
        idInfo.setPreferredSize(new Dimension(550, 100));
        idInfo.setMaximumSize(new Dimension(550, 100));
        TitledBorder idBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " IDENTIFICATION ");
        idBorder.setTitleJustification(TitledBorder.CENTER);
        idInfo.setBorder(idBorder);
        idInfo.setLayout(new GridBagLayout());
        idInfo.setOpaque(false);

        JLabel idNumber = new JLabel("ID Number");
        idNumber.setFont(formFont);
        idNumber.setForeground(textColor);

        idNumber_Value = new JTextField(12);
        idNumber_Value.setBackground(textFieldColor);

        JLabel id = new JLabel("ID Type");
        id.setFont(formFont);
        id.setForeground(textColor);

        String[] id_Values = {"Drivers Licence", "Nation ID", "School ID", "Other"};
        idType_Value = new JComboBox<Object>(id_Values);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(5, 5, 5, 5);
        c2.gridx = 0;
        c2.gridy = 0;
        idInfo.add(id, c2);

        c2.gridx = 1;
        c2.gridy = 0;
        idInfo.add(idType_Value, c2);

        c2.gridx = 3;
        c2.gridy = 0;
        idInfo.add(idNumber, c2);

        c2.gridx = 4;
        c2.gridy = 0;
        idInfo.add(idNumber_Value, c2);

        JPanel reservationPanel = new JPanel();
        reservationPanel.setMaximumSize(new Dimension(550, 100));
        reservationPanel.setPreferredSize(new Dimension(550, 100));
        reservationPanel.setMaximumSize(new Dimension(550, 100));
        TitledBorder reservationBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " RESERVATIONS ");
        reservationBorder.setTitleJustification(TitledBorder.CENTER);
        reservationPanel.setBorder(reservationBorder);
        reservationPanel.setLayout(new GridBagLayout());
        reservationPanel.setOpaque(false);

        JLabel arrivalDate = new JLabel("Arrival");
        arrivalDate.setFont(formFont);
        arrivalDate.setForeground(textColor);
        arrivalDate.setMinimumSize(new Dimension(100, 20));
        arrivalDate.setPreferredSize(new Dimension(100, 20));
        arrivalDate.setMaximumSize(new Dimension(100, 20));

        arrivalDate_Value = new DatePicker();
        arrivalDate_Value.setMinimumSize(new Dimension(130, 30));
        arrivalDate_Value.setPreferredSize(new Dimension(130, 30));
        arrivalDate_Value.setMaximumSize(new Dimension(130, 30));

        JLabel departureDate = new JLabel("Departure");
        departureDate.setFont(formFont);
        departureDate.setForeground(textColor);
        departureDate.setMinimumSize(new Dimension(100, 20));
        departureDate.setPreferredSize(new Dimension(100, 20));
        departureDate.setMaximumSize(new Dimension(100, 20));

        departureDate_Value = new DatePicker();
        departureDate_Value.setMinimumSize(new Dimension(130, 30));
        departureDate_Value.setPreferredSize(new Dimension(130, 30));
        departureDate_Value.setMaximumSize(new Dimension(130, 30));

        JLabel noOfDays = new JLabel("No. Of Days");
        noOfDays.setFont(formFont);
        noOfDays.setForeground(textColor);
        noOfDays.setMinimumSize(new Dimension(100, 20));
        noOfDays.setPreferredSize(new Dimension(100, 20));
        noOfDays.setMaximumSize(new Dimension(100, 20));

        noOfDays_Value = new JLabel();
        noOfDays_Value.setFont(formFont);
        noOfDays_Value.setForeground(Color.RED);
        noOfDays_Value.setMinimumSize(new Dimension(100, 20));
        noOfDays_Value.setPreferredSize(new Dimension(100, 20));
        noOfDays_Value.setMaximumSize(new Dimension(100, 20));

        departureDate_Value.addActionListener(e -> {

            dateBetween = Days.daysBetween(new DateTime(arrivalDate_Value.returnDate()).toLocalDate(), new DateTime(departureDate_Value.returnDate()).toLocalDate()).getDays();

            if (dateBetween < 1) {
                JOptionPane.showMessageDialog(null, "Invalid Departure Date.");
            } else {
                noOfDays_Value.setText(Integer.toString(dateBetween));
                roomType_Value.setEnabled(true);
            }
        });

        GridBagConstraints c3 = new GridBagConstraints();
        c3.insets = new Insets(3, 3, 3, 3);
        c3.gridx = 0;
        c3.gridy = 0;
        reservationPanel.add(arrivalDate, c3);

        c3.gridx = 1;
        c3.gridy = 0;
        reservationPanel.add(arrivalDate_Value, c3);

        c3.gridx = 2;
        c3.gridy = 0;
        reservationPanel.add(departureDate, c3);

        c3.gridx = 3;
        c3.gridy = 0;
        reservationPanel.add(departureDate_Value, c3);

        c3.gridx = 0;
        c3.gridy = 1;
        reservationPanel.add(noOfDays, c3);

        c3.gridx = 1;
        c3.gridy = 1;
        reservationPanel.add(noOfDays_Value, c3);

        JPanel roomInfo = new JPanel();
        roomInfo.setMaximumSize(new Dimension(550, 100));
        roomInfo.setPreferredSize(new Dimension(550, 100));
        roomInfo.setMaximumSize(new Dimension(550, 100));
        TitledBorder roomInfo_Border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ROOM INFORMATION ");
        roomInfo_Border.setTitleJustification(TitledBorder.CENTER);
        roomInfo.setBorder(roomInfo_Border);
        roomInfo.setLayout(new GridBagLayout());
        roomInfo.setOpaque(false);

        JLabel roomNumber = new JLabel("Room No");
        roomNumber.setFont(formFont);
        roomNumber.setForeground(textColor);

        roomNumber_Value = new JComboBox<Object>();
        roomNumber_Value.setMinimumSize(new Dimension(70, 30));
        roomNumber_Value.setPreferredSize(new Dimension(70, 30));
        roomNumber_Value.setMaximumSize(new Dimension(70, 30));

        JLabel roomRate = new JLabel("Prize/Night");
        roomRate.setForeground(textColor);
        roomRate.setFont(formFont);
        roomRate.setMinimumSize(new Dimension(100, 20));
        roomRate.setPreferredSize(new Dimension(100, 20));
        roomRate.setMaximumSize(new Dimension(100, 20));

        roomRate_Value = new JLabel();
        roomRate_Value.setForeground(Color.RED);
        roomRate_Value.setFont(formFont);
        roomRate_Value.setMinimumSize(new Dimension(100, 20));
        roomRate_Value.setPreferredSize(new Dimension(100, 20));
        roomRate_Value.setMaximumSize(new Dimension(100, 20));

        JLabel totalPrize = new JLabel("Total Prize");
        totalPrize.setForeground(textColor);
        totalPrize.setFont(formFont);

        totalPrizeValue = new JLabel();
        totalPrizeValue.setForeground(Color.RED);
        totalPrizeValue.setFont(formFont);
        totalPrizeValue.setMinimumSize(new Dimension(100, 20));
        totalPrizeValue.setPreferredSize(new Dimension(100, 20));
        totalPrizeValue.setMaximumSize(new Dimension(100, 20));

        JLabel roomType = new JLabel("Room Type");
        roomType.setForeground(textColor);
        roomType.setFont(formFont);

        roomType.setMinimumSize(new Dimension(100, 20));
        roomType.setPreferredSize(new Dimension(100, 20));
        roomType.setMaximumSize(new Dimension(100, 20));

        final String[] roomTypes = {"Diplomatic", "Executive", "Super Executive"};

        roomType_Value = new JComboBox<Object>(roomTypes);
        roomType_Value.setSelectedItem(null);
        roomType_Value.setMinimumSize(new Dimension(100, 30));
        roomType_Value.setPreferredSize(new Dimension(100, 30));
        roomType_Value.setMaximumSize(new Dimension(100, 30));
        roomType_Value.setEnabled(false);
        roomType_Value.addItemListener(e -> {
            final Vector stringRoomNo = new Vector();

            try {

                String sql = "SELECT * FROM `rooms` WHERE `Room Type`='" + roomType_Value.getSelectedItem().toString() + "' AND `Status`='" + "Available" + "'";
                ResultSet rs = dbConnection.getStatement().executeQuery(sql);

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Sorry no available room, please choose a different room type.");
                    roomType_Value.removeAll();
                    roomType_Value.setModel(new DefaultComboBoxModel(roomTypes));
                } else {
                    while (rs.next()) {
                        stringRoomNo.addElement(rs.getString("Room No"));
                    }

                    rs.previous();

                    roomRate_Value.setText(rs.getString("Prize (=N=)"));

                    roomNumber_Value.removeAllItems();
                    roomNumber_Value.setModel(new DefaultComboBoxModel(stringRoomNo));
                    roomNumber_Value.repaint();

                    totalPrizeValue.setText(Integer.toString(Integer.parseInt(roomRate_Value.getText().toString()) * dateBetween));
                }
                rs.close();
                dbConnection.getStatement().close();
                dbConnection.getConnection().close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        GridBagConstraints c4 = new GridBagConstraints();
        c4.insets = new Insets(3, 3, 3, 3);

        c4.gridx = 0;
        c4.gridy = 0;
        roomInfo.add(roomType, c4);

        c4.gridx = 1;
        c4.gridy = 0;
        roomInfo.add(roomType_Value, c4);

        c4.gridx = 2;
        c4.gridy = 0;
        roomInfo.add(roomNumber, c4);

        c4.gridx = 3;
        c4.gridy = 0;
        roomInfo.add(roomNumber_Value, c4);

        c4.gridx = 0;
        c4.gridy = 1;
        roomInfo.add(roomRate, c4);

        c4.gridx = 1;
        c4.gridy = 1;
        roomInfo.add(roomRate_Value, c4);

        c4.gridx = 2;
        c4.gridy = 1;
        roomInfo.add(totalPrize, c4);

        c4.gridx = 3;
        c4.gridy = 1;
        roomInfo.add(totalPrizeValue, c4);

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(imagePanel);
        add(reservationNumberPanel);
        add(personalInfo);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(idInfo);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(reservationPanel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(roomInfo);

        setVisible(false);
    }
}
