package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsCalendarDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serviceId;
    private String date;
    private String exceptionType;

    public GtfsCalendarDate() {
        super();
    }

    public GtfsCalendarDate(String serviceId, String date,
                            String exceptionType) {
        super();
        this.serviceId = serviceId;
        this.date = date;
        this.exceptionType = exceptionType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

}

