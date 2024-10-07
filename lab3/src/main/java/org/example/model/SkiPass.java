package org.example.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class SkiPass {
    private final UUID id;
    private SkiPassType type;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean isBlocked;

    public SkiPass(SkiPassType type, LocalDate fromDate, LocalDate toDate) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isBlocked = false;
    }

    public UUID getId() {
        return id;
    }

    public SkiPassType getType() {
        return type;
    }

    public void setType(SkiPassType type) {
        this.type = type;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        isBlocked = true;
    }

    public boolean isValid() {
        LocalDate today = LocalDate.now();
        return !isBlocked && !today.isBefore(fromDate) && !today.isAfter(toDate);
    }

    public boolean pass() {
        if (isValid()) {
            return true;
        }
        return false;
    }
}
