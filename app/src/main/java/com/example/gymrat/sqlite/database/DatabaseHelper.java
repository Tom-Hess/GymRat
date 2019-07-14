package com.example.gymrat.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gymrat.Model.Review;
import com.example.gymrat.sqlite.database.model.ReviewsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "reviews_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create reviews table
        db.execSQL(ReviewsTable.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ReviewsTable.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertReview(Review review) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(ReviewsTable.COLUMN_BENCH_PRESSES, review.getBenchPresses());
        values.put(ReviewsTable.COLUMN_COMMENT, review.getComment());
        values.put(ReviewsTable.COLUMN_SQUAT_RACKS, review.getSquatRacks());
        values.put(ReviewsTable.COLUMN_OVERALL_RATING, review.getOverallRating());

        // insert row
        long id = db.insert(ReviewsTable.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    // TODO - create updates for certain parameters (WHERE clause)
    public int updateReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ReviewsTable.COLUMN_OVERALL_RATING, review.getOverallRating());

        // updating row
        return db.update(ReviewsTable.TABLE_NAME, values, ReviewsTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(review.getId())});
    }

    public void deleteReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ReviewsTable.TABLE_NAME, ReviewsTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(review.getId())});
        db.close();
    }


    public Review getReview(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ReviewsTable.TABLE_NAME,
                new String[]{ReviewsTable.COLUMN_ID, ReviewsTable.COLUMN_COMMENT, ReviewsTable.COLUMN_BENCH_PRESSES,
                        ReviewsTable.COLUMN_SQUAT_RACKS, ReviewsTable.COLUMN_OVERALL_RATING, ReviewsTable.COLUMN_TIMESTAMP},
                ReviewsTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare review object
        Review review = new Review(
                cursor.getInt(cursor.getColumnIndex(ReviewsTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ReviewsTable.COLUMN_COMMENT)),
                cursor.getInt(cursor.getColumnIndex(ReviewsTable.COLUMN_BENCH_PRESSES)),
                cursor.getInt(cursor.getColumnIndex(ReviewsTable.COLUMN_SQUAT_RACKS)),
                cursor.getInt(cursor.getColumnIndex(ReviewsTable.COLUMN_OVERALL_RATING)),
                cursor.getString(cursor.getColumnIndex(ReviewsTable.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return review;
    }

//    public List<Review> getAllNotes() {
//        List<Review> notes = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
//                Note.COLUMN_TIMESTAMP + " DESC";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Note note = new Note();
//                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
//                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
//                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
//
//                notes.add(note);
//            } while (cursor.moveToNext());
//        }
//
//        // close db connection
//        db.close();
//
//        // return notes list
//        return notes;
//    }

    public int getReviewsCount() {
        String countQuery = "SELECT  * FROM " + ReviewsTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }


}