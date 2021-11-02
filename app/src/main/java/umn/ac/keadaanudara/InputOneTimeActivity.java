package umn.ac.keadaanudara;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class InputOneTimeActivity extends AppCompatActivity {
    TextView tvDate, tvTime;
    EditText etActivity, etLocation, etDate, etTime, etReminder;
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

        tvTime = findViewById(R.id.tv_time);
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
                Intent intent = new Intent(umn.ac.keadaanudara.InputOneTimeActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        umn.ac.keadaanudara.InputOneTimeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
//                tvDate.setText(date);
                etDate.setText(date);
            }
        };

        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        umn.ac.keadaanudara.InputOneTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        umn.ac.keadaanudara.InputOneTimeActivity.this,
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneTimeActivityModel oneTimeActivityModel;
                if (etActivity.length() != 0 && etLocation.length() != 0 && etDate.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                    oneTimeActivityModel = new OneTimeActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etDate.getText().toString(), etTime.getText().toString(), Integer.parseInt(etReminder.getText().toString()));
                    Toast.makeText(umn.ac.keadaanudara.InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(umn.ac.keadaanudara.InputOneTimeActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    oneTimeActivityModel = new OneTimeActivityModel("NaN", "NaN", "NaN", "NaN", 0);
                }
                OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(umn.ac.keadaanudara.InputOneTimeActivity.this);
                boolean success = oneTimeDatabaseHelper.addOne(oneTimeActivityModel);

//                Toast.makeText(InputOneTimeActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
//                Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(umn.ac.keadaanudara.InputOneTimeActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}