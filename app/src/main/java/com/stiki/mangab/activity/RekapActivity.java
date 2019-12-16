package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.RekapAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;

public class RekapActivity extends AppCompatActivity {

    private Api api;
    private RekapAbsensiAdapter rekapAbsensiAdapter;

    private RecyclerView rvRekap;
    private Button btnRekap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        api = ApiClient.getClient();

        rvRekap = findViewById(R.id.rvRekap);
        btnRekap = findViewById(R.id.btnRekap);


    }

    public void setRecyclerView() {

    }
}
