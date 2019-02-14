package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsStop;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsStopImportTask implements Callable<List<GtfsStop>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsStopImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsStop> call() {
        List<GtfsStop> stops = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsStop model = new GtfsStop();
                model.setStopId(row.get("stop_id"));
                model.setStopCode(row.get("stop_code"));
                model.setStopName(row.get("stop_name"));
                model.setStopDesc(row.get("stop_desc"));
                model.setStopLat(row.get("stop_lat"));
                model.setStopLon(row.get("stop_lon"));
                model.setZoneId(row.get("zone_id"));
                model.setLocationType(row.get("location_type"));
                model.setParentStation(row.get("parent_station"));
                model.setStopTimezone(row.get("stop_timezone"));
                model.setWheelchairBoarding(row.get("wheelchair_boarding"));
                stops.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return stops;
    }
}

