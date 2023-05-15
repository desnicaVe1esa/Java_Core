import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class BeatBox {

    JTextField userMessage;
    JList incomingList;
    Vector<String> listVector = new Vector<>();
    int nextNum;
    String userName;
    ObjectOutputStream out;
    ObjectInputStream in;
    HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();
    Sequence mySequence = null;
    JPanel jPanel;
    ArrayList<JCheckBox> checkBoxArrayList;
    JFrame frame;
    Sequencer sequencer;
    Sequence sequence;
    Track track;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};

    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox().startUp("User");
    }

    public void startUp(String name) {
        userName = name;
        try {
            Socket socket = new Socket("127.0.0.1", 4242);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch (Exception e) {
            System.out.println("Couldn't connect - you'll have to play alone");
            e.printStackTrace();
        }
        sutUpMidi();
        buildGUI();
    }

    public void buildGUI() {
        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        JPanel background = new JPanel(borderLayout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxArrayList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton play = new JButton("Play");
        play.addActionListener(new PlayListener());
        buttonBox.add(play);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo up");
        upTempo.addActionListener(new UpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo down");
        downTempo.addActionListener(new DownTempoListener());
        buttonBox.add(downTempo);

        JButton sendIt = new JButton("Send it");
        sendIt.addActionListener(new SendListener());
        buttonBox.add(sendIt);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        incomingList = new JList<>();
        incomingList.addListSelectionListener(new MyListSelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector); // Нет начальных данных

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        frame.getContentPane().add(background);

        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setVgap(2);
        jPanel = new JPanel(gridLayout);
        background.add(BorderLayout.CENTER, jPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox jCheckBox = new JCheckBox();
            jCheckBox.setSelected(false);
            checkBoxArrayList.add(jCheckBox);
            jPanel.add(jCheckBox);
        }

        sutUpMidi();

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public void sutUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }

    public void buildTrackAndStart() {
        ArrayList<Integer> trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new ArrayList<>();
            for (int j = 0; j < 16; j++) {
                JCheckBox jCheckBox = (JCheckBox) checkBoxArrayList.get(j + (16 * i));
                if (jCheckBox.isSelected()) {
                    int key = instruments[i];
                    trackList.add(key);
                } else {
                    trackList.add(null);
                }
            }
            makeTracks(trackList);
        }
        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    class PlayListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    class StopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    class UpTempoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    class DownTempoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * .97));
        }
    }

    class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkBoxArrayList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            }
            String messageToSend = null;
            try {
                out.writeObject(userName + " " + nextNum++ + " : " + userMessage.getText());
                out.writeObject(checkboxState);
            } catch (Exception ex) {
                System.out.println("Sorry bro, go fuck urself");
            }
            userMessage.setText("");
        }
    }

    public class MyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    // Переходим к отображению и изменяем последовательность
                    boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        }
    }

    public class RemoteReader implements Runnable {

        boolean[] checkBoxState = null;
        String nameToShow = null;
        Object object = null;

        @Override
        public void run() {
            try {
                while ((object = in.readObject()) != null) {
                    System.out.println("got an object from server");
                    System.out.println(object.getClass());
                    String nameToShow = (String) object;
                    checkBoxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkBoxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private class MyPLayMineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mySequence != null) {
                sequence = mySequence;
            }
        }
    }

    public void changeSequence(boolean[] checkBoxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = (JCheckBox) checkBoxArrayList.get(i);
            if (checkBoxState[i]) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
        }
    }


//    class ReadInListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            boolean[] checkboxState = null;
//            try {
//                FileInputStream fileInputStream = new FileInputStream(new File("Checkbox.ser"));
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                checkboxState = (boolean[]) objectInputStream.readObject();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            for (int i = 0; i < 256; i++) {
//                JCheckBox checkBox = (JCheckBox) checkBoxArrayList.get(i);
//                if (checkboxState[i]) {
//                    checkBox.setSelected(true);
//                } else {
//                    checkBox.setSelected(false);
//                }
//            }
//            sequencer.stop();
//            buildTrackAndStart();
//        }
//    }

    public void makeTracks(ArrayList list) {

        Iterator iterator = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) iterator.next();

            if (num != null) {
                int numKey = num.intValue();
                track.add(makeEvent(144, 9, numKey, 100, i));
                track.add(makeEvent(128, 9, numKey, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {

        MidiEvent midiEvent = null;
        try {
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(comd, chan, one, two);
            midiEvent = new MidiEvent(shortMessage, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return midiEvent;
    }
}
