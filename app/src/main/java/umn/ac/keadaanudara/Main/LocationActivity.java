package umn.ac.keadaanudara.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.L;
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

import umn.ac.keadaanudara.Adapter.CityAdapter;
import umn.ac.keadaanudara.Model.City;
import umn.ac.keadaanudara.Model.LocationModel;
import umn.ac.keadaanudara.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private List<City> cityArrayList = new ArrayList<>();
    private CityAdapter cityAdapter = null;
    private static final String SHARED_PREF = "SHARED_PREF";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String KABKO_KEY = "KABKO";
    private static final String LAT_KEY = "LAT";
    private static final String LON_KEY = "LON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        getSupportActionBar().setTitle("Change Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        ImageView imgLocateMe = findViewById(R.id.imgBGLocateMe);

        loadCity();

        initListener();

        imgLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = sharedPreferences.edit();
                editor.putString(KABKO_KEY, null);
                editor.putString(LAT_KEY, "0.0");
                editor.putString(LON_KEY, "0.0");
                editor.apply();
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_location_id);
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(stringBuilder);
    }

    private void loadCity() {
        try {
            String jsonString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i<jsonArray.length(); ++i) {
                JSONObject zeroJsonObject = jsonArray.getJSONObject(i);
                City cities = new City();
                cities.setKabko(zeroJsonObject.getString("kabko"));
                cities.setLat(zeroJsonObject.getDouble("lat"));
                cities.setLon(zeroJsonObject.getDouble("lon"));
                cityArrayList.add(cities);
            }

            RecyclerView recyclerView = findViewById(R.id.locationRecycler);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            cityAdapter = new CityAdapter(this, cityArrayList);
            recyclerView.setAdapter(cityAdapter);
            cityAdapter.notifyDataSetChanged();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater locationMenuInflater = getMenuInflater();
        locationMenuInflater.inflate(R.menu.menu, menu);

        MenuItem locationMenuItem = menu.findItem(R.id.menuSearch);
        SearchView locationSearchView = (SearchView) locationMenuItem.getActionView();

        locationSearchView.setQueryHint("Search City");
        locationSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                cityAdapter.getFilter().filter(s);
                return false;
            }
        });
        locationMenuItem.getIcon().setVisible(false, false);

        return true;

    }

    public void initListener() {
        cityAdapter.setOnItemClickListener((view, position) -> {
            City changeCity = cityArrayList.get(position);
            editor = sharedPreferences.edit();
            editor.putString(KABKO_KEY, changeCity.getKabko());
            editor.putString(LAT_KEY, String.valueOf(changeCity.getLat()));
            editor.putString(LON_KEY, String.valueOf(changeCity.getLon()));
            editor.apply();
            Intent intent = new Intent(LocationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LocationActivity.this, MainActivity.class);
        startActivity(intent);
    }
}