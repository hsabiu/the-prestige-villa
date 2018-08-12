package com.habib.prestige;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXMonthView;

public class MainFrame {

    static Color textFieldColor = new Color(234, 238, 186);
    static JLabel welcomeLabel;
    static JButton admin;
    static JButton credit_card;
    static Font addInfoFont = new Font("Bookman Old Style", Font.PLAIN, 13);
    static JFrame HomeWindow;
    static Color myColor = new Color(238, 238, 238);
    static Color contentColor = new Color(231, 234, 239);
    static Connection con;
    static Statement st;
    static ResultSet rs;
    static JButton receptionist;
    static JLabel l6;
    static JLabel res;
    static JLabel logo;
    static JLabel arrival_RNo;
    static JLabel departure_RNo;
    static JTextField arrival_RNoValue;
    static JTextField departure_RNoValue;
    private static JTable arrive_table;
    private static JTable depart_table;
    private static JTable allReservations_table;
    private static JCheckBox payed;
    private static JButton home;
    private static JButton reservation;
    private static JButton room;
    private static JButton cancelButton;
    private static JButton postponedButton_1;
    private static JButton viewButton_1;
    private static JButton editButton;
    private static JCheckBox checkOut;
    private static JButton postponedButton_2;
    private static JButton viewButton_2;
    static JPanel centerPaneContainer = expected_Arrive_Depart();
    private static JButton viewRooms;
    private static JButton viewCostomer;
    private static JPanel centerDownPane;
    private static JPanel contentPane;
    private static Font tableFont = new Font("Lucida Console", Font.PLAIN, 11);
    private static Color tableTextColor = new Color(94, 80, 80);

    public MainFrame() {

        HomeWindow = new JFrame();
        HomeWindow.setTitle("The Prestige Villa");
        HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeWindow.setResizable(false);
        HomeWindow.setSize(1200, 730);
        HomeWindow.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        HomeWindow.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // CustomToolbar is a customize tool bar i created with a background
        // image
        CustomToolbar myToolBar = new CustomToolbar();

        home = new JButton("");
        home.setIcon(new ImageIcon(this.getClass().getResource("/icons/Home.png")));

        home.setToolTipText("Home");
        home.addActionListener(e -> {
            centerPaneContainer.removeAll();
            centerPaneContainer.add(expected_Arrive_Depart());

            arrival_RNo.setEnabled(true);
            departure_RNo.setEnabled(true);

            arrival_RNoValue.setEnabled(true);
            departure_RNoValue.setEnabled(true);

            arrival_RNoValue.setText("");
            departure_RNoValue.setText("");

            centerPaneContainer.repaint();
            contentPane.add(centerPaneContainer, BorderLayout.CENTER);
        });

        reservation = new JButton("");
        reservation.setIcon(new ImageIcon(this.getClass().getResource("/icons/reservation.png")));
        reservation.setToolTipText("Reservation");
        reservation.addActionListener(e -> {
            centerPaneContainer.removeAll();
            centerPaneContainer.add(allReservations());

            contentPane.add(centerPaneContainer, BorderLayout.CENTER);
            centerPaneContainer.repaint();
        });

        room = new JButton("");
        room.setIcon(new ImageIcon(this.getClass().getResource("/icons/room.png")));
        room.setToolTipText("Room Management");
        room.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerPaneContainer.removeAll();
                centerPaneContainer.add(reservedRoomsCenterPane());

                contentPane.add(centerPaneContainer, BorderLayout.CENTER);
                centerPaneContainer.repaint();
            }
        });

        admin = new JButton("");
        admin.setIcon(new ImageIcon(this.getClass().getResource("/icons/administrator.png")));
        admin.setToolTipText("Administrator");
        admin.addActionListener(e -> {
            centerPaneContainer.removeAll();
            centerPaneContainer.add(adminCenterPane());

            contentPane.add(centerPaneContainer, BorderLayout.CENTER);
            centerPaneContainer.repaint();
        });

        receptionist = new JButton("");
        receptionist.setIcon(new ImageIcon(this.getClass().getResource("/icons/waiter.png")));
        receptionist.setToolTipText("Receptionist");
        receptionist.addActionListener(e -> {
            centerPaneContainer.removeAll();
            centerPaneContainer.add(userAccount());

            contentPane.add(centerPaneContainer, BorderLayout.CENTER);
            centerPaneContainer.repaint();
        });

        credit_card = new JButton("");
        credit_card.setIcon(new ImageIcon(this.getClass().getResource("/icons/credit_card.PNG")));
        credit_card.setToolTipText("Payment");
        credit_card.addActionListener(e -> {
            centerPaneContainer.removeAll();
            centerPaneContainer.add(paymentsPane());

            contentPane.add(centerPaneContainer, BorderLayout.CENTER);
            centerPaneContainer.repaint();
        });

        JButton exit = new JButton("");
        exit.setIcon(new ImageIcon(this.getClass().getResource("/icons/exit.png")));
        exit.setToolTipText("Exit");
        exit.addActionListener(e -> {

            int choice;
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                System.exit(0);
            }
        });

        myToolBar.add(home);
        myToolBar.addSeparator();
        myToolBar.add(reservation);
        myToolBar.add(room);
        myToolBar.addSeparator();
        myToolBar.add(receptionist);
        myToolBar.add(admin);
        myToolBar.addSeparator();
        myToolBar.add(credit_card);
        myToolBar.addSeparator();
        myToolBar.add(exit);
        myToolBar.addSeparator();

        ImageIcon Downbackground = new ImageIcon(this.getClass().getResource("/icons/upperFrame.PNG"));

        ImagePanel downPane = new ImagePanel(Downbackground.getImage());
        downPane.setMinimumSize(new Dimension(1100, 30));
        downPane.setPreferredSize(new Dimension(1100, 30));
        downPane.setMaximumSize(new Dimension(1100, 30));
        downPane.setLayout(new BoxLayout(downPane, BoxLayout.LINE_AXIS));
        downPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        downPane.add(Box.createHorizontalGlue());

        JLabel hotelName = new JLabel("The Prestige Villa");
        hotelName.setFont(new Font("Vivaldi", Font.BOLD, 14));
        hotelName.setForeground(new Color(151, 73, 86));

        JLabel downPaneLabel = new JLabel(" \u00a9 2014 - Developed By Habib Ado Sabiu \u00ae\u2122   ");
        downPaneLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 14));
        downPaneLabel.setForeground(new Color(122, 110, 112));

        downPane.add(hotelName);
        downPane.add(downPaneLabel);

        contentPane.add(myToolBar, BorderLayout.NORTH);
        contentPane.add(addLeftPane(), BorderLayout.WEST);
        contentPane.add(centerPaneContainer, BorderLayout.CENTER);
        contentPane.add(downPane, BorderLayout.SOUTH);

        arrival_RNo.setEnabled(true);
        departure_RNo.setEnabled(true);

        arrival_RNoValue.setEnabled(true);
        departure_RNoValue.setEnabled(true);

        HomeWindow.setVisible(true);
    }

    private static int getRowByValue(String value, JTable table) {
        int rowNumbers = 0;

        for (int rowCount = 0; rowCount < table.getRowCount(); rowCount++) {
            for (int columnCount = 0; columnCount < table.getColumnCount(); columnCount++) {
                if (table.getValueAt(rowCount, columnCount).toString().equals(value)) {
                    rowNumbers = rowCount;
                    break;
                }
            }
        }
        return rowNumbers;
    }

    public static JPanel expected_Arrive_Depart() {

        final JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPane.setBackground(myColor);

        final JPanel centerUpPane = new JPanel();
        centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ARRIVALS ");
        centerUpBorder.setTitleJustification(TitledBorder.CENTER);
        centerUpPane.setBorder(centerUpBorder);
        centerUpPane.setBackground(myColor);

        Vector<String> expectedArrivalsColumnNames = new Vector<String>();
        Vector<Vector<Object>> expectedArrivalsRoomData = new Vector<Vector<Object>>();

        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = dateFormat.format(dt);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT `Res`, `First Name`, `Last Name`, `Phone No`, `ID Type`, `ID No`, `Room No`, `Total Prize`, `Status` FROM `reservations` WHERE `Arrival`='" + formatedDate + "'AND `Status`='" + "Waiting" + "'ORDER BY `Res`");

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                expectedArrivalsColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                expectedArrivalsRoomData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        arrive_table = new JTable(expectedArrivalsRoomData, expectedArrivalsColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        arrive_table.getTableHeader().setReorderingAllowed(false);
        arrive_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        arrive_table.setFont(new Font("Lucida Console", Font.PLAIN, 11));
        arrive_table.setForeground(new Color(94, 80, 80));
        arrive_table.setGridColor(new Color(223, 223, 223));
        arrive_table.setShowGrid(true);
        arrive_table.setShowVerticalLines(false);
        arrive_table.setOpaque(false);

        TableColumn arriveTablecol = null;
        for (int i = 0; i < arrive_table.getColumnCount(); i++) {
            if (i == 1 || i == 2 || i == 3 || i == 6) arriveTablecol.setPreferredWidth(70);
            else if (i == 4 || i == 5) arriveTablecol.setPreferredWidth(110);
            else if (i == 7 || i == 8) arriveTablecol.setPreferredWidth(50);
            else if (i == 9) arriveTablecol.setPreferredWidth(30);
            arriveTablecol = arrive_table.getColumnModel().getColumn(i);
            arriveTablecol.setCellRenderer(new CustomTableCellRenderer());
            arriveTablecol.setResizable(false);
        }

        JScrollPane arrive_tableContainer = new JScrollPane(arrive_table);
        arrive_tableContainer.getViewport().setBackground(contentColor);

        JPanel reserveButtonPane = new JPanel();
        reserveButtonPane.setLayout(new BoxLayout(reserveButtonPane, BoxLayout.LINE_AXIS));
        reserveButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        reserveButtonPane.add(Box.createHorizontalGlue());
        reserveButtonPane.setBackground(myColor);

        arrival_RNo = new JLabel("Reservation No");
        arrival_RNo.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        arrival_RNo.setForeground(Color.RED);

        arrival_RNoValue = new JTextField(7);
        arrival_RNoValue.setBackground(textFieldColor);
        arrival_RNoValue.setMinimumSize(new Dimension(90, 30));
        arrival_RNoValue.setPreferredSize(new Dimension(90, 30));
        arrival_RNoValue.setMaximumSize(new Dimension(90, 30));
        arrival_RNoValue.setEnabled(false);
        arrival_RNoValue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getRowByValue(arrival_RNoValue.getText(), arrive_table) != 0) {
                    arrive_table.requestFocus();
                    arrive_table.changeSelection(getRowByValue(arrival_RNoValue.getText(), arrive_table), 0, false, false);

                    payed.setSelected(false);
                    payed.setEnabled(true);

                    viewButton_1.setEnabled(true);
                    postponedButton_1.setEnabled(true);
                    editButton.setEnabled(true);
                    cancelButton.setEnabled(true);

                    arrival_RNoValue.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! Reservation number not found");
                    arrival_RNoValue.setText("");
                }
            }
        });

        final JCheckBox checkIN = new JCheckBox("Checked");
        checkIN.setFont(addInfoFont);
        checkIN.setForeground(Color.RED);
        checkIN.setEnabled(false);
        checkIN.setBackground(myColor);
        checkIN.addActionListener(e -> {
            if (checkIN.isSelected()) {

                String str = arrive_table.getValueAt(arrive_table.getSelectedRow(), 0).toString();
                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Do you collect the payment?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    try {

                        Connection con;
                        Statement st;
                        ResultSet rs;

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                        rs.first();

                        rs.updateString("Status", checkIN.getText());
                        String strRoomNo = rs.getString("Room No");

                        rs.updateRow();

                        Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs1 = st1.executeQuery("SELECT * FROM `users` WHERE `users`.`Staff ID` = '" + LoginWindow.user + "'");
                        rs1.first();

                        Date paymentDate = new Date();
                        SimpleDateFormat paymentFormat = new SimpleDateFormat("dd/MM/yyyy");

                        st.executeUpdate("INSERT INTO `payments` (`Res`, `Paid By`, `Recieved By`, `Date`, `Amount(=N=)`) VALUES ('" + rs.getString("Res") + "', '" + rs.getString("First Name") + " " + rs.getString("Last Name") + "', '" + rs1.getString("First Name") + " " + rs1.getString("Last Name") + "', '" + paymentFormat.format(paymentDate) + "', '" + rs.getString("Total Prize") + "')");

                        JOptionPane.showMessageDialog(null, "Checked in.");

                        centerPaneContainer.removeAll();
                        centerPaneContainer.add(expected_Arrive_Depart());

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `rooms` WHERE `Room No`='" + strRoomNo + "'");

                        rs.first();

                        rs.updateString("Status", "Occupied");

                        rs.updateRow();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    payed.setSelected(false);
                    checkIN.setSelected(false);
                    checkIN.setEnabled(false);
                }
            }
        });

        payed = new JCheckBox("Payed");
        payed.setFont(addInfoFont);
        payed.setForeground(Color.RED);
        payed.setEnabled(false);
        payed.setBackground(myColor);
        payed.addActionListener(e -> {
            if (payed.isSelected()) {
                checkIN.setEnabled(true);
            }
        });

        postponedButton_1 = new JButton("Postponed");
        postponedButton_1.setEnabled(false);
        postponedButton_1.addActionListener(e -> {
            PostponeReservation.resColumnData = arrive_table.getValueAt(arrive_table.getSelectedRow(), 0).toString();
            new PostponeReservation();
        });

        viewButton_1 = new JButton("View");
        viewButton_1.setPreferredSize(postponedButton_1.getPreferredSize());
        viewButton_1.setEnabled(false);
        viewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewQuest.viewColumnData = arrive_table.getValueAt(arrive_table.getSelectedRow(), 0).toString();
                new ViewQuest();
            }
        });

        editButton = new JButton("Edit");
        editButton.setPreferredSize(postponedButton_1.getPreferredSize());
        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditReservation.resColumnData = arrive_table.getValueAt(arrive_table.getSelectedRow(), 0).toString();
                new EditReservation();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(postponedButton_1.getPreferredSize());
        cancelButton.setEnabled(false);
        cancelButton.setBackground(new Color(80, 169, 169));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String str = arrive_table.getValueAt(arrive_table.getSelectedRow(), 0).toString();
                String str2 = arrive_table.getValueAt(arrive_table.getSelectedRow(), 6).toString();

                int choice;

                choice = JOptionPane.showConfirmDialog(null, "Do you want to cancel the reservation?", "Warning", JOptionPane.YES_NO_OPTION);

                if (choice == 0) {

                    try {

                        Connection con;
                        Statement st;
                        ResultSet rs;

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                        rs.first();

                        rs.updateString("Status", "Canceled");
                        rs.updateString("CancelDate", (new SimpleDateFormat("dd/MM/yyyy").format(new Date())).toString());

                        rs.updateRow();
                        rs.close();
                        st.close();

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        st.executeUpdate("UPDATE `rooms` SET `Status` = 'Available' WHERE `Room No`='" + str2 + "'");


                        JOptionPane.showMessageDialog(null, "Reservation canceled!");

                        centerPaneContainer.removeAll();
                        centerPaneContainer.add(expected_Arrive_Depart());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        arrive_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    if (arrive_table.getValueAt(arrive_table.getSelectedRow(), 8).toString().equals("Checked")) {
                        payed.setSelected(true);
                        payed.setEnabled(false);

                        checkIN.setSelected(true);
                        checkIN.setEnabled(false);

                        cancelButton.setEnabled(false);

                    } else if (arrive_table.getValueAt(arrive_table.getSelectedRow(), 8).toString().equals("Canceled")) {
                        payed.setSelected(false);
                        payed.setEnabled(false);

                        checkIN.setSelected(false);
                        checkIN.setEnabled(false);

                        cancelButton.setEnabled(false);
                    } else {
                        payed.setSelected(false);
                        payed.setEnabled(true);

                        checkIN.setSelected(false);
                        checkIN.setEnabled(false);

                        cancelButton.setEnabled(true);
                    }

                    depart_table.getSelectionModel().clearSelection();

                    checkOut.setEnabled(false);
                    postponedButton_2.setEnabled(false);
                    viewButton_2.setEnabled(false);

                    postponedButton_1.setEnabled(true);
                    viewButton_1.setEnabled(true);
                    editButton.setEnabled(true);
                }
            }
        });

        reserveButtonPane.add(arrival_RNo);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(arrival_RNoValue);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(75, 0)));
        reserveButtonPane.add(payed);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(checkIN);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(viewButton_1);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(postponedButton_1);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(editButton);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(cancelButton);

        centerUpPane.add(arrive_tableContainer);
        centerUpPane.add(reserveButtonPane);

        centerPane.add(centerUpPane);
        centerPane.add(Box.createRigidArea(new Dimension(0, 10)));

        final JPanel centerDownPane = new JPanel();
        centerDownPane.setLayout(new BoxLayout(centerDownPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerMiddleBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " DEPARTURES ");
        centerMiddleBorder.setTitleJustification(TitledBorder.CENTER);
        centerDownPane.setBorder(centerMiddleBorder);
        centerDownPane.setBackground(myColor);

        Vector<String> departureColumnNames = new Vector<String>();
        Vector<Vector<Object>> departureData = new Vector<Vector<Object>>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT `Res`, `First Name`, `Last Name`, `ID Type`, `ID No`, `Arrival`, `Departure` FROM `reservations` WHERE `Departure`='" + formatedDate + "' AND `Status`='" + "Checked" + "' ORDER BY `Res`");
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                departureColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                departureData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        depart_table = new JTable(departureData, departureColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        depart_table.getTableHeader().setReorderingAllowed(false);
        depart_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        depart_table.setFont(new Font("Lucida Console", Font.PLAIN, 11));
        depart_table.setForeground(new Color(94, 80, 80));
        depart_table.setGridColor(new Color(223, 223, 223));
        depart_table.setShowGrid(true);
        depart_table.setShowVerticalLines(false);
        depart_table.setOpaque(false);

        TableColumn departTablecol = null;
        for (int i = 0; i < depart_table.getColumnCount(); i++) {
            if (i == 2 || i == 3 || i == 5 || i == 6) departTablecol.setPreferredWidth(80);
            else if (i == 4) departTablecol.setPreferredWidth(180);
            else if (i == 1 || i == 7 || i == 8) departTablecol.setPreferredWidth(50);
            else if (i == 9) departTablecol.setPreferredWidth(110);
            departTablecol = depart_table.getColumnModel().getColumn(i);
            departTablecol.setCellRenderer(new CustomTableCellRenderer());
            departTablecol.setResizable(false);
        }

        JTableHeader departHeader = depart_table.getTableHeader();
        departHeader.setBackground(Color.GREEN);

        JScrollPane depart_tableContainer = new JScrollPane(depart_table);
        depart_tableContainer.getViewport().setBackground(contentColor);

        JPanel departButtonPane = new JPanel();
        departButtonPane.setLayout(new BoxLayout(departButtonPane, BoxLayout.LINE_AXIS));
        departButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        departButtonPane.add(Box.createHorizontalGlue());
        departButtonPane.setBackground(myColor);

        departure_RNo = new JLabel("Reservation No");
        departure_RNo.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        departure_RNo.setForeground(Color.RED);

        departure_RNoValue = new JTextField(7);
        departure_RNoValue.setBackground(textFieldColor);
        departure_RNoValue.setMinimumSize(new Dimension(90, 30));
        departure_RNoValue.setPreferredSize(new Dimension(90, 30));
        departure_RNoValue.setMaximumSize(new Dimension(90, 30));
        departure_RNoValue.setEnabled(false);
        departure_RNoValue.addActionListener(e -> {
            if (getRowByValue(departure_RNoValue.getText(), depart_table) != 0) {
                depart_table.requestFocus();
                depart_table.changeSelection(getRowByValue(departure_RNoValue.getText(), depart_table), 0, false, false);

                checkOut.setEnabled(true);
                postponedButton_2.setEnabled(true);
                viewButton_2.setEnabled(true);

                departure_RNoValue.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! Reservation number not found");
                departure_RNoValue.setText("");
            }
        });

        checkOut = new JCheckBox("Check Out");
        checkOut.setFont(addInfoFont);
        checkOut.setForeground(Color.RED);
        checkOut.setEnabled(false);
        checkOut.setBackground(myColor);
        checkOut.addActionListener(e -> {
            if (checkOut.isSelected()) {

                String str = depart_table.getValueAt(depart_table.getSelectedRow(), 0).toString();

                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Do you verify the payment?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    try {

                        Connection con;
                        Statement st;
                        ResultSet rs;

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                        rs.first();

                        rs.updateString("Status", "Checked Out");

                        String strRoomNo = rs.getString("Room No");

                        rs.updateRow();

                        JOptionPane.showMessageDialog(null, "Checked out!");

                        centerPaneContainer.removeAll();
                        centerPaneContainer.add(expected_Arrive_Depart());

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `rooms` WHERE `Room No`='" + strRoomNo + "'");

                        rs.first();

                        rs.updateString("Status", "Available");

                        rs.updateRow();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    checkOut.setSelected(false);
                }
            }
        });

        JButton refresh = new JButton("Refresh All");
        refresh.addActionListener(e -> home.doClick());

        postponedButton_2 = new JButton("Postponed");
        postponedButton_2.setPreferredSize(refresh.getPreferredSize());
        postponedButton_2.setEnabled(false);
        postponedButton_2.addActionListener(e -> {
            PostponeReservation.resColumnData = depart_table.getValueAt(depart_table.getSelectedRow(), 0).toString();
            new PostponeReservation();
            PostponeButton.arriveDateValue.setEnabled(false);
        });

        viewButton_2 = new JButton("View");
        viewButton_2.setPreferredSize(refresh.getPreferredSize());
        viewButton_2.setEnabled(false);
        viewButton_2.addActionListener(e -> {
            ViewQuest.viewColumnData = depart_table.getValueAt(depart_table.getSelectedRow(), 0).toString();
            new ViewQuest();
        });

        depart_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {

                    arrive_table.getSelectionModel().clearSelection();

                    payed.setEnabled(false);
                    checkIN.setSelected(false);
                    cancelButton.setEnabled(false);
                    postponedButton_1.setEnabled(false);
                    viewButton_1.setEnabled(false);
                    editButton.setEnabled(false);

                    checkOut.setEnabled(true);
                    postponedButton_2.setEnabled(true);
                    viewButton_2.setEnabled(true);
                }
            }
        });

        departButtonPane.add(departure_RNo);
        departButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        departButtonPane.add(departure_RNoValue);
        departButtonPane.add(Box.createRigidArea(new Dimension(250, 0)));
        departButtonPane.add(checkOut);
        departButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        departButtonPane.add(viewButton_2);
        departButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        departButtonPane.add(postponedButton_2);
        departButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        departButtonPane.add(refresh);

        centerDownPane.add(depart_tableContainer);
        centerDownPane.add(departButtonPane);

        centerPane.add(centerDownPane);
        return centerPane;
    }

    public static void resAdditionalInfo() {
        String resColumnData = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();

        try {

            Connection con;
            Statement st;
            ResultSet rs;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + resColumnData + "' ORDER BY `Res`");

            rs.first();

            JLabel l3 = new JLabel("Reservation Date :");
            l3.setFont(addInfoFont);

            JLabel l1 = new JLabel("Reservation No :");
            l1.setFont(addInfoFont);
            l1.setPreferredSize(l3.getPreferredSize());

            JLabel l2 = new JLabel(rs.getString("Res"));
            l2.setFont(addInfoFont);
            l2.setForeground(Color.RED);
            l2.setAlignmentX(-5);
            l2.setPreferredSize(l3.getPreferredSize());

            JLabel l4 = new JLabel(rs.getString("RDate"));
            l4.setFont(addInfoFont);
            l4.setForeground(Color.RED);
            l4.setPreferredSize(l3.getPreferredSize());

            JLabel l5 = new JLabel("Guest ID Type :");
            l5.setFont(addInfoFont);
            l5.setPreferredSize(l3.getPreferredSize());

            JLabel l6 = new JLabel(rs.getString("ID Type"));
            l6.setFont(addInfoFont);
            l6.setForeground(Color.RED);
            l6.setPreferredSize(l3.getPreferredSize());

            JLabel l7 = new JLabel("Guest ID No :");
            l7.setFont(addInfoFont);
            l7.setPreferredSize(l3.getPreferredSize());

            JLabel l8 = new JLabel(rs.getString("ID No"));
            l8.setFont(addInfoFont);
            l8.setForeground(Color.RED);
            l8.setPreferredSize(l3.getPreferredSize());

            JLabel l11 = new JLabel("Room No :");
            l11.setFont(addInfoFont);
            l11.setPreferredSize(l3.getPreferredSize());

            JLabel l12 = new JLabel(rs.getString("Room No"));
            l12.setFont(addInfoFont);
            l12.setForeground(Color.RED);
            l12.setPreferredSize(l3.getPreferredSize());

            JLabel l15 = new JLabel("Prize :");
            l15.setFont(addInfoFont);
            l15.setPreferredSize(l3.getPreferredSize());

            JLabel l16 = new JLabel("=N= " + rs.getString("Room Rate"));
            l16.setFont(addInfoFont);
            l16.setForeground(Color.RED);
            l16.setPreferredSize(l3.getPreferredSize());

            JLabel l17 = new JLabel("Arrival Date :");
            l17.setFont(addInfoFont);
            l17.setPreferredSize(l3.getPreferredSize());

            JLabel l18 = new JLabel(rs.getString("Arrival"));
            l18.setFont(addInfoFont);
            l18.setForeground(Color.RED);
            l18.setPreferredSize(l3.getPreferredSize());

            JLabel l19 = new JLabel("Departure Date :");
            l19.setFont(addInfoFont);
            l19.setPreferredSize(l3.getPreferredSize());

            JLabel l20 = new JLabel(rs.getString("Departure"));
            l20.setFont(addInfoFont);
            l20.setForeground(Color.RED);
            l20.setPreferredSize(l3.getPreferredSize());

            centerDownPane.repaint();
            centerDownPane.removeAll();

            GridBagConstraints c = new GridBagConstraints();

            c.insets = new Insets(10, 0, 10, 40);
            c.gridx = 0;
            c.gridy = 0;
            centerDownPane.add(l1, c);

            c.gridx = 1;
            c.gridy = 0;
            centerDownPane.add(l2, c);

            c.gridx = 2;
            c.gridy = 0;
            centerDownPane.add(l3, c);

            c.gridx = 3;
            c.gridy = 0;
            centerDownPane.add(l4, c);

            c.gridx = 0;
            c.gridy = 1;
            centerDownPane.add(l5, c);

            c.gridx = 1;
            c.gridy = 1;
            centerDownPane.add(l6, c);

            c.gridx = 2;
            c.gridy = 1;
            centerDownPane.add(l7, c);

            c.gridx = 3;
            c.gridy = 1;
            centerDownPane.add(l8, c);

            c.gridx = 0;
            c.gridy = 2;
            centerDownPane.add(l11, c);

            c.gridx = 1;
            c.gridy = 2;
            centerDownPane.add(l12, c);

            c.gridx = 2;
            c.gridy = 2;
            centerDownPane.add(l15, c);

            c.gridx = 3;
            c.gridy = 2;
            centerDownPane.add(l16, c);

            c.gridx = 0;
            c.gridy = 4;
            centerDownPane.add(l17, c);

            c.gridx = 1;
            c.gridy = 4;
            centerDownPane.add(l18, c);

            c.gridx = 2;
            c.gridy = 4;
            centerDownPane.add(l19, c);

            c.gridx = 3;
            c.gridy = 4;
            centerDownPane.add(l20, c);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static JPanel allReservations() {

        final JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPane.setBackground(myColor);

        final JPanel centerDownPaneContainer = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(10, 10);

                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };
        centerDownPaneContainer.setBackground(contentColor);
        centerDownPaneContainer.setOpaque(false);

        final JPanel centerUpPane = new JPanel();
        centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " RESERVATIONS ");
        centerUpBorder.setTitleJustification(TitledBorder.CENTER);
        centerUpPane.setBorder(centerUpBorder);
        centerUpPane.setBackground(myColor);

        Vector<String> allReservationsColumnNames = new Vector<String>();
        Vector<Vector<Object>> allReservationsData = new Vector<Vector<Object>>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            String sql = "SELECT `Res`, `First Name`, `Last Name`, `Phone No`, `RDate`, `Status`, `Room No`  FROM `reservations` WHERE `Status`='" + "Waiting" + "' OR `Status`='" + "Checked" + "'ORDER BY `Res` DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                allReservationsColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                allReservationsData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        allReservations_table = new JTable(allReservationsData, allReservationsColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        allReservations_table.getTableHeader().setReorderingAllowed(false);
        allReservations_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allReservations_table.setOpaque(false);
        allReservations_table.setFont(tableFont);
        allReservations_table.setForeground(tableTextColor);
        allReservations_table.setGridColor(new Color(223, 223, 223));
        allReservations_table.setShowGrid(true);
        allReservations_table.setShowVerticalLines(false);

        TableColumn allReservationsTablecol = null;
        for (int i = 0; i < allReservations_table.getColumnCount(); i++) {
            if (i == 1) allReservationsTablecol.setPreferredWidth(60);
            else if (i == 2 || i == 3) allReservationsTablecol.setPreferredWidth(120);
            else if (i == 4 || i == 5) allReservationsTablecol.setPreferredWidth(120);
            else if (i == 6 || i == 7) allReservationsTablecol.setPreferredWidth(100);
            allReservationsTablecol = allReservations_table.getColumnModel().getColumn(i);
            allReservationsTablecol.setCellRenderer(new CustomTableCellRenderer());
            allReservationsTablecol.setResizable(false);
        }

        JTableHeader allReservataionHeader = allReservations_table.getTableHeader();
        allReservataionHeader.setBackground(Color.ORANGE);

        final JScrollPane allReservataionTableContainer = new JScrollPane(allReservations_table);
        allReservataionTableContainer.setMinimumSize(new Dimension(1300, 270));
        allReservataionTableContainer.setPreferredSize(new Dimension(1300, 270));
        allReservataionTableContainer.setMaximumSize(new Dimension(11300, 270));
        allReservataionTableContainer.getViewport().setBackground(contentColor);

        final JPanel reserveButtonPane = new JPanel();
        reserveButtonPane.setLayout(new BoxLayout(reserveButtonPane, BoxLayout.LINE_AXIS));
        reserveButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
        reserveButtonPane.add(Box.createHorizontalGlue());
        reserveButtonPane.setBackground(myColor);

        JLabel searchQuest = new JLabel("Search Guest");
        searchQuest.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        searchQuest.setForeground(Color.RED);

        final JTextField searchQuestValue = new JTextField(7);
        searchQuestValue.setBackground(textFieldColor);
        searchQuestValue.setMinimumSize(new Dimension(90, 30));
        searchQuestValue.setPreferredSize(new Dimension(90, 30));
        searchQuestValue.setMaximumSize(new Dimension(90, 30));

        final JButton addReservation = new JButton("Add New");

        addReservation.addActionListener(e -> {
            final Reservation addRes = new Reservation();

            JPanel down_ButtonPanel = new JPanel();
            down_ButtonPanel.setMaximumSize(new Dimension(550, 70));
            down_ButtonPanel.setPreferredSize(new Dimension(550, 70));
            down_ButtonPanel.setMinimumSize(new Dimension(550, 70));
            down_ButtonPanel.setLayout(new BoxLayout(down_ButtonPanel, BoxLayout.LINE_AXIS));
            down_ButtonPanel.add(Box.createHorizontalGlue());
            down_ButtonPanel.setOpaque(false);

            JButton add_Button = new JButton("Add");
            JButton cancel_Button = new JButton("Cancel");
            add_Button.setPreferredSize(cancel_Button.getPreferredSize());

            add_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if ((addRes.fnameValue.getText()).equals("") || (addRes.lnameValue.getText()).equals("") || (addRes.phoneNumber.getText()).equals("") || (addRes.addressValue.getText()).equals("") || (addRes.idNumber_Value.getText()).equals("") || (addRes.arrivalDate_Value.getDate()).equals("") || (addRes.departureDate_Value.getDate()).equals("") || (addRes.roomRate_Value.getText()).equals("")) {
                        JOptionPane.showMessageDialog(null, "Form must be filled with all values before it can be submitted");
                    } else {

                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            rs = st.executeQuery("SELECT * FROM `reservations` ORDER BY `Res` DESC");

                            rs.moveToInsertRow();

                            rs.updateString("Res", addRes.reservation_Number.getText());
                            rs.updateString("First Name", addRes.fnameValue.getText().toUpperCase());
                            rs.updateString("Last Name", addRes.lnameValue.getText().toUpperCase());
                            rs.updateString("Gender", addRes.genderValues.getSelectedItem().toString());
                            rs.updateString("Phone No", addRes.phoneNumber.getText());
                            rs.updateString("Country", addRes.countryValues.getSelectedItem().toString());
                            rs.updateString("Address", addRes.addressValue.getText().toUpperCase());
                            rs.updateString("ID Type", addRes.idType_Value.getSelectedItem().toString());
                            rs.updateString("ID No", addRes.idNumber_Value.getText().toUpperCase());

                            rs.updateString("Arrival", addRes.arrivalDate_Value.returnStringDate());
                            rs.updateString("Departure", addRes.departureDate_Value.returnStringDate());

                            rs.updateString("Days", addRes.noOfDays_Value.getText());

                            rs.updateString("RDate", (new SimpleDateFormat("dd/MM/yyyy").format(new Date())).toString());

                            rs.updateString("Room Type", addRes.roomType_Value.getSelectedItem().toString());

                            String strRoomNo = addRes.roomNumber_Value.getSelectedItem().toString();
                            rs.updateString("Room No", strRoomNo);

                            rs.updateString("Room Rate", addRes.roomRate_Value.getText());
                            rs.updateString("Total Prize", addRes.totalPrizeValue.getText());

                            rs.updateString("Status", "Waiting");

                            rs.insertRow();

                            JOptionPane.showMessageDialog(null, "Record Inserted");
                            addRes.dispose();

                            rs.close();
                            st.close();

                            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            rs = st.executeQuery("SELECT * FROM `reservations` ORDER BY `Res`");

                            centerPaneContainer.removeAll();
                            centerPaneContainer.add(allReservations());


                            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            st.executeUpdate("UPDATE `rooms` SET `Status` = 'Reserved' WHERE `Room No` = '" + strRoomNo + "'");


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            cancel_Button.addActionListener(e1 -> {
                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Data not saved, do you want to cancel?", "Warning", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    addRes.dispose();
                    Reservation.reservationCounter -= 1;
                }
            });

            down_ButtonPanel.add(add_Button);
            down_ButtonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            down_ButtonPanel.add(cancel_Button);

            addRes.add(down_ButtonPanel);
            addRes.add(Box.createRigidArea(new Dimension(0, 10)));
            addRes.setVisible(true);

        });

        final JButton editReservation = new JButton("Edit");
        editReservation.setEnabled(false);
        editReservation.setPreferredSize(addReservation.getPreferredSize());
        editReservation.addActionListener(e -> {

            EditReservation.resColumnData = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();

            new EditReservation();
        });

        final JButton viewPerson = new JButton("View Details");
        viewPerson.setEnabled(false);
        viewPerson.setBackground(new Color(80, 169, 169));
        viewPerson.addActionListener(e -> {
            ViewQuest.viewColumnData = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();
            new ViewQuest();
        });

        final JButton cancelButton2 = new JButton("Cancel");
        cancelButton2.setPreferredSize(addReservation.getPreferredSize());
        cancelButton2.setEnabled(false);
        cancelButton2.setBackground(new Color(80, 169, 169));
        cancelButton2.addActionListener(e -> {

            String str = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();
            String str2 = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 6).toString();

            int choice;

            choice = JOptionPane.showConfirmDialog(null, "Do you want to cancel the reservation?", "Warning", JOptionPane.YES_NO_OPTION);

            if (choice == 0) {
                try {

                    Connection con;
                    Statement st;
                    ResultSet rs;

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                    rs.first();

                    rs.updateString("Status", "Canceled");
                    rs.updateString("CancelDate", (new SimpleDateFormat("dd/MM/yyyy").format(new Date())).toString());

                    rs.updateRow();
                    rs.close();
                    st.close();

                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    st.executeUpdate("UPDATE `rooms` SET `Status` = 'Available' WHERE `Room No`='" + str2 + "'");


                    JOptionPane.showMessageDialog(null, "Reservation canceled!");

                    centerPaneContainer.removeAll();
                    centerPaneContainer.add(expected_Arrive_Depart());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton refresh = new JButton("Refresh All");
        refresh.addActionListener(e -> reservation.doClick());

        searchQuestValue.addActionListener(e -> {
            if (getRowByValue(searchQuestValue.getText(), allReservations_table) != 0) {
                allReservations_table.requestFocus();
                allReservations_table.changeSelection(getRowByValue(searchQuestValue.getText(), allReservations_table), 0, false, false);

                editReservation.setEnabled(true);
                viewPerson.setEnabled(true);

                String str = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();

                try {

                    Connection con;
                    Statement st;
                    ResultSet rs;

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                    rs.first();

                    if (rs.getString("Status").equals("Waiting")) {
                        cancelButton2.setEnabled(true);
                    } else {
                        cancelButton2.setEnabled(false);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                resAdditionalInfo();

                searchQuestValue.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Sorry! Reservation number not found");
                searchQuestValue.setText("");
            }
        });

        allReservations_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    editReservation.setEnabled(true);
                    viewPerson.setEnabled(true);

                    String str = allReservations_table.getValueAt(allReservations_table.getSelectedRow(), 0).toString();

                    try {

                        Connection con;
                        Statement st;
                        ResultSet rs;

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Res`='" + str + "'");

                        rs.first();

                        if (rs.getString("Status").equals("Waiting")) {
                            cancelButton2.setEnabled(true);
                        } else {
                            cancelButton2.setEnabled(false);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        reserveButtonPane.add(searchQuest);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(searchQuestValue);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(150, 0)));
        reserveButtonPane.add(addReservation);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(editReservation);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(cancelButton2);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(viewPerson);
        reserveButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        reserveButtonPane.add(refresh);

        centerUpPane.add(allReservataionTableContainer);
        centerUpPane.add(reserveButtonPane);

        centerPane.add(centerUpPane);
        centerPane.add(Box.createRigidArea(new Dimension(0, 10)));

        centerDownPane = new JPanel();
        TitledBorder centerDownBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ADDITIONAL INFORMATION ");
        centerDownBorder.setTitleJustification(TitledBorder.CENTER);
        centerDownPane.setBorder(centerDownBorder);
        centerDownPane.setLayout(new GridBagLayout());
        centerDownPane.setOpaque(false);
        centerDownPane.setMinimumSize(new Dimension(850, 210));
        centerDownPane.setPreferredSize(new Dimension(850, 210));
        centerDownPane.setMaximumSize(new Dimension(850, 210));

        allReservations_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    resAdditionalInfo();
                }
            }
        });

        centerDownPaneContainer.add(centerDownPane);
        centerPane.add(centerDownPaneContainer);
        return centerPane;
    }

    public static JPanel adminCenterPane() {

        final JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPane.setBackground(myColor);

        JPanel centerUpPane = new JPanel();
        centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " STAFFS MANAGEMENT");
        centerUpBorder.setTitleJustification(TitledBorder.CENTER);
        centerUpPane.setBorder(centerUpBorder);
        centerUpPane.setBackground(myColor);

        Vector<String> staffColumnNames = new Vector<String>();
        Vector<Vector<Object>> staffData = new Vector<Vector<Object>>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            String sql = "SELECT `Staff ID`, `First Name`, `Last Name`, `Address`, `Phone No`, `User Type`, `Work Days` FROM `users` ORDER BY `Staff ID`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                staffColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                staffData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        final JTable staffTable = new JTable(staffData, staffColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        staffTable.setOpaque(false);
        staffTable.getTableHeader().setReorderingAllowed(false);
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staffTable.setFont(tableFont);
        staffTable.setForeground(tableTextColor);
        staffTable.setGridColor(new Color(223, 223, 223));
        staffTable.setShowGrid(true);
        staffTable.setShowVerticalLines(false);

        TableColumn staffTablecol = null;
        for (int i = 0; i < staffTable.getColumnCount(); i++) {
            if (i == 1) staffTablecol.setPreferredWidth(10);
            else if (i == 2 || i == 3 || i == 5) staffTablecol.setPreferredWidth(40);
            else if (i == 4) staffTablecol.setPreferredWidth(120);
            else if (i == 6) staffTablecol.setPreferredWidth(30);
            else if (i == 7) staffTablecol.setPreferredWidth(70);
            staffTablecol = staffTable.getColumnModel().getColumn(i);
            staffTablecol.setCellRenderer(new CustomTableCellRenderer());
            staffTablecol.setResizable(false);
        }

        JTableHeader staffTabelHeader = staffTable.getTableHeader();
        staffTabelHeader.setBackground(Color.RED);

        JScrollPane staffTableContainer = new JScrollPane(staffTable);
        staffTableContainer.getViewport().setBackground(contentColor);
        staffTableContainer.setMinimumSize(new Dimension(1100, 505));
        staffTableContainer.setPreferredSize(new Dimension(1100, 505));
        staffTableContainer.setMaximumSize(new Dimension(1100, 505));

        JPanel staffButtonPane = new JPanel();
        staffButtonPane.setLayout(new BoxLayout(staffButtonPane, BoxLayout.LINE_AXIS));
        staffButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 5));
        staffButtonPane.add(Box.createHorizontalGlue());
        staffButtonPane.setBackground(myColor);

        viewRooms = new JButton("Rooms");
        viewRooms.setBackground(new Color(198, 192, 128));
        viewRooms.addActionListener(e -> {

            final JPanel centerUpPane1 = new JPanel();
            centerUpPane1.setLayout(new BoxLayout(centerUpPane1, BoxLayout.PAGE_AXIS));
            TitledBorder centerUpBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ROOMS MANAGEMENT ");
            centerUpBorder1.setTitleJustification(TitledBorder.CENTER);
            centerUpPane1.setBorder(centerUpBorder1);
            centerUpPane1.setBackground(myColor);

            final Vector<String> reservedRoomsColumnNames = new Vector<String>();
            final Vector<Vector<Object>> reservedRoomsData = new Vector<Vector<Object>>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                String sql = "SELECT * FROM `rooms` ORDER BY `Room No`";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                for (int i = 1; i <= columns; i++) {
                    reservedRoomsColumnNames.addElement(md.getColumnName(i));
                }
                while (rs.next()) {
                    Vector<Object> row = new Vector<Object>(columns);
                    for (int i = 1; i <= columns; i++) {
                        row.addElement(rs.getObject(i));
                    }
                    reservedRoomsData.addElement(row);
                }
                rs.close();
                stmt.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            JTable roomTable = new JTable(reservedRoomsData, reservedRoomsColumnNames) {
                private static final long serialVersionUID = 1L;

                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            roomTable.setOpaque(false);
            roomTable.getTableHeader().setReorderingAllowed(false);
            roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            roomTable.setFont(tableFont);
            roomTable.setForeground(tableTextColor);
            roomTable.setGridColor(new Color(223, 223, 223));
            roomTable.setShowGrid(true);
            roomTable.setShowVerticalLines(false);

            TableColumn roomTablecol = null;

            for (int i = 0; i < roomTable.getColumnCount(); i++) {
                if (i == 1 || i == 4 || i == 3) {
                    roomTablecol.setMinWidth(80);
                    roomTablecol.setPreferredWidth(80);
                    roomTablecol.setMaxWidth(80);
                } else if (i == 2) {
                    roomTablecol.setMinWidth(120);
                    roomTablecol.setPreferredWidth(120);
                    roomTablecol.setMaxWidth(120);
                } else if (i == 5) {
                    roomTablecol.setMinWidth(550);
                    roomTablecol.setPreferredWidth(550);
                    roomTablecol.setMaxWidth(550);
                }
                roomTablecol = roomTable.getColumnModel().getColumn(i);
                roomTablecol.setCellRenderer(new CustomTableCellRenderer());
                roomTablecol.setResizable(false);
            }

            JTableHeader roomHeader = roomTable.getTableHeader();
            roomHeader.setBackground(new Color(80, 169, 169));

            final JScrollPane roomTableContainer = new JScrollPane(roomTable);
            roomTableContainer.getViewport().setBackground(contentColor);
            roomTableContainer.setMinimumSize(new Dimension(1100, 505));
            roomTableContainer.setPreferredSize(new Dimension(1100, 505));
            roomTableContainer.setMaximumSize(new Dimension(1100, 505));

            centerUpPane1.add(roomTableContainer);

            JButton viewStaffs = new JButton("Staffs");
            viewStaffs.setBackground(new Color(198, 192, 128));
            viewStaffs.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    centerPaneContainer.removeAll();
                    centerPaneContainer.add(adminCenterPane());
                    centerPaneContainer.repaint();
                }
            });

            JButton addRooms = new JButton("Add");
            addRooms.setPreferredSize(viewStaffs.getPreferredSize());
            addRooms.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new AddRooms();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.setBackground(myColor);

            JButton cancel_Button = new JButton("Cancel");
            JButton add_Button = new JButton("Add");
            add_Button.setPreferredSize(cancel_Button.getPreferredSize());

            buttonPanel.add(viewStaffs);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPanel.add(addRooms);

            centerPane.removeAll();
            centerPane.add(centerUpPane1);
            centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPane.add(buttonPanel);

        });

        final JButton editStaff = new JButton("Edit");
        editStaff.setPreferredSize(viewRooms.getPreferredSize());
        editStaff.setEnabled(false);
        editStaff.addActionListener(e -> {
            EditStaffs.staffsColumnData = staffTable.getValueAt(staffTable.getSelectedRow(), 0).toString();
            new EditStaffs();
        });

        JButton addStaff = new JButton("Add");
        addStaff.setPreferredSize(viewRooms.getPreferredSize());
        addStaff.addActionListener(e -> new NewStaff().setVisible(true));

        staffTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    editStaff.setEnabled(true);
                }
            }
        });

        staffButtonPane.add(viewRooms);
        staffButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        staffButtonPane.add(editStaff);
        staffButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        staffButtonPane.add(addStaff);

        centerUpPane.add(staffTableContainer);

        centerPane.add(centerUpPane);
        centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPane.add(staffButtonPane);
        centerPane.repaint();

        return centerPane;
    }

    public static JPanel userAccount() {

        JPanel userDetails = new JPanel();
        userDetails.setLayout(new BoxLayout(userDetails, BoxLayout.PAGE_AXIS));
        userDetails.setMaximumSize(new Dimension(850, 600));
        userDetails.setPreferredSize(new Dimension(850, 600));
        userDetails.setMaximumSize(new Dimension(850, 600));
        userDetails.setOpaque(false);

        JLabel userLogo = new JLabel(new ImageIcon("res/waiter1.png"));

        JPanel imagePanel = new JPanel();
        imagePanel.setMaximumSize(new Dimension(550, 150));
        imagePanel.setPreferredSize(new Dimension(550, 150));
        imagePanel.setMaximumSize(new Dimension(550, 150));
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setOpaque(false);

        JLabel smallLogo = new JLabel(new ImageIcon(LoginWindow.class.getResource("/icons/myLogo.png")));

        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 0;
        imagePanel.add(smallLogo, c5);

        JLabel staffNameValue = new JLabel();
        staffNameValue.setForeground(new Color(163, 175, 39));
        staffNameValue.setFont(new Font("Costantia", Font.BOLD, 22));

        JPanel staffId = new JPanel();
        staffId.setLayout(new GridBagLayout());
        staffId.setMaximumSize(new Dimension(200, 110));
        staffId.setPreferredSize(new Dimension(200, 110));
        staffId.setMaximumSize(new Dimension(200, 110));
        staffId.setOpaque(false);

        JPanel centerDownPaneContainer = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(10, 10);

                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };
        centerDownPaneContainer.setBackground(new Color(223, 222, 212));
        centerDownPaneContainer.setOpaque(false);

        JPanel staffAcc = new JPanel();
        TitledBorder staffInfoBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " USER INFORMATION ");
        staffInfoBorder.setTitleJustification(TitledBorder.CENTER);
        staffAcc.setBorder(staffInfoBorder);
        staffAcc.setLayout(new GridBagLayout());
        staffAcc.setOpaque(false);

        JLabel staffID = new JLabel("Staff ID : ");
        staffID.setMinimumSize(new Dimension(100, 20));
        staffID.setPreferredSize(new Dimension(100, 20));
        staffID.setMaximumSize(new Dimension(100, 20));
        staffID.setFont(MainFrame.addInfoFont);

        JLabel staffIDValue = new JLabel();
        staffIDValue.setMinimumSize(new Dimension(150, 20));
        staffIDValue.setPreferredSize(new Dimension(150, 20));
        staffIDValue.setFont(MainFrame.addInfoFont);
        staffIDValue.setForeground(Color.RED);

        JLabel staffAddress = new JLabel("Address : ");
        staffAddress.setMinimumSize(new Dimension(100, 20));
        staffAddress.setPreferredSize(new Dimension(100, 20));
        staffAddress.setMaximumSize(new Dimension(100, 20));
        staffAddress.setFont(MainFrame.addInfoFont);

        JLabel staffAddressValue = new JLabel();
        staffAddressValue.setPreferredSize(new Dimension(300, 20));
        staffAddressValue.setFont(MainFrame.addInfoFont);
        staffAddressValue.setForeground(Color.RED);

        JLabel staffPhoneNo = new JLabel("Phone No. : ");
        staffPhoneNo.setMinimumSize(new Dimension(100, 20));
        staffPhoneNo.setPreferredSize(new Dimension(100, 20));
        staffPhoneNo.setMaximumSize(new Dimension(100, 20));
        staffPhoneNo.setFont(MainFrame.addInfoFont);

        JLabel staffPhoneNoValue = new JLabel();
        staffPhoneNoValue.setMinimumSize(new Dimension(150, 20));
        staffPhoneNoValue.setPreferredSize(new Dimension(150, 20));
        staffPhoneNoValue.setFont(MainFrame.addInfoFont);
        staffPhoneNoValue.setForeground(Color.RED);

        JLabel staffGender = new JLabel("Gender : ");
        staffGender.setMinimumSize(new Dimension(100, 20));
        staffGender.setPreferredSize(new Dimension(100, 20));
        staffGender.setMaximumSize(new Dimension(100, 20));
        staffGender.setFont(MainFrame.addInfoFont);

        JLabel staffGenderValue = new JLabel();
        staffGenderValue.setMinimumSize(new Dimension(300, 20));
        staffGenderValue.setPreferredSize(new Dimension(300, 20));
        staffGenderValue.setMaximumSize(new Dimension(300, 20));
        staffGenderValue.setFont(MainFrame.addInfoFont);
        staffGenderValue.setForeground(Color.RED);

        JLabel userType = new JLabel("Type : ");
        userType.setMinimumSize(new Dimension(100, 20));
        userType.setPreferredSize(new Dimension(100, 20));
        userType.setMaximumSize(new Dimension(100, 20));
        userType.setFont(MainFrame.addInfoFont);

        JLabel userTypeValue = new JLabel();
        userTypeValue.setMinimumSize(new Dimension(150, 20));
        userTypeValue.setPreferredSize(new Dimension(150, 20));
        userTypeValue.setFont(MainFrame.addInfoFont);
        userTypeValue.setForeground(Color.RED);

        JLabel workDays = new JLabel("Work Days : ");
        workDays.setMinimumSize(new Dimension(100, 20));
        workDays.setPreferredSize(new Dimension(100, 20));
        workDays.setMaximumSize(new Dimension(100, 20));
        workDays.setFont(MainFrame.addInfoFont);

        JLabel workDaysValue = new JLabel();
        workDaysValue.setMinimumSize(new Dimension(300, 20));
        workDaysValue.setPreferredSize(new Dimension(300, 20));
        workDaysValue.setMaximumSize(new Dimension(300, 20));
        workDaysValue.setFont(MainFrame.addInfoFont);
        workDaysValue.setForeground(Color.RED);

        try {
            Connection con;
            Statement st;
            ResultSet rs;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("SELECT * FROM `users` WHERE `Staff ID`='" + LoginWindow.user + "'");

            rs.first();

            staffNameValue.setText(rs.getString("First Name") + " " + rs.getString("Last Name"));

            staffIDValue.setText(rs.getString("Staff ID"));
            staffAddressValue.setText(rs.getString("Address"));
            staffPhoneNoValue.setText(rs.getString("Phone No"));
            staffGenderValue.setText(rs.getString("Gender"));
            userTypeValue.setText(rs.getString("User Type"));
            workDaysValue.setText(rs.getString("Work Days"));

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 5, 5, 5);

        c1.gridx = 0;
        c1.gridy = 0;
        staffId.add(userLogo, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        staffId.add(staffNameValue, c1);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        staffAcc.add(staffID, c);

        c.gridx = 1;
        c.gridy = 0;
        staffAcc.add(staffIDValue, c);

        c.gridx = 2;
        c.gridy = 0;
        staffAcc.add(staffAddress, c);

        c.gridx = 3;
        c.gridy = 0;
        staffAcc.add(staffAddressValue, c);

        c.gridx = 0;
        c.gridy = 1;
        staffAcc.add(staffPhoneNo, c);

        c.gridx = 1;
        c.gridy = 1;
        staffAcc.add(staffPhoneNoValue, c);

        c.gridx = 2;
        c.gridy = 1;
        staffAcc.add(staffGender, c);

        c.gridx = 3;
        c.gridy = 1;
        staffAcc.add(staffGenderValue, c);

        c.gridx = 0;
        c.gridy = 2;
        staffAcc.add(userType, c);

        c.gridx = 1;
        c.gridy = 2;
        staffAcc.add(userTypeValue, c);

        c.gridx = 2;
        c.gridy = 2;
        staffAcc.add(workDays, c);

        c.gridx = 3;
        c.gridy = 2;
        staffAcc.add(workDaysValue, c);

        centerDownPaneContainer.add(staffAcc);

        JPanel changePassword = new JPanel();
        changePassword.setLayout(new BoxLayout(changePassword, BoxLayout.LINE_AXIS));
        changePassword.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 5));
        changePassword.add(Box.createHorizontalGlue());
        changePassword.setOpaque(false);

        JButton change = new JButton("Change Password");
        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ChangePassword();
            }
        });
        changePassword.add(change);

        userDetails.add(Box.createRigidArea(new Dimension(0, 30)));
        userDetails.add(imagePanel);
        userDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        userDetails.add(staffId);
        userDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        userDetails.add(centerDownPaneContainer);
        userDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        userDetails.add(changePassword);

        return userDetails;
    }

    public static JPanel paymentsPane() {

        final JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPane.setBackground(myColor);

        JPanel centerUpPane = new JPanel();
        centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " STAFFS MANAGEMENT");
        centerUpBorder.setTitleJustification(TitledBorder.CENTER);
        centerUpPane.setBorder(centerUpBorder);
        centerUpPane.setBackground(myColor);

        Vector<String> paymentColumnNames = new Vector<String>();
        Vector<Vector<Object>> paymentData = new Vector<Vector<Object>>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            String sql = "SELECT `Res`, `Paid By`, `Recieved By`, `Date`, `Amount(=N=)` FROM `payments` ORDER BY `Res` DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                paymentColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                paymentData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        final JTable paymentTable = new JTable(paymentData, paymentColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        paymentTable.setOpaque(false);
        paymentTable.getTableHeader().setReorderingAllowed(false);
        paymentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paymentTable.setFont(tableFont);
        paymentTable.setForeground(tableTextColor);
        paymentTable.setGridColor(new Color(223, 223, 223));
        paymentTable.setShowGrid(true);
        paymentTable.setShowVerticalLines(false);

        TableColumn paymentTablecol = null;
        for (int i = 0; i < paymentTable.getColumnCount(); i++) {
            if (i == 1 || i == 4 || i == 5) paymentTablecol.setPreferredWidth(20);
            else if (i == 2 || i == 3) paymentTablecol.setPreferredWidth(200);
            paymentTablecol = paymentTable.getColumnModel().getColumn(i);
            paymentTablecol.setCellRenderer(new CustomTableCellRenderer());
            paymentTablecol.setResizable(false);
        }

        JTableHeader paymentTableHeader = paymentTable.getTableHeader();
        paymentTableHeader.setBackground(Color.RED);

        JScrollPane paymentTableContainer = new JScrollPane(paymentTable);
        paymentTableContainer.getViewport().setBackground(contentColor);
        paymentTableContainer.setMinimumSize(new Dimension(1100, 505));
        paymentTableContainer.setPreferredSize(new Dimension(1100, 505));
        paymentTableContainer.setMaximumSize(new Dimension(1100, 505));

        JPanel paymentButtonPane = new JPanel();
        paymentButtonPane.setLayout(new BoxLayout(paymentButtonPane, BoxLayout.LINE_AXIS));
        paymentButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 5));
        paymentButtonPane.add(Box.createHorizontalGlue());
        paymentButtonPane.setBackground(myColor);

        centerUpPane.add(paymentTableContainer);

        centerPane.add(centerUpPane);
        centerPane.repaint();

        return centerPane;

    }

    @SuppressWarnings("serial")
    public JPanel addLeftPane() {

        JPanel leftPane = new JPanel();
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
        leftPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(239, 241, 236), new Color(239, 241, 236), null, null));

        JPanel leftUpPaneContainer = new JPanel();
        leftUpPaneContainer.setBackground(new Color(223, 222, 212));
        leftUpPaneContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JPanel leftUpPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(10, 10);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint
            }
        };
        leftUpPane.setBackground(new Color(240, 240, 240));
        leftUpPane.setLayout(new GridBagLayout());
        leftUpPane.setBounds(10, 10, 100, 30);
        leftUpPane.setOpaque(false);

        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Algerian Regular", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.RED);

        logo = new JLabel();
        MyClock clock = new MyClock();
        MyDate date_Today = new MyDate();
        final JXMonthView calendar = new JXMonthView();
        calendar.setBorder(new LineBorder(new Color(0, 0, 0)));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        leftUpPane.add(welcomeLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        leftUpPane.add(logo, c);

        c.gridx = 0;
        c.gridy = 2;
        leftUpPane.add(clock, c);

        c.gridx = 0;
        c.gridy = 3;
        leftUpPane.add(date_Today, c);

        c.gridx = 0;
        c.gridy = 4;
        leftUpPane.add(calendar, c);

        leftUpPaneContainer.add(leftUpPane);

        leftPane.add(leftUpPaneContainer);

        return leftPane;
    }

    public JPanel reservedRoomsCenterPane() {

        final JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPane.setBackground(myColor);

        viewCostomer = new JButton("View Details");
        viewCostomer.setBackground(new Color(80, 169, 169));
        viewCostomer.setEnabled(false);

        final JPanel centerUpPane = new JPanel();
        centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
        TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " RESERVED ROOMS ");
        centerUpBorder.setTitleJustification(TitledBorder.CENTER);
        centerUpPane.setBorder(centerUpBorder);
        centerUpPane.setBackground(myColor);

        final Vector<String> reservedRoomsColumnNames = new Vector<String>();
        final Vector<Vector<Object>> reservedRoomsData = new Vector<Vector<Object>>();

        try {
            String reserved = "Reserved";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

            String sql = "SELECT * FROM `rooms` WHERE `Status`='" + reserved + "' ORDER BY `Room No`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                reservedRoomsColumnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                reservedRoomsData.addElement(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        final JTable roomTable = new JTable(reservedRoomsData, reservedRoomsColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        roomTable.setOpaque(false);
        roomTable.getTableHeader().setReorderingAllowed(false);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.setFont(tableFont);
        roomTable.setForeground(tableTextColor);
        roomTable.setGridColor(new Color(223, 223, 223));
        roomTable.setShowGrid(true);
        roomTable.setShowVerticalLines(false);

        TableColumn roomTablecol = null;

        for (int i = 0; i < roomTable.getColumnCount(); i++) {
            if (i == 1 || i == 4 || i == 3) {
                roomTablecol.setMinWidth(80);
                roomTablecol.setPreferredWidth(80);
                roomTablecol.setMaxWidth(80);
            } else if (i == 2) {
                roomTablecol.setMinWidth(120);
                roomTablecol.setPreferredWidth(120);
                roomTablecol.setMaxWidth(120);
            } else if (i == 5) {
                roomTablecol.setMinWidth(550);
                roomTablecol.setPreferredWidth(550);
                roomTablecol.setMaxWidth(550);
            }
            roomTablecol = roomTable.getColumnModel().getColumn(i);
            roomTablecol.setCellRenderer(new CustomTableCellRenderer());
            roomTablecol.setResizable(false);
        }

        viewCostomer.addActionListener(e -> {
            ViewQuest.viewColumnData = l6.getText();
            new ViewQuest();
        });

        roomTable.getSelectionModel().clearSelection();

        JTableHeader roomHeader = roomTable.getTableHeader();
        roomHeader.setBackground(new Color(80, 169, 169));

        JScrollPane roomTableContainer = new JScrollPane(roomTable);
        roomTableContainer.getViewport().setBackground(contentColor);

        roomTableContainer.setMinimumSize(new Dimension(1300, 330));
        roomTableContainer.setPreferredSize(new Dimension(1300, 330));
        roomTableContainer.setMaximumSize(new Dimension(1300, 330));

        centerUpPane.add(roomTableContainer);

        JPanel centerDownPaneContainer = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(10, 10);

                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };
        centerDownPaneContainer.setBackground(contentColor);
        centerDownPaneContainer.setOpaque(false);

        final JPanel centerDownPane = new JPanel();
        TitledBorder centerDownBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ADDITIONAL INFORMATION ");
        centerDownBorder.setTitleJustification(TitledBorder.CENTER);
        centerDownBorder.setBorder(centerUpBorder);
        centerDownPane.setBorder(centerDownBorder);
        centerDownPane.setLayout(new GridBagLayout());
        centerDownPane.setOpaque(false);

        centerDownPane.setMinimumSize(new Dimension(850, 150));
        centerDownPane.setPreferredSize(new Dimension(850, 150));
        centerDownPane.setMaximumSize(new Dimension(850, 150));

        roomTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {

                    viewCostomer.setEnabled(true);

                    String roomColumnData = roomTable.getValueAt(roomTable.getSelectedRow(), 0).toString();

                    try {

                        Connection con;
                        Statement st;
                        ResultSet rs;

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");
                        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Room No`='" + roomColumnData + "'");

                        rs.first();

                        JLabel l9 = new JLabel("Expected Departure : ");
                        l9.setFont(addInfoFont);

                        JLabel l3 = new JLabel("Reserved Date : ");
                        l3.setFont(addInfoFont);
                        l3.setMinimumSize(l9.getPreferredSize());
                        l3.setPreferredSize(l9.getPreferredSize());
                        l3.setMaximumSize(l9.getPreferredSize());

                        JLabel l4 = new JLabel(rs.getString("RDate"));
                        l4.setFont(addInfoFont);
                        l4.setForeground(Color.RED);
                        l4.setMinimumSize(l9.getPreferredSize());
                        l4.setPreferredSize(l9.getPreferredSize());
                        l4.setMaximumSize(l9.getPreferredSize());

                        JLabel l5 = new JLabel("Reservation No :");
                        l5.setFont(addInfoFont);
                        l5.setMinimumSize(l9.getPreferredSize());
                        l5.setPreferredSize(l9.getPreferredSize());
                        l5.setMaximumSize(l9.getPreferredSize());

                        l6 = new JLabel(rs.getString("Res"));
                        l6.setFont(addInfoFont);
                        l6.setForeground(Color.RED);
                        l6.setMinimumSize(l9.getPreferredSize());
                        l6.setPreferredSize(l9.getPreferredSize());
                        l6.setMaximumSize(l9.getPreferredSize());

                        JLabel l7 = new JLabel("Expected Arrival : ");
                        l7.setFont(addInfoFont);
                        l7.setMinimumSize(l9.getPreferredSize());
                        l7.setPreferredSize(l9.getPreferredSize());
                        l7.setMaximumSize(l9.getPreferredSize());

                        JLabel l8 = new JLabel(rs.getString("Arrival"));
                        l8.setFont(addInfoFont);
                        l8.setForeground(Color.RED);
                        l8.setMinimumSize(l9.getPreferredSize());
                        l8.setPreferredSize(l9.getPreferredSize());
                        l8.setMaximumSize(l9.getPreferredSize());

                        JLabel l10 = new JLabel(rs.getString("Departure"));
                        l10.setFont(addInfoFont);
                        l10.setForeground(Color.RED);
                        l10.setPreferredSize(l9.getPreferredSize());
                        l10.setMinimumSize(l9.getPreferredSize());
                        l10.setPreferredSize(l9.getPreferredSize());
                        l10.setMaximumSize(l9.getPreferredSize());

                        JLabel l11 = new JLabel("Person Name : ");
                        l11.setFont(addInfoFont);
                        l11.setPreferredSize(l9.getPreferredSize());
                        l11.setMinimumSize(l9.getPreferredSize());
                        l11.setPreferredSize(l9.getPreferredSize());
                        l11.setMaximumSize(l9.getPreferredSize());

                        JLabel l12 = new JLabel(rs.getString("First Name") + " " + rs.getString("Last Name"));
                        l12.setFont(addInfoFont);
                        l12.setPreferredSize(l9.getPreferredSize());
                        l12.setForeground(Color.RED);
                        l12.setMinimumSize(l9.getPreferredSize());
                        l12.setPreferredSize(l9.getPreferredSize());
                        l12.setMaximumSize(l9.getPreferredSize());

                        centerDownPane.removeAll();
                        centerDownPane.repaint();

                        GridBagConstraints c = new GridBagConstraints();

                        c.insets = new Insets(10, 10, 10, 10);
                        c.gridx = 0;
                        c.gridy = 0;
                        centerDownPane.add(l3, c);

                        c.gridx = 1;
                        c.gridy = 0;
                        centerDownPane.add(l4, c);

                        c.gridx = 2;
                        c.gridy = 0;
                        centerDownPane.add(l5, c);

                        c.gridx = 3;
                        c.gridy = 0;
                        centerDownPane.add(l6, c);

                        c.gridx = 0;
                        c.gridy = 1;
                        centerDownPane.add(l7, c);

                        c.gridx = 1;
                        c.gridy = 1;
                        centerDownPane.add(l8, c);

                        c.gridx = 2;
                        c.gridy = 1;
                        centerDownPane.add(l9, c);

                        c.gridx = 3;
                        c.gridy = 1;
                        centerDownPane.add(l10, c);

                        c.gridx = 0;
                        c.gridy = 2;
                        centerDownPane.add(l11, c);

                        c.gridx = 1;
                        c.gridy = 2;
                        centerDownPane.add(l12, c);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        final JPanel roomButtonPane = new JPanel();
        roomButtonPane.setLayout(new BoxLayout(roomButtonPane, BoxLayout.LINE_AXIS));
        roomButtonPane.add(Box.createHorizontalGlue());
        roomButtonPane.setBackground(myColor);

        JButton allRooms = new JButton("All Rooms");
        allRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                viewCostomer.setEnabled(false);

                final JPanel centerUpPane = new JPanel();
                centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
                TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ALL ROOMS ");
                centerUpBorder.setTitleJustification(TitledBorder.CENTER);
                centerUpPane.setBorder(centerUpBorder);
                centerUpPane.setBackground(myColor);

                final Vector<String> reservedRoomsColumnNames = new Vector<String>();
                final Vector<Vector<Object>> reservedRoomsData = new Vector<Vector<Object>>();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                    String sql = "SELECT * FROM `rooms` ORDER BY `Room No`";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    for (int i = 1; i <= columns; i++) {
                        reservedRoomsColumnNames.addElement(md.getColumnName(i));
                    }
                    while (rs.next()) {
                        Vector<Object> row = new Vector<Object>(columns);
                        for (int i = 1; i <= columns; i++) {
                            row.addElement(rs.getObject(i));
                        }
                        reservedRoomsData.addElement(row);
                    }
                    rs.close();
                    stmt.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                JTable roomTable = new JTable(reservedRoomsData, reservedRoomsColumnNames) {
                    private static final long serialVersionUID = 1L;

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                roomTable.setOpaque(false);
                roomTable.getTableHeader().setReorderingAllowed(false);
                roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                roomTable.setFont(tableFont);
                roomTable.setForeground(tableTextColor);
                roomTable.setGridColor(new Color(223, 223, 223));
                roomTable.setShowGrid(true);
                roomTable.setShowVerticalLines(false);

                TableColumn roomTablecol = null;

                for (int i = 0; i < roomTable.getColumnCount(); i++) {
                    if (i == 1 || i == 3 || i == 4) {
                        roomTablecol.setMinWidth(75);
                        roomTablecol.setPreferredWidth(75);
                        roomTablecol.setMaxWidth(75);
                    } else if (i == 2) {
                        roomTablecol.setMinWidth(115);
                        roomTablecol.setPreferredWidth(115);
                        roomTablecol.setMaxWidth(115);
                    } else if (i == 5) {
                        roomTablecol.setMinWidth(550);
                        roomTablecol.setPreferredWidth(550);
                        roomTablecol.setMaxWidth(550);
                    }
                    roomTablecol = roomTable.getColumnModel().getColumn(i);
                    roomTablecol.setCellRenderer(new CustomTableCellRenderer());
                    roomTablecol.setResizable(false);
                }

                JTableHeader roomHeader = roomTable.getTableHeader();
                roomHeader.setBackground(new Color(80, 169, 169));

                JScrollPane roomTableContainer = new JScrollPane(roomTable);
                roomTableContainer.getViewport().setBackground(contentColor);

                roomTableContainer.setMinimumSize(new Dimension(1100, 500));
                roomTableContainer.setPreferredSize(new Dimension(1100, 500));
                roomTableContainer.setMaximumSize(new Dimension(1100, 500));

                centerUpPane.add(roomTableContainer);

                centerPane.removeAll();
                centerPane.add(centerUpPane);
                centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
                centerPane.add(roomButtonPane);
            }
        });

        JButton availableRooms = new JButton("Available");
        availableRooms.setPreferredSize(allRooms.getPreferredSize());
        availableRooms.addActionListener(e -> {

            viewCostomer.setEnabled(false);

            final JPanel centerUpPane1 = new JPanel();
            centerUpPane1.setLayout(new BoxLayout(centerUpPane1, BoxLayout.PAGE_AXIS));
            TitledBorder centerUpBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " AVAILABLE ROOMS ");
            centerUpBorder1.setTitleJustification(TitledBorder.CENTER);
            centerUpPane1.setBorder(centerUpBorder1);
            centerUpPane1.setBackground(myColor);

            final Vector<String> reservedRoomsColumnNames1 = new Vector<String>();
            final Vector<Vector<Object>> reservedRoomsData1 = new Vector<Vector<Object>>();

            try {
                String reserved = "Available";
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                String sql = "SELECT * FROM `rooms` WHERE `Status`='" + reserved + "' ORDER BY `Room No`";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                for (int i = 1; i <= columns; i++) {
                    reservedRoomsColumnNames1.addElement(md.getColumnName(i));
                }
                while (rs.next()) {
                    Vector<Object> row = new Vector<Object>(columns);
                    for (int i = 1; i <= columns; i++) {
                        row.addElement(rs.getObject(i));
                    }
                    reservedRoomsData1.addElement(row);
                }
                rs.close();
                stmt.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            JTable roomTable1 = new JTable(reservedRoomsData1, reservedRoomsColumnNames1) {
                private static final long serialVersionUID = 1L;

                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            roomTable1.setOpaque(false);
            roomTable1.getTableHeader().setReorderingAllowed(false);
            roomTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            roomTable1.setFont(tableFont);
            roomTable1.setForeground(tableTextColor);
            roomTable1.setGridColor(new Color(223, 223, 223));
            roomTable1.setShowGrid(true);
            roomTable1.setShowVerticalLines(false);

            TableColumn roomTablecol1 = null;

            for (int i = 0; i < roomTable1.getColumnCount(); i++) {
                if (i == 1 || i == 4 || i == 3) {
                    roomTablecol1.setMinWidth(80);
                    roomTablecol1.setPreferredWidth(80);
                    roomTablecol1.setMaxWidth(80);
                } else if (i == 2) {
                    roomTablecol1.setMinWidth(120);
                    roomTablecol1.setPreferredWidth(120);
                    roomTablecol1.setMaxWidth(120);
                } else if (i == 5) {
                    roomTablecol1.setMinWidth(550);
                    roomTablecol1.setPreferredWidth(550);
                    roomTablecol1.setMaxWidth(550);
                }
                roomTablecol1 = roomTable1.getColumnModel().getColumn(i);
                roomTablecol1.setCellRenderer(new CustomTableCellRenderer());
                roomTablecol1.setResizable(false);
            }

            JTableHeader roomHeader1 = roomTable1.getTableHeader();
            roomHeader1.setBackground(new Color(80, 169, 169));

            JScrollPane roomTableContainer1 = new JScrollPane(roomTable1);
            roomTableContainer1.getViewport().setBackground(contentColor);

            roomTableContainer1.setMinimumSize(new Dimension(1100, 500));
            roomTableContainer1.setPreferredSize(new Dimension(1100, 500));
            roomTableContainer1.setMaximumSize(new Dimension(1100, 500));

            centerUpPane1.add(roomTableContainer1);

            centerPane.removeAll();
            centerPane.add(centerUpPane1);
            centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPane.add(roomButtonPane);

        });

        JButton reservedRooms = new JButton("Reserved");
        reservedRooms.setPreferredSize(allRooms.getPreferredSize());
        reservedRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                room.doClick();
            }
        });

        JButton occupied = new JButton("Occupied");
        occupied.setPreferredSize(availableRooms.getPreferredSize());
        occupied.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                viewCostomer.setEnabled(false);

                final JPanel centerUpPane = new JPanel();
                centerUpPane.setLayout(new BoxLayout(centerUpPane, BoxLayout.PAGE_AXIS));
                TitledBorder centerUpBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " OCCUPIED ROOMS ");
                centerUpBorder.setTitleJustification(TitledBorder.CENTER);
                centerUpPane.setBorder(centerUpBorder);
                centerUpPane.setBackground(myColor);

                final Vector<String> reservedRoomsColumnNames = new Vector<String>();
                final Vector<Vector<Object>> reservedRoomsData = new Vector<Vector<Object>>();

                try {
                    String reserved = "Occupied";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");

                    String sql = "SELECT * FROM `rooms` WHERE `Status`='" + reserved + "' ORDER BY `Room No`";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    for (int i = 1; i <= columns; i++) {
                        reservedRoomsColumnNames.addElement(md.getColumnName(i));
                    }
                    while (rs.next()) {
                        Vector<Object> row = new Vector<Object>(columns);
                        for (int i = 1; i <= columns; i++) {
                            row.addElement(rs.getObject(i));
                        }
                        reservedRoomsData.addElement(row);
                    }
                    rs.close();
                    stmt.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                final JTable roomTable = new JTable(reservedRoomsData, reservedRoomsColumnNames) {
                    private static final long serialVersionUID = 1L;

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                roomTable.setOpaque(false);
                roomTable.getTableHeader().setReorderingAllowed(false);
                roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                roomTable.setFont(tableFont);
                roomTable.setForeground(tableTextColor);
                roomTable.setGridColor(new Color(223, 223, 223));
                roomTable.setShowGrid(true);
                roomTable.setShowVerticalLines(false);

                TableColumn roomTablecol = null;

                for (int i = 0; i < roomTable.getColumnCount(); i++) {
                    if (i == 1 || i == 4 || i == 3) {
                        roomTablecol.setMinWidth(80);
                        roomTablecol.setPreferredWidth(80);
                        roomTablecol.setMaxWidth(80);
                    } else if (i == 2) {
                        roomTablecol.setMinWidth(120);
                        roomTablecol.setPreferredWidth(120);
                        roomTablecol.setMaxWidth(120);
                    } else if (i == 5) {
                        roomTablecol.setMinWidth(550);
                        roomTablecol.setPreferredWidth(550);
                        roomTablecol.setMaxWidth(550);
                    }
                    roomTablecol = roomTable.getColumnModel().getColumn(i);
                    roomTablecol.setCellRenderer(new CustomTableCellRenderer());
                    roomTablecol.setResizable(false);
                }

                JTableHeader roomHeader = roomTable.getTableHeader();
                roomHeader.setBackground(new Color(80, 169, 169));

                JScrollPane roomTableContainer = new JScrollPane(roomTable);
                roomTableContainer.getViewport().setBackground(contentColor);
                roomTableContainer.setMinimumSize(new Dimension(1300, 330));
                roomTableContainer.setPreferredSize(new Dimension(1300, 330));
                roomTableContainer.setMaximumSize(new Dimension(1300, 330));

                centerUpPane.add(roomTableContainer);

                JPanel centerDownPaneContainer = new JPanel() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Dimension arcs = new Dimension(10, 10);

                        int width = getWidth();
                        int height = getHeight();
                        Graphics2D graphics = (Graphics2D) g;
                        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        graphics.setColor(getBackground());
                        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                        graphics.setColor(getForeground());
                        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                    }
                };
                centerDownPaneContainer.setBackground(contentColor);
                centerDownPaneContainer.setOpaque(false);

                final JPanel centerDownPane = new JPanel();
                TitledBorder centerDownBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), " ADDITIONAL INFORMATION ");
                centerDownBorder.setTitleJustification(TitledBorder.CENTER);
                centerDownBorder.setBorder(centerUpBorder);
                centerDownPane.setBorder(centerDownBorder);
                centerDownPane.setLayout(new GridBagLayout());
                centerDownPane.setBackground(contentColor);

                centerDownPane.setMinimumSize(new Dimension(850, 150));
                centerDownPane.setPreferredSize(new Dimension(850, 150));
                centerDownPane.setMaximumSize(new Dimension(850, 150));

                roomTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(final MouseEvent e) {
                        if (e.getClickCount() == 1) {

                            String roomColumnData = roomTable.getValueAt(roomTable.getSelectedRow(), 0).toString();

                            try {

                                Connection con;
                                Statement st;
                                ResultSet rs;

                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa", "root", "root");
                                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                rs = st.executeQuery("SELECT * FROM `reservations` WHERE `Room No`='" + roomColumnData + "'");

                                rs.first();

                                JLabel l9 = new JLabel("Expected Departure : ");
                                l9.setFont(addInfoFont);

                                JLabel l3 = new JLabel("Reserved Date : ");
                                l3.setFont(addInfoFont);
                                l3.setMinimumSize(l9.getPreferredSize());
                                l3.setPreferredSize(l9.getPreferredSize());
                                l3.setMaximumSize(l9.getPreferredSize());

                                JLabel l4 = new JLabel(rs.getString("RDate"));
                                l4.setFont(addInfoFont);
                                l4.setForeground(Color.RED);
                                l4.setMinimumSize(l9.getPreferredSize());
                                l4.setPreferredSize(l9.getPreferredSize());
                                l4.setMaximumSize(l9.getPreferredSize());

                                JLabel l5 = new JLabel("Reservation No :");
                                l5.setFont(addInfoFont);
                                l5.setMinimumSize(l9.getPreferredSize());
                                l5.setPreferredSize(l9.getPreferredSize());
                                l5.setMaximumSize(l9.getPreferredSize());

                                res = new JLabel(rs.getString("Res"));
                                res.setFont(addInfoFont);
                                res.setForeground(Color.RED);
                                res.setMinimumSize(l9.getPreferredSize());
                                res.setPreferredSize(l9.getPreferredSize());
                                res.setMaximumSize(l9.getPreferredSize());

                                JLabel l7 = new JLabel("Arrival Date : ");
                                l7.setFont(addInfoFont);
                                l7.setMinimumSize(l9.getPreferredSize());
                                l7.setPreferredSize(l9.getPreferredSize());
                                l7.setMaximumSize(l9.getPreferredSize());

                                JLabel l8 = new JLabel(rs.getString("Arrival"));
                                l8.setFont(addInfoFont);
                                l8.setForeground(Color.RED);
                                l8.setMinimumSize(l9.getPreferredSize());
                                l8.setPreferredSize(l9.getPreferredSize());
                                l8.setMaximumSize(l9.getPreferredSize());

                                JLabel l10 = new JLabel(rs.getString("Departure"));
                                l10.setFont(addInfoFont);
                                l10.setForeground(Color.RED);
                                l10.setMinimumSize(l9.getPreferredSize());
                                l10.setPreferredSize(l9.getPreferredSize());
                                l10.setMaximumSize(l9.getPreferredSize());

                                JLabel l11 = new JLabel("Occupant Name : ");
                                l11.setFont(addInfoFont);
                                l11.setMinimumSize(l9.getPreferredSize());
                                l11.setPreferredSize(l9.getPreferredSize());
                                l11.setMaximumSize(l9.getPreferredSize());

                                JLabel l12 = new JLabel(rs.getString("First Name") + " " + rs.getString("Last Name"));
                                l12.setFont(addInfoFont);
                                l12.setForeground(Color.RED);
                                l12.setMinimumSize(l9.getPreferredSize());
                                l12.setPreferredSize(l9.getPreferredSize());
                                l12.setMaximumSize(l9.getPreferredSize());

                                centerDownPane.removeAll();
                                centerDownPane.repaint();

                                GridBagConstraints c = new GridBagConstraints();

                                c.insets = new Insets(10, 10, 10, 10);
                                c.gridx = 0;
                                c.gridy = 0;
                                centerDownPane.add(l3, c);

                                c.gridx = 1;
                                c.gridy = 0;
                                centerDownPane.add(l4, c);

                                c.gridx = 2;
                                c.gridy = 0;
                                centerDownPane.add(l5, c);

                                c.gridx = 3;
                                c.gridy = 0;
                                centerDownPane.add(res, c);

                                c.gridx = 0;
                                c.gridy = 1;
                                centerDownPane.add(l7, c);

                                c.gridx = 1;
                                c.gridy = 1;
                                centerDownPane.add(l8, c);

                                c.gridx = 2;
                                c.gridy = 1;
                                centerDownPane.add(l9, c);

                                c.gridx = 3;
                                c.gridy = 1;
                                centerDownPane.add(l10, c);

                                c.gridx = 0;
                                c.gridy = 2;
                                centerDownPane.add(l11, c);

                                c.gridx = 1;
                                c.gridy = 2;
                                centerDownPane.add(l12, c);

                                viewCostomer.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        ViewQuest.viewColumnData = res.getText();
                                        new ViewQuest();
                                    }
                                });

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                centerDownPaneContainer.add(centerDownPane);

                centerPane.removeAll();
                centerPane.add(centerUpPane);
                centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
                centerPane.add(centerDownPaneContainer);
                centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
                centerPane.add(roomButtonPane);
                centerPane.repaint();
            }
        });

        roomButtonPane.add(reservedRooms);
        roomButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        roomButtonPane.add(occupied);
        roomButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        roomButtonPane.add(availableRooms);
        roomButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        roomButtonPane.add(allRooms);
        roomButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        roomButtonPane.add(viewCostomer);

        centerDownPaneContainer.add(centerDownPane);

        centerPane.add(centerUpPane);
        centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPane.add(centerDownPaneContainer);
        centerPane.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPane.add(roomButtonPane);

        return centerPane;
    }
}