package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsRoute;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsRouteImportTask implements Callable<List<GtfsRoute>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsRouteImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsRoute> call() {
        List<GtfsRoute> routes = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsRoute model = new GtfsRoute();
                model.setRouteId(row.get("route_id"));
                model.setAgencyId(row.get("agency_id"));
                model.setRouteShortName(row.get("route_short_name"));
                model.setRouteLongName(row.get("route_long_name"));
                model.setRouteDesc(row.get("route_desc"));
                model.setRouteType(row.get("route_type"));
                model.setRouteUrl(row.get("route_url"));
                model.setRouteColor(row.get("route_color"));
                model.setRouteTextColor(row.get("route_text_color"));
                routes.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return routes;
    }
}

