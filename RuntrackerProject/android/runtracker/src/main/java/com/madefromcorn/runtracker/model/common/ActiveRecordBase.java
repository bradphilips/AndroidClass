package com.madefromcorn.runtracker.model.common;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 8:48 PM
 */
public abstract class ActiveRecordBase implements Serializable {

    private SQLiteOpenHelper mDatabaseHelper;
    private Boolean mIsNew;

    public ActiveRecordBase(SQLiteOpenHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    public static <T extends ActiveRecordBase> T load(int id, Class<T> recordType, SQLiteOpenHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        T record = null;
        try {
            record = recordType.getConstructor(SQLiteOpenHelper.class).newInstance(databaseHelper);
            Annotation[] annotations = recordType.getAnnotations();
            ArrayList<String> columns = new ArrayList<String>();
            for (Annotation annotation : annotations) {
                //Build column list
                if(annotation.annotationType() == DataColumn.class) {

                }
            }
            Cursor cursor = database.query(record.getTable(), null, String.format("%sID = %d", record.getTable(), id),
                    record.getColumns(), null, null, null, "1");
            record.setIsNew(false);
            record.loadFromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }

    public void save() {
        SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        // TODO:  Insert or update based on the existence of the record..  loaded or not.
        if (mIsNew) {    // Insert
            mIsNew = false;
        } else {        // Update

        }
    }

    public int destroy() {
        SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        try {
            return database.delete(this.getClass().getSimpleName(), String.format("%sId = %d", getTable(), getId()), null);
        } finally {
            database.close();
        }
    }

    protected String getTable() {
        return getClass().getSimpleName();
    }

    public abstract int getId();

    public abstract void loadFromCursor(Cursor cursor);

    public abstract String[] getColumns();

    protected void setIsNew(boolean aNew) {
        mIsNew = aNew;
    }
}
