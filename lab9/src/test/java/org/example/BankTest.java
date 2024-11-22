package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest {

    @Test
    void testTransferOperationMaintainsTotalBalance() throws InterruptedException {
        int numberOfAccounts = 100;
        int transferAmount = 100;

        // Ініціалізуємо рахунки з випадковими балансами між 500 і 1500
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i, ThreadLocalRandom.current().nextInt(500, 1500)));
        }

        // Визначаємо початковий баланс банку
        int initialBankBalance = accounts.stream().mapToInt(Account::getBalance).sum();

        Bank bank = new Bank();
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        // Виконуємо одночасні трансфери
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
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Підраховуємо фінальний баланс банку після всіх трансферів
        int finalBankBalance = accounts.stream().mapToInt(Account::getBalance).sum();

        // Перевірка, що загальна сума грошей у банку залишилась незмінною
        assertEquals(initialBankBalance, finalBankBalance, "Загальний баланс банку має залишатися незмінним після всіх операцій.");
    }
}
