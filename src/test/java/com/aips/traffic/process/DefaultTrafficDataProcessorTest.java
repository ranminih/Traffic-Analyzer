
package com.aips.traffic.process;

import com.aips.traffic.model.TrafficCounterDataEntry;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test the functionality of DefaultTrafficDataProcessor
 */
public class DefaultTrafficDataProcessorTest {

    private static final Map<LocalDateTime, Integer> expectedMostCars = new HashMap<LocalDateTime, Integer>() {
        {
            put(LocalDateTime.parse("2021-12-01T07:30:00"), 46);
            put(LocalDateTime.parse("2021-12-01T08:00:00"), 42);
            put(LocalDateTime.parse("2021-12-08T18:00:00"), 33);
        }
    };

    private static final Map<LocalDateTime, Integer> expectedLeastCars1 = new HashMap<LocalDateTime, Integer>() {
        {
            put(LocalDateTime.parse("2021-12-01T05:00:00"), 5);
            put(LocalDateTime.parse("2021-12-01T05:30:00"), 12);
            put(LocalDateTime.parse("2021-12-01T06:00:00"), 14);
        }
    };

    private static final Map<LocalDateTime, Integer> expectedLeastCars2 = new HashMap<LocalDateTime, Integer>() {
        {
            put(LocalDateTime.parse("2021-12-01T05:00:00"), 5);
            put(LocalDateTime.parse("2021-12-01T05:30:00"), 12);
            put(LocalDateTime.parse("2021-12-01T06:00:00"), 14);
        }
    };

    private DefaultTrafficDataProcessor dataProcessor;
    private List<TrafficCounterDataEntry> trafficDataEntries;
    private List<TrafficCounterDataEntry> trafficDataEntriesMultiple;

    @Before
    public void init() {

        dataProcessor = new DefaultTrafficDataProcessor();
        trafficDataEntries = dataProcessor.retrieveTrafficDataFromFile("src/test/resources/TrafficData.txt");
        trafficDataEntriesMultiple = dataProcessor.retrieveTrafficDataFromFile(
                "src/test/resources/TrafficData_MultipleOccurrences.txt");
        assertEquals(28, trafficDataEntries.size());
    }

    @Test
    public void testRetrieveTotalCars() {

        int totalCars = dataProcessor.retrieveTotalCars(trafficDataEntries);
        assertEquals(453, totalCars);
    }

    @Test
    public void testRetrieveCarsByDay() {

        Map<LocalDate, Integer> carsByDay = dataProcessor.retrieveCarsByDay(trafficDataEntries);
        assertEquals(4, carsByDay.size());
        assertNotNull(carsByDay.get(LocalDate.parse("2021-12-01")));
        assertNotNull(carsByDay.get(LocalDate.parse("2021-12-05")));
        assertNotNull(carsByDay.get(LocalDate.parse("2021-12-08")));
        assertNotNull(carsByDay.get(LocalDate.parse("2021-12-09")));
        assertEquals(230, carsByDay.get(LocalDate.parse("2021-12-01")).intValue());
        assertEquals(81, carsByDay.get(LocalDate.parse("2021-12-05")).intValue());
        assertEquals(134, carsByDay.get(LocalDate.parse("2021-12-08")).intValue());
        assertEquals(8, carsByDay.get(LocalDate.parse("2021-12-09")).intValue());
    }

    @Test
    public void testRetrieveMostCars() {

        List<TrafficCounterDataEntry> carsByDay = dataProcessor.retrieveMostCars(trafficDataEntries, 3);
        assertEquals(3, carsByDay.size());
        carsByDay.stream().forEach(
                entry -> {
                    assertTrue(expectedMostCars.containsKey(entry.getDateTime()));
                    assertEquals(expectedMostCars.get(entry.getDateTime()).intValue(), entry.getNumOfCars());
                });
    }

    @Test
    public void testRetrieveMostCars_MultipleOccurrences() {

        List<TrafficCounterDataEntry> carsByDay = dataProcessor.retrieveMostCars(trafficDataEntriesMultiple, 3);
        assertEquals(3, carsByDay.size());
        carsByDay.stream().forEach(
                entry -> {
                    assertTrue(expectedMostCars.containsKey(entry.getDateTime()));
                    assertEquals(expectedMostCars.get(entry.getDateTime()).intValue(), entry.getNumOfCars());
                });
    }

    @Test
    public void testRetrieveLeastCars() {

        List<TrafficCounterDataEntry> carsByDay = dataProcessor.retrieveLeastCars(trafficDataEntries, 1.5f, 0.5f);
        assertEquals(3, carsByDay.size());
        carsByDay.stream().forEach(
                entry -> {
                    assertTrue(expectedLeastCars1.containsKey(entry.getDateTime()));
                    assertEquals(expectedLeastCars1.get(entry.getDateTime()).intValue(), entry.getNumOfCars());
                });
    }

    @Test
    public void testRetrieveLeastCars_MultipleOccurrences() {

        List<TrafficCounterDataEntry> carsByDay = dataProcessor.retrieveLeastCars(trafficDataEntriesMultiple, 1.5f,
                0.5f);
        assertEquals(3, carsByDay.size());
        carsByDay.stream().forEach(
                entry -> {
                    assertTrue(expectedLeastCars1.containsKey(entry.getDateTime()));
                    assertEquals(expectedLeastCars1.get(entry.getDateTime()).intValue(), entry.getNumOfCars());
                });
    }

    @Test
    public void testRetrieveTrafficDataFromFile_Empty() {

        List<TrafficCounterDataEntry> trafficDataEntries = dataProcessor
                .retrieveTrafficDataFromFile("src/test/resources/TrafficData_Empty.txt");
        assertNotNull(trafficDataEntries);
        assertEquals(0, trafficDataEntries.size());

        int totalCars = dataProcessor.retrieveTotalCars(trafficDataEntries);
        assertEquals(0, totalCars);

        Map<LocalDate, Integer> carsByDay = dataProcessor.retrieveCarsByDay(trafficDataEntries);
        assertEquals(0, carsByDay.size());

        List<TrafficCounterDataEntry> mostCars = dataProcessor.retrieveMostCars(trafficDataEntries, 3);
        assertEquals(0, mostCars.size());

        List<TrafficCounterDataEntry> leastCars = dataProcessor.retrieveLeastCars(trafficDataEntries, 90f, 30f);
        assertEquals(0, leastCars.size());
    }
}
