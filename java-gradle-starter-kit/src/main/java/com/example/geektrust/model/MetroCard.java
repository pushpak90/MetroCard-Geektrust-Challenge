package com.example.geektrust.model;

import com.example.geektrust.enums.StationType;

public class MetroCard {
    private final String id;
    private int balance;
    private StationType lastJourneyOrigin;
    private boolean hasUnsettledOutbound;

    public MetroCard(String id, int balance) {
        this.id = id;
        this.balance = balance;
        this.lastJourneyOrigin = null;
        this.hasUnsettledOutbound = false;
    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void deductBalance(int amount) {
        this.balance -= amount;
    }

    public StationType getLastJourneyOrigin() {
        return lastJourneyOrigin;
    }

    public void setLastJourneyOrigin(StationType lastJourneyOrigin) {
        this.lastJourneyOrigin = lastJourneyOrigin;
    }

    public boolean hasUnsettledOutbound() {
        return hasUnsettledOutbound;
    }

    public void setHasUnsettledOutbound(boolean hasUnsettledOutbound) {
        this.hasUnsettledOutbound = hasUnsettledOutbound;
    }

    public boolean isReturnJourney(StationType currentStation) {
        return hasUnsettledOutbound && lastJourneyOrigin != currentStation;
    }

    public void markJourneyStart(StationType station) {
        this.hasUnsettledOutbound = true;
        this.lastJourneyOrigin = station;
    }

    public void clearJourney() {
        this.hasUnsettledOutbound = false;
        this.lastJourneyOrigin = null;
    }

    

}
