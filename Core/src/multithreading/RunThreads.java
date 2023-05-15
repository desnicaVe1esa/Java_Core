package multithreading;

public class RunThreads implements Runnable{

    public static void main(String[] args) {
        RunThreads runThreads = new RunThreads();
        Thread alfa = new Thread(runThreads);
        Thread beta = new Thread(runThreads);
        alfa.setName("альфа");
        beta.setName("бета");
        alfa.start();
        beta.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 25; i ++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Сейчас работает: " + threadName);
        }
    }
}
