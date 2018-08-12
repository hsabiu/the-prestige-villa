package com.habib.prestige;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, false, row, column);

        if (isSelected) {
            cell.setBackground(new Color(19, 21, 20));
        } else {
            if (row % 2 == 0) {
                cell.setBackground(new Color(241, 244, 247));
            } else {
                cell.setBackground(new Color(250, 250, 250));
            }
        }

        if (value instanceof Date) {
            String strDate = new SimpleDateFormat("MM/dd/yyyy").format((Date) value);
            setText(strDate);
        }
        return cell;
    }
}