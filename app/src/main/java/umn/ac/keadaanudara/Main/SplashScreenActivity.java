package umn.ac.keadaanudara.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import umn.ac.keadaanudara.R;

public class SplashScreenActivity extends AppCompatActivity{
    boolean pernahPake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        pernahPake = loadData();

        getSupportActionBar().hide();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(pernahPake == false){
                startActivity(new Intent(getApplicationContext(), IntroActivity.class));
                finish();
            }else{
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

        }, 1000L);
    }

    public boolean loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("switch1", false);
    }
}