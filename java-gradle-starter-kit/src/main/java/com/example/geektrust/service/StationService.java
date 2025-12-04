package com.example.geektrust.service;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationType;
import com.example.geektrust.model.StationSummary;

import java.util.EnumMap;
import java.util.Map;

public class StationService {
    private final Map<StationType, StationSummary> summaries = new EnumMap<>(StationType.class);

    public StationService(){
        summaries.put(StationType.CENTRAL, new StationSummary(StationType.CENTRAL));
        summaries.put(StationType.AIRPORT, new StationSummary(StationType.AIRPORT));
    }

    public void addFareCollection(StationType station, int amount){
        summaries.get(station).addCollection(amount);
    }

    public void addDiscount(StationType station, int amount){
        summaries.get(station).addDiscount(amount);
    }

    public void incrementPassengerCount(StationType station, PassengerType type){
        summaries.get(station).incrementPassengerCount(type);
    }

    public Map<StationType, StationSummary> getSummaries(){
        return summaries;
    }
}
