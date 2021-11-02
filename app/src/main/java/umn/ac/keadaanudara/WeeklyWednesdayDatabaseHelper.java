package umn.ac.keadaanudara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WeeklyWednesdayDatabaseHelper extends SQLiteOpenHelper {
    public static final String WEDNESDAY_ACTIVITY_TABLE = "WEDNESDAY_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";

    public WeeklyWednesdayDatabaseHelper(@Nullable Context context){
        super(context, "wednesdayActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + WEDNESDAY_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(umn.ac.keadaanudara.WeeklyWednesdayActivityModel wednesdayModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, wednesdayModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, wednesdayModel.getLocation());
        cv.put(COLUMN_ACTIVITY_TIME, wednesdayModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, wednesdayModel.getReminders());

        long insert = db.insert(WEDNESDAY_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }


    public Cursor getEveryone(){
        List<String> activityNameList = new ArrayList<>();
        List<String> activityLocationList = new ArrayList<>();
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + WEDNESDAY_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);


        return cursor;
    }
}
