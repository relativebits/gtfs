package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsCalendar;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsCalendarImportTask implements Callable<List<GtfsCalendar>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsCalendarImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsCalendar> call() {
        List<GtfsCalendar> calendars = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsCalendar model = new GtfsCalendar();
                model.setServiceId(row.get("service_id"));
                model.setMonday(row.get("monday"));
                model.setTuesday(row.get("tuesday"));
                model.setWednesday(row.get("wednesday"));
                model.setThursday(row.get("thursday"));
                model.setFriday(row.get("friday"));
                model.setSaturday(row.get("saturday"));
                model.setSunday(row.get("sunday"));
                model.setStartDate(row.get("start_date"));
                model.setEndDate(row.get("end_date"));
                calendars.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return calendars;
    }
}

