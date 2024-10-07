package org.example.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkiPassSystem {
    private Map<UUID, SkiPass> skiPassMap;

    public SkiPassSystem() {
        skiPassMap = new HashMap<>();
    }

    public SkiPass registrateSkiPass(SkiPassType type, LocalDate fromDate, LocalDate toDate, int lifts){
        SkiPass skiPass = null;
        if (SkiPassType.LIFTS_LIMITED.equals(type)) {
            skiPass = new LimitedLiftsSkiPass(type, fromDate, toDate, lifts);
        } else if (SkiPassType.SEASON.equals(type)) {
            skiPass = new SeasonSkiPass(type, fromDate, toDate);
        }
        assert skiPass != null;
        skiPassMap.put(skiPass.getId(), skiPass);
        return skiPass;
    }

    public void blockSkiPass(UUID skiPassId) {
        SkiPass skiPass = skiPassMap.get(skiPassId);
        if (skiPass != null) {
            skiPass.block();
        }
    }

    public SkiPass getSkiPass(UUID uuid) {
        return skiPassMap.get(uuid);
    }
}
