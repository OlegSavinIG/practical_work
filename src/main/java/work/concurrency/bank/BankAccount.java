package work.concurrency.bank;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int id;
    private int balance;

    public BankAccount(Integer id) {
        this.id = id;
    }

    public synchronized void withdraw(int amount) {
            balance -= amount;
    }

    public synchronized void deposit(int amount) {
            balance += amount;
    }

    public synchronized Integer getBalance() {
            return balance;
    }

    public Integer getId() {
        return id;
    }
}
