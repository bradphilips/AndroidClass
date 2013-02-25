package com.madefromcorn.runtracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.madefromcorn.runtracker.model.common.ActiveRecordBase;

import java.util.Date;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 2/25/13
 * Time: 4:34 PM
 */
@DatabaseTable(tableName = "Milestone")
public class Milestone extends ActiveRecordBase {
    @DatabaseField(columnName = "MilestoneID",
            generatedId = true,
            dataType = DataType.INTEGER,
            canBeNull = false)
    private Integer mMilestoneID;
    @DatabaseField(columnName = "RunID",
            dataType = DataType.INTEGER,
            canBeNull = false,
            foreignColumnName = "RunID")
    private Run mRun;
    @DatabaseField(columnName = "Latitude",
            dataType = DataType.FLOAT,
            canBeNull = false)
    private Float mLatitude;
    @DatabaseField(columnName = "Longitude",
            dataType = DataType.FLOAT,
            canBeNull = false)
    private Float mLongitude;
    @DatabaseField(columnName = "Description",
            dataType = DataType.STRING,
            canBeNull = false)
    private String mDescription;
    @DatabaseField(columnName = "CreatedAt",
            dataType = DataType.TIME_STAMP,
            canBeNull = false)
    private Date mCreatedAt;

    public Milestone(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }
}
