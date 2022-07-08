package rmi;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniMusicService implements Service {

    ThisDrawPanel thisDrawPanel;

    @Override
    public JPanel getGuiPanel() {
        JPanel mainPanel = new JPanel();
        thisDrawPanel = new ThisDrawPanel();
        JButton playItButton = new JButton("Play it");
        playItButton.addActionListener(new PlayItListener());
        mainPanel.add(thisDrawPanel);
        mainPanel.add(playItButton);
        return mainPanel;
    }

    public class PlayItListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();

                sequencer.addControllerEventListener(thisDrawPanel, new int[] {127});
                Sequence sequence = new Sequence(Sequence.PPQ, 4);
                Track track = sequence.createTrack();

                for (int i = 0; i < 100; i++) {
                    int rNum = (int) ((Math.random() * 50) + 1);
                    if (rNum < 38) {
                        track.add(makeEvent(144, 1, rNum, 100, i));
                        track.add(makeEvent(176, 1, 127, 0, i));
                        track.add(makeEvent(128, 1, rNum, 100, i + 2));
                    }
                }

                sequencer.setSequence(sequence);
                sequencer.start();
                sequencer.setTempoInBPM(220);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(comd, chan, one, two);
            event = new MidiEvent(message, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    class ThisDrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

        @Override
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }

        public void paintComponent(Graphics g) {
            if (msg) {
                Graphics2D graphics2D = (Graphics2D) g;

                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);

                g.setColor(new Color(r, gr, b));

                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);

                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g.fillRect(x, y, ht, width);
                msg = false;
            }
        }
    }
}
