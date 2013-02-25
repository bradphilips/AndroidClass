package com.madefromcorn.runtracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.madefromcorn.runtracker.model.common.ActiveRecordBase;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 2/25/13
 * Time: 4:28 PM
 */
@DatabaseTable(tableName = "Route")
public class Route extends ActiveRecordBase {

    @DatabaseField(columnName = "RouteID",
            generatedId = true,
            dataType = DataType.INTEGER,
            canBeNull = false)
    private Integer mRouteID;
    @DatabaseField(columnName = "Name",
            dataType = DataType.STRING,
            canBeNull = false)
    private Integer mName;
    @DatabaseField(columnName = "Notes",
            dataType = DataType.CHAR_OBJ,
            canBeNull = true)
    private Integer mNotes;
    @DatabaseField(columnName = "Polyline",
            dataType = DataType.CHAR_OBJ,
            canBeNull = false)
    private Integer mPolyline;

    public Route(SQLiteOpenHelper databaseHelper) {
        super(databaseHelper);
    }


}
