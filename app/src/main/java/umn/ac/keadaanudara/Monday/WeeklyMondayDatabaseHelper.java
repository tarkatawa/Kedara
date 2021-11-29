package umn.ac.keadaanudara.Monday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WeeklyMondayDatabaseHelper extends SQLiteOpenHelper {
    public static final String MONDAY_ACTIVITY_TABLE = "MONDAY_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";

    public WeeklyMondayDatabaseHelper(@Nullable Context context){
        super(context, "mondayActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MONDAY_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(WeeklyMondayActivityModel mondayModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, mondayModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, mondayModel.getLocation());
        cv.put(COLUMN_ACTIVITY_TIME, mondayModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, mondayModel.getReminders());

        long insert = db.insert(MONDAY_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

//    public boolean deleteOne(OneTimeActivityModel oneTimeModel){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String queryString = "DELETE FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE " + COLUMN_ACTIVITY_NAME + " = " + oneTimeModel.getActivity();
//
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        if(cursor.moveToFirst()){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }

    public Cursor getEveryone(){
        List<String> activityNameList = new ArrayList<>();
        List<String> activityLocationList = new ArrayList<>();
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MONDAY_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);


        return cursor;
    }
}
