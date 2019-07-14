package com.example.gymrat.sqlite.database.model;

public class ReviewsTable {

    public static final String TABLE_NAME = "reviews";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_SQUAT_RACKS = "squat_racks";
    public static final String COLUMN_BENCH_PRESSES = "bench_presses";
    public static final String COLUMN_OVERALL_RATING = "overall_rating";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private String comment;
    private int benchPresses;
    private int squatRacks;
    private int overallRating;
    private int id;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_COMMENT + " TEXT,"
                    + COLUMN_BENCH_PRESSES + " INTEGER,"
                    + COLUMN_SQUAT_RACKS + " INTEGER,"
                    + COLUMN_OVERALL_RATING + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public ReviewsTable() {
    }

    public ReviewsTable(int id, String comment, int benchPresses, int squatRacks, int overallRating, String timestamp) {
        this.id = id;
        this.comment = comment;
        this.benchPresses = benchPresses;
        this.squatRacks = squatRacks;
        this.overallRating = overallRating;
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBenchPresses() {
        return benchPresses;
    }

    public void setBenchPresses(int benchPresses) {
        this.benchPresses = benchPresses;
    }

    public int getSquatRacks() {
        return squatRacks;
    }

    public void setSquatRacks(int squatRacks) {
        this.squatRacks = squatRacks;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
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
}
