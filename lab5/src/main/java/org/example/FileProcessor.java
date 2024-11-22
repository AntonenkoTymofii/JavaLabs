package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor implements Serializable {

    // Метод для серіалізації текстового файлу в список рядків
    public void serializeTextFile(String inputFilePath, String outputFilePath) {
        List<String> lines = new ArrayList<>();

        // Зчитуємо рядки з текстового файлу та додаємо їх до списку
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
            return;
        }

        // Серіалізуємо список рядків у файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
            oos.writeObject(lines);
            System.out.println("Файл серіалізовано у: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Помилка при серіалізації файлу: " + e.getMessage());
        }
    }

    // Метод для пошуку рядка з максимальною кількістю слів у серіалізованому файлі
    public String findLongestLine(String serializedFilePath) {
        String longestLine = "";
        int maxWords = 0;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedFilePath))) {
            List<String> lines = (List<String>) ois.readObject();

            for (String line : lines) {
                int wordCount = line.split("\\s+").length;
                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    longestLine = line;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при читанні серіалізованого файлу: " + e.getMessage());
        }

        return longestLine;
    }
}
