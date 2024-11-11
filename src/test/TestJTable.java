package test;

import com.mysql.cj.xdevapi.Table;
import edu.wxu.pojo.Worker;
import edu.wxu.views.AdminJFrame;
import edu.wxu.views.MyTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestJTable {
    public static void main(String[] args) throws Exception {
        DefaultTableModel dtm = new DefaultTableModel();
        String[] headers = {"学号","密码"};
        for (String header : headers) {
            dtm.addColumn(header);
        }

        Worker worker1 = new Worker(1,"吕林","123456");
        Worker worker2 = new Worker(2,"吕筱刘","123456");
        Worker worker3 = new Worker(3,"张三","123456");
        List<Worker> lists = new ArrayList<>();
        lists.add(worker1);
        lists.add(worker2);
        lists.add(worker3);
        for (Worker worker : lists) {
            dtm.addRow(new Object[]{worker.getNo(),worker.getName(),worker.getPassword()});
        }
        DefaultTableModel dtm2 = new DefaultTableModel();
        dtm2.addColumn("你好");
        dtm2.addRow(new Object[]{111});
        dtm2.addRow(new Object[]{222});
        JTable table = new JTable(dtm);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("宋体",Font.BOLD,25));
        tableHeader.setPreferredSize(new Dimension(tableHeader.getHeight(),30));
        table.setRowHeight(30);
        table.setFont(new Font("宋体",Font.BOLD,25));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int selectedRow : selectedRows) {
                    for (int i = 0; i < headers.length; i++) {
                        System.out.println(table.getValueAt(selectedRow,i));
                    }
                }
            }
        });
        JScrollPane jScrollPane = new JScrollPane(table);

        AdminJFrame adminJFrame = new AdminJFrame(null);
        jScrollPane.setBounds(10,10,800,800);
        adminJFrame.add(jScrollPane);
    }
}
