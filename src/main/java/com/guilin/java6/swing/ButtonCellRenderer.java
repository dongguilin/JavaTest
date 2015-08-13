package com.guilin.java6.swing;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by guilin on 2014/4/29.
 */
public class ButtonCellRenderer implements TableCellRenderer, ActionListener {

    private JButton label;
    private Person person;

    public ButtonCellRenderer() {
        Icon icon = new ImageIcon(this.getClass().getResource("/").getPath() + "left.jpg");
        label = new JButton(icon);
        label.addActionListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        person = (Person) value;
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, person);
    }
}
