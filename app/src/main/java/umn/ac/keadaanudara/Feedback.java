package umn.ac.keadaanudara;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import umn.ac.keadaanudara.R;

public class Feedback extends AppCompatActivity {
    Button button;
    RatingBar ratingBar;
    TextView pesanFeedback;
    Dialog popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        popup = new Dialog (this);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pesanFeedback = findViewById(R.id.textView6);
        ratingBar = findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {

        });


        button = findViewById(R.id.button3);
        button.setOnClickListener(v -> {
            Button close;
            popup.setContentView(R.layout.popup);
            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popup.show();
        });
    }
}