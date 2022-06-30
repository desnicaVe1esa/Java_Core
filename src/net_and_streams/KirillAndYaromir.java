package net_and_streams;

public class KirillAndYaromir implements Runnable {

    private BankAccount bankAccount = new BankAccount();

    public static void main(String[] args) {
        KirillAndYaromir kirillAndYaromir = new KirillAndYaromir();
        Thread one = new Thread(kirillAndYaromir);
        Thread two = new Thread(kirillAndYaromir);
        one.setName("Кирилл");
        two.setName("Яромир");
        one.start();
        two.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            makeWithdrawal(10);
            if (bankAccount.getBalance() < 0) {
                System.out.println("Превышение лимита");
            }
        }
    }

    private synchronized void makeWithdrawal(int amount) {
        if (bankAccount.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " собирается снять деньги");
            try {
                System.out.println(Thread.currentThread().getName() + " идет подремать");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " просыпается");
            bankAccount.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " заканчивает транзакцию");
        } else {
            System.out.println("Сорян, для клиента " + Thread.currentThread().getName() + " недостаточно денег");
        }
    }
}

class BankAccount {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }
}
