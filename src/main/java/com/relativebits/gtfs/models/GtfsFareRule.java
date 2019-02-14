package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsFareRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fareId;
    private String routeId;
    private String originId;
    private String destinationId;
    private String containsId;

    public GtfsFareRule() {
        super();
    }

    public GtfsFareRule(String fareId, String routeId,
                        String originId, String destinationId, String containsId) {
        super();
        this.fareId = fareId;
        this.routeId = routeId;
        this.originId = originId;
        this.destinationId = destinationId;
        this.containsId = containsId;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getContainsId() {
        return containsId;
    }

    public void setContainsId(String containsId) {
        this.containsId = containsId;
    }

}
