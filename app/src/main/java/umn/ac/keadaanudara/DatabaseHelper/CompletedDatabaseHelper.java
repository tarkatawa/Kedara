package umn.ac.keadaanudara.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import umn.ac.keadaanudara.Model.CompletedActivityModel;

public class CompletedDatabaseHelper extends SQLiteOpenHelper {
    public static final String COMPLETED_ACTIVITY_TABLE = "COMPLETED_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_DATE = "ACTIVITY_DATE";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";
    public static final String COLUMN_ACTIVITY_LON = "ACTIVITY_LON";
    public static final String COLUMN_ACTIVITY_LAT = "ACTIVITY_LAT";

    public CompletedDatabaseHelper(@Nullable Context context){
        super(context, "completedActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + COMPLETED_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_DATE + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT," + COLUMN_ACTIVITY_LON + " DOUBLE, " + COLUMN_ACTIVITY_LAT + " DOUBLE)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CompletedActivityModel completedModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, completedModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, completedModel.getLocation());
        cv.put(COLUMN_ACTIVITY_DATE, completedModel.getDate());
        cv.put(COLUMN_ACTIVITY_TIME, completedModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, completedModel.getReminders());
        cv.put(COLUMN_ACTIVITY_LON, completedModel.getLon());
        cv.put(COLUMN_ACTIVITY_LAT, completedModel.getLat());


        long insert = db.insert(COMPLETED_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteOne(String activity){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + COMPLETED_ACTIVITY_TABLE + " WHERE " + COLUMN_ACTIVITY_NAME + " = " + "'"+activity+"'";

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

      public Cursor getEveryone(){
        List<String> activityNameList = new ArrayList<>();
        List<String> activityLocationList = new ArrayList<>();
        List<String> activityDateList = new ArrayList<>();
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COMPLETED_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
    }
    }
