package com.aips.traffic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrafficCounterDataEntry {

    private static final DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private LocalDateTime dateTime;
    private LocalDate date;
    private int numOfCars;

    public TrafficCounterDataEntry(String dateTime, String numOfCars) {

        this.dateTime = LocalDateTime.parse(dateTime, isoDateTimeFormatter);
        this.date = this.dateTime.toLocalDate();
        this.numOfCars = Integer.parseInt(numOfCars);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime.format(isoDateTimeFormatter));
    }

    public int getNumOfCars() {
        return numOfCars;
    }

    public void setNumOfCars(int numOfCars) {
        this.numOfCars = numOfCars;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
