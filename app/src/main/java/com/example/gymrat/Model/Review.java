package com.example.gymrat.Model;

public class Review {

    private String comment;
    private int squatRacks;
    private int benchPresses;
    private int overallRating;
    private String locationName;
    private int id;
    private String timestamp;

    public Review() { }

    public Review(int id, String comment, int benchPresses, int squatRacks, int overallRating, String timestamp) {
        this.comment = comment;
        this.benchPresses = benchPresses;
        this.squatRacks = squatRacks;
        this.overallRating = overallRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSquatRacks() {
        return squatRacks;
    }

    public void setSquatRacks(int squatRacks) {
        this.squatRacks = squatRacks;
    }

    public int getBenchPresses() {
        return benchPresses;
    }

    public void setBenchPresses(int benchPresses) {
        this.benchPresses = benchPresses;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
