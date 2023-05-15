package multithreading;

import java.io.*;
import java.net.*;

public class DailyAdviceClient {

    String[] adviceList = {"Ешьте меньшими порциями", "Не покупайте облегающие джинсы", "Плотва"};


    public void go() {
//        try {
//            Socket socket = new Socket("176.59.57.231", 4242);
//
//            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//
//            String advice = reader.readLine();
//            System.out.println("Сегодня ты должен: " + advice);
//
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceClient dailyAdviceClient = new DailyAdviceClient();
        dailyAdviceClient.go();
    }
}
