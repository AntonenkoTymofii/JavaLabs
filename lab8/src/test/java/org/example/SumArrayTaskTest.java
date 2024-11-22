package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumArrayTaskTest {

    @Test
    public void testSumArrayTaskWithSmallArray() {
        int[] array = {1, 2, 3, 4, 5};
        SumArrayTask task = new SumArrayTask(array, 0, array.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(task);

        assertEquals(15, result, "Сума елементів масиву {1, 2, 3, 4, 5} повинна дорівнювати 15");
    }

    @Test
    public void testSumArrayTaskWithLargeArray() {
        int[] array = new int[1_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        SumArrayTask task = new SumArrayTask(array, 0, array.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(task);

        assertEquals(1_000_000, result, "Сума елементів масиву з 1_000_000 одиниць повинна дорівнювати 1_000_000");
    }

    @Test
    public void testSumArrayTaskWithThresholdBoundary() {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        SumArrayTask task = new SumArrayTask(array, 0, array.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(task);

        assertEquals(210, result, "Сума елементів масиву {1, 2, ..., 20} повинна дорівнювати 210");
    }

    @Test
    public void testSumArrayTaskWithEmptyArray() {
        int[] array = {};
        SumArrayTask task = new SumArrayTask(array, 0, array.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(task);

        assertEquals(0, result, "Сума елементів порожнього масиву повинна дорівнювати 0");
    }
}
