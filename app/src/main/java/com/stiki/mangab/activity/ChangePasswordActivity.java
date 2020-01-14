package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.BaseResponse;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private Api api;
    private User user;

    private EditText etPasswordNew, etPasswordConfirm;
    private Button btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle("Change Password");

        api = ApiClient.getClient();
        user = AppPreference.getUser(this);

        etPasswordNew = findViewById(R.id.etPasswordNew);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        btnChangePassword.setOnClickListener(v -> api.changePassword(user.noInduk, etPasswordConfirm.getText().toString()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(!etPasswordNew.getText().toString().equals(etPasswordConfirm.getText().toString())){
                    Toast.makeText(ChangePasswordActivity.this, "Confirm password is not same", Toast.LENGTH_SHORT).show();
                }else if(etPasswordNew.getText().toString().equals("") || etPasswordConfirm.getText().toString().equals("")){
                    Toast.makeText(ChangePasswordActivity.this, "Please fill your new password", Toast.LENGTH_SHORT).show();
                }else{
                    if (!response.body().error) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                if(t instanceof UnknownHostException){
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }else {
                    t.printStackTrace();
                }
                Log.e("ChangePassword", t.getMessage());
            }
        }));
    }
}
