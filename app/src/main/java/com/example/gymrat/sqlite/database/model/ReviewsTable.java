package com.example.gymrat.sqlite.database.model;

public class ReviewsTable {

    public static final String TABLE_NAME = "reviews";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_SQUAT_RACKS = "squat_racks";
    public static final String COLUMN_BENCH_PRESSES = "bench_presses";
    public static final String COLUMN_OVERALL_RATING = "overall_rating";
    public static final String COLUMN_TIMESTAMP = "timestamp";

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

}
