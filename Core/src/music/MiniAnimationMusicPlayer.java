package music;

import gui.DrawPanel;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;

public class MiniAnimationMusicPlayer {
    static JFrame frame = new JFrame("Мой первый музыкальный клип");
    static DrawPanel drawPanel;

    public static void main(String[] args) {
        MiniAnimationMusicPlayer miniAnimationMusicPlayer =
                new MiniAnimationMusicPlayer();
        miniAnimationMusicPlayer.go();
    }
    // Установка графики
    public void setUpGui() {
        drawPanel = new DrawPanel();
        frame.setContentPane(drawPanel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }

    public void go() {
        setUpGui();
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(drawPanel, new int[] {127});
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int r = 0;
            for ( int i = 0; i < 60; i += 4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(144, 1, 127, 0, i));
                track.add(makeEvent(144, 1, r, 100, i + 2));
            }

            sequencer.setSequence(sequence);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MidiEvent makeEvent (int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    class DrawPanel extends JPanel implements ControllerEventListener {
        boolean msg = false;
        @Override
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent (Graphics g) {
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
