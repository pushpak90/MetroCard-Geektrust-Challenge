package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetroCardServiceTest {

    @Test
    void shouldAutoRechargeWithServiceFee() {
        MetroCardService service = new MetroCardService();
        service.addBalance("MC1", 50);

        MetroCard card = service.getCard("MC1");
        int serviceFee = service.autoRecharge(card, 100);

        assertEquals(2, serviceFee);
        assertTrue(card.getBalance() >= 100);
    }

    @Test
    void shouldDetectSufficientBalance() {
        MetroCardService service = new MetroCardService();
        service.addBalance("MC1", 200);

        MetroCard card = service.getCard("MC1");
        assertTrue(service.hasSufficientBalance(card, 100));
    }
}