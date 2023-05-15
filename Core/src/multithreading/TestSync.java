package multithreading;

public class TestSync implements Runnable{

    private int balance;

    @Override
    public void run() {
        for(int i = 0; i < 50; i++) {
            increment();
            System.out.println("Баланс равен " + Thread.currentThread().getName() + " " + balance);
        }
    }

    public synchronized void increment() {
        int i = balance;
        balance = i + 1;
    }
}

class TestSyncTest {
    public static void main(String[] args) {
        TestSync testSync = new TestSync();
        Thread a = new Thread(testSync);
        Thread b = new Thread(testSync);
        a.setName("a");
        b.setName("b");
        a.start();
        b.start();
    }
}
