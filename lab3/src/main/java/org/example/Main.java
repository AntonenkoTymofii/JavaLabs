package org.example;

import org.example.controller.TurnstileController;
import org.example.model.SkiPass;
import org.example.model.SkiPassSystem;
import org.example.model.SkiPassType;
import org.example.view.TurnstileView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SkiPassSystem skiPassSystem = new SkiPassSystem();

        TurnstileView turnstileView = new TurnstileView();

        TurnstileController controller = new TurnstileController(skiPassSystem, turnstileView);

        SkiPass skiPass1 = skiPassSystem.registrateSkiPass(SkiPassType.LIFTS_LIMITED,
                LocalDate.now(), LocalDate.now().plusDays(5), 5);

        for (int i = 0; i < 6; i++) {
            controller.checkSkiPass(skiPass1.getId());
        }

        controller.showStatistics();
    }
}