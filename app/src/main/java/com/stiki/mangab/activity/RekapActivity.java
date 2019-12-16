package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.RekapAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.BaseResponse;
import com.stiki.mangab.api.response.DetailAbsenResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RekapActivity extends AppCompatActivity implements RekapAbsensiAdapter.RekapAbsensiListener {

    private Api api;
    private String qrCode;

    private RecyclerView rvRekap;
    private Button btnRekap;
    private EditText etNote;
    
    private ArrayList<DetailAbsenResponse.MhsData> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        ArrayList<DetailAbsenResponse.MhsData> list = (ArrayList) getIntent()
                .getSerializableExtra("absen");

        for(int i=0;i<list.size();i++){
            if(list.get(i).statusAbsen == 0){
                filteredList.add(list.get(i));
            }
        }

        qrCode = getIntent().getStringExtra("qrcode");

        api = ApiClient.getClient();

        rvRekap = findViewById(R.id.rvRekap);
        btnRekap = findViewById(R.id.btnRekap);
        etNote = findViewById(R.id.etNote);

        setRecyclerView();

        btnRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.rekap(qrCode, etNote.getText().toString()).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if(!response.body().error){
                            Toast.makeText(RekapActivity.this, response.message(),
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RekapActivity.this, response.body().message,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(RekapActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onIzinMhs(DetailAbsenResponse.MhsData data) {
        api.absenMhs(qrCode, data.nrp, "2").enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(RekapActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(RekapActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSakitMhs(DetailAbsenResponse.MhsData data) {
        api.absenMhs(qrCode, data.nrp, "3").enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(RekapActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(RekapActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRecyclerView() {
        rvRekap.setLayoutManager(new LinearLayoutManager(this));
        rvRekap.setAdapter(new RekapAbsensiAdapter(filteredList, this));
    }
}
