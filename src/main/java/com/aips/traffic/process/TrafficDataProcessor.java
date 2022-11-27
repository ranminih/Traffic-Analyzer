package com.aips.traffic.process;

import com.aips.traffic.model.TrafficCounterDataEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TrafficDataProcessor {

    int retrieveTotalCars(List<TrafficCounterDataEntry> trafficData);

    Map<LocalDate, Integer> retrieveCarsByDay(List<TrafficCounterDataEntry> trafficData);

    List<TrafficCounterDataEntry> retrieveMostCars(List<TrafficCounterDataEntry> trafficData, int numOfResults);

    List<TrafficCounterDataEntry> retrieveLeastCars(List<TrafficCounterDataEntry> trafficData, float period, float gap);
}
