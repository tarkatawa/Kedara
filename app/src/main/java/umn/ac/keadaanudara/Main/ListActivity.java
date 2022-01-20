package umn.ac.keadaanudara.Main;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

import umn.ac.keadaanudara.Adapter.CompletedAdapter;
import umn.ac.keadaanudara.DatabaseHelper.CompletedDatabaseHelper;
import umn.ac.keadaanudara.Adapter.OneTimeAdapter;
import umn.ac.keadaanudara.DatabaseHelper.OneTimeDatabaseHelper;
import umn.ac.keadaanudara.Model.CompletedActivityModel;
import umn.ac.keadaanudara.R;

public class ListActivity extends AppCompatActivity implements OneTimeAdapter.OnNoteListener, CompletedAdapter.OnNoteListener {
    Button toRepetitive, toOneTime, btndelete, btncancel;
    ImageButton back;
    FloatingActionButton fab1, fab2;
    Instant now = Instant.now();
    Instant yesterday = now.minus(1, ChronoUnit.DAYS);
    Date myDate = Date.from(yesterday);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String formattedYesterday = formatter.format(myDate);


    RecyclerView recyclerView, recyclerViewCompleted, recyclerViewMonday, recyclerViewTuesday, recyclerViewWednesday, recyclerViewThursday, recyclerViewFriday, recyclerViewSaturday, recyclerViewSunday;
    RecyclerView.Adapter programAdapter, programAdapterCompleted, programAdapterMonday, programAdapterTuesday, programAdapterWednesday, programAdapterThursday, programAdapterFriday, programAdapterSaturday, programAdapterSunday;
    RecyclerView.LayoutManager layoutmanager, layoutManagerCompleted, layoutManagerMonday, layoutManagerTuesday, layoutManagerWednesday, layoutManagerThursday, layoutManagerFriday, layoutManagerSaturday, layoutManagerSunday;

    List<String> activityNameList = new ArrayList<String>();
    List<String> activityLocationList = new ArrayList<String>();
    List<String> activityDateList = new ArrayList<String>();
    List<String> activityTimeList = new ArrayList<String>();

    List<String> activityNameListCompleted = new ArrayList<String>();
    List<String> activityLocationListCompleted = new ArrayList<String>();
    List<String> activityDateListCompleted = new ArrayList<String>();
    List<String> activityTimeListCompleted = new ArrayList<String>();


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
            }
        });

        OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(ListActivity.this);
        Cursor cursor = oneTimeDatabaseHelper.getEveryone();

        CompletedActivityModel completedActivityModel;

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(formattedYesterday)){
                    String activityNameCompleted = cursor.getString(0);

                    String activityLocationCompleted = cursor.getString(1);

                    String activityDateCompleted = cursor.getString(2);

                    String activityTimeCompleted = cursor.getString(3);

                    completedActivityModel = new CompletedActivityModel(activityNameCompleted, activityLocationCompleted, activityDateCompleted, activityTimeCompleted, 0, 0.0, 0.0);

                    CompletedDatabaseHelper completedDatabaseHelper = new CompletedDatabaseHelper(ListActivity.this);
                    boolean success = completedDatabaseHelper.addOne(completedActivityModel);


                    oneTimeDatabaseHelper.deleteOne(activityNameCompleted);
                    cursor.moveToNext();
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

        recyclerView = findViewById(R.id.rvUpcoming);

        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);

        String[] activityNameString = activityNameList.toArray(new String[0]);
        String[] activityLocationString = activityLocationList.toArray(new String[0]);
        String[] activityDateString = activityDateList.toArray(new String[0]);
        String[] activityTimeString = activityTimeList.toArray(new String[0]);

        programAdapter = new OneTimeAdapter(this, activityNameString, activityLocationString, activityDateString, activityTimeString, this);
        recyclerView.setAdapter(programAdapter);

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

        recyclerViewCompleted = findViewById(R.id.rvCompleted);

        layoutManagerCompleted = new LinearLayoutManager(this);
        recyclerViewCompleted.setLayoutManager(layoutManagerCompleted);

        String[] activityNameStringCompleted = activityNameListCompleted.toArray(new String[0]);
        String[] activityLocationStringCompleted = activityLocationListCompleted.toArray(new String[0]);
        String[] activityDateStringCompleted = activityDateListCompleted.toArray(new String[0]);
        String[] activityTimeStringCompleted = activityTimeListCompleted.toArray(new String[0]);

        programAdapterCompleted = new CompletedAdapter(this, activityNameStringCompleted, activityLocationStringCompleted, activityDateStringCompleted, activityTimeStringCompleted, this);
        recyclerViewCompleted.setAdapter(programAdapterCompleted);

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
    }

@Override
public void onNoteClick(int position) {
    OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(ListActivity.this);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(intent);
    }

}