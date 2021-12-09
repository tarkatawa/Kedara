package umn.ac.keadaanudara.Main;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import umn.ac.keadaanudara.R;

public class Feedback extends AppCompatActivity {
    Button button;
    EditText textInput;
    RatingBar ratingBar;
    TextView pesanFeedback;
    Dialog popup;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        popup = new Dialog (this);

        back = findViewById(R.id.back);
        getSupportActionBar().hide();
        pesanFeedback = findViewById(R.id.textView6);

        textInput = findViewById(R.id.textInput);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feedback.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        ratingBar = findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {

        });

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jumlahtext;
                jumlahtext = textInput.getText().toString();
                if (jumlahtext.length() > 0) {
                    popup.setContentView(R.layout.popup);
                    popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popup.show();
                }else {
                    Toast.makeText(Feedback.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}