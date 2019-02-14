package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsCalendarDate;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsCalendarDateImportTask implements Callable<List<GtfsCalendarDate>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsCalendarDateImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsCalendarDate> call() {
        List<GtfsCalendarDate> calendarDates = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsCalendarDate model = new GtfsCalendarDate();
                model.setServiceId(row.get("service_id"));
                model.setDate(row.get("date"));
                model.setExceptionType(row.get("exception_type"));
                calendarDates.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return calendarDates;
    }
}
