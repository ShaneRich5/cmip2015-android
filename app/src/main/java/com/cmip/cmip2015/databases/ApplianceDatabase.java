package com.cmip.cmip2015.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.cmip.cmip2015.logs.Logger;
import com.cmip.cmip2015.pojo.Appliance;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shane on 5/24/2015.
 */
public class ApplianceDatabase {
    private ApplianceHelper mHelper;
    private SQLiteDatabase mDatabase;

    public ApplianceDatabase(Context context) {
        this.mHelper = new ApplianceHelper(context);
        this.mDatabase = mDatabase;
    }

    public int updateMovies(Appliance appliance) {
        ContentValues values = new ContentValues();
        values.put(ApplianceHelper.COLUMN_NAME, appliance.getName());
        values.put(ApplianceHelper.COLUMN_AMOUNT, appliance.getAmount());
        values.put(ApplianceHelper.COLUMN_HOURS, appliance.getHours());
        values.put(ApplianceHelper.COLUMN_WATTS, appliance.getWatts());
        values.put(ApplianceHelper.COLUMN_ID, appliance.getId());

        return mDatabase.update(ApplianceHelper.TABLE_APPLIANCE, values, ApplianceHelper.COLUMN_ID + " = ?",
                new String[] {String.valueOf(appliance.getId())});
    }

    public void insertMovies(ArrayList<Appliance> listAppliances, boolean clearPrevious) {
        if (clearPrevious) {
            deleteAppliances();
        }
        //create a sql prepared statement
        String sql = "INSERT INTO " + ApplianceHelper.TABLE_APPLIANCE + " VALUES (?,?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        for (int i = 0; i < listAppliances.size(); i++) {
            Appliance currentAppliance = listAppliances.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            statement.bindLong(1, currentAppliance.getId());
            statement.bindString(2, currentAppliance.getName());
            statement.bindLong(3, currentAppliance.getAmount());
            statement.bindDouble(4, currentAppliance.getHours());
            statement.bindDouble(5, currentAppliance.getWatts());

            statement.execute();
        }
        //set the transaction as successful and end the transaction
        Logger.m("inserting entries " + listAppliances.size() + new Date(System.currentTimeMillis()));
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Appliance> readAppliances() {
        ArrayList<Appliance> listAppliances = new ArrayList<>();

        //get a list of columns to be retrieved, we need all of them
        String[] columns = {
                ApplianceHelper.COLUMN_ID,
                ApplianceHelper.COLUMN_NAME,
                ApplianceHelper.COLUMN_AMOUNT,
                ApplianceHelper.COLUMN_HOURS,
                ApplianceHelper.COLUMN_WATTS,
        };
        Cursor cursor = mDatabase.query(ApplianceHelper.TABLE_APPLIANCE, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Logger.m("loading entries " + cursor.getCount() + new Date(System.currentTimeMillis()));
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                Appliance appliance = new Appliance();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                appliance.setName(cursor.getString(cursor.getColumnIndex(ApplianceHelper.COLUMN_NAME)));
                appliance.setWatts(cursor.getDouble(cursor.getColumnIndex(ApplianceHelper.COLUMN_WATTS)));
                appliance.setHours(cursor.getDouble(cursor.getColumnIndex(ApplianceHelper.COLUMN_HOURS)));
                appliance.setAmount(cursor.getInt(cursor.getColumnIndex(ApplianceHelper.COLUMN_AMOUNT)));
                //add the movie to the list of movie objects which we plan to return
                listAppliances.add(appliance);
            }
            while (cursor.moveToNext());
        }
        return listAppliances;
    }

    private void deleteAppliances() {
        mDatabase.delete(ApplianceHelper.TABLE_APPLIANCE, null, null);
    }

    private static class ApplianceHelper extends SQLiteOpenHelper {
        private static final String TABLE_APPLIANCE = "appliance";

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_WATTS = "watts";
        private static final String COLUMN_HOURS = "hours";
        private static final String COLUMN_AMOUNT = "amount";

        private static final String CREATE_TABLE_APPLIANCE = "CREATE TABLE" + TABLE_APPLIANCE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_AMOUNT + " INTEGER," +
                COLUMN_HOURS + " REAL," +
                COLUMN_WATTS + " REAL);";

        private static final String DB_NAME = "appliances_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public ApplianceHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_APPLIANCE);
            } catch (SQLException exception) {
                Logger.toastShort(mContext, exception + "");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Logger.m("upgrade table box office executed");
                db.execSQL(" DROP TABLE " + TABLE_APPLIANCE + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException exception) {
                Logger.toastShort(mContext, exception + "");
            }
        }
    }
}
