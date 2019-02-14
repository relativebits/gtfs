package com.relativebits.gtfs.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.models.GtfsFareAttribute;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class GtfsFareAttributeImportTask implements Callable<List<GtfsFareAttribute>> {

    private GtfsFeedType gtfsFeedType;
    private String gtfsFilePath;

    public GtfsFareAttributeImportTask(GtfsFeedType gtfsFeedType, String gtfsFilePath) {
        this.gtfsFeedType = gtfsFeedType;
        this.gtfsFilePath = gtfsFilePath;
    }

    @Override
    public List<GtfsFareAttribute> call() {
        List<GtfsFareAttribute> fareAttributes = new ArrayList<>();
        ICsvMapReader reader = null;
        Map<String, String> row = null;

        try {
            reader = new CsvMapReader(new InputStreamReader(new BOMInputStream(new FileInputStream(gtfsFilePath), false)), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            while ((row = reader.read(header)) != null) {
                GtfsFareAttribute model = new GtfsFareAttribute();
                model.setFareId(row.get("fare_id"));
                model.setPrice(row.get("price"));
                model.setCurrencyType(row.get("currency_type"));
                model.setPaymentMethod(row.get("payment_method"));
                model.setTransfers(row.get("transfers"));
                model.setTransferDuration(row.get("transfer_duration"));
                fareAttributes.add(model);
            }
        } catch (Exception ex) {
            Logger.error(this.getClass(), "Error importing CSV file: " + gtfsFilePath + ": " + ex);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return fareAttributes;
    }
}
