package edu.wxu.views;

import javax.swing.*;
import javax.swing.table.TableModel;

public class MyTable extends JTable {
    public MyTable(TableModel dm) {
        super(dm);
    }

    @Override
    public boolean isCellEditable(int row,int column){
        return false;
    }
}
