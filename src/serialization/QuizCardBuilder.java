package serialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilder {

    private JFrame jFrame;
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;

    public static void main(String[] args) {
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.go();
    }

    public void go() {
        jFrame = new JFrame("Quiz Cad Builder");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        Font font = new Font("sanserif", Font.BOLD, 24);

        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(font);

        JScrollPane qScrollPane = new JScrollPane(question);
        qScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(font);

        JScrollPane aScrollPane = new JScrollPane(answer);
        aScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList<>();

        JLabel qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        jPanel.add(qLabel);
        jPanel.add(qScrollPane);
        jPanel.add(aLabel);
        jPanel.add(aScrollPane);
        jPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());

        saveMenuItem.addActionListener(new SaveMenuListener());
        menu.add(newMenuItem);
        menu.add(saveMenuItem);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);
        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel);
        jFrame.setSize(500, 600);
        jFrame.setVisible(true);
    }

    public class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard quizCard = new QuizCard(question.getText(), answer.getText());
            cardList.add(quizCard);
            clearCard();
        }
    }

    public class SaveMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard quizCard = new QuizCard(question.getText(), answer.getText());
            cardList.add(quizCard);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(jFrame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    public class NewMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (QuizCard quizCard : cardList) {
                bufferedWriter.write(quizCard.getQuestion() + "/");
                bufferedWriter.write(quizCard.getAnswer() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
