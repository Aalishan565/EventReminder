package com.eventreminder.database;

import java.util.ArrayList;
import java.util.List;

import com.eventreminder.dtos.EventDto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "event_database";
    private static final String TABLE_EVENT = "event_details";
    private static final String NAME = "name";
    private static final String NOTE = "note";
    private static final String DATE = "date";
    private static final String EVENT = "event";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {// "(" + NAME + " TEXT," + NOTE + " TEXT" + ")"
        String CREATE_BDAY_TABLE = "CREATE TABLE " + TABLE_EVENT + "(" + NAME + " TEXT," + NOTE + " TEXT," + DATE + " TEXT," + EVENT + " TEXT" + ")";
        db.execSQL(CREATE_BDAY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(db);

    }

    public void addOccasion(String name, String note, String date, String event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(NOTE, note);
        values.put(DATE, date);
        values.put(EVENT, event);


        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public List<EventDto> getAllEvent() {
        List<EventDto> eventList = new ArrayList<EventDto>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EventDto eventDto = new EventDto();
                eventDto.setName(cursor.getString(0));
                eventDto.setNote(cursor.getString(1));
                eventDto.setDate(cursor.getString(2));
                eventDto.setEvent(cursor.getString(3));
                eventList.add(eventDto);
            } while (cursor.moveToNext());
        }

        // return event list
        return eventList;
    }

    public boolean checkIfDataExists(String name, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();

        return false;
    }

}
