package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.service.FareService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.StationService;

public class TravelTransaction {

    private final MetroCard metroCard;
    private final PassengerType passengerType;
    private final StationType stationType;
    private final FareService fareService;
    private final MetroCardService metroCardService;
    private final StationService stationService;

    public TravelTransaction(
            MetroCard metroCard,
            PassengerType passengerType,
            StationType stationType,
            FareService fareService,
            MetroCardService metroCardService,
            StationService stationService) {
        this.metroCard = metroCard;
        this.passengerType = passengerType;
        this.stationType = stationType;
        this.fareService = fareService;
        this.metroCardService = metroCardService;
        this.stationService = stationService;
    }

    public void execute() {

        int baseFare = passengerType.getBaseFare();

        int fare = fareService.calculateFare(metroCard, passengerType, stationType);
        int discount = baseFare - fare;

        if (!metroCardService.hasSufficientBalance(metroCard, fare)) {
            int required = fare - metroCard.getBalance();
            int serviceFee = metroCardService.autoRecharge(metroCard, required);
            stationService.addFareCollection(stationType, serviceFee);
        }

        metroCardService.deductFee(metroCard, fare);
        stationService.addFareCollection(stationType, fare);
        stationService.addDiscount(stationType, discount);
        stationService.incrementPassengerCount(stationType, passengerType);
    }
}
