package com.example.geektrust.model;

import com.example.geektrust.enums.StationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetroCardTest {

    @Test
    void shouldAddAndDeductBalanceCorrectly() {
        MetroCard card = new MetroCard("MC1", 100);
        card.addBalance(50);
        card.deductBalance(30);
        assertEquals(120, card.getBalance());
    }

    @Test
    void shouldDetectReturnJourney() {
        MetroCard card = new MetroCard("MC1", 200);
        card.markJourneyStart(StationType.CENTRAL);
        assertTrue(card.isReturnJourney(StationType.AIRPORT));
    }

    @Test
    void shouldClearJourneyState() {
        MetroCard card = new MetroCard("MC1", 200);
        card.markJourneyStart(StationType.CENTRAL);
        card.clearJourney();
        assertFalse(card.hasUnsettledOutbound());
        assertNull(card.getLastJourneyOrigin());
    }
}