package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsTrip;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsTripImportTask implements Callable<List<GtfsTrip>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsTripImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsTrip> call() {
        List<GtfsTrip> trips = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsTrip model = new GtfsTrip();
                model.setRouteId(row.get("route_id"));
                model.setServiceId(row.get("service_id"));
                model.setTripId(row.get("trip_id"));
                model.setTripHeadsign(row.get("trip_headsign"));
                model.setTripShortName(row.get("trip_short_name"));
                model.setDirectionId(row.get("direction_id"));
                model.setBlockId(row.get("block_id"));
                model.setShapeId(row.get("shape_id"));
                model.setWheelchairAccessible(row.get("wheelchair_accessible"));
                model.setBikesAllowed(row.get("bikes_allowed"));
                trips.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return trips;
    }
}

