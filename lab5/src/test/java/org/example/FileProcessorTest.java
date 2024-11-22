package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileProcessorTest {

    private FileProcessor fileProcessor;
    private Path inputFilePath;
    private Path serializedFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        fileProcessor = new FileProcessor();

        // Створюємо тимчасовий текстовий файл для тестування
        inputFilePath = Files.createTempFile("testInput", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFilePath.toFile()))) {
            writer.write("This is a test line.\n");
            writer.write("This line has the most words in this file.\n");
            writer.write("Short line.\n");
        }

        // Створюємо тимчасовий файл для серіалізованих даних
        serializedFilePath = Files.createTempFile("testSerialized", ".ser");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(inputFilePath);
        Files.deleteIfExists(serializedFilePath);
    }

    @Test
    public void testSerializeTextFile() {
        fileProcessor.serializeTextFile(inputFilePath.toString(), serializedFilePath.toString());

        // Перевіряємо, чи файл серіалізовано та створено
        assertTrue(Files.exists(serializedFilePath), "Серіалізований файл має існувати");

        // Перевіряємо, чи серіалізований файл містить дані у вигляді об'єкту
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedFilePath.toFile()))) {
            Object object = ois.readObject();
            assertTrue(object instanceof List, "Серіалізовані дані мають бути списком рядків");

            @SuppressWarnings("unchecked")
            List<String> lines = (List<String>) object;
            assertEquals(3, lines.size(), "Список має містити 3 рядки");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindLongestLine() {
        // Спочатку серіалізуємо файл
        fileProcessor.serializeTextFile(inputFilePath.toString(), serializedFilePath.toString());

        // Тепер шукаємо рядок з максимальною кількістю слів у серіалізованому файлі
        String longestLine = fileProcessor.findLongestLine(serializedFilePath.toString());
        assertEquals("This line has the most words in this file.", longestLine,
                "Рядок з максимальною кількістю слів має бути знайдений правильно");
    }
}
