package multithreading;

public class TestRunnable implements Runnable {

    @Override
    public void run() {
        go();
    }

    public void go() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doMore();
    }

    public void doMore() {
        System.out.println("Вершина стека");
    }
}

class Test {
    public static void main(String[] args) {
        Runnable runnable = new TestRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Метод main");
    }
}
