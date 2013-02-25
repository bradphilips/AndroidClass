package com.madefromcorn.runtracker.model.common;

import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 8:48 PM
 */
public abstract class ActiveRecordBase implements Serializable {

    private SQLiteOpenHelper mDatabaseHelper;

    public ActiveRecordBase(SQLiteOpenHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    public static <T extends ActiveRecordBase> T load(int id, Class<T> recordType, SQLiteOpenHelper databaseHelper) {
        return null;
    }

    public void save() {
    }

    public int destroy() {
        return 0;
    }
}
