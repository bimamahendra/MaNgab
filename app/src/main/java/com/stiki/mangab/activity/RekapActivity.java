package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.DetailAbsensiAdapter;
import com.stiki.mangab.adapter.RekapAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.DetailAbsenResponse;

import java.util.ArrayList;

public class RekapActivity extends AppCompatActivity {

    private Api api;

    private RecyclerView rvRekap;
    private Button btnRekap;
    
    private ArrayList<DetailAbsenResponse.MhsData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        list = (ArrayList) getIntent().getSerializableExtra("absen");

        api = ApiClient.getClient();

        rvRekap = findViewById(R.id.rvRekap);
        btnRekap = findViewById(R.id.btnRekap);

        setRecyclerView();
    }

    public void setRecyclerView() {
        rvRekap.setLayoutManager(new LinearLayoutManager(this));
        rvRekap.setAdapter(new DetailAbsensiAdapter(list));
    }
}
