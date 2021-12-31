package umn.ac.keadaanudara.Main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import umn.ac.keadaanudara.Adapter.WeatherAdapter;
import umn.ac.keadaanudara.Model.LocationModel;
import umn.ac.keadaanudara.Model.Modelmain;
import umn.ac.keadaanudara.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_FINE_LOCATION = 99;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String appid = "8f415b7021ae02e32442cc8555f6d572";
    private String today;
    private TextView txtDate;
    RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private final ArrayList<Modelmain> modelmain = new ArrayList<>();
    private LocationModel locationModel = new LocationModel();
    FloatingActionButton fab_action1;
    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        ImageView imgFeed = findViewById(R.id.imgFeed);
        ImageView imgChangeLocation = findViewById(R.id.imgChangeLocation);

        imgFeed.setOnClickListener(this);
        imgChangeLocation.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerWeather);

        txtDate = findViewById(R.id.txtDate);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        Date dateNow = Calendar.getInstance().getTime();
        today = (String) DateFormat.format("EEE", dateNow);

        updateGPS();

        fab_action1 = findViewById(R.id.fab_action1);
        fab_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //getActivityWeatherInfo();
    }

    private void getToday() {
        Date dateTime = Calendar.getInstance().getTime();
        String date = (String) DateFormat.format("d MMM yyyy", dateTime);
        String formatDate = today + ", " + date;
        txtDate.setText(formatDate);
    }

    private void updateGPS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnabled()) {
                    LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);

                            LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);

                            if (locationResult.getLocations().size() > 0) {
                                int index = locationResult.getLocations().size() - 1;
                                locationModel.setLat(locationResult.getLocations().get(index).getLatitude());
                                locationModel.setLon(locationResult.getLocations().get(index).getLongitude());

                                getToday();
                                getCurrentWeather();
                                getListWeather();
                            }
                        }
                    }, Looper.getMainLooper());
                } else {
                    turnOnGPS();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

    private void turnOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MainActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MainActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });
    }

    private boolean isGPSEnabled() {
        LocationManager locationManager;
        boolean isEnabled;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

//    private void getActivityWeatherInfo(){
//        QUERY DARI MODEL ACTIVITY DENGAN LOGIC DATE_SUB(event_date, INTERVAL days_perior) >= HARI INI

        //cursor yang dicek sama API
        //return
//    }

    private void getCurrentWeather() {
        double lat = locationModel.getLat();
        double lon = locationModel.getLon();
        AndroidNetworking.get(BASE_URL + "weather?lat=" + lat + "&lon=" + lon + "&units=metric&appid=" + appid)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weatherJsonArray = response.getJSONArray("weather");
                            JSONObject zeroJsonObject = weatherJsonArray.getJSONObject(0);
                            String main = zeroJsonObject.getString("main");
                            String description = zeroJsonObject.getString("description");
                            String icon = zeroJsonObject.getString("icon");
                            JSONObject mainJsonObject = response.getJSONObject("main");
                            double temp = mainJsonObject.getDouble("temp");
                            String name = response.getString("name");

                            TextView txtCityName = findViewById(R.id.txtCityName);
                            TextView txtDesc = findViewById(R.id.txtDesc);
                            TextView txtTemp = findViewById(R.id.txtTemp);
                            LottieAnimationView imgWeather = findViewById(R.id.imgWeather);

                            switch (icon) {
                                case "01d":
                                    imgWeather.setAnimation(R.raw.clear_01d);
                                    break;
                                case "01n":
                                    imgWeather.setAnimation(R.raw.clear_01n);
                                    break;
                                case "02d":
                                    imgWeather.setAnimation(R.raw.partly_cloudy_02d);
                                    break;
                                case "02n":
                                    imgWeather.setAnimation(R.raw.partly_cloudy_02n);
                                    break;
                                case "03d":
                                case "03n":
                                    imgWeather.setAnimation(R.raw.cloudy_03);
                                    break;
                                case "04d":
                                    imgWeather.setAnimation(R.raw.overcast_04d);
                                    break;
                                case "04n":
                                    imgWeather.setAnimation(R.raw.overcast_04n);
                                    break;
                                case "09d":
                                case "09n":
                                    imgWeather.setAnimation(R.raw.drizzle_09);
                                    break;
                                case "10d":
                                    imgWeather.setAnimation(R.raw.overcast_10d_rain);
                                    break;
                                case "10n":
                                    imgWeather.setAnimation(R.raw.overcast_10n_rain);
                                    break;
                                case "11d":
                                    imgWeather.setAnimation(R.raw.thunderstorms_11d);
                                    break;
                                case "11n":
                                    imgWeather.setAnimation(R.raw.thunderstorms_11n);
                                    break;
                                case "13d":
                                case "13n":
                                    imgWeather.setAnimation(R.raw.snow_13);
                                    break;
                                case "50d":
                                case "50n":
                                    imgWeather.setAnimation(R.raw.mist_50);
                                    break;
                                default:
                                    imgWeather.setAnimation(R.raw.not_available);
                                    break;
                            }

                            txtCityName.setText(name);
                            txtDesc.setText(description);
                            txtTemp.setText(String.format(Locale.getDefault(), "%.0f°C", temp));


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Failed to show the data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "No Internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getListWeather() {
        double lat = locationModel.getLat();
        double lon = locationModel.getLon();
        AndroidNetworking.get(BASE_URL + "forecast?lat=" + lat + "&lon=" + lon + "&units=metric&appid=" + appid)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listJsonArray = response.getJSONArray("list");
                            for (int i = 0; i<6; i++) {
                                Modelmain modelmain1 = new Modelmain();
                                JSONObject listJsonObject = listJsonArray.getJSONObject(i);
                                JSONObject mainJsonObject = listJsonObject.getJSONObject("main");
                                JSONArray weatherJsonArray = listJsonObject.getJSONArray("weather");
                                JSONObject zeroJsonObject = weatherJsonArray.getJSONObject(0);
                                String time = listJsonObject.getString("dt_txt");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");

                                try {
                                    Date timesFormat = dateFormat.parse(time);
                                    if (timesFormat != null) {
                                        time = timeFormat.format(timesFormat);
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                modelmain1.setTime(time);
                                modelmain1.setCurrentTemp(mainJsonObject.getDouble("temp"));
                                modelmain1.setIcon(zeroJsonObject.getString("icon"));
                                modelmain.add(modelmain1);
                            }

                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                            recyclerView.setHasFixedSize(true);
                            weatherAdapter = new WeatherAdapter(modelmain);
                            recyclerView.setAdapter(weatherAdapter);
                            weatherAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Failed to show the data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to exit this application?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgFeed:
                Intent feedIntent = new Intent(MainActivity.this, Feedback.class);
                startActivity(feedIntent);
                break;
            case R.id.imgChangeLocation:
                Intent changeLocationIntent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(changeLocationIntent);
                break;
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                lat = Double.parseDouble(data.getStringExtra("lat"));
                lon = Double.parseDouble(data.getStringExtra("lon"));
            }
        }
    }*/
}