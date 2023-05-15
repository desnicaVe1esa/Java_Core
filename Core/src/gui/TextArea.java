package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TextArea implements ActionListener, ItemListener, ListSelectionListener {

    JTextArea textArea;
    JCheckBox checkBox = new JCheckBox("Check test");

    String[] listEntries = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta"};
    JList list = new JList(listEntries);

    public static void main(String[] args) {
        TextArea gui = new TextArea();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton button = new JButton("Click");
        button.addActionListener(this);

        checkBox.addItemListener(this);


        textArea = new JTextArea(10,20);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane scroller = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane);
        panel.add(checkBox);
        panel.add(scroller);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setSize(350, 300);
        frame.setVisible(true);

        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        checkBox.setSelected(true);
        checkBox.setSelected(false);
        checkBox.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.append("button clicked \n");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String onOrOff = "off";
        if (checkBox.isSelected()) {
            onOrOff = "om";
        }
        System.out.println("Check box is " + onOrOff);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selection = (String) list.getSelectedValue();
            System.out.println(selection);
        }
    }
}
