package org.example.view;

public class TurnstileView {
    public void showPassAllowed() {
        System.out.println("Прохід дозволено.");
    }

    public void showPassDenied() {
        System.out.println("Прохід заборонено.");
    }

    public void showStatistics(int totalPasses, int totalDenials) {
        System.out.println("Загальна кількість дозволених проходів: " + totalPasses);
        System.out.println("Загальна кількість відмов: " + totalDenials);
    }
}
