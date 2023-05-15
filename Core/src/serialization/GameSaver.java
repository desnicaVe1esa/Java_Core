package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaver {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Лучник", new String[]{"Лук", "Кинжал"});
        GameCharacter two = new GameCharacter(200, "Охотник", new String[]{"Копье", "Ловушки"});
        GameCharacter three = new GameCharacter(120, "Маг", new String[]{"Посох", "Сфера огня"});


        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Game.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(one);
            objectOutputStream.writeObject(two);
            objectOutputStream.writeObject(three);
        } catch (IOException e) {
            e.printStackTrace();
        }
        one = null;
        two = null;
        three = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Game.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            GameCharacter oneRestore = (GameCharacter) objectInputStream.readObject();
            GameCharacter twoRestore = (GameCharacter) objectInputStream.readObject();
            GameCharacter threeRestore = (GameCharacter) objectInputStream.readObject();

            System.out.println("Тип первого: " + oneRestore.getType());
            System.out.println("Тип второго: " + twoRestore.getType());
            System.out.println("Тип третьего: " + threeRestore.getType());
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
