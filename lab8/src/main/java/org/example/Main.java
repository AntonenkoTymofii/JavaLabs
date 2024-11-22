package org.example;

import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[1000000];
        Random random = new Random();

        // Ініціалізуємо масив випадковими значеннями від 0 до 100
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        // Створюємо ForkJoinPool для виконання рекурсивної задачі
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumArrayTask task = new SumArrayTask(array, 0, array.length);

        // Запускаємо задачу і отримуємо результат
        long result = forkJoinPool.invoke(task);
        System.out.println("Сума елементів масиву: " + result);
    }
}
