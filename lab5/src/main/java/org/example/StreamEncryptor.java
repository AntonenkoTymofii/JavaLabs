package org.example;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class StreamEncryptor {
    private static final Logger logger = Logger.getLogger(StreamEncryptor.class.getName());
    private final char key;
    private static ResourceBundle messages;

    static {
        try {
            // Налаштування для виведення в файл
            FileHandler fileHandler = new FileHandler("encryption.log", true);
            fileHandler.setLevel(Level.INFO); // Виводимо тільки INFO і вище
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Налаштування для виведення в консоль
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING); // Виводимо тільки WARNING і вище
            logger.addHandler(consoleHandler);

            logger.setLevel(Level.ALL); // Логер буде приймати всі рівні
            logger.setUseParentHandlers(false); // Вимикаємо вивід за замовчуванням
        } catch (IOException e) {
            System.err.println("Failed to initialize logger handler: " + e.getMessage());
        }
    }

    public StreamEncryptor(char encryptionKey) {
        this.key = encryptionKey;
    }

    public static void selectLanguage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select language / Виберіть мову:");
        System.out.println("1. English");
        System.out.println("2. Українська");
        System.out.println("3. France");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                messages = ResourceBundle.getBundle("location/messages_en", new Locale("en"));
                break;
            case "2":
                messages = ResourceBundle.getBundle("location/messages_uk", new Locale("uk"));
                break;
            case "3":
                messages = ResourceBundle.getBundle("location/messages_fr", new Locale("fr", "FR"));
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                messages = ResourceBundle.getBundle("location/messages", new Locale("en"));
        }
    }

    public void encryptFile(String inputFilePath, String outputFilePath) throws IOException {
        logger.info(messages.getString("encryption_failed") + inputFilePath);
        try (Reader reader = new FileReader(inputFilePath);
             Writer writer = new FilterWriter(new FileWriter(outputFilePath)) {
                 public void write(int c) throws IOException {
                     super.write(c + key);
                 }
             }) {
            int data;
            while ((data = reader.read()) != -1) {
                writer.write(data);
            }
            logger.info(messages.getString("encryption_completed") + outputFilePath);
        } catch (IOException e) {
            logger.severe(messages.getString("encryption_failed") + e.getMessage());
            throw e;
        }
    }

    public void decryptFile(String encryptedFilePath, String outputFilePath) throws IOException {
        logger.info(messages.getString("decryption_failed") + encryptedFilePath);
        try (Reader reader = new FilterReader(new FileReader(encryptedFilePath)) {
            public int read() throws IOException {
                int c = super.read();
                return (c == -1) ? -1 : (c - key);
            }
        };
             Writer writer = new FileWriter(outputFilePath)) {
            int data;
            while ((data = reader.read()) != -1) {
                writer.write(data);
            }
            logger.info(messages.getString("decryption_completed") + outputFilePath);
        } catch (IOException e) {
            logger.severe(messages.getString("decryption_failed") + e.getMessage());
            throw e;
        }
    }

    public void execute() {
        selectLanguage();  // Додаємо виклик вибору мови на початку виконання

        Scanner scanner = new Scanner(System.in);
        System.out.print(messages.getString("enter_key"));
        char key = scanner.nextLine().charAt(0);
        StreamEncryptor encryptor = new StreamEncryptor(key);

        try {
            System.out.print(messages.getString("enter_input_path"));
            String inputFile = scanner.nextLine();
            System.out.print(messages.getString("enter_encrypted_output_path"));
            String encryptedFile = scanner.nextLine();
            encryptor.encryptFile(inputFile, encryptedFile);

            System.out.print(messages.getString("enter_decrypted_output_path"));
            String decryptedFile = scanner.nextLine();
            encryptor.decryptFile(encryptedFile, decryptedFile);

            System.out.println(messages.getString("encryption_completed"));
        } catch (IOException e) {
            System.out.println(messages.getString("execution_failed") + e.getMessage());
            logger.warning(messages.getString("execution_failed"));
        }
    }
}
