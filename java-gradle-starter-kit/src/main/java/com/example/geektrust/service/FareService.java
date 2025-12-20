package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;

public class FareService {

    public int calculateFare(MetroCard card, PassengerType type, StationType currentStation) {

        int baseFare = type.getBaseFare();

        if (card.isReturnJourney(currentStation)) {
            int discountedFare = baseFare / 2;
            card.clearJourney();
            return discountedFare;
        }

        card.markJourneyStart(currentStation);
        return baseFare;
    }
}