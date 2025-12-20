package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StationSummaryTest {

    @Test
    void shouldAccumulateCollectionAndDiscount() {
        StationSummary summary = new StationSummary(StationType.CENTRAL);
        summary.addCollection(300);
        summary.addDiscount(50);

        assertEquals(300, summary.getTotalCollection());
        assertEquals(50, summary.getTotalDiscount());
    }

    @Test
    void shouldIncrementPassengerCount() {
        StationSummary summary = new StationSummary(StationType.AIRPORT);
        summary.incrementPassengerCount(PassengerType.ADULT);
        summary.incrementPassengerCount(PassengerType.ADULT);

        assertEquals(2, summary.getPassengerCounts().get(PassengerType.ADULT));
    }
}