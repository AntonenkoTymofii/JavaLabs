package org.example;

import org.example.controller.TurnstileController;
import org.example.model.SkiPass;
import org.example.model.SkiPassSystem;
import org.example.model.SkiPassType;
import org.example.view.TurnstileView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Модель
        SkiPassSystem skiPassSystem = new SkiPassSystem();

        // Представлення
        TurnstileView turnstileView = new TurnstileView();

        // Контролер
        TurnstileController controller = new TurnstileController(skiPassSystem, turnstileView);

        // Випускаємо ski-pass на 5 підйомів
        SkiPass skiPass1 = skiPassSystem.registrateSkiPass(SkiPassType.LIFTS_LIMITED, LocalDate.now(), LocalDate.now().plusDays(5), 5);

        // Симуляція перевірки ski-pass
        for (int i = 0; i < 6; i++) {
            controller.checkSkiPass(skiPass1.getId());
        }

        // Виведення статистики
        controller.showStatistics();
    }
}