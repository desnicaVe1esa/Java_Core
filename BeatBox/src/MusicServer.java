import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MusicServer {

    ArrayList<ObjectOutputStream> clientOutputStreams;

    public static void main(String[] args) {
        new MusicServer().go();
    }

    public class ClientHandler implements Runnable {

        ObjectInputStream in;
        Socket clientSoket;

        public ClientHandler(Socket socket) {
            try {
                clientSoket = socket;
                in = new ObjectInputStream(clientSoket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            Object o1 = null;
            Object o2 = null;
            try {
                while ((o1 = in.readObject()) != null) {

                    o2 = in.readObject();

                    System.out.println("Read two objects");
                    tellEveryone(o1, o2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void go() {
        clientOutputStreams = new ArrayList<ObjectOutputStream>();

        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

                System.out.println("Got a connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tellEveryone(Object one, Object two) {

        Iterator iterator = clientOutputStreams.iterator();
        while (iterator.hasNext()) {
            try {

                ObjectOutputStream out = (ObjectOutputStream) iterator.next();
                out.writeObject(one);
                out.writeObject(two);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

