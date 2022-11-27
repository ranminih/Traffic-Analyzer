
package com.aips.traffic.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrafficCounterDataEntryTest {

    @Test
    public void testSetAndGetTrafficDataEntry() {

        TrafficCounterDataEntry trafficDataEntry = new TrafficCounterDataEntry("2021-12-01T05:00:30", "5");
        assertEquals(5, trafficDataEntry.getNumOfCars());
        assertEquals("2021-12-01T05:00:30", trafficDataEntry.getDateTime().toString());
        assertEquals("2021-12-01", trafficDataEntry.getDate().toString());
    }
}
