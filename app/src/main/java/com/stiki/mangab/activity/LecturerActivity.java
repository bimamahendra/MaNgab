package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.model.ClassModel;
import com.stiki.mangab.model.RoomModel;
import com.stiki.mangab.model.SubjectModel;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LecturerActivity extends AppCompatActivity {

    CardView cvGenerate, cvHistory;
    TextView tvCurrentDate, tvName, tvNoInduk;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        user = AppPreference.getUser(this);

        cvGenerate = findViewById(R.id.cvGenerate);
        cvHistory = findViewById(R.id.cvHistory);
        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        tvName = findViewById(R.id.tvName);
        tvNoInduk = findViewById(R.id.tvNoInduk);

        tvCurrentDate.setText(new SimpleDateFormat("MM dd, yyyy", Locale.getDefault())
                .format(Calendar.getInstance().getTime()));
        tvName.setText(user.nama);
        tvNoInduk.setText(user.noInduk);

        cvGenerate.setOnClickListener(v -> {
            Intent intent = new Intent(LecturerActivity.this, GenerateActivity.class);
            startActivity(intent);
        });
    }
}
