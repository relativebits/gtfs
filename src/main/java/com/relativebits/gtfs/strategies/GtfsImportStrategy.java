package com.relativebits.gtfs.strategies;

import com.relativebits.gtfs.callables.*;
import com.relativebits.gtfs.common.Config;
import com.relativebits.gtfs.common.Logger;
import com.relativebits.gtfs.enums.GtfsFeedType;
import com.relativebits.gtfs.enums.GtfsFileType;
import com.relativebits.gtfs.helper.FileHelper;
import com.relativebits.gtfs.models.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GtfsImportStrategy {

    private int numberOfThreads;
    private String feedsRootPath;
    private GtfsFeedType gtfsFeedType = null;
    private List<GtfsAgency> agencies;
    private List<GtfsCalendarDate> calendarDates;
    private List<GtfsCalendar> calendars;
    private List<GtfsFareAttribute> fareAttributes;
    private List<GtfsFareRule> fareRules;
    private List<GtfsFeedInfo> feedInfos;
    private List<GtfsFrequency> frequencies;
    private List<GtfsRoute> routes;
    private List<GtfsShape> shapes;
    private List<GtfsStopTime> stopTimes;
    private List<GtfsStop> stops;
    private List<GtfsTransfer> transfers;
    private List<GtfsTrip> trips;

    public GtfsImportStrategy(GtfsFeedType gtfsFeedType) {
        this.numberOfThreads = Config.GtfsImportThreads();
        this.feedsRootPath = Config.GtfsImportFolder();
        this.gtfsFeedType = gtfsFeedType;
    }

    public void execute() throws InterruptedException, ExecutionException {
        String rootFeedPath = feedsRootPath + File.separator + gtfsFeedType.getUnpackedFileName();
        String packedFeedPath = rootFeedPath + File.separator + gtfsFeedType.getPackedFileName();
        String unpackedFeedPath = rootFeedPath + File.separator + gtfsFeedType.getUnpackedFileName();

        boolean doesRootFeedPathExist = FileHelper.doesFileOrFolderExist(rootFeedPath);
        if (doesRootFeedPathExist) {
            FileHelper.deleteFolderOrFile(rootFeedPath);
        }

        boolean isCreated = FileHelper.createFolder(rootFeedPath);
        if (!isCreated) {
            Logger.error(this.getClass(), "GTFS root feed path [" + gtfsFeedType.getName() + "] did not create");
            return;
        }

        boolean isDownloaded = FileHelper.downloadFile(gtfsFeedType.getUrl(), packedFeedPath);
        if (!isDownloaded) {
            Logger.error(this.getClass(), "GTFS feed [" + gtfsFeedType.getName() + "] did not download");
            return;
        }

        boolean isUnpacked = FileHelper.unpackFile(packedFeedPath, unpackedFeedPath);
        if (!isUnpacked) {
            Logger.error(this.getClass(), "GTFS feed [" + gtfsFeedType.getName() + "] did not unpack");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Future<List<GtfsAgency>> fAgencies = executorService.submit(new GtfsAgencyImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.AGENCY.getFileName()));
        Future<List<GtfsCalendarDate>> fCalendarDates = executorService.submit(new GtfsCalendarDateImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.CALENDAR_DATE.getFileName()));
        Future<List<GtfsCalendar>> fCalendars = executorService.submit(new GtfsCalendarImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.CALENDAR.getFileName()));
        Future<List<GtfsFareAttribute>> fFareAttributes = executorService.submit(new GtfsFareAttributeImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.FARE_ATTRIBUTE.getFileName()));
        Future<List<GtfsFareRule>> fFareRules = executorService.submit(new GtfsFareRuleImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.FARE_RULE.getFileName()));
        Future<List<GtfsFeedInfo>> fFeedInfos = executorService.submit(new GtfsFeedInfoImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.FEED_INFO.getFileName()));
        Future<List<GtfsFrequency>> fFrequencies = executorService.submit(new GtfsFrequencyImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.FREQUENCY.getFileName()));
        Future<List<GtfsRoute>> fRoutes = executorService.submit(new GtfsRouteImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.ROUTE.getFileName()));
        Future<List<GtfsShape>> fShapes = executorService.submit(new GtfsShapeImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.SHAPE.getFileName()));
        Future<List<GtfsStopTime>> fStopTimes = executorService.submit(new GtfsStopTimeImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.STOP_TIME.getFileName()));
        Future<List<GtfsStop>> fStops = executorService.submit(new GtfsStopImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.STOP.getFileName()));
        Future<List<GtfsTransfer>> fTransfers = executorService.submit(new GtfsTransferImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.TRANSFER.getFileName()));
        Future<List<GtfsTrip>> fTrips = executorService.submit(new GtfsTripImportTask(gtfsFeedType, unpackedFeedPath + File.separator + GtfsFileType.TRIP.getFileName()));

        this.agencies = fAgencies.get();
        this.calendarDates = fCalendarDates.get();
        this.calendars = fCalendars.get();
        this.fareAttributes = fFareAttributes.get();
        this.fareRules = fFareRules.get();
        this.feedInfos = fFeedInfos.get();
        this.frequencies = fFrequencies.get();
        this.routes = fRoutes.get();
        this.shapes = fShapes.get();
        this.stopTimes = fStopTimes.get();
        this.stops = fStops.get();
        this.transfers = fTransfers.get();
        this.trips = fTrips.get();

        executorService.shutdown();
    }

    public GtfsFeedType getGtfsFeedType() {
        return gtfsFeedType;
    }

    public List<GtfsAgency> getAgencies() {
        return agencies;
    }

    public List<GtfsCalendarDate> getCalendarDates() {
        return calendarDates;
    }

    public List<GtfsCalendar> getCalendars() {
        return calendars;
    }

    public List<GtfsFareAttribute> getFareAttributes() {
        return fareAttributes;
    }

    public List<GtfsFareRule> getFareRules() {
        return fareRules;
    }

    public List<GtfsFeedInfo> getFeedInfos() {
        return feedInfos;
    }

    public List<GtfsFrequency> getFrequencies() {
        return frequencies;
    }

    public List<GtfsRoute> getRoutes() {
        return routes;
    }

    public List<GtfsShape> getShapes() {
        return shapes;
    }

    public List<GtfsStopTime> getStopTimes() {
        return stopTimes;
    }

    public List<GtfsStop> getStops() {
        return stops;
    }

    public List<GtfsTransfer> getTransfers() {
        return transfers;
    }

    public List<GtfsTrip> getTrips() {
        return trips;
    }
}