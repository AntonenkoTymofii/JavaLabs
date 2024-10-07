package org.example.model;

import java.time.LocalDate;

public class LimitedLiftsSkiPass extends SkiPass {
    private int lifts;

    public LimitedLiftsSkiPass(SkiPassType type, LocalDate fromDate, LocalDate toDate, int lifts) {
        super(type, fromDate, toDate);
        this.lifts = lifts;
    }

    @Override
    public boolean pass() {
        if (super.pass() && lifts > 0) {
            lifts--;
            return true;
        }
        return false;
    }

    public int getLifts() {
        return lifts;
    }

    public void setLifts(int lifts) {
        this.lifts = lifts;
    }
}
