package umn.ac.keadaanudara;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    Button toRepetitive, toOneTime;
    ImageButton back;
    FloatingActionButton fab1, fab2;

    RecyclerView recyclerView, recyclerViewMonday, recyclerViewTuesday, recyclerViewWednesday, recyclerViewThursday, recyclerViewFriday, recyclerViewSaturday, recyclerViewSunday;
    RecyclerView.Adapter programAdapter, programAdapterMonday, programAdapterTuesday, programAdapterWednesday, programAdapterThursday, programAdapterFriday, programAdapterSaturday, programAdapterSunday;
    RecyclerView.LayoutManager layoutmanager, layoutManagerMonday, layoutManagerTuesday, layoutManagerWednesday, layoutManagerThursday, layoutManagerFriday, layoutManagerSaturday, layoutManagerSunday;

    List<String> activityNameList = new ArrayList<String>();
    List<String> activityLocationList = new ArrayList<String>();
    List<String> activityDateList = new ArrayList<String>();
    List<String> activityTimeList = new ArrayList<String>();

    //Monday
    List<String> activityNameListMonday = new ArrayList<String>();
    List<String> activityLocationListMonday = new ArrayList<String>();
    List<String> activityTimeListMonday = new ArrayList<String>();

    //Tuesday
    List<String> activityNameListTuesday = new ArrayList<String>();
    List<String> activityLocationListTuesday = new ArrayList<String>();
    List<String> activityTimeListTuesday = new ArrayList<String>();

    //Wednesday
    List<String> activityNameListWednesday = new ArrayList<String>();
    List<String> activityLocationListWednesday = new ArrayList<String>();
    List<String> activityTimeListWednesday = new ArrayList<String>();

    //Thursday
    List<String> activityNameListThursday = new ArrayList<String>();
    List<String> activityLocationListThursday = new ArrayList<String>();
    List<String> activityTimeListThursday = new ArrayList<String>();

    //Friday
    List<String> activityNameListFriday = new ArrayList<String>();
    List<String> activityLocationListFriday = new ArrayList<String>();
    List<String> activityTimeListFriday = new ArrayList<String>();

    //Saturday
    List<String> activityNameListSaturday = new ArrayList<String>();
    List<String> activityLocationListSaturday = new ArrayList<String>();
    List<String> activityTimeListSaturday = new ArrayList<String>();

    //Sunday
    List<String> activityNameListSunday = new ArrayList<String>();
    List<String> activityLocationListSunday = new ArrayList<String>();
    List<String> activityTimeListSunday = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(umn.ac.keadaanudara.ListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database
        OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(ListActivity.this);
        Cursor cursor = oneTimeDatabaseHelper.getEveryone();



        if(cursor.moveToFirst()){
            do{
                String activityName = cursor.getString(0);
                activityNameList.add(activityName);

                String activityLocation = cursor.getString(1);
                activityLocationList.add(activityLocation);

                String activityDate = cursor.getString(2);
                activityDateList.add(activityDate);

                String activityTime = cursor.getString(3);
                activityTimeList.add(activityTime);
            }while(cursor.moveToNext());
        }else{ }
        cursor.close();

        //Ngurus recyclerview one-time-activity
        recyclerView = findViewById(R.id.rvUpcoming);

        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);

        String[] activityNameString = activityNameList.toArray(new String[0]);
        String[] activityLocationString = activityLocationList.toArray(new String[0]);
        String[] activityDateString = activityDateList.toArray(new String[0]);
        String[] activityTimeString = activityTimeList.toArray(new String[0]);

        programAdapter = new OneTimeAdapter(this, activityNameString, activityLocationString, activityDateString, activityTimeString);
        recyclerView.setAdapter(programAdapter);
        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk senin
        WeeklyMondayDatabaseHelper weeklyMondayDatabaseHelper = new WeeklyMondayDatabaseHelper(ListActivity.this);
        Cursor cursorMonday = weeklyMondayDatabaseHelper.getEveryone();

        if(cursorMonday.moveToFirst()){
            do{
                String activityName = cursorMonday.getString(0);
                activityNameListMonday.add(activityName);

                String activityLocation = cursorMonday.getString(1);
                activityLocationListMonday.add(activityLocation);

                String activityTime = cursorMonday.getString(2);
                activityTimeListMonday.add(activityTime);
            }while(cursorMonday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewMonday = findViewById(R.id.rvMonday);

        layoutManagerMonday = new LinearLayoutManager(this);
        recyclerViewMonday.setLayoutManager(layoutManagerMonday);

        String[] activityNameStringMonday = activityNameListMonday.toArray(new String[0]);
        String[] activityLocationStringMonday = activityLocationListMonday.toArray(new String[0]);
        String[] activityTimeStringMonday = activityTimeListMonday.toArray(new String[0]);

        programAdapterMonday = new WeeklyMondayAdapter(this, activityNameStringMonday, activityLocationStringMonday, activityTimeStringMonday);
        recyclerViewMonday.setAdapter(programAdapterMonday);

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk selasa
        WeeklyTuesdayDatabaseHelper weeklyTuesdayDatabaseHelper = new WeeklyTuesdayDatabaseHelper(ListActivity.this);
        Cursor cursorTuesday = weeklyTuesdayDatabaseHelper.getEveryone();

        if(cursorTuesday.moveToFirst()){
            do{
                String activityName = cursorTuesday.getString(0);
                activityNameListTuesday.add(activityName);

                String activityLocation = cursorTuesday.getString(1);
                activityLocationListTuesday.add(activityLocation);

                String activityTime = cursorTuesday.getString(2);
                activityTimeListTuesday.add(activityTime);
            }while(cursorMonday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewTuesday = findViewById(R.id.rvTuesday);

        layoutManagerTuesday = new LinearLayoutManager(this);
        recyclerViewTuesday.setLayoutManager(layoutManagerTuesday);

        String[] activityNameStringTuesday = activityNameListTuesday.toArray(new String[0]);
        String[] activityLocationStringTuesday = activityLocationListTuesday.toArray(new String[0]);
        String[] activityTimeStringTuesday = activityTimeListTuesday.toArray(new String[0]);

        programAdapterTuesday = new WeeklyTuesdayAdapter(this, activityNameStringTuesday, activityLocationStringTuesday, activityTimeStringTuesday);
        recyclerViewTuesday.setAdapter(programAdapterTuesday);
        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk rabu
        WeeklyWednesdayDatabaseHelper weeklyWednesdayDatabaseHelper = new WeeklyWednesdayDatabaseHelper(ListActivity.this);
        Cursor cursorWednesday = weeklyWednesdayDatabaseHelper.getEveryone();

        if(cursorWednesday.moveToFirst()){
            do{
                String activityName = cursorWednesday.getString(0);
                activityNameListWednesday.add(activityName);

                String activityLocation = cursorWednesday.getString(1);
                activityLocationListWednesday.add(activityLocation);

                String activityTime = cursorWednesday.getString(2);
                activityTimeListWednesday.add(activityTime);
            }while(cursorWednesday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewWednesday = findViewById(R.id.rvWednesday);

        layoutManagerWednesday = new LinearLayoutManager(this);
        recyclerViewWednesday.setLayoutManager(layoutManagerWednesday);

        String[] activityNameStringWednesday = activityNameListWednesday.toArray(new String[0]);
        String[] activityLocationStringWednesday = activityLocationListWednesday.toArray(new String[0]);
        String[] activityTimeStringWednesday = activityTimeListWednesday.toArray(new String[0]);

        programAdapterWednesday = new WeeklyTuesdayAdapter(this, activityNameStringWednesday, activityLocationStringWednesday, activityTimeStringWednesday);
        recyclerViewWednesday.setAdapter(programAdapterWednesday);

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk kamis
        WeeklyThursdayDatabaseHelper weeklyThursdayDatabaseHelper = new WeeklyThursdayDatabaseHelper(ListActivity.this);
        Cursor cursorThursday = weeklyThursdayDatabaseHelper.getEveryone();

        if(cursorThursday.moveToFirst()){
            do{
                String activityName = cursorThursday.getString(0);
                activityNameListThursday.add(activityName);

                String activityLocation = cursorThursday.getString(1);
                activityLocationListThursday.add(activityLocation);

                String activityTime = cursorThursday.getString(2);
                activityTimeListThursday.add(activityTime);
            }while(cursorThursday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewThursday = findViewById(R.id.rvThursday);

        layoutManagerThursday = new LinearLayoutManager(this);
        recyclerViewThursday.setLayoutManager(layoutManagerThursday);

        String[] activityNameStringThursday = activityNameListThursday.toArray(new String[0]);
        String[] activityLocationStringThursday = activityLocationListThursday.toArray(new String[0]);
        String[] activityTimeStringThursday = activityTimeListThursday.toArray(new String[0]);

        programAdapterThursday = new WeeklyTuesdayAdapter(this, activityNameStringThursday, activityLocationStringThursday, activityTimeStringThursday);
        recyclerViewThursday.setAdapter(programAdapterThursday);

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk jumat
        WeeklyFridayDatabaseHelper weeklyFridayDatabaseHelper = new WeeklyFridayDatabaseHelper(ListActivity.this);
        Cursor cursorFriday = weeklyFridayDatabaseHelper.getEveryone();

        if(cursorFriday.moveToFirst()){
            do{
                String activityName = cursorFriday.getString(0);
                activityNameListFriday.add(activityName);

                String activityLocation = cursorFriday.getString(1);
                activityLocationListFriday.add(activityLocation);

                String activityTime = cursorFriday.getString(2);
                activityTimeListFriday.add(activityTime);
            }while(cursorFriday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewFriday = findViewById(R.id.rvFriday);

        layoutManagerFriday = new LinearLayoutManager(this);
        recyclerViewFriday.setLayoutManager(layoutManagerFriday);

        String[] activityNameStringFriday = activityNameListFriday.toArray(new String[0]);
        String[] activityLocationStringFriday = activityLocationListFriday.toArray(new String[0]);
        String[] activityTimeStringFriday = activityTimeListFriday.toArray(new String[0]);

        programAdapterFriday = new WeeklyFridayAdapter(this, activityNameStringFriday, activityLocationStringFriday, activityTimeStringFriday);
        recyclerViewFriday.setAdapter(programAdapterFriday);

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk sabtu
        WeeklySaturdayDatabaseHelper weeklySaturdayDatabaseHelper = new WeeklySaturdayDatabaseHelper(ListActivity.this);
        Cursor cursorSaturday = weeklySaturdayDatabaseHelper.getEveryone();

        if(cursorSaturday.moveToFirst()){
            do{
                String activityName = cursorSaturday.getString(0);
                activityNameListSaturday.add(activityName);

                String activityLocation = cursorSaturday.getString(1);
                activityLocationListSaturday.add(activityLocation);

                String activityTime = cursorSaturday.getString(2);
                activityTimeListSaturday.add(activityTime);
            }while(cursorSaturday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewSaturday = findViewById(R.id.rvSaturday);

        layoutManagerSaturday = new LinearLayoutManager(this);
        recyclerViewSaturday.setLayoutManager(layoutManagerSaturday);

        String[] activityNameStringSaturday = activityNameListSaturday.toArray(new String[0]);
        String[] activityLocationStringSaturday = activityLocationListSaturday.toArray(new String[0]);
        String[] activityTimeStringSaturday = activityTimeListSaturday.toArray(new String[0]);

        programAdapterSaturday = new WeeklySaturdayAdapter(this, activityNameStringSaturday, activityLocationStringSaturday, activityTimeStringSaturday);
        recyclerViewSaturday.setAdapter(programAdapterSaturday);

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk minggu
        WeeklySundayDatabaseHelper weeklySundayDatabaseHelper = new WeeklySundayDatabaseHelper(ListActivity.this);
        Cursor cursorSunday = weeklySundayDatabaseHelper.getEveryone();

        if(cursorSunday.moveToFirst()){
            do{
                String activityName = cursorSunday.getString(0);
                activityNameListSunday.add(activityName);

                String activityLocation = cursorSunday.getString(1);
                activityLocationListSunday.add(activityLocation);

                String activityTime = cursorSunday.getString(2);
                activityTimeListSunday.add(activityTime);
            }while(cursorSunday.moveToNext());
        }else{ }
        cursor.close();
        //Ngurus recyclerview repetitive-activity monday
        recyclerViewSunday = findViewById(R.id.rvSunday);

        layoutManagerSunday = new LinearLayoutManager(this);
        recyclerViewSunday.setLayoutManager(layoutManagerSunday);

        String[] activityNameStringSunday = activityNameListSunday.toArray(new String[0]);
        String[] activityLocationStringSunday = activityLocationListSunday.toArray(new String[0]);
        String[] activityTimeStringSunday = activityTimeListSunday.toArray(new String[0]);

        programAdapterSunday = new WeeklySundayAdapter(this, activityNameStringSunday, activityLocationStringSunday, activityTimeStringSunday);
        recyclerViewSunday.setAdapter(programAdapterSunday);


        //Pindah
        fab1 = findViewById(R.id.fab_action1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        fab1 = findViewById(R.id.fab_action1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, InputRepetitive.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        fab2 = findViewById(R.id.fab_action2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, InputOneTimeActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        
//        toOneTime = findViewById(R.id.toOneTime);
//        toOneTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ListActivity.this, InputOneTimeActivity.class);
//                startActivity(intent);
//                finish();
//                return;
//            }
//        });
//
//        toRepetitive = findViewById(R.id.toRepetitive);
//        toRepetitive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ListActivity.this, InputRepetitive.class);
//                startActivity(intent);
//                finish();
//                return;
//            }
//        });


    }

//    public void showToast(String message){
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }

}