package umn.ac.keadaanudara.DatabaseHelper;

import static android.provider.Settings.System.DATE_FORMAT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import umn.ac.keadaanudara.Model.OneTimeActivityModel;

public class OneTimeDatabaseHelper extends SQLiteOpenHelper {
    public static final String ONE_TIME_ACTIVITY_TABLE = "ONE_TIME_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_DATE = "ACTIVITY_DATE";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";
    public static final String COLUMN_ACTIVITY_LON = "ACTIVITY_LON";
    public static final String COLUMN_ACTIVITY_LAT = "ACTIVITY_LAT";

    String dateOfToday = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    public OneTimeDatabaseHelper(@Nullable Context context){
        super(context, "onetimeActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ONE_TIME_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_DATE + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT," + COLUMN_ACTIVITY_LON + " DOUBLE, " + COLUMN_ACTIVITY_LAT + " DOUBLE)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(OneTimeActivityModel oneTimeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, oneTimeModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, oneTimeModel.getLocation());
        cv.put(COLUMN_ACTIVITY_DATE, oneTimeModel.getDate());
        cv.put(COLUMN_ACTIVITY_TIME, oneTimeModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, oneTimeModel.getReminders());
        cv.put(COLUMN_ACTIVITY_LON, oneTimeModel.getLon());
        cv.put(COLUMN_ACTIVITY_LAT, oneTimeModel.getLat());


        long insert = db.insert(ONE_TIME_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteOne(String activity){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE " + COLUMN_ACTIVITY_NAME + " = " + "'"+activity+"'";

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor getReminder(){
        SQLiteDatabase db = this.getWritableDatabase();
        //        QUERY DARI MODEL ACTIVITY DENGAN LOGIC DATE_SUB(event_date, INTERVAL days_prior) >= HARI INI
//         String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE DATE_FORMAT( DATE_SUB(" + COLUMN_ACTIVITY_DATE + ", INTERVAL " + COLUMN_ACTIVITY_REMINDER + "), '%d/%M/%Y' )  = '"+dateOfToday+"'";
//        String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE DATE_FORMAT(DATE_SUB(" + COLUMN_ACTIVITY_DATE + ", INTERVAL " + '4' + " DAY),'%d/%M/%Y') = '"+dateOfToday+"'";
        Object[] params = new Object[]{ONE_TIME_ACTIVITY_TABLE, COLUMN_ACTIVITY_DATE, COLUMN_ACTIVITY_REMINDER, dateOfToday};


//        String queryString = MessageFormat.format("SELECT * FROM {0} WHERE DATE_FORMAT(DATE_SUB({1}, INTERVAL 4 DAY), '%d/%M/%Y') = '{2}'", params);

//        String jawaban = Cast (( JulianDay({1}) - JulianDay({2}) ) AS Integer)
//        Cast (( JulianDay({1}) - JulianDay({2}) ) AS Integer)

//        String queryString =  MessageFormat.format("SELECT * FROM {0} WHERE = {3}", params);
                                                                                            //11/01/2022 - 2
//        Select Cast ((JulianDay(ToDate) - JulianDay(FromDate)) As Integer)

//        String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE strftime('%d/%m/%y', DATE(" + COLUMN_ACTIVITY_DATE + ", '-5 day')) = '"+dateOfToday+"'";

//        String queryString = "SELECT DATE(ACTIVITY_DATE,'-5 day') AS COBATANGGAL FROM ONE_TIME_ACTIVITY_TABLE";


        String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE + " WHERE " + COLUMN_ACTIVITY_REMINDER + " = '"+dateOfToday+"'";

        Cursor cursor = db.rawQuery(queryString, null);
//        Log.e("Jawab", cursor.getString(0));
//        Log.e("Jawab", cursor.getString(0));




        return cursor;
    }

      public Cursor getEveryone(){
        List<String> activityNameList = new ArrayList<>();
        List<String> activityLocationList = new ArrayList<>();
        List<String> activityDateList = new ArrayList<>();
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ONE_TIME_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
    }
    }
