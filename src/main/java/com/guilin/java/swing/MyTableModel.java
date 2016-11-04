package com.guilin.java.swing;

import javax.swing.table.DefaultTableModel;

/**
 * Created by guilin on 2014/4/29.
 */
public class MyTableModel extends DefaultTableModel {
    private String[] colNames = {"图层", "前一时次", "后一时次", "上一层次", "下一层次", "隐藏",
            "删除", "编辑", "另存", "路径"};

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 2) {
            return true;
        }
        return false;
    }
}
