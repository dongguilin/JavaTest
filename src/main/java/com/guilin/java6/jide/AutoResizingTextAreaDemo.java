//package com.guilin.java6.jide;
//
//import com.jidesoft.plaf.LookAndFeelFactory;
//import com.jidesoft.swing.AutoResizingTextArea;
//import com.jidesoft.swing.JideBoxLayout;
//import com.jidesoft.swing.JideSwingUtilities;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * Created by guilin on 2014/5/6.
// */
//public class AutoResizingTextAreaDemo {
//
//    public AutoResizingTextAreaDemo() {
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(getDemoPanel());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        JideSwingUtilities.globalCenterWindow(frame);
//        frame.setVisible(true);
//        frame.toFront();
//    }
//
//    public Component getDemoPanel() {
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(400, 400));
//        panel.setLayout(new JideBoxLayout(panel, JideBoxLayout.Y_AXIS));
//        panel.add(new JScrollPane(new AutoResizingTextArea("Typing in new line here to see the text area growing automatically. \nMinimum 2 rows and maximum 10 rows", 5, 10, 20)));
//        panel.add(Box.createGlue(), JideBoxLayout.VARY);
//        return panel;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
//                new AutoResizingTextAreaDemo();
//            }
//        });
//    }
//
//}
