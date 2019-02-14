package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsFareRule;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsFareRuleImportTask implements Callable<List<GtfsFareRule>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsFareRuleImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsFareRule> call() {
        List<GtfsFareRule> fareRules = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsFareRule model = new GtfsFareRule();
                model.setFareId(row.get("fare_id"));
                model.setRouteId(row.get("route_id"));
                model.setOriginId(row.get("origin_id"));
                model.setDestinationId(row.get("destination_id"));
                model.setContainsId(row.get("contains_id"));
                fareRules.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return fareRules;
    }
}
