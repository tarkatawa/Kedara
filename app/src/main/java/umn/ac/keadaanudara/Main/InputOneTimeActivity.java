package umn.ac.keadaanudara.Main;

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

import umn.ac.keadaanudara.Model.OneTimeActivityModel;
import umn.ac.keadaanudara.DatabaseHelper.OneTimeDatabaseHelper;
import umn.ac.keadaanudara.R;

public class InputOneTimeActivity extends AppCompatActivity {
    TextView tvDate, theTime, etTime, theDate, etDate;
    EditText etActivity, etLocation, etReminder;
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
                String month2 = String.valueOf(month);
                String day2 = String.valueOf(day);

                if(month2.length() < 2){
                    month2 = "0"+month2;
                }
                if(day2.length() < 2){
                    day2 = "0"+day2;
                }

                String date = day2+"/"+month2+"/"+year;
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


                        String month2 = String.valueOf(month);
                        String day2 = String.valueOf(day);

                        if(month2.length() < 2){
                            month2 = "0"+month2;
                        }
                        if(day2.length() < 2){
                            day2 = "0"+day2;
                        }

                        String date = day2+"/"+month2+"/"+year;
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jam = etTime.getText().toString(); // 13:03
                String jamJawab = jam.substring(0,2); //13 -> 12, 14 -> 15, 16 -> 15
                switch (jamJawab) {
                    case "01":
                        jamJawab = "00:00";
                        break;
                    case "02":
                        jamJawab = "03:00";
                        break;
                    case "03":
                        jamJawab = "03:00";
                        break;
                    case "04":
                        jamJawab = "03:00";
                        break;
                    case "05":
                        jamJawab = "06:00";
                        break;
                    case "06":
                        jamJawab = "06:00";
                        break;
                    case "07":
                        jamJawab = "06:00";
                        break;
                    case "08":
                        jamJawab = "09:00";
                        break;
                    case "09":
                        jamJawab = "09:00";
                        break;
                    case "10":
                        jamJawab = "09:00";
                        break;
                    case "11":
                        jamJawab = "12:00";
                        break;
                    case "12":
                        jamJawab = "12:00";
                        break;
                    case "13":
                        jamJawab = "12:00";
                        break;
                    case "14":
                        jamJawab = "15:00";
                        break;
                    case "15":
                        jamJawab = "15:00";
                        break;
                    case "16":
                        jamJawab = "15:00";
                        break;
                    case "17":
                        jamJawab = "18:00";
                        break;
                    case "18":
                        jamJawab = "18:00";
                        break;
                    case "19":
                        jamJawab = "18:00";
                        break;
                    case "20":
                        jamJawab = "21:00";
                        break;
                    case "21":
                        jamJawab = "21:00";
                        break;
                    case "22":
                        jamJawab = "21:00";
                        break;
                    case "23":
                        jamJawab = "00:00";
                        break;
                    default:
                        jamJawab = "00:00";
                        break;
                }


                OneTimeActivityModel oneTimeActivityModel;
                if (etActivity.length() != 0 && etLocation.length() != 0 && etDate.length() != 0 && etTime.length() != 0 && etReminder.length() != 0) {
                    oneTimeActivityModel = new OneTimeActivityModel(etActivity.getText().toString(), etLocation.getText().toString(), etDate.getText().toString(), jamJawab, Integer.parseInt(etReminder.getText().toString()), 106.8451, -6.2146);
                    Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InputOneTimeActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    oneTimeActivityModel = new OneTimeActivityModel("NaN", "NaN", "NaN", "NaN", 0, 0.0, 0.0);
                }
                OneTimeDatabaseHelper oneTimeDatabaseHelper = new OneTimeDatabaseHelper(InputOneTimeActivity.this);
                boolean success = oneTimeDatabaseHelper.addOne(oneTimeActivityModel);

//                Toast.makeText(InputOneTimeActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
//                Toast.makeText(InputOneTimeActivity.this, oneTimeActivityModel.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InputOneTimeActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}