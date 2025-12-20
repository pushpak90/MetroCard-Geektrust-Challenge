package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.service.FareService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.StationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TravelTransactionTest {

    @Test
    void shouldProcessNormalJourney() {

        MetroCardService cardService = new MetroCardService();
        StationService stationService = new StationService();
        FareService fareService = new FareService();

        cardService.addBalance("MC1", 500);
        MetroCard card = cardService.getCard("MC1");

        TravelTransaction transaction =
                new TravelTransaction(
                        card,
                        PassengerType.ADULT,
                        StationType.CENTRAL,
                        fareService,
                        cardService,
                        stationService
                );

        transaction.execute();

        StationSummary summary =
                stationService.getSummaries().get(StationType.CENTRAL);

        assertEquals(1, summary.getPassengerCounts().get(PassengerType.ADULT));
        assertEquals(PassengerType.ADULT.getBaseFare(), summary.getTotalCollection());
    }

    @Test
    void shouldProcessReturnJourneyWithDiscount() {

        MetroCardService cardService = new MetroCardService();
        StationService stationService = new StationService();
        FareService fareService = new FareService();

        cardService.addBalance("MC1", 500);
        MetroCard card = cardService.getCard("MC1");

        new TravelTransaction(
                card,
                PassengerType.ADULT,
                StationType.CENTRAL,
                fareService,
                cardService,
                stationService
        ).execute();

        new TravelTransaction(
                card,
                PassengerType.ADULT,
                StationType.AIRPORT,
                fareService,
                cardService,
                stationService
        ).execute();

        StationSummary airport =
                stationService.getSummaries().get(StationType.AIRPORT);

        assertEquals(
                PassengerType.ADULT.getBaseFare() / 2,
                airport.getTotalCollection()
        );
        assertEquals(
                PassengerType.ADULT.getBaseFare() / 2,
                airport.getTotalDiscount()
        );
    }
}