package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stiki.mangab.R;

public class ScanResultActivity extends AppCompatActivity {

    private ImageView ivMedal;
    private TextView tvKeterangan;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        ivMedal = findViewById(R.id.ivMedal);
        tvKeterangan = findViewById(R.id.tvKeterangan);
        btnBack = findViewById(R.id.btnBack);

        if (getIntent().getBooleanExtra("error", false)) {
            ivMedal.setImageResource(R.drawable.failure);
        } else {
            ivMedal.setImageResource(R.drawable.success);
        }

        tvKeterangan.setText(getIntent().getStringExtra("message"));

        btnBack.setOnClickListener(view -> {
            finish();
        });
    }
}
