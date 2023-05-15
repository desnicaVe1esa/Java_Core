package serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Pond implements Serializable {

    transient private Duck duck = new Duck();

    public static void main(String[] args) {
        Pond pond = new Pond();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Pond.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(pond);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Duck{
}
