package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testFindWordWithMinUniqueChars_NormalCase() {
        String input = "example words with different characters";
        String[] expected = {"with"};
        String[] result = Main.findWordWithMinUniqueChars(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindWordWithMinUniqueChars_SingleWord() {
        String input = "single";
        String[] expected = {"single"};
        String[] result = Main.findWordWithMinUniqueChars(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindWordWithMinUniqueChars_MultipleSameMinWords() {
        String input = "aa bb cc dd";
        String[] expected = {"aa"};  // Повертається перше слово з мінімумом символів
        String[] result = Main.findWordWithMinUniqueChars(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindWordWithMinUniqueChars_EmptyString() {
        String input = "";
        String[] expected = {""};
        String[] result = Main.findWordWithMinUniqueChars(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindWordWithMinUniqueChars_WhitespaceOnly() {
        String input = "     ";  // Тільки пробіли
        String[] expected = {};  // Порожній масив
        String[] result = Main.findWordWithMinUniqueChars(input);
        assertArrayEquals(expected, result);
    }
}
