package test;

import edu.wxu.views.AdminJFrame;

import javax.swing.*;

public class TestRemove {
    public static void main(String[] args) throws Exception {
        AdminJFrame adminJFrame = new AdminJFrame(null);
        adminJFrame.remove(adminJFrame.jScrollPane);
        adminJFrame.initWorkerTable();
    }
}
