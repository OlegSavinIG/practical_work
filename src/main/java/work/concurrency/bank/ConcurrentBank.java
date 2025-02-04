package work.concurrency.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private final AtomicInteger id = new AtomicInteger(1);
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankAccount createAccount(int deposit) {
        BankAccount account = new BankAccount(id.getAndIncrement());
        account.deposit(deposit);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount destination, int amount) {
        BankAccount firstLock = from.getId() > destination.getId() ? from : destination;
        BankAccount secondLock = from.getId() > destination.getId() ? destination : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                from.withdraw(amount);
                destination.deposit(amount);
            }
        }
    }

    public synchronized Integer getTotalBalance() {
        return accounts.stream()
                .mapToInt(BankAccount::getBalance)
                .sum();
    }
}
