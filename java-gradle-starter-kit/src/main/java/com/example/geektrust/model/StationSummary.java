package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;

import java.util.EnumMap;
import java.util.Map;

public class StationSummary {
    private final StationType stationType;
    private int totalCollection;
    private int totalDiscount;
    private final Map<PassengerType, Integer> passengerCounts;

    public StationSummary(StationType stationType) {
        this.stationType = stationType;
        this.totalCollection = 0;
        this.totalDiscount = 0;
        this.passengerCounts = new EnumMap<>(PassengerType.class);
        // initialize counts to 0
        for (PassengerType type : PassengerType.values()) {
            passengerCounts.put(type, 0);
        }
    }

    public StationType getStationType() {
        return stationType;
    }

    public int getTotalCollection() {
        return totalCollection;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public Map<PassengerType, Integer> getPassengerCounts() {
        return passengerCounts;
    }

    public void addCollection(int amount) {
        this.totalCollection += amount;
    }

    public void addDiscount(int amount) {
        this.totalDiscount += amount;
    }

    public void incrementPassengerCount(PassengerType type) {
        passengerCounts.put(type, passengerCounts.get(type) + 1);
    }
}
