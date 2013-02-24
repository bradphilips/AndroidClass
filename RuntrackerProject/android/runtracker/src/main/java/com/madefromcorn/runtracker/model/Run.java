package com.madefromcorn.runtracker.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import com.madefromcorn.runtracker.model.common.ActiveRecordBase;
import com.madefromcorn.runtracker.model.common.DataColumn;
import roboguice.inject.InjectView;

import java.util.Date;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 8:46 PM
 */
public class Run extends ActiveRecordBase {
    private static final String[] COLUMNS = {"RunID", "Name", "RouteID", "StartTime", "EndTime", "TotalDistance",
            "TargetedPaceMinutes", "TargetedPaceSeconds", "TargetedDistance"};

    @DataColumn("RunID")
    private Integer mRunId;
    private String mName;
    private Integer mRouteId;
    private Date mStartTime;
    private Date mEndTime;

    public Run(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public int getId() {
        return mRunId;
    }

    @Override
    public void loadFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        mRunId = cursor.getInt(0);
        mName = cursor.getString(1);
        mRouteId = cursor.getInt(2);

        long startTime = cursor.getLong(3);
        mStartTime = new Date(startTime * 1000);
        long endTime = cursor.getLong(3);
        mEndTime = new Date(endTime * 1000);

        // etc...
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
