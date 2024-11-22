package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void testConcurrentDepositsAndWithdrawals() throws InterruptedException {
        Account account = new Account(1, 1010);
        int initialBalance = account.getBalance();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> account.deposit(10));
            executor.execute(() -> account.withdraw(10));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // Balance should remain the same after matched deposits and withdrawals
        assertEquals(initialBalance, account.getBalance(), "Balance should match initial balance after concurrent operations");
    }

    @Test
    void testConcurrentOperationsWithMultipleAccounts() throws InterruptedException {
        Account account1 = new Account(1, 500);
        Account account2 = new Account(2, 1000);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                account1.deposit(10);
                account1.withdraw(10);
            });
            executor.execute(() -> {
                account2.deposit(20);
                account2.withdraw(20);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // Final balances should remain unchanged
        assertEquals(500, account1.getBalance(), "Account 1 balance should remain at 500 after concurrent operations");
        assertEquals(1000, account2.getBalance(), "Account 2 balance should remain at 1000 after concurrent operations");
    }
}
