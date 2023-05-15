package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerButton {
    JFrame frame;
    JButton button;

    public static void main(String[] args) {
        InnerButton gui = new InnerButton();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("A");
        button.addActionListener(new BListener());
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setSize(200, 100);
        frame.setVisible(true);
    }

    class BListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getText().equals("A")) {
                button.setText("B");
            } else {
                button.setText("A");
            }
        }
    }
}
