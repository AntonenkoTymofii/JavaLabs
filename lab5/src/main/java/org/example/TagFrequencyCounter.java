package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequencyCounter {

    // Метод для підрахунку тегів з URL
    public Map<String, Integer> countTags(String url) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            Pattern tagPattern = Pattern.compile("<(/?\\w+)");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = tagPattern.matcher(line);
                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase();
                    tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tagFrequency;
    }

    // Метод для підрахунку тегів з HTML-контенту у вигляді рядка (для тестів)
    public Map<String, Integer> countTagsFromHtml(String htmlContent) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        Pattern tagPattern = Pattern.compile("<(/?\\w+)");
        Matcher matcher = tagPattern.matcher(htmlContent);

        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
        }
        return tagFrequency;
    }

    // Метод execute, що забезпечує інтерактивну взаємодію з користувачем
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter URL to analyze tags: ");
        String url = scanner.nextLine();

        Map<String, Integer> tagFrequency = countTags(url);

        // Вивід у лексикографічному порядку
        System.out.println("Tags in lexicographical order:");
        tagFrequency.keySet().stream()
                .sorted()
                .forEach(tag -> System.out.println(tag + ": " + tagFrequency.get(tag)));

        // Вивід за частотою
        System.out.println("\nTags by frequency:");
        tagFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
