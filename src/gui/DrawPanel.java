package gui;

import javax.swing.*;
import java.awt.*;

// JPanel - длядобаления виджетов
public class DrawPanel extends JPanel {

    // Поверхность для рисования, принадлежащая типу Graphics. На ней можно рисовать
    public void paintComponent(Graphics graphics) {

        // Для расширения возможностей
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Координаты, вся панель черная
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Рандомный начальный цвет
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color startColor = new Color(red, green, blue);

        // Рандомный конечный цвет
        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        Color endColor = new Color(red, green, blue);

        GradientPaint gradientPaint = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        // Назначение виртуальной кисти
        graphics2D.setPaint(gradientPaint);
        // Начать рисовать с 70 пикселов слева и 70 пикселов сверху, ширина и высота 100 пикселов
        graphics2D.fillOval(70, 70, 100, 100);
    }
}