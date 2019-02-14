package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsFareAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fareId;
    private String price;
    private String currencyType;
    private String paymentMethod;
    private String transfers;
    private String transferDuration;

    public GtfsFareAttribute() {
        super();
    }

    public GtfsFareAttribute(String fareId, String price,
                             String currencyType, String paymentMethod, String transfers,
                             String transferDuration) {
        super();
        this.fareId = fareId;
        this.price = price;
        this.currencyType = currencyType;
        this.paymentMethod = paymentMethod;
        this.transfers = transfers;
        this.transferDuration = transferDuration;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransfers() {
        return transfers;
    }

    public void setTransfers(String transfers) {
        this.transfers = transfers;
    }

    public String getTransferDuration() {
        return transferDuration;
    }

    public void setTransferDuration(String transferDuration) {
        this.transferDuration = transferDuration;
    }

}
