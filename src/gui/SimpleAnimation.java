package gui;

import javax.swing.*;
import java.awt.*;

// ActionListener для передачи событий после нажатия кнопки
public class SimpleAnimation {

    // Координаты
    int x = 70;
    int y = 70;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        // Фрейм
        JFrame frame = new JFrame();
        // Завершение работы проги при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel drawPanel = new DrawPanel();

        frame.getContentPane().add(drawPanel);
        // Размер фрейма
        frame.setSize(300, 300);
        // Селать фрейм видимым
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {
            // Движение координат
            x++;
            y++;
            // Перерисовывание панели
            drawPanel.repaint();
            // Замедляем процесс, что бы было все видно
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            // Для закрашивания нарисованного
            g.setColor(Color.white);
            g.fillOval(0, 0, this.getWidth(), this.getHeight());

            // Рисует движение
            g.setColor(Color.green);
            g.fillOval(x, y, 40, 40);
        }
    }
}

