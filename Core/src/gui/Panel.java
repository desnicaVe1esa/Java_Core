package gui;

import javax.swing.*;
import java.awt.*;

public class Panel {

    public static void main(String[] args) {
        Panel gui = new Panel();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton buttonOne = new JButton("Click");
        JButton buttonTwo = new JButton("Push");

        panel.add(buttonOne);
        panel.add(buttonTwo);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
