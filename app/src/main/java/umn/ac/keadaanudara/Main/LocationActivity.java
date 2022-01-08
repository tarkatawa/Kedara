package umn.ac.keadaanudara.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.airbnb.lottie.L;

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
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "SHARED_PREF";
    private static final String KABKO_KEY = "KABKO";
    private static final String LAT_KEY = "LAT";
    private static final String LON_KEY = "LON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        loadCity();

        initListener();

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);

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
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String lat = String.valueOf(changeCity.getLat());
            String lon = String.valueOf(changeCity.getLon());
            editor.putString(KABKO_KEY, changeCity.getKabko());
            editor.putString(LAT_KEY, lat);
            editor.putString(LON_KEY, lon);
            editor.apply();
            Intent intent = new Intent(LocationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}