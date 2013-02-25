package com.madefromcorn.runtracker.model.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.madefromcorn.runtracker.model.Milestone;
import com.madefromcorn.runtracker.model.Route;
import com.madefromcorn.runtracker.model.Run;
import com.madefromcorn.runtracker.model.RunData;

import java.sql.SQLException;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 4:26 PM
 */
@Singleton
public class RuntrackerDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Runtracker.db";
    private static final int DATABASE_VERSION = 1;

    @Inject
    public RuntrackerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ConnectionSource source = new AndroidConnectionSource(db);
        try {
            TableUtils.createTable(source, Run.class);
            TableUtils.createTable(source, Route.class);
            TableUtils.createTable(source, Milestone.class);
            TableUtils.createTable(source, RunData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now; we'll just drop and re-create the db... In the future we may migrate to new version and
        // port all existing data in order to preserve our database...
        ConnectionSource source = new AndroidConnectionSource(db);
        try {
            TableUtils.dropTable(source, Run.class, true);
            TableUtils.dropTable(source, Route.class, true);
            TableUtils.dropTable(source, Milestone.class, true);
            TableUtils.dropTable(source, RunData.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(db);
    }
}
