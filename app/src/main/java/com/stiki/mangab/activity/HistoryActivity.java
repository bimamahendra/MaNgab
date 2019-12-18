package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.adapter.HistoryAbsensiAdapter;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.HistoryAbsensiResponse;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.net.UnknownHostException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private Api api;
    private User user;

    private RecyclerView rvHistory;
    private SwipeRefreshLayout srlHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        api = ApiClient.getClient();
        user = AppPreference.getUser(this);

        rvHistory = findViewById(R.id.rvHistory);
        srlHistory = findViewById(R.id.srlHistory);

        getHistory();

        srlHistory.setOnRefreshListener(() -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                getHistory();
                srlHistory.setRefreshing(false);
            }, 2500);
        });
    }

    public void getHistory() {
        api.historyAbsensiDosen(user.noInduk).enqueue(new Callback<HistoryAbsensiResponse>() {
            @Override
            public void onResponse(Call<HistoryAbsensiResponse> call, Response<HistoryAbsensiResponse> response) {
                if (response.code() == 200) {
                    if (!response.body().error) {
                        setRecyclerView(response.body().data);
                    }else {
                        Toast.makeText(HistoryActivity.this, response.body().message,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryAbsensiResponse> call, Throwable t) {
                if(t instanceof UnknownHostException){
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }else {
                    t.printStackTrace();
                }
                Log.e("getHistory", t.getMessage());
            }
        });
    }

    public void setRecyclerView(List<HistoryAbsensiResponse.HistoryAbsensiData> list) {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new HistoryAbsensiAdapter(list));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
