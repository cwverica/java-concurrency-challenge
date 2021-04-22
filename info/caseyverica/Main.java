package info.caseyverica;

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("12345-678", 1000.00);

        Thread jane = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300);
                account.withdraw(50);
            }
        });

        Thread john = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100);
            }
        });

        jane.start();
        john.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){}
        System.out.println(account.getBalance());
    }

}

