package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static double averageLength = 0;

    static void countAverageWidth(String[] input) {
        for (String str : input) {
            averageLength += str.length();
        }
        averageLength /= input.length;
    }

    public static String[] filterStringsByLength(String[] input, boolean shorterThanAverage) {
        if (input.length == 0) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();

        for (String str : input) {
            if (shorterThanAverage && str.length() < averageLength) {
                result.add(str);
            } else if (!shorterThanAverage && str.length() > averageLength) {
                result.add(str);
            }
        }

        return result.toArray(new String[0]);
    }

    public static double getAverageLength() {
        return averageLength;
    }

    public static void main(String[] args) {
        String[] input = {"apple", "banana", "pear", "kiwi", "watermelon", "orange"};

        countAverageWidth(input);
        System.out.println("Середня довжина рядку: " + averageLength);

        String[] shorterStrings = filterStringsByLength(input, true);

        System.out.println("Рядки, довжина яких менша за середню:");
        for (String str : shorterStrings) {
            System.out.println(str);
        }

        String[] longerStrings = filterStringsByLength(input, false);

        System.out.println("Рядки, довжина яких більша за середню:");
        for (String str : longerStrings) {
            System.out.println(str);
        }
    }
}
