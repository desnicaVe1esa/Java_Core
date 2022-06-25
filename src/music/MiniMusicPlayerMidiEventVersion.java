package music;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicPlayerMidiEventVersion {
    public static void main(String[] args) {
        try {
            // Создаем синтезатор
            Sequencer sequencer = MidiSystem.getSequencer();
            // Открываем синтезатор
            sequencer.open();
            // Создаем послежовательность
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            // Создаем дорожку
            Track track = sequence.createTrack();
            // Создаем последовательность событий, чтобы ноты продолжали подниматься от фортепиано 5 до ноты 61
            for (int i = 5; i < 61; i += 4) {
                track.add(makeEvent(144,1,i,100,i));
                track.add(makeEvent(128,1,i,100,i + 2));
            }
            // Запуск
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
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
}
