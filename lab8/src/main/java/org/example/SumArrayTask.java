package org.example;

import java.util.concurrent.RecursiveTask;

public class SumArrayTask extends RecursiveTask<Long> {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 20;

    public SumArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        // Якщо розмір підмасиву менше порогового значення, обчислюємо суму
        if (length <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Інакше, ділимо масив на дві частини і обробляємо їх рекурсивно
            int mid = start + length / 2;
            SumArrayTask leftTask = new SumArrayTask(array, start, mid);
            SumArrayTask rightTask = new SumArrayTask(array, mid, end);

            // Запускаємо ліву частину асинхронно
            leftTask.fork();
            // Обчислюємо праву частину в поточному потоці
            long rightResult = rightTask.compute();
            // Очікуємо завершення лівої частини і отримуємо результат
            long leftResult = leftTask.join();

            // Повертаємо суму результатів обох частин
            return leftResult + rightResult;
        }
    }
}
