package gui;

import javax.swing.*;
import java.awt.*;

public class Button {
    public static void main(String[] args) {
        Button gui = new Button();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();

        JButton east = new JButton("East");
        JButton west = new JButton("West");
        JButton south = new JButton("South");
        JButton north = new JButton("North");
        JButton center = new JButton("Centre");

        Font bigFont = new Font("serif", Font.BOLD, 28); // Редактор шрифтов

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.EAST, east);
        frame.getContentPane().add(BorderLayout.WEST, west);
        frame.getContentPane().add(BorderLayout.SOUTH, south);
        frame.getContentPane().add(BorderLayout.NORTH, north);
        frame.getContentPane().add(BorderLayout.CENTER, center);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
