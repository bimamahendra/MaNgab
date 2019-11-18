package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.stiki.mangab.R;

public class ResultActivity extends AppCompatActivity {
    ImageView ivQR;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ivQR = findViewById(R.id.ivQRCode);

        bitmap = getIntent().getParcelableExtra(GenerateActivity.BitmapValue);

        ivQR.setImageBitmap(bitmap);

    }
}
