package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class Bank {
    public void transfer(Account from, Account to, int amount) {
        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfAccounts = 100;
        int transferAmount = 100;

        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i, ThreadLocalRandom.current().nextInt(500, 1500)));
        }

        int initialBankBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Initial Bank Balance: " + initialBankBalance);

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        Bank bank = new Bank();

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                int fromIndex = ThreadLocalRandom.current().nextInt(numberOfAccounts);
                int toIndex = ThreadLocalRandom.current().nextInt(numberOfAccounts);
                while (fromIndex == toIndex) {
                    toIndex = ThreadLocalRandom.current().nextInt(numberOfAccounts);
                }

                int amount = ThreadLocalRandom.current().nextInt(1, transferAmount);
                bank.transfer(accounts.get(fromIndex), accounts.get(toIndex), amount);
            });
        }

        executor.shutdown();

        int finalBankBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Final Bank Balance: " + finalBankBalance);

        assert initialBankBalance == finalBankBalance : "Bank balance mismatch!";
    }
}
