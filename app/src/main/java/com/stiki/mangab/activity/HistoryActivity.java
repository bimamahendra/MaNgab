package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.HistoryAbsensiAdapter;
import com.stiki.mangab.adapter.RekapAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.HistoryAbsensiResponse;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private Api api;
    private User user;

    private RecyclerView rvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        api = ApiClient.getClient();
        user = AppPreference.getUser(this);

        rvHistory = findViewById(R.id.rvHistory);

        api.historyAbsensiDosen(user.noInduk).enqueue(new Callback<HistoryAbsensiResponse>() {
            @Override
            public void onResponse(Call<HistoryAbsensiResponse> call, Response<HistoryAbsensiResponse> response) {
                if (response.code() == 200) {
                    if (!response.body().data.isEmpty()) {
                        setRecyclerView(response.body().data);
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryAbsensiResponse> call, Throwable t) {
                Log.e("getHistory", t.getMessage());
            }
        });
    }

    public void setRecyclerView(List<HistoryAbsensiResponse.HistoryAbsensiData> list) {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new HistoryAbsensiAdapter(list));
    }
}
