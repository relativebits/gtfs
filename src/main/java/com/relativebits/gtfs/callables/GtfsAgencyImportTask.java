package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsAgency;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsAgencyImportTask implements Callable<List<GtfsAgency>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsAgencyImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsAgency> call() {
        List<GtfsAgency> agencies = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsAgency model = new GtfsAgency();
                model.setAgencyId(row.get("agency_id"));
                model.setAgencyName(row.get("agency_name"));
                model.setAgencyUrl(row.get("agency_url"));
                model.setAgencyTimezone(row.get("agency_timezone"));
                model.setAgencyLang(row.get("agency_lang"));
                model.setAgencyPhone(row.get("agency_phone"));
                model.setAgencyFareUrl(row.get("agency_fare_url"));
                agencies.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return agencies;
    }
}
