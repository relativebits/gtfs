package com.relativebits.gtfs.test;

import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.strategies.GtfsImportStrategy;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

public class GtfsTests {

    @Test
    public void importBlacksburgTransit() {
        long time = System.currentTimeMillis();
        System.out.println("--------------------------------------------------");

        GtfsFeedType gtfsFeedType = GtfsFeedType.BLACKSBURG_TRANSIT;
        GtfsImportStrategy gtfsImportStrategy = new GtfsImportStrategy(gtfsFeedType);

        try {
            gtfsImportStrategy.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        int expectedAgencyCount = 1;
        int actualAgencyCount = gtfsImportStrategy.getAgencies().size();
        assertEquals(expectedAgencyCount, actualAgencyCount);

        System.out.println(((System.currentTimeMillis() - time) / 1000) + " seconds");
        System.out.println("--------------------------------------------------");
    }
}
