package com.guilin.java6.jide;

import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.swing.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guilin on 2014/5/5.
 */
public class AutoCompletionDemo {
    protected String[] _fontNames = new String[]{
            "Mail",
            "Calendar",
            "Contacts",
            "Tasks",
            "Notes",
            "Folder List",
            "Shortcuts",
            "Journal"
    };
    protected List<String> _fontList;

    public Component getDemoPanel() {
//        _fontNames = DemoData.getFontNames();
        _fontList = Arrays.asList(_fontNames);

        JPanel panel1 = createPanel1();
        JPanel panel2 = createPanel2();

        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.add(panel1, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(panel2);
        return panel;
    }

    public AutoCompletionDemo() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(getDemoPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        JideSwingUtilities.globalCenterWindow(frame);
        frame.setVisible(true);
        frame.toFront();
    }

    private JPanel createPanel1() {
        JPanel panel = new JPanel();
        panel.setLayout(new JideBoxLayout(panel, JideBoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(new JideTitledBorder(new PartialEtchedBorder(PartialEtchedBorder.LOWERED, PartialSide.NORTH),
                        "AutoCompletion combo box and text field", JideTitledBorder.LEADING, JideTitledBorder.ABOVE_TOP),
                BorderFactory.createEmptyBorder(10, 5, 10, 5)
        ));

        JComboBox autoCompletionComboBox = new AutoCompletionComboBox(_fontNames);
        autoCompletionComboBox.setName("AutoCompletion JComboBox (Strict)");
        autoCompletionComboBox.setToolTipText("AutoCompletion JComboBox (Strict)");
        panel.add(new JLabel("AutoCompletion JComboBox (Strict)"));
        panel.add(Box.createVerticalStrut(3), JideBoxLayout.FIX);
        panel.add(autoCompletionComboBox);
        panel.add(Box.createVerticalStrut(12), JideBoxLayout.FIX);

        AutoCompletionComboBox autoCompletionComboBoxNotStrict = new AutoCompletionComboBox(_fontNames);
        autoCompletionComboBoxNotStrict.setStrict(false);
        autoCompletionComboBoxNotStrict.setName("AutoCompletion JComboBox (Not strict)");
        autoCompletionComboBoxNotStrict.setToolTipText("AutoCompletion JComboBox (Not strict)");
        panel.add(new JLabel("AutoCompletion JComboBox (Not strict)"));
        panel.add(Box.createVerticalStrut(3), JideBoxLayout.FIX);
        panel.add(autoCompletionComboBoxNotStrict);
        panel.add(Box.createVerticalStrut(12), JideBoxLayout.FIX);

        // create tree combobox
        final JTextField textField = new JTextField();
        textField.setName("AutoCompletion JTextField with a hidden data");
        SelectAllUtils.install(textField);
        new AutoCompletion(textField, _fontList);
        panel.add(new JLabel("AutoCompletion JTextField with a hidden data"));
        panel.add(Box.createVerticalStrut(3), JideBoxLayout.FIX);
        panel.add(textField);
        panel.add(Box.createVerticalStrut(12), JideBoxLayout.FIX);

        return panel;
    }

    private JPanel createPanel2() {
        JPanel panel = new JPanel();
        panel.setLayout(new JideBoxLayout(panel, JideBoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(new JideTitledBorder(new PartialEtchedBorder(PartialEtchedBorder.LOWERED, PartialSide.ALL),
                        "AutoCompletion with list and tree", JideTitledBorder.LEADING, JideTitledBorder.CENTER),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // create tree combobox
        final JTextField treeTextField = new JTextField();
        treeTextField.setName("AutoCompletion JTextField with JTree");
        SelectAllUtils.install(treeTextField);
        final JTree tree = new JTree();
        tree.setVisibleRowCount(10);
        final TreeSearchable searchable = new TreeSearchable(tree);
        searchable.setRecursive(true);
        new AutoCompletion(treeTextField, searchable);
        panel.add(new JLabel("AutoCompletion JTextField with JTree"));
        panel.add(Box.createVerticalStrut(3), JideBoxLayout.FIX);
        panel.add(treeTextField);
        panel.add(Box.createVerticalStrut(2), JideBoxLayout.FIX);
        panel.add(new JScrollPane(tree));
        panel.add(Box.createVerticalStrut(12), JideBoxLayout.FIX);

        // create font name combobox
        final JTextField fontNameTextField = new JTextField();
        fontNameTextField.setName("AutoCompletion JTextField with JList");
        SelectAllUtils.install(fontNameTextField);
        final JList fontNameList = new JList(_fontNames);
        fontNameList.setVisibleRowCount(10);
        new AutoCompletion(fontNameTextField, new ListSearchable(fontNameList));
        panel.add(new JLabel("AutoCompletion JTextField with JList"));
        panel.add(Box.createVerticalStrut(3), JideBoxLayout.FIX);
        panel.add(fontNameTextField);
        panel.add(Box.createVerticalStrut(2), JideBoxLayout.FIX);
        panel.add(new JScrollPane(fontNameList));
        panel.add(Box.createVerticalStrut(12), JideBoxLayout.FIX);

        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
                new AutoCompletionDemo();
            }
        });
    }

}
