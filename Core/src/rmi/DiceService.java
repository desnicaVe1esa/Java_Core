package rmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceService implements Service {

    JLabel label;
    JComboBox numOfDice;
    @Override
    public JPanel getGuiPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Roll!");
        String[] choices = {"1", "2", "3", "4", "5"};
        numOfDice = new JComboBox<>(choices);
        label = new JLabel("dice values here");
        button.addActionListener(new RollEmListener());
        panel.add(numOfDice);
        panel.add(button);
        panel.add(label);
        return panel;
    }

    class RollEmListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String diceOutput = "";
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);
            for (int i = 0; i < numOfDiceToRoll; i++) {
                int r = (int) ((Math.random() * 6) + 1);
            }
            label.setText(diceOutput);
        }
    }
}
