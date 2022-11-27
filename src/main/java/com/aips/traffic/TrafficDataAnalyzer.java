package com.aips.traffic;

import com.aips.traffic.model.TrafficCounterDataEntry;
import com.aips.traffic.process.DefaultTrafficDataProcessor;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TrafficDataAnalyzer {

    public static void main(String[] args) {

        if (args == null || Array.getLength(args) == 0) {
            System.err.println("Specify a path of the input file.");
            return;
        }

        DefaultTrafficDataProcessor dataProcessor = new DefaultTrafficDataProcessor();
        List<TrafficCounterDataEntry> trafficDataEntries = dataProcessor.retrieveTrafficDataFromFile(args[0]);

        int totalCars = dataProcessor.retrieveTotalCars(trafficDataEntries);
        System.out.println("Total Number of Cars : " + totalCars);

        Map<LocalDate, Integer> carsByDay = dataProcessor.retrieveCarsByDay(trafficDataEntries);
        System.out.println("\nNumber of Cars by Day :");
        carsByDay.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        List<TrafficCounterDataEntry> mostCars = dataProcessor.retrieveMostCars(trafficDataEntries, 3);
        System.out.println("\nThe Top 3 half hours with Most Cars :");
        mostCars.stream().forEach(entry -> System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .format(entry.getDateTime()) + " " + entry.getNumOfCars()));

        List<TrafficCounterDataEntry> leastCars = dataProcessor.retrieveLeastCars(trafficDataEntries, 90f, 30f);
        System.out.println("\nThe 1.5 Hour Period with Least Cars :");
        leastCars.stream().forEach(entry -> System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .format(entry.getDateTime()) + " " + entry.getNumOfCars()));
    }
}
