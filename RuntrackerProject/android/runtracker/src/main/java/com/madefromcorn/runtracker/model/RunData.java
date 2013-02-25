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
 * Time: 4:41 PM
 */
@DatabaseTable(tableName = "RunData")
public class RunData extends ActiveRecordBase {

    @DatabaseField(columnName = "RunDataID",
            generatedId = true,
            dataType = DataType.INTEGER,
            canBeNull = false)
    private Integer mRunDataID;
    @DatabaseField(columnName = "RunID",
            dataType = DataType.INTEGER,
            canBeNull = false,
            foreignColumnName = "RunID",
            foreign = true)
    private Run mRun;
    @DatabaseField(columnName = "Latitude",
            dataType = DataType.FLOAT,
            canBeNull = false)
    private Float mLatitude;
    @DatabaseField(columnName = "Longitude",
            dataType = DataType.FLOAT,
            canBeNull = false)
    private Float mLongitude;
    @DatabaseField(columnName = "Altitude",
            dataType = DataType.FLOAT,
            canBeNull = false)
    private Float mAltitude;
    @DatabaseField(columnName = "CreatedAt",
            dataType = DataType.TIME_STAMP,
            canBeNull = false)
    private Date mCreatedAt;

    public RunData(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }
}
