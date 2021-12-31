package umn.ac.keadaanudara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import umn.ac.keadaanudara.Main.ListActivity;

public class popup_delete extends AppCompatActivity {
    Button btn_delete, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_delete);

        btn_delete = findViewById(R.id.btn_delete);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_delete.this, ListActivity.class);
                startActivity(intent);

                Toast.makeText(popup_delete.this, "Succes", Toast.LENGTH_LONG).show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_delete.this, ListActivity.class);
                startActivity(intent);

                Toast.makeText(popup_delete.this, "canceled", Toast.LENGTH_LONG).show();
            }
        });
    }
}