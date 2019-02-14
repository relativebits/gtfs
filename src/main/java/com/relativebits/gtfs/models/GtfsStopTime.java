package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsStopTime implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tripId;
    private String arrivalTime;
    private String departureTime;
    private String stopId;
    private String stopSequence;
    private String stopHeadsign;
    private String pickupType;
    private String dropOffType;
    private String shapeDistTraveled;
    private GtfsTrip gtfsTrip;

    public GtfsStopTime() {
        super();
    }

    public GtfsStopTime(String tripId, String arrivalTime,
                        String departureTime, String stopId, String stopSequence,
                        String stopHeadsign, String pickupType, String dropOffType,
                        String shapeDistTraveled) {
        super();
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stopSequence = stopSequence;
        this.stopHeadsign = stopHeadsign;
        this.pickupType = pickupType;
        this.dropOffType = dropOffType;
        this.shapeDistTraveled = shapeDistTraveled;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(String stopSequence) {
        this.stopSequence = stopSequence;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }

    public void setStopHeadsign(String stopHeadsign) {
        this.stopHeadsign = stopHeadsign;
    }

    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }

    public String getDropOffType() {
        return dropOffType;
    }

    public void setDropOffType(String dropOffType) {
        this.dropOffType = dropOffType;
    }

    public String getShapeDistTraveled() {
        return shapeDistTraveled;
    }

    public void setShapeDistTraveled(String shapeDistTraveled) {
        this.shapeDistTraveled = shapeDistTraveled;
    }

    public GtfsTrip getGtfsTrip() {
        return gtfsTrip;
    }

    public void setGtfsTrip(GtfsTrip gtfsTrip) {
        this.gtfsTrip = gtfsTrip;
    }

}

