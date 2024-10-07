package org.example.controller;

import org.example.model.SkiPass;
import org.example.model.SkiPassSystem;
import org.example.view.TurnstileView;

import java.util.UUID;

public class TurnstileController {
    private SkiPassSystem skiPassSystem;
    private TurnstileView turnstileView;
    private int totalPasses;
    private int totalDenials;

    public TurnstileController(SkiPassSystem skiPassSystem, TurnstileView turnstileView) {
        this.skiPassSystem = skiPassSystem;
        this.turnstileView = turnstileView;
        this.totalPasses = 0;
        this.totalDenials = 0;
    }

    public void checkSkiPass(UUID skiPassId) {
        SkiPass skiPass = skiPassSystem.getSkiPass(skiPassId);
        if (skiPass != null && skiPass.pass()) {
            totalPasses++;
            turnstileView.showPassAllowed();
        } else {
            totalDenials++;
            turnstileView.showPassDenied();
        }
    }

    public void showStatistics() {
        turnstileView.showStatistics(totalPasses, totalDenials);
    }
}
