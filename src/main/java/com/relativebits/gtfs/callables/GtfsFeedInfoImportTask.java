package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsFeedInfo;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsFeedInfoImportTask implements Callable<List<GtfsFeedInfo>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsFeedInfoImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsFeedInfo> call() {
        List<GtfsFeedInfo> feedInfos = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsFeedInfo model = new GtfsFeedInfo();
                model.setFeedPublisherName(row.get("feed_publisher_name"));
                model.setFeedPublisherUrl(row.get("feed_publisher_url"));
                model.setFeedLang(row.get("feed_lang"));
                model.setFeedStartDate(row.get("feed_start_date"));
                model.setFeedEndDate(row.get("feed_end_date"));
                model.setFeedVersion(row.get("feed_version"));
                feedInfos.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return feedInfos;
    }
}

