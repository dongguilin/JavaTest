package com.guilin.java6.swing;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by guilin on 2014/4/29.
 */
public class LabelCellRenderer implements TableCellRenderer, MouseListener {

    private JLabel label;
    private Person person;

    public LabelCellRenderer() {
        Icon icon = new ImageIcon(this.getClass().getResource("/").getPath() + "left.jpg");
        label = new JLabel(icon);
        label.addMouseListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        person = (Person) value;
        return label;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JOptionPane.showMessageDialog(null, person);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
