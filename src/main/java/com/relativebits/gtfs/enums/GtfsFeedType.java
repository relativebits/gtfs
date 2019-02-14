package com.relativebits.gtfs.enums;

public enum GtfsFeedType {
    BLACKSBURG_TRANSIT("Blacksburg Transit", "http://216.252.195.248/gtfs/google_transit.zip", "blacksburg.zip", "blacksburg"),
    VALLEY_METRO_TRANSIT("Valley Metro", "http://www.gtfs-data-exchange.com/agency/valley-metro/latest.zip", "valley_metro.zip", "valley_metro"),
    PULASKI_AREA_TRANSIT("Pulaski Area Transit", "http://www.gtfs-data-exchange.com/agency/pulaski-area-transit/latest.zip", "pulaski_area_transit.zip", "pulaski_area_transit"),
    RADFORD_TRANSIT("Radford Transit", "http://www.gtfs-data-exchange.com/agency/radford-transit/latest.zip", "radford_transit.zip", "radford_transit");

    private String name;
    private String url;
    private String packedFileName;
    private String unpackedFileName;

    private GtfsFeedType(String name, String url, String packedFileName, String unpackedFileName) {
        this.name = name;
        this.url = url;
        this.packedFileName = packedFileName;
        this.unpackedFileName = unpackedFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPackedFileName() {
        return packedFileName;
    }

    public void setPackedFileName(String packedFileName) {
        this.packedFileName = packedFileName;
    }

    public String getUnpackedFileName() {
        return unpackedFileName;
    }

    public void setUnpackedFileName(String unpackedFileName) {
        this.unpackedFileName = unpackedFileName;
    }
}
