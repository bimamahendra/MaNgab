package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.DetailAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.DetailAbsenResponse;
import com.stiki.mangab.api.response.GenerateQrCodeResponse;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements Callback<DetailAbsenResponse> {
    Api api = ApiClient.getClient();
    Call<DetailAbsenResponse> request;

    ImageView ivQR;
    RecyclerView rvList;
    Button btnDone;

    GenerateQrCodeResponse generateQrCodeResponse;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(request == null){
                request = api.detailAbsen(generateQrCodeResponse.idAbsen);
            }

            if(request.isExecuted()){
                request = api.detailAbsen(generateQrCodeResponse.idAbsen);
            }

            request.enqueue(ResultActivity.this);
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        generateQrCodeResponse = (GenerateQrCodeResponse) getIntent()
                .getSerializableExtra(GenerateActivity.GenerateResponse);

        ivQR = findViewById(R.id.ivQRCode);
        rvList = findViewById(R.id.rvList);
        btnDone = findViewById(R.id.btnDone);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new DetailAbsensiAdapter(generateQrCodeResponse.dataMhs));

        byte[] byteArray = getIntent().getByteArrayExtra(GenerateActivity.BitmapValue);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ivQR.setImageBitmap(bmp);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RekapActivity.class);
                intent.putExtra("absen", (ArrayList)((DetailAbsensiAdapter) rvList.getAdapter()).getDataMhs());
                intent.putExtra("qrcode", generateQrCodeResponse.qrCode);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void onResponse(Call<DetailAbsenResponse> call, Response<DetailAbsenResponse> response) {
        if(!response.body().error){
            ((DetailAbsensiAdapter) rvList.getAdapter()).setNewData(response.body().data);
        }else {
            Toast.makeText(this, response.body().message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<DetailAbsenResponse> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
