package com.example.geektrust.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTypeTest {

    @Test
    void shouldHaveCorrectBaseFares() {
        assertTrue(PassengerType.ADULT.getBaseFare() > 0);
        assertTrue(PassengerType.KID.getBaseFare() > 0);
        assertTrue(PassengerType.SENIOR_CITIZEN.getBaseFare() > 0);
    }
}