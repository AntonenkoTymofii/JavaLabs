package org.example.model;

import java.time.LocalDate;

public class SeasonSkiPass extends SkiPass {
    public SeasonSkiPass(SkiPassType type, LocalDate fromDate, LocalDate toDate) {
        super(type, fromDate, toDate);
    }
}
