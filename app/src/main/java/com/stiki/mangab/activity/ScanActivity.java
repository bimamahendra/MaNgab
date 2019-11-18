package com.stiki.mangab.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.zxing.Result;
import com.stiki.mangab.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private boolean isCaptured = false;
    FrameLayout frameLayoutCamera;
    Guideline guideline;
    TextView tvValue;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        frameLayoutCamera = findViewById(R.id.frame_layout_camera);
        guideline = findViewById(R.id.guideline);
        tvValue = findViewById(R.id.text_view_qr_code_value);
        btnReset = findViewById(R.id.button_reset);
        initScannerView();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScannerView.resumeCameraPreview(ScanActivity.this);
                initDefaultView();
            }
        });
    }

    private void initScannerView() {
        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        frameLayoutCamera.addView(mScannerView);
    }

    private void initDefaultView() {
        tvValue.setText("QR Code Value");
        btnReset.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        mScannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }

    private void doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100){
            initScannerView();
        }else{
            //nothing to do
        }
    }

    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }

    @Override
    public void handleResult(Result result) {

        tvValue.setText(result.getText());
        btnReset.setVisibility(View.VISIBLE);
    }
}

