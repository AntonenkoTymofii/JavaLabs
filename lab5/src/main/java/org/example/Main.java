package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StreamEncryptor streamEncryptor = new StreamEncryptor('A');
        TagFrequencyCounter tagCounter = new TagFrequencyCounter();
        FileProcessor fileProcessor = new FileProcessor();

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Find line with maximum words and save to file");
            System.out.println("2. Encrypt/Decrypt a file");
            System.out.println("3. Count tag frequency from URL");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            String input = scanner.nextLine();  // Читаємо весь рядок як введення

            try {
                int choice = Integer.parseInt(input.trim());  // Перетворюємо рядок на число

                switch (choice) {
                    case 1:
                        System.out.print("Enter Path: ");
                        String inputFilePath = scanner.nextLine();
                        String outputFilePath = scanner.nextLine();
                        fileProcessor.serializeTextFile(inputFilePath, outputFilePath);
                        String longestLine = fileProcessor.findLongestLine(outputFilePath);
                        System.out.println("Line with max words: " + longestLine);
                        break;
                    case 2:
                        streamEncryptor.execute();
                        break;
                    case 3:
                        tagCounter.execute();
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}