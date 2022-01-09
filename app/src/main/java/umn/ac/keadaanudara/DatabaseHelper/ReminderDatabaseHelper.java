package umn.ac.keadaanudara.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ReminderDatabaseHelper extends SQLiteOpenHelper {
    public static final String REMINDER_ACTIVITY_TABLE = "REMINDER_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_DATE = "ACTIVITY_DATE"; //
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME"; //
    public static final String COLUMN_ACTIVITY_LON = "ACTIVITY_LON"; //
    public static final String COLUMN_ACTIVITY_LAT = "ACTIVITY_LAT"; //
    public static final String COLUMN_ACTIVITY_ICON = "ACTIVITY_ICON";
    public static final String COLUMN_ACTIVITY_CONDITION = "ACTIVITY_CONDITION";

    public ReminderDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
