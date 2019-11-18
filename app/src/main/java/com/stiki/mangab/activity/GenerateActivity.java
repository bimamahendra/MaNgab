package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.stiki.mangab.R;

import java.io.ByteArrayOutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateActivity extends AppCompatActivity {

    Button btnGenerate;
    TextView tvDosenName, tvDate;
    String dosenName, date, inputValue;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    public static final String BitmapValue = "bitmap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        btnGenerate = findViewById(R.id.btnGenerate);
        tvDosenName = findViewById(R.id.tvDosenName);
        tvDate = findViewById(R.id.tvDate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosenName = tvDosenName.getText().toString().replace(" ", "");
                date = tvDate.getText().toString().replace(" ", "");

                inputValue = dosenName.concat("_").concat(date);
                Log.d("inputvalue", inputValue);

                if (inputValue.length() > 0){
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);

                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        Intent intent = new Intent(GenerateActivity.this, ResultActivity.class);
                        intent.putExtra(BitmapValue, byteArray);
                        startActivity(intent);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }else {
                    //
                }
            }
        });


    }
}
