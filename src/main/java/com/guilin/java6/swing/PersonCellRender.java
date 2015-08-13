package com.guilin.java6.swing;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by guilin on 2014/4/29.
 */
public class PersonCellRender extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, MouseListener {
    private JLabel label;
    private Person person;

    public PersonCellRender() {
        Icon icon = new ImageIcon(this.getClass().getResource("/").getPath() + "right.jpg");
        label = new JLabel(icon);
        label.addMouseListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return label;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.person = (Person) value;
        return label;
    }

    @Override
    public Object getCellEditorValue() {
        return person;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String str = JOptionPane.showInputDialog("Person name:");
        if (str == null) {
            return;
        }
        str = str.trim();
        if (str.length() > 0) {
            person.setName(str.trim());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
