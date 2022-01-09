package umn.ac.keadaanudara.Main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.InputFilter;
import android.text.Spanned;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import umn.ac.keadaanudara.LocationActivityPick;
import umn.ac.keadaanudara.Model.OneTimeActivityModel;
import umn.ac.keadaanudara.DatabaseHelper.OneTimeDatabaseHelper;
import umn.ac.keadaanudara.R;


public class InputOneTimeActivity extends AppCompatActivity {
    private static final String SHARED_PREF = "SHARED_PREF";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView tvDate, theTime, etTime, theDate, etDate, etLocation;
    EditText etActivity, etReminder;
    Button btnSave;
    ImageButton btnBack;

    int t1Hour, t1Minute;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_one_time);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.back);

        etActivity = findViewById(R.id.theActivity);
        etLocation = findViewById(R.id.theLocation);

        tvDate = findViewById(R.id.tv_date);
        etDate = findViewById(R.id.theDate);
        theDate = findViewById(R.id.theDate);

        theTime = findViewById(R.id.theTime);
        etTime = findViewById(R.id.theTime);

        etReminder = findViewById(R.id.theReminder);

        btnSave = findViewById(R.id.save);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputOneTimeActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        etLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputOneTimeActivity.this, LocationActivityPick.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        Intent intent = getIntent();

        String KABKO = intent.getStringExtra("KABKO");
        Double LAT = intent.getDoubleExtra("LAT", 0.00);
        Double LON = intent.getDoubleExtra("LON", 0.00);
//        String CONDITION = intent.getStringExtra("CONDITION");
        etLocation.setText(KABKO);
//        etLocation.setText(LAT);



        theDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InputOneTimeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
//                tvDate.setText(date);
                theDate.setText(date);
            }
        };

        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InputOneTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        theTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePckerDialog = new TimePickerDialog(
                        InputOneTimeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0, t1Hour, t1Minute);

                                theTime.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePckerDialog.updateTime(t1Hour, t1Minute);
                timePckerDialog.show();
            }
        });

        @SuppressLint("CutPasteId") EditText et = (EditText) findViewById(R.id.theReminder);
        et.setFilters(new InputFilter[]{ new InputMax("1", "5")});



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneTimeActivityModel oneTimeActivityModel;
                Intent intent1 = getIntent();

                String KABKO = intent1.getStringExtra("KABKO");
                Double LAT = intent1.getDoubleExtra("LAT", 0.00);
                Double LON = intent1.getDoubleExtra("LON", 00);

                if (etActivity.length() != 0 && etLocation.length() != 0 && etDate.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                    oneTimeActivityModel = new OneTimeActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etDate.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()), LAT, LON);
                    OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(InputOneTimeActivity.this);
                    boolean success = oneTimeDatabaseHelper.addOne(oneTimeActivityModel);
                    Intent intent = new Intent(InputOneTimeActivity.this, ListActivity.class);
                    startActivity(intent);
                    Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(InputOneTimeActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
//                    oneTimeActivityModel = new OneTimeActivityModel("NaN", "NaN", "NaN", "NaN", 0, 0.0, 0.0);
                }


//                Toast.makeText(InputOneTimeActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
//                Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();

                return;
            }
        });
    }
}