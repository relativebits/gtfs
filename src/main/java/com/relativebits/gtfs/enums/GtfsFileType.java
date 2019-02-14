package com.relativebits.gtfs.enums;


public enum GtfsFileType {
    AGENCY("Agency", "agency.txt"),
    CALENDAR("Calendar", "calendar.txt"),
    CALENDAR_DATE("CalendarDate", "calendar_dates.txt"),
    FARE_ATTRIBUTE("FareAttribute", "fare_attributes.txt"),
    FARE_RULE("FareRule", "fare_rules.txt"),
    FEED_INFO("FeedInfo", "feed_info.txt"),
    FREQUENCY("Frequency", "frequencies.txt"),
    ROUTE("Route", "routes.txt"),
    SHAPE("Shape", "shapes.txt"),
    STOP("Stop", "stops.txt"),
    STOP_TIME("StopTime", "stop_times.txt"),
    TRANSFER("Transfer", "transfers.txt"),
    TRIP("Trip", "trips.txt");

    private final String id;
    private final String fileName;

    private GtfsFileType(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public String getId() {
        return this.id;
    }

    public String getFileName() {
        return this.fileName;
    }

}

