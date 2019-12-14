package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private Api api;

    private EditText etPasswordNew, etPasswordConfirm;
    private Button btnChangePassword;

    private String nrp, tipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle("Change Password");

        api = ApiClient.getClient();

        nrp = getIntent().getStringExtra("nrp");
        tipe = getIntent().getStringExtra("tipe");

        etPasswordNew = findViewById(R.id.etPasswordNew);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.changePassword(nrp, etPasswordConfirm.getText().toString()).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (!response.body().error) {
                            if (tipe.equalsIgnoreCase("Mahasiswa")) {
                                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                                intent.putExtra("nrp", nrp);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), LecturerActivity.class);
                                intent.putExtra("nrp", nrp);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.e("ChangePassword", t.getMessage());
                    }
                });
            }
        });
    }
}
