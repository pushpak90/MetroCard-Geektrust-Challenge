package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;


public class FareService {
    public int calculateFare(MetroCard card, PassengerType type, StationType currentStation) {

        int baseFare = type.getBaseFare();

        // Return journey
        if (card.hasUnsettledOutbound() && card.getLastJourneyOrigin() != currentStation) {

            int discountedFare = baseFare / 2;

            card.setHasUnsettledOutbound(false);
            card.setLastJourneyOrigin(null);

            return discountedFare;
        }

        // Normal journey
        card.setHasUnsettledOutbound(true);
        card.setLastJourneyOrigin(currentStation);

        return baseFare;
    }
}
