package serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Box implements Serializable {

    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) {
        Box box = new Box();
        box.setWidth(50);
        box.setHeight(50);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("foo.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(box);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
