package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsStopTime;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsStopTimeImportTask implements Callable<List<GtfsStopTime>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsStopTimeImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsStopTime> call() {
        List<GtfsStopTime> stopTimes = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsStopTime model = new GtfsStopTime();
                model.setTripId(row.get("trip_id"));
                model.setArrivalTime(row.get("arrival_time"));
                model.setDepartureTime(row.get("departure_time"));
                model.setStopId(row.get("stop_id"));
                model.setStopSequence(row.get("stop_sequence"));
                model.setStopHeadsign(row.get("stop_headsign"));
                model.setPickupType(row.get("pickup_type"));
                model.setDropOffType(row.get("drop_off_type"));
                model.setShapeDistTraveled(row.get("shape_dist_traveled"));
                stopTimes.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return stopTimes;
    }
}

