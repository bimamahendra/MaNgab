package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.printservice.PrintService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stiki.mangab.R;

public class MainActivity extends AppCompatActivity {

    EditText etNrp, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        etNrp = findViewById(R.id.etNRP);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNrp.getText().toString().equalsIgnoreCase("siswa")){
                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, LecturerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
