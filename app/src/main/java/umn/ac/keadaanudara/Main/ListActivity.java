package umn.ac.keadaanudara.Main;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

import umn.ac.keadaanudara.Adapter.CompletedAdapter;
import umn.ac.keadaanudara.Adapter.WeeklyFridayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.CompletedDatabaseHelper;
import umn.ac.keadaanudara.DatabaseHelper.WeeklyFridayDatabaseHelper;
import umn.ac.keadaanudara.Adapter.WeeklyMondayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.WeeklyMondayDatabaseHelper;
import umn.ac.keadaanudara.Adapter.OneTimeAdapter;
import umn.ac.keadaanudara.DatabaseHelper.OneTimeDatabaseHelper;
import umn.ac.keadaanudara.Adapter.WeeklySaturdayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.WeeklySaturdayDatabaseHelper;
import umn.ac.keadaanudara.Adapter.WeeklySundayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.WeeklySundayDatabaseHelper;
import umn.ac.keadaanudara.Adapter.WeeklyThursdayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.WeeklyThursdayDatabaseHelper;
import umn.ac.keadaanudara.Adapter.WeeklyTuesdayAdapter;
import umn.ac.keadaanudara.DatabaseHelper.WeeklyTuesdayDatabaseHelper;
import umn.ac.keadaanudara.DatabaseHelper.WeeklyWednesdayDatabaseHelper;
import umn.ac.keadaanudara.Model.City;
import umn.ac.keadaanudara.Model.CompletedActivityModel;
import umn.ac.keadaanudara.R;

public class ListActivity extends AppCompatActivity implements OneTimeAdapter.OnNoteListener, CompletedAdapter.OnNoteListener {
    Button toRepetitive, toOneTime, btndelete, btncancel;
    ImageButton back;
    FloatingActionButton fab1, fab2;
    String dateOfToday = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    RecyclerView recyclerView, recyclerViewCompleted, recyclerViewMonday, recyclerViewTuesday, recyclerViewWednesday, recyclerViewThursday, recyclerViewFriday, recyclerViewSaturday, recyclerViewSunday;
    RecyclerView.Adapter programAdapter, programAdapterCompleted, programAdapterMonday, programAdapterTuesday, programAdapterWednesday, programAdapterThursday, programAdapterFriday, programAdapterSaturday, programAdapterSunday;
    RecyclerView.LayoutManager layoutmanager, layoutManagerCompleted, layoutManagerMonday, layoutManagerTuesday, layoutManagerWednesday, layoutManagerThursday, layoutManagerFriday, layoutManagerSaturday, layoutManagerSunday;


    //OneTime
    List<String> activityNameList = new ArrayList<String>();
    List<String> activityLocationList = new ArrayList<String>();
    List<String> activityDateList = new ArrayList<String>();
    List<String> activityTimeList = new ArrayList<String>();

    //Completed
    List<String> activityNameListCompleted = new ArrayList<String>();
    List<String> activityLocationListCompleted = new ArrayList<String>();
    List<String> activityDateListCompleted = new ArrayList<String>();
    List<String> activityTimeListCompleted = new ArrayList<String>();

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
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
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
                if(cursor.getString(2) == dateOfToday){
                    String activityNameCompleted = cursor.getString(0);
                    String activityLocationCompleted = cursor.getString(1);
                    String activityDateCompleted = cursor.getString(2);
                    String activityTimeCompleted = cursor.getString(3);

                    CompletedActivityModel completedActivityModel;
                    completedActivityModel = new CompletedActivityModel (activityNameCompleted, activityLocationCompleted, activityDateCompleted, activityTimeCompleted, 0, 0.0, 0.0);

                    oneTimeDatabaseHelper.deleteOne(activityNameCompleted);
                    continue;
                }

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

        programAdapter = new OneTimeAdapter(this, activityNameString, activityLocationString, activityDateString, activityTimeString, this);
        recyclerView.setAdapter(programAdapter);
//        recyclerView.addOnItemTouchListener(new AdapterView.OnItemClickListener(){
//
//        });

        //----------------------------------------------------------------------------------------------------------
        //Ambil data dari database untuk completed
        CompletedDatabaseHelper completedDatabaseHelper = new CompletedDatabaseHelper(ListActivity.this);
        Cursor cursorCompleted = completedDatabaseHelper.getEveryone();




        if(cursorCompleted.moveToFirst()){
            do{
                String activityName = cursorCompleted.getString(0);
                activityNameListCompleted.add(activityName);

                String activityLocation = cursorCompleted.getString(1);
                activityLocationListCompleted.add(activityLocation);

                String activityDate = cursorCompleted.getString(2);
                activityDateListCompleted.add(activityDate);

                String activityTime = cursorCompleted.getString(3);
                activityTimeListCompleted.add(activityTime);
            }while(cursorCompleted.moveToNext());
        }else{ }
        cursorCompleted.close();

        //Ngurus recyclerview completed-activity
        recyclerViewCompleted = findViewById(R.id.rvCompleted);

        layoutManagerCompleted = new LinearLayoutManager(this);
        recyclerViewCompleted.setLayoutManager(layoutManagerCompleted);

        String[] activityNameStringCompleted = activityNameListCompleted.toArray(new String[0]);
        String[] activityLocationStringCompleted = activityLocationListCompleted.toArray(new String[0]);
        String[] activityDateStringCompleted = activityDateListCompleted.toArray(new String[0]);
        String[] activityTimeStringCompleted = activityTimeListCompleted.toArray(new String[0]);

        programAdapterCompleted = new CompletedAdapter(this, activityNameStringCompleted, activityLocationStringCompleted, activityDateStringCompleted, activityTimeStringCompleted, this);
        recyclerView.setAdapter(programAdapterCompleted);

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

        programAdapterThursday = new WeeklyThursdayAdapter(this, activityNameStringThursday, activityLocationStringThursday, activityTimeStringThursday);
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

//    @Override
//    public void onNoteClick(int position) {
//        OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(ListActivity.this);
////        OneTimeActivityModel clickedActivity = activityNameList.get(position);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
//
//        builder.setMessage("Your activity will be deleted permanently")
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        oneTimeDatabaseHelper.deleteOne(activityNameList.get(position));
//                        finish();
//                        overridePendingTransition(0, 0);
//                        startActivity(getIntent());
//                        overridePendingTransition(0, 0);
//                    }
//                }).setNegativeButton("Cancel", null);
//
//        AlertDialog alert = builder.create();
//        alert.show();

@Override
public void onNoteClick(int position) {
    OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(ListActivity.this);
//        OneTimeActivityModel clickedActivity = activityNameList.get(position);

    Dialog dialog = new Dialog(this);
    dialog.setContentView(R.layout.activity_popup_delete);
    dialog.setCancelable(false);
    dialog.getWindow().getAttributes().windowAnimations = R.style.aniamation;

    btndelete = (Button) dialog.findViewById(R.id.btn_delete);
    btncancel = (Button) dialog.findViewById(R.id.btn_cancel);

    btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            oneTimeDatabaseHelper.deleteOne(activityNameList.get(position));
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

            Toast.makeText(ListActivity.this, "Deleted", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    });

    btncancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(ListActivity.this, "Canceled", Toast.LENGTH_LONG). show();
            dialog.dismiss();
        }
    });

    dialog.show();
    }

//    public void showToast(String message){
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }

}