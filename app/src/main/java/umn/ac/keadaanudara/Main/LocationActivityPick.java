package umn.ac.keadaanudara.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.gms.location.LocationRequest;

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

import umn.ac.keadaanudara.Adapter.CityAdapter;
import umn.ac.keadaanudara.Model.City;
import umn.ac.keadaanudara.R;

public class LocationActivityPick extends AppCompatActivity {
    private List<City> cityArrayList = new ArrayList<>();
    private CityAdapter cityAdapter = null;
    private static final String SHARED_PREF = "SHARED_PREF";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    LocationRequest locationRequest;
    private static final int PERMISSION_FINE_LOCATION = 99;
    private static final String KABKO_KEY = "KABKO";
    private static final String LAT_KEY = "LAT";
    private static final String LON_KEY = "LON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pick);
        getSupportActionBar().setTitle("Change Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        ImageView imgLocateMe = findViewById(R.id.imgBGLocateMe);

        loadCity();

        initListener();

        imgLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                editor = sharedPreferences.edit();
                editor.putString(KABKO_KEY, null);
                editor.putString(LAT_KEY, "0.0");
                editor.putString(LON_KEY, "0.0");
                editor.apply();
                Intent intent = new Intent(LocationActivityPick.this, InputOneTimeActivity.class);
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
            Intent intent = new Intent(LocationActivityPick.this, InputOneTimeActivity.class);
            intent.putExtra("KABKO", changeCity.getKabko());
            intent.putExtra("LAT", Double.valueOf(changeCity.getLat()));
            intent.putExtra("LON", Double.valueOf(changeCity.getLon()));
            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
    Intent intent = new Intent(LocationActivityPick.this, InputOneTimeActivity.class);
    startActivity(intent);
    }
}