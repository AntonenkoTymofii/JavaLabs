package org.example;

import java.util.*;

public class Main {

    public static String[] findWordWithMinUniqueChars(String input) {
        String[] words = input.split("\\s+");

        if (words.length == 0) {
            return new String[] {};
        }

        String minUniqueWord = Arrays.stream(words)
                .min(Comparator.comparingInt(word -> (int) word.chars().distinct().count()))
                .orElse("");

        return new String[] { minUniqueWord };
    }

    public static void main(String[] args) {
        String input1 = "example words with different characters";
        String input2 = "apple orange tiger banana watermelon onion horse";
        String input3 = "know this madam";
        String[] result1 = findWordWithMinUniqueChars(input1);
        String[] result2 = findWordWithMinUniqueChars(input2);
        String[] result3 = findWordWithMinUniqueChars(input3);

        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.toString(result3));
    }
}