package umn.ac.keadaanudara;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import umn.ac.keadaanudara.Friday.WeeklyFridayActivityModel;
import umn.ac.keadaanudara.Friday.WeeklyFridayDatabaseHelper;
import umn.ac.keadaanudara.Monday.WeeklyMondayActivityModel;
import umn.ac.keadaanudara.Monday.WeeklyMondayDatabaseHelper;
import umn.ac.keadaanudara.Saturday.WeeklySaturdayActivityModel;
import umn.ac.keadaanudara.Saturday.WeeklySaturdayDatabaseHelper;
import umn.ac.keadaanudara.Sunday.WeeklySundayActivityModel;
import umn.ac.keadaanudara.Sunday.WeeklySundayDatabaseHelper;
import umn.ac.keadaanudara.Thursday.WeeklyThursdayActivityModel;
import umn.ac.keadaanudara.Thursday.WeeklyThursdayDatabaseHelper;
import umn.ac.keadaanudara.Tuesday.WeeklyTuesdayActivityModel;
import umn.ac.keadaanudara.Tuesday.WeeklyTuesdayDatabaseHelper;
import umn.ac.keadaanudara.Wednesday.WeeklyWednesdayActivityModel;
import umn.ac.keadaanudara.Wednesday.WeeklyWednesdayDatabaseHelper;

public class InputRepetitive extends AppCompatActivity {

    TextView tvTime;
    EditText etActivity, etLocation, etTime, etReminder;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;
    Button btnSave;
    ImageButton btnBack;

    int t1Hour, t1Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_repetitive);
        getSupportActionBar().hide();

        etActivity = findViewById(R.id.theActivity);
        etLocation = findViewById(R.id.theLocation);

        cbMonday = findViewById(R.id.senin);
        cbTuesday = findViewById(R.id.selasa);
        cbWednesday = findViewById(R.id.rabu);
        cbThursday = findViewById(R.id.kamis);
        cbFriday = findViewById(R.id.jumat);
        cbSaturday = findViewById(R.id.sabtu);
        cbSunday = findViewById(R.id.minggu);

        tvTime = findViewById(R.id.tv_time);
        etTime = findViewById(R.id.theTime);

        etReminder = findViewById(R.id.theReminder);

        btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(umn.ac.keadaanudara.InputRepetitive.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        umn.ac.keadaanudara.InputRepetitive.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                //Initialize calendar
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                etTime.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(t1Hour, t1Minute);

                timePickerDialog.show();
            }



        });
        // pake if if if buat masukin ke harian
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMonday.isChecked()){
                    WeeklyMondayActivityModel weeklyMondayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklyMondayActivityModel = new WeeklyMondayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklyMondayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklyMondayActivityModel = new WeeklyMondayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklyMondayDatabaseHelper weeklyMondayDatabaseHelper = new WeeklyMondayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklyMondayDatabaseHelper.addOne(weeklyMondayActivityModel);
                }
                if(cbTuesday.isChecked()){
                    WeeklyTuesdayActivityModel weeklyTuesdayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklyTuesdayActivityModel = new WeeklyTuesdayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklyTuesdayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklyTuesdayActivityModel = new WeeklyTuesdayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklyTuesdayDatabaseHelper weeklyTuesdayDatabaseHelper = new WeeklyTuesdayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklyTuesdayDatabaseHelper.addOne(weeklyTuesdayActivityModel);
                }
                if(cbWednesday.isChecked()){
                    WeeklyWednesdayActivityModel weeklyWednesdayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklyWednesdayActivityModel = new WeeklyWednesdayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklyWednesdayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklyWednesdayActivityModel = new WeeklyWednesdayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklyWednesdayDatabaseHelper weeklyWednesdayDatabaseHelper = new WeeklyWednesdayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklyWednesdayDatabaseHelper.addOne(weeklyWednesdayActivityModel);
                }
                if(cbThursday.isChecked()){
                    WeeklyThursdayActivityModel weeklyThursdayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklyThursdayActivityModel = new WeeklyThursdayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklyThursdayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklyThursdayActivityModel = new WeeklyThursdayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklyThursdayDatabaseHelper weeklyThursdayDatabaseHelper = new WeeklyThursdayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklyThursdayDatabaseHelper.addOne(weeklyThursdayActivityModel);
                }
                if(cbFriday.isChecked()){
                    WeeklyFridayActivityModel weeklyFridayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklyFridayActivityModel = new WeeklyFridayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklyFridayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklyFridayActivityModel = new WeeklyFridayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklyFridayDatabaseHelper weeklyFridayDatabaseHelper = new WeeklyFridayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklyFridayDatabaseHelper.addOne(weeklyFridayActivityModel);
                }
                if(cbSaturday.isChecked()){
                    WeeklySaturdayActivityModel weeklySaturdayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklySaturdayActivityModel = new WeeklySaturdayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklySaturdayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklySaturdayActivityModel = new WeeklySaturdayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklySaturdayDatabaseHelper weeklySaturdayDatabaseHelper = new WeeklySaturdayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklySaturdayDatabaseHelper.addOne(weeklySaturdayActivityModel);
                }
                if(cbSunday.isChecked()){
                    WeeklySundayActivityModel weeklySundayActivityModel;
                    if (etActivity.length() != 0 && etLocation.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                        weeklySundayActivityModel = new WeeklySundayActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, weeklySundayActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(umn.ac.keadaanudara.InputRepetitive.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        weeklySundayActivityModel = new WeeklySundayActivityModel("NaN", "NaN", "NaN", 0);
                    }
                    WeeklySundayDatabaseHelper weeklySundayDatabaseHelper = new WeeklySundayDatabaseHelper(umn.ac.keadaanudara.InputRepetitive.this);
                    boolean success = weeklySundayDatabaseHelper.addOne(weeklySundayActivityModel);
                }

//
//                Weekly___ActivityModel weekly____ActivityModel;
//                if (etActivity.length() != 0 && etLocation.length() != 0 && etDate.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
//                    oneTimeActivityModel = new OneTimeActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etDate.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
//                    Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(InputOneTimeActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
//                    oneTimeActivityModel = new OneTimeActivityModel("NaN", "NaN", "NaN", "NaN", 0);
//                }
//                OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(InputOneTimeActivity.this);
//                boolean success = oneTimeDatabaseHelper.addOne(oneTimeActivityModel);

//                Toast.makeText(InputOneTimeActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
//                Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();





                Intent intent = new Intent(umn.ac.keadaanudara.InputRepetitive.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}