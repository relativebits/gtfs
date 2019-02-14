package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsFeedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String feedPublisherName;
    private String feedPublisherUrl;
    private String feedLang;
    private String feedStartDate;
    private String feedEndDate;
    private String feedVersion;

    public GtfsFeedInfo() {
        super();
    }

    public GtfsFeedInfo(String feedPublisherName,
                        String feedPublisherUrl, String feedLang, String feedStartDate,
                        String feedEndDate, String feedVersion) {
        super();
        this.feedPublisherName = feedPublisherName;
        this.feedPublisherUrl = feedPublisherUrl;
        this.feedLang = feedLang;
        this.feedStartDate = feedStartDate;
        this.feedEndDate = feedEndDate;
        this.feedVersion = feedVersion;
    }

    public String getFeedPublisherName() {
        return feedPublisherName;
    }

    public void setFeedPublisherName(String feedPublisherName) {
        this.feedPublisherName = feedPublisherName;
    }

    public String getFeedPublisherUrl() {
        return feedPublisherUrl;
    }

    public void setFeedPublisherUrl(String feedPublisherUrl) {
        this.feedPublisherUrl = feedPublisherUrl;
    }

    public String getFeedLang() {
        return feedLang;
    }

    public void setFeedLang(String feedLang) {
        this.feedLang = feedLang;
    }

    public String getFeedStartDate() {
        return feedStartDate;
    }

    public void setFeedStartDate(String feedStartDate) {
        this.feedStartDate = feedStartDate;
    }

    public String getFeedEndDate() {
        return feedEndDate;
    }

    public void setFeedEndDate(String feedEndDate) {
        this.feedEndDate = feedEndDate;
    }

    public String getFeedVersion() {
        return feedVersion;
    }

    public void setFeedVersion(String feedVersion) {
        this.feedVersion = feedVersion;
    }

}
