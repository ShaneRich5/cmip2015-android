package com.cmip.cmip2015.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmip.cmip2015.pojo.Appliance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane on 5/24/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "appliancesManager";

    // Contacts table name
    private static final String TABLE_APPLIANCES = "appliances";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_WATTS = "watts";
    private static final String KEY_HOURS = "hours";
    private static final String KEY_AMOUNT = "amount";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_APPLIANCES_TABLE = "CREATE TABLE " + TABLE_APPLIANCES + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_AMOUNT + " INTEGER" +
                KEY_HOURS + " REAL" +
                KEY_WATTS + " REAL)";
        db.execSQL(CREATE_APPLIANCES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLIANCES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Appliance appliance) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, appliance.getName()); // Appliance Name
        values.put(KEY_AMOUNT, appliance.getAmount()); // Appliance Amount
        values.put(KEY_HOURS, appliance.getHours()); // Appliance Amount
        values.put(KEY_WATTS, appliance.getWatts()); // Appliance Amount

        // Inserting Row
        db.insert(TABLE_APPLIANCES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Appliance getAppliance(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPLIANCES, new String[] { KEY_ID,
                        KEY_NAME, KEY_WATTS, KEY_AMOUNT, KEY_HOURS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // return Appliance
        Appliance appliance = new Appliance(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getDouble(3),
                cursor.getInt(4));
        cursor.close();
        return appliance;
    }

    // Getting All Appliances
    public ArrayList<Appliance> getAllAppliances() {
        ArrayList<Appliance> applianceList = new ArrayList<Appliance>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_APPLIANCES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Appliance appliance = new Appliance();
                appliance.setId(cursor.getInt(0));
                appliance.setName(cursor.getString(1));
                appliance.setWatts(cursor.getDouble(2));
                appliance.setHours(cursor.getDouble(3));
                appliance.setAmount(cursor.getInt(4));
                // Adding Appliance to list
                applianceList.add(appliance);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return Appliance list
        return applianceList;
    }

    // Updating single Appliance
    public int updateAppliance(Appliance appliance) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, appliance.getName());
        values.put(KEY_WATTS, appliance.getWatts());
        values.put(KEY_HOURS, appliance.getHours());
        values.put(KEY_AMOUNT, appliance.getAmount());

        // updating row
        return db.update(TABLE_APPLIANCES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(appliance.getId()) });
    }

    // Deleting single Appliance
    public void deleteAppliance(Appliance Appliance) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APPLIANCES, KEY_ID + " = ?",
                new String[] { String.valueOf(Appliance.getId()) });
        db.close();
    }


    // Getting Appliances Count
    public int getAppliancesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_APPLIANCES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int amount = cursor.getCount();
        cursor.close();

        // return count
        return amount;
    }

}
