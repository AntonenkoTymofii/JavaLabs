package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private static String[] input;

    @BeforeAll
    static void setUp() {
        input = new String[]{"apple", "banana", "pear", "kiwi", "watermelon", "orange"};
        Main.countAverageWidth(input);
    }

    @Test
    void testAverageLengthCalculation() {
        double expectedAverage = 35 / 6.0;
        assertEquals(expectedAverage, Main.getAverageLength(), "Середня довжина повинна бути правильною");
    }

    @Test
    void testFilterStringsShorterThanAverage() {
        String[] result = Main.filterStringsByLength(input, true);
        String[] expected = {"apple", "pear", "kiwi"};
        assertArrayEquals(expected, result, "Рядки, коротші за середню, повинні відповідати очікуванню");
    }

    @Test
    void testFilterStringsLongerThanAverage() {
        String[] result = Main.filterStringsByLength(input, false);
        String[] expected = {"banana", "watermelon", "orange"};
        assertArrayEquals(expected, result, "Рядки, довші за середню, повинні відповідати очікуванню");
    }

    @Test
    void testEmptyInput() {
        String[] emptyInput = new String[0];
        String[] result = Main.filterStringsByLength(emptyInput, true);
        assertEquals(0, result.length, "Порожній вхід повинен повертати порожній результат");
    }
}

