package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.BaseResponse;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    private Api api = ApiClient.getClient();
    private User user;

    private CardView cvScan;
    TextView tvCurrentDate, tvName, tvNoInduk;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        user = AppPreference.getUser(this);

        cvScan = findViewById(R.id.cvScan);
        btnLogout = findViewById(R.id.btnLogout);
        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        tvName = findViewById(R.id.tvName);
        tvNoInduk = findViewById(R.id.tvNoInduk);

        tvCurrentDate.setText(new SimpleDateFormat("MM dd, yyyy", Locale.getDefault())
                .format(Calendar.getInstance().getTime()));
        tvName.setText(user.nama);
        tvNoInduk.setText(user.noInduk);

        cvScan.setOnClickListener(v -> {
            Intent intent = new Intent(StudentActivity.this, ScanActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> api.logout(user.noInduk).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (!response.body().error) {
                    Toast.makeText(StudentActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(StudentActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("logout", t.getMessage());
            }
        }));
    }
}
