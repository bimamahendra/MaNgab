package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        byte[] byteArray = getIntent().getByteArrayExtra(GenerateActivity.BitmapValue);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ivQR.setImageBitmap(bmp);

    }
}
