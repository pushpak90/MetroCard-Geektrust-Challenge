package com.example.geektrust.processor;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.StationSummary;
import com.example.geektrust.model.TravelTransaction;
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

        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");

            if ("BALANCE".equals(parts[0])) {
                metroCardService.addBalance(parts[1], Integer.parseInt(parts[2]));
            } else if ("CHECK_IN".equals(parts[0])) {
                handleCheckIn(parts);
            } else if ("PRINT_SUMMARY".equals(parts[0])) {
                printSummary();
            }
        }

        sc.close();
    }

    private void handleCheckIn(String[] parts) {

        MetroCard card = metroCardService.getCard(parts[1]);
        PassengerType passengerType = PassengerType.valueOf(parts[2]);
        StationType stationType = StationType.valueOf(parts[3]);

        TravelTransaction transaction =
                new TravelTransaction(
                        card,
                        passengerType,
                        stationType,
                        fareService,
                        metroCardService,
                        stationService
                );

        transaction.execute();
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
                .filter(e -> e.getValue() > 0)
                .sorted((a, b) -> {
                    int c = b.getValue() - a.getValue();
                    return c != 0 ? c : a.getKey().name().compareTo(b.getKey().name());
                })
                .forEach(e ->
                        System.out.println(e.getKey() + " " + e.getValue())
                );
    }
}
