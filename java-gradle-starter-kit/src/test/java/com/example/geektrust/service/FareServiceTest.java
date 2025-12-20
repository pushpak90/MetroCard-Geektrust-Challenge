package com.example.geektrust.service;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.model.MetroCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FareServiceTest {

    @Test
    void shouldChargeBaseFareForFirstJourney() {
        FareService fareService = new FareService();
        MetroCard card = new MetroCard("MC1", 500);

        int fare = fareService.calculateFare(card, PassengerType.ADULT, StationType.CENTRAL);

        assertEquals(PassengerType.ADULT.getBaseFare(), fare);
    }

    @Test
    void shouldApplyHalfFareForReturnJourney() {
        FareService fareService = new FareService();
        MetroCard card = new MetroCard("MC1", 500);

        fareService.calculateFare(card, PassengerType.ADULT, StationType.CENTRAL);
        int returnFare = fareService.calculateFare(card, PassengerType.ADULT, StationType.AIRPORT);

        assertEquals(PassengerType.ADULT.getBaseFare() / 2, returnFare);
    }
}