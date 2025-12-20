package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;

public class Journey {

    private final MetroCard metroCard;
    private final PassengerType passengerType;
    private final StationType stationType;

    public Journey(MetroCard metroCard, PassengerType passengerType, StationType stationType) {
        this.metroCard = metroCard;
        this.passengerType = passengerType;
        this.stationType = stationType;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public StationType getStationType() {
        return stationType;
    }
}