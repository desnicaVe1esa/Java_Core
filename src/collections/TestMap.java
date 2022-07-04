package collections;

import java.util.HashMap;

public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Парень", 29);
        hashMap.put("Чухан", 6);
        hashMap.put("Кто-то", 0);
        System.out.println(hashMap);
    }
}
