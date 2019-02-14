package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsFrequency implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tripId;
    private String startTime;
    private String endTime;
    private String headwaySecs;
    private String exactTimes;

    public GtfsFrequency() {
        super();
    }

    public GtfsFrequency(String tripId, String startTime,
                         String endTime, String headwaySecs, String exactTimes) {
        super();
        this.tripId = tripId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.headwaySecs = headwaySecs;
        this.exactTimes = exactTimes;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHeadwaySecs() {
        return headwaySecs;
    }

    public void setHeadwaySecs(String headwaySecs) {
        this.headwaySecs = headwaySecs;
    }

    public String getExactTimes() {
        return exactTimes;
    }

    public void setExactTimes(String exactTimes) {
        this.exactTimes = exactTimes;
    }

}
