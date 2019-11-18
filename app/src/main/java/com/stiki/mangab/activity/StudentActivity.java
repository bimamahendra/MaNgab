package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stiki.mangab.R;

public class StudentActivity extends AppCompatActivity {

    CardView cvScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        cvScan = findViewById(R.id.cvScan);

        cvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });
    }
}
