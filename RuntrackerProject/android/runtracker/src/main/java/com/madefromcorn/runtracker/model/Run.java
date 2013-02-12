package com.madefromcorn.runtracker.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import com.madefromcorn.runtracker.model.common.ActiveRecordBase;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 8:46 PM
 */
public class Run extends ActiveRecordBase {
    private static final String[] COLUMNS = {"RunID", "Name", "RouteID", "Start", "EndTime", "TotalDistance",
            "TargetedPaceMinutes", "TargetedPaceSeconds", "TargetedDistance"};

    public Run(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public int getId() {
        // TODO: Return the actual ID!
        return 0;
    }

    @Override
    public void loadFromCursor(Cursor cursor) {
        // TODO: DOIT!
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
