package music;

import javax.sound.midi.*;

public class MiniMusicCmdLine {
    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if (args.length < 2) {
            System.out.println("лох-пидр");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
            mini.play(1,2);
        }
    }

    public void play(int instrument, int note) {
        try {
            // Получаем синтезатор и открываем его
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            // Задаем темп синтезатору
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            // Запрашиваем трек у последовательности
            Track track = seq.createTrack();

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);
            // Помещаем трек в миди события
            // Создаем сообщение
            ShortMessage a = new ShortMessage();
            // Помещаем в сообщение инструкцию
            a.setMessage(144, 1, note, 100); // Что-то типа:"Начать проигрывать ноту №44"
            // Используя сообщение, создаем новую инструкцию
            MidiEvent noteOn = new MidiEvent(a, 1); // Сообщение a сработает на первом такте (бит 1)
            // Добавляем событие в трек
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            // Передаем последовательность синтезатору, типа вставки CD в проигрыватель
            player.setSequence(seq);

            // Собсна запуск
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
