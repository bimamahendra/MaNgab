package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.LoginResponse;
import com.stiki.mangab.preference.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Api api;

    EditText etNrp, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        api = ApiClient.getClient();

        btnLogin = findViewById(R.id.btnLogin);
        etNrp = findViewById(R.id.etNRP);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(v -> api.login(etNrp.getText().toString(), etPassword.getText().toString(), getDeviceId()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.body().error){
                    AppPreference.saveUser(getApplicationContext(), response.body().toUser());
                    if(response.body().statusPassword == 0){
                        startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                    } else {
                        if (response.body().type.equalsIgnoreCase("Mahasiswa")) {
                            startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), LecturerActivity.class));
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("FailLogin", t.getMessage());
            }
        }));
    }

    private String getDeviceId(){
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imei = manager.getDeviceId();
        if (imei == null || imei.trim().length() == 0) {
            imei = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                imei = manager.getImei();
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }
        return imei;
    }
}
