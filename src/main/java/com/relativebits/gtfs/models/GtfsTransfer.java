package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fromStopId;
    private String toStopId;
    private String transferType;
    private String minTransferTime;

    public GtfsTransfer() {
        super();
    }

    public GtfsTransfer(String fromStopId, String toStopId,
                        String transferType, String minTransferTime) {
        super();
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.transferType = transferType;
        this.minTransferTime = minTransferTime;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getMinTransferTime() {
        return minTransferTime;
    }

    public void setMinTransferTime(String minTransferTime) {
        this.minTransferTime = minTransferTime;
    }

}

