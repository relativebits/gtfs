package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsTrip implements Serializable {

    private static final long serialVersionUID = 1L;

    private String routeId;
    private String serviceId;
    private String tripId;
    private String tripHeadsign;
    private String tripShortName;
    private String directionId;
    private String blockId;
    private String shapeId;
    private String wheelchairAccessible;
    private String bikesAllowed;

    public GtfsTrip() {
        super();
    }

    public GtfsTrip(String routeId, String serviceId, String tripId,
                    String tripHeadsign, String tripShortName, String directionId,
                    String blockId, String shapeId, String wheelchairAccessible,
                    String bikesAllowed) {
        super();
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.tripId = tripId;
        this.tripHeadsign = tripHeadsign;
        this.tripShortName = tripShortName;
        this.directionId = directionId;
        this.blockId = blockId;
        this.shapeId = shapeId;
        this.wheelchairAccessible = wheelchairAccessible;
        this.bikesAllowed = bikesAllowed;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public void setTripHeadsign(String tripHeadsign) {
        this.tripHeadsign = tripHeadsign;
    }

    public String getTripShortName() {
        return tripShortName;
    }

    public void setTripShortName(String tripShortName) {
        this.tripShortName = tripShortName;
    }

    public String getDirectionId() {
        return directionId;
    }

    public void setDirectionId(String directionId) {
        this.directionId = directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public String getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(String wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public String getBikesAllowed() {
        return bikesAllowed;
    }

    public void setBikesAllowed(String bikesAllowed) {
        this.bikesAllowed = bikesAllowed;
    }

}

