package com.madefromcorn.runtracker.model.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 4:26 PM
 */
@Singleton
public class RuntrackerDatabase extends SQLiteOpenHelper {
    public static final String RUN_TABLE = "Run";
    public static final String ROUTE_TABLE = "Route";
    public static final String MILESTONE_TABLE = "Milestone";
    public static final String RUN_DATA_TABLE = "RunData";
    public static final String[] TABLES = {RUN_TABLE, ROUTE_TABLE, MILESTONE_TABLE, RUN_DATA_TABLE};
    private static final String DATABASE_NAME = "Runtracker.db";
    private static final int DATABASE_VERSION = 1;

    @Inject
    public RuntrackerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RUN_TABLE + " (" +
                RUN_TABLE + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(255) NOT NULL," +
                "RouteID INTEGER," +
                "StartTime INTEGER," +
                "EndTime INTEGER," +
                "TotalDistance NUMERIC(6,2) NOT NULL," +
                "TargetedPaceMinutes INTEGER NOT NULL," +
                "TargetedPaceSeconds INTEGER NOT NULL," +
                "TargetedDistance NUMERIC(6,2) NOT NULL);"
        );

        db.execSQL("CREATE TABLE " + ROUTE_TABLE + " (" +
                "RouteID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(255) NOT NULL," +
                "Notes CLOB NOT NULL," +
                "Polyline CLOB NOT NULL);"
        );

        db.execSQL("CREATE TABLE " + MILESTONE_TABLE + " (" +
                "MilestoneID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "RunID INTEGER NOT NULL," +
                "Latitude NUMERIC(6,10) NOT NULL," +
                "Longitude NUMERIC(6,10) NOT NULL," +
                "Description CLOB NOT NULL," +
                "CreatedAt TIMESTAMP NOT NULL DEFAULT (strftime('%s','now')));"
        );

        db.execSQL("CREATE TABLE " + RUN_DATA_TABLE + " (" +
                "RunDataID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "RunID INTEGER NOT NULL," +
                "Latitude NUMERIC(6,10) NOT NULL," +
                "Longitude NUMERIC(6,10) NOT NULL," +
                "Altitude NUMERIC(6,10) NOT NULL," +
                "CreatedAt INTEGER NOT NULL DEFAULT (strftime('%s','now')));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now; we'll just drop and re-create the db... In the future we may migrate to new version and
        // port all existing data in order to preserve our database...
        for (String table : TABLES) {
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", table));
        }
        onCreate(db);
    }
}
