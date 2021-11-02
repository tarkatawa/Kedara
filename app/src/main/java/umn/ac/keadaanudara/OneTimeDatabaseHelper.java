package umn.ac.keadaanudara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OneTimeDatabaseHelper extends SQLiteOpenHelper {
    public static final String ONE_TIME_ACTIVITY_TABLE = "ONE_TIME_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_DATE = "ACTIVITY_DATE";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";

    public OneTimeDatabaseHelper(@Nullable Context context){
        super(context, "onetimeActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ONE_TIME_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_DATE + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(umn.ac.keadaanudara.OneTimeActivityModel oneTimeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, oneTimeModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, oneTimeModel.getLocation());
        cv.put(COLUMN_ACTIVITY_DATE, oneTimeModel.getDate());
        cv.put(COLUMN_ACTIVITY_TIME, oneTimeModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, oneTimeModel.getReminders());

        long insert = db.insert(ONE_TIME_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteOne(umn.ac.keadaanudara.OneTimeActivityModel oneTimeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE " + COLUMN_ACTIVITY_NAME + " = " + oneTimeModel.getActivity();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }

//    public List<Cursor> getEveryone(){
      public Cursor getEveryone(){
//        String[] activityNameList = {};
        List<String> activityNameList = new ArrayList<>();
//        String[] activityLocationList ={};
        List<String> activityLocationList = new ArrayList<>();
//        String[] activityDateList ={};
        List<String> activityDateList = new ArrayList<>();
//        String[] activityTimeList ={};
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

//        if(cursor.moveToFirst()){
//            do{
//                String activityName = cursor.getString(0);
//                activityNameList.add(activityName);
//
//                String activityLocation = cursor.getString(1);
//                activityLocationList.add(activityLocation);
//
//                String activityDate = cursor.getString(2);
//                activityDateList.add(activityDate);
//
//                String activityTime = cursor.getString(3);
//                activityTimeList.add(activityTime);
//            }while(cursor.moveToNext());
//        }else{ }
//        cursor.close();
//        db.close();
//        return Arrays.asList(activityNameList, activityLocationList, activityDateList, activityTimeList);
//        return Arrays.asList(cursor);
        return cursor;
    }
    }
