package com.relativebits.gtfs.models;

import java.io.Serializable;

public class GtfsShape implements Serializable {

    private static final long serialVersionUID = 1L;

    private String shapeId;
    private String shapePtLat;
    private String shapePtLon;
    private String shapePtSequence;
    private String shapeDistTraveled;

    public GtfsShape() {
        super();
    }

    public GtfsShape(String shapeId, String shapePtLat,
                     String shapePtLon, String shapePtSequence, String shapeDistTraveled) {
        super();
        this.shapeId = shapeId;
        this.shapePtLat = shapePtLat;
        this.shapePtLon = shapePtLon;
        this.shapePtSequence = shapePtSequence;
        this.shapeDistTraveled = shapeDistTraveled;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public String getShapePtLat() {
        return shapePtLat;
    }

    public void setShapePtLat(String shapePtLat) {
        this.shapePtLat = shapePtLat;
    }

    public String getShapePtLon() {
        return shapePtLon;
    }

    public void setShapePtLon(String shapePtLon) {
        this.shapePtLon = shapePtLon;
    }

    public String getShapePtSequence() {
        return shapePtSequence;
    }

    public void setShapePtSequence(String shapePtSequence) {
        this.shapePtSequence = shapePtSequence;
    }

    public String getShapeDistTraveled() {
        return shapeDistTraveled;
    }

    public void setShapeDistTraveled(String shapeDistTraveled) {
        this.shapeDistTraveled = shapeDistTraveled;
    }

}
