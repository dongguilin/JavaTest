package com.guilin.java6.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by guilin on 2014/4/29.
 */
public class JTableTest extends JFrame {

    private JTable table;

    public JTableTest() {
        table = new JTable();

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel tableModel = new MyTableModel();
        table.setModel(tableModel);

        TableColumn tableColumn = table.getColumnModel().getColumn(0);
        tableColumn.setCellRenderer(new ButtonCellRenderer());

        tableColumn = table.getColumnModel().getColumn(1);
        tableColumn.setPreferredWidth(100);
        tableColumn.setResizable(false);
        tableColumn.setCellRenderer(new LabelCellRenderer());

        tableColumn = table.getColumnModel().getColumn(2);
        PersonCellRender personCellRender = new PersonCellRender();
        tableColumn.setCellEditor(personCellRender);
        tableColumn.setCellRenderer(personCellRender);

        tableModel.addRow(new Object[]{new Person("hehe", 1), new Person("向左", 11), new Person("向右", 22)});


        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        JButton button = new JButton("Person");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person p = (Person) table.getModel().getValueAt(0, 2);
                String msg = "Person:" + p.getName() + "," + p.getAge();
                JOptionPane.showMessageDialog(null, msg);
            }
        });
        this.getContentPane().add(button, BorderLayout.NORTH);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JTableTest();

    }

}
