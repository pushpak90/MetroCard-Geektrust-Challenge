package com.example.geektrust.service;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.model.StationSummary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StationServiceTest {

    @Test
    void shouldUpdateStationSummary() {

        StationService stationService = new StationService();

        stationService.addFareCollection(StationType.CENTRAL, 200);
        stationService.addDiscount(StationType.CENTRAL, 50);
        stationService.incrementPassengerCount(
                StationType.CENTRAL, PassengerType.KID
        );

        StationSummary summary =
                stationService.getSummaries().get(StationType.CENTRAL);

        assertEquals(200, summary.getTotalCollection());
        assertEquals(50, summary.getTotalDiscount());
        assertEquals(1, summary.getPassengerCounts().get(PassengerType.KID));
    }
}
