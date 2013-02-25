package com.madefromcorn.runtracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.madefromcorn.runtracker.model.common.ActiveRecordBase;

import java.util.Date;

/**
 * Project: runtracker
 * Author: Brad Philips
 * Date: 2/11/13
 * Time: 8:46 PM
 */
@DatabaseTable(tableName = "Run")
public class Run extends ActiveRecordBase {
    @DatabaseField(columnName = "RunID",
            generatedId = true,
            dataType = DataType.INTEGER,
            allowGeneratedIdInsert = true,
            canBeNull = false)
    private Integer mRunId;
    @DatabaseField(columnName = "Name",
            dataType = DataType.STRING,
            canBeNull = false)
    private String mName;
    @DatabaseField(columnName = "RouteID",
            dataType = DataType.INTEGER,
            foreignColumnName = "RouteID")
    private Integer mRouteId;
    @DatabaseField(columnName = "StartTime",
            dataType = DataType.DATE)
    private Date mStartTime;
    @DatabaseField(columnName = "EndTime",
            dataType = DataType.DATE)
    private Date mEndTime;
    @DatabaseField(columnName = "TotalDistance",
            dataType = DataType.FLOAT)
    private Float mTotalDistance;
    @DatabaseField(columnName = "TargetedPaceMinutes",
            dataType = DataType.INTEGER)
    private Integer mTargetedPaceMinutes;
    @DatabaseField(columnName = "TargetedPaceSeconds",
            dataType = DataType.INTEGER)
    private Integer mTargetedPaceSeconds;
    @DatabaseField(columnName = "TargetedDistance",
            dataType = DataType.FLOAT)
    private Float mTargetedDistance;

    public Run(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }
}
