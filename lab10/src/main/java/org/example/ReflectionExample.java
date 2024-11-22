package org.example;

import java.lang.reflect.Field;
import java.util.Scanner;

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            // 1. Ініціалізація рядка як літерала
            String literalString = "Hello, World!";
            System.out.println("Літерал до зміни: " + literalString.hashCode());

            // Зміна значення літерального рядка
            modifyStringValue(literalString, "Modified literal!");
            System.out.println("Літерал після зміни: " + literalString.hashCode());

            // 2. Ініціалізація рядка введенням з клавіатури
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введіть рядок: ");
            String inputString = scanner.nextLine();
            System.out.println("Рядок до зміни: " + inputString);

            // Зміна значення введеного рядка
            System.out.print("Введіть нове значення для заміни: ");
            String newValue = scanner.nextLine();
            modifyStringValue(inputString, newValue);
            System.out.println("Рядок після зміни: " + inputString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyStringValue(String original, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        // Перевіряємо, чи це char[] або byte[]
        Object value = valueField.get(original);
        if (value instanceof char[]) {
            char[] valueArray = (char[]) value;
            for (int i = 0; i < Math.min(newValue.length(), valueArray.length); i++) {
                valueArray[i] = newValue.charAt(i);
            }
            for (int i = newValue.length(); i < valueArray.length; i++) {
                valueArray[i] = '\0';
            }
        } else if (value instanceof byte[]) {
            byte[] valueArray = (byte[]) value;
            for (int i = 0; i < Math.min(newValue.length(), valueArray.length); i++) {
                valueArray[i] = (byte) newValue.charAt(i);
            }
            for (int i = newValue.length(); i < valueArray.length; i++) {
                valueArray[i] = (byte) '\0';
            }
        } else {
            throw new UnsupportedOperationException("Невідомий формат масиву");
        }
    }
}
