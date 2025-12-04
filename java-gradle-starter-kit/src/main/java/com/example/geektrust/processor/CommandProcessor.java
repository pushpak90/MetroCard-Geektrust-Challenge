package com.example.geektrust.processor;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.StationSummary;
import com.example.geektrust.service.FareService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.StationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class CommandProcessor {
    private final MetroCardService metroCardService = new MetroCardService();
    private final StationService stationService = new StationService();
    private final FareService fareService = new FareService();

    public void process(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.length() == 0) {
                continue;
            }
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "BALANCE":
                    processBalance(parts);
                    break;
                case "CHECK_IN":
                    processCheckIn(parts);
                    break;
                case "PRINT_SUMMARY":
                    printSummary();
                    break;
            }
        }
        sc.close();
    }

    private void processBalance(String[] parts) {
        String cardId = parts[1];
        int amount = Integer.parseInt(parts[2]);
        metroCardService.addBalance(cardId, amount);
    }

    private void processCheckIn(String[] parts) {

        String cardId = parts[1];
        PassengerType passengerType = PassengerType.valueOf(parts[2]);
        StationType station = StationType.valueOf(parts[3]);

        MetroCard card = metroCardService.getCard(cardId);

        // Step 1: Calculate fare
        int fare = fareService.calculateFare(card, passengerType, station);
        int discount = passengerType.getBaseFare() - fare;

        // Step 2: Check balance
        if (!metroCardService.hasSufficientBalance(card, fare)) {

            int required = fare - card.getBalance();
            int serviceFee = metroCardService.autoRecharge(card, required);

            // Service fee goes to station
            stationService.addFareCollection(station, serviceFee);
        }

        // Step 3: Deduct fare
        metroCardService.deductFee(card, fare);

        // Step 4: Update station summary
        stationService.addFareCollection(station, fare);
        stationService.addDiscount(station, discount);
        stationService.incrementPassengerCount(station, passengerType);
    }

    private void printSummary() {

        Map<StationType, StationSummary> summaries = stationService.getSummaries();

        printStationSummary(summaries.get(StationType.CENTRAL));
        printStationSummary(summaries.get(StationType.AIRPORT));
    }

   private void printStationSummary(StationSummary summary) {

    System.out.println("TOTAL_COLLECTION " +
            summary.getStationType() + " " +
            summary.getTotalCollection() + " " +
            summary.getTotalDiscount());

    System.out.println("PASSENGER_TYPE_SUMMARY");

    summary.getPassengerCounts().entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 0)   
        .sorted((a, b) -> {                     
            int cmp = b.getValue() - a.getValue();
            if (cmp != 0) return cmp;
            return a.getKey().name().compareTo(b.getKey().name());
        })
        .forEach(entry ->
            System.out.println(entry.getKey() + " " + entry.getValue())
        );
}

}
