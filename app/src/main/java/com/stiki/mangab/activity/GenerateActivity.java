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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.stiki.mangab.R;
import com.stiki.mangab.api.Api;
import com.stiki.mangab.api.ApiClient;
import com.stiki.mangab.api.response.GenerateQrCodeResponse;
import com.stiki.mangab.api.response.MyClassResponse;
import com.stiki.mangab.api.response.MyLectureResponse;
import com.stiki.mangab.model.User;
import com.stiki.mangab.preference.AppPreference;

import java.io.ByteArrayOutputStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateActivity extends AppCompatActivity implements View.OnClickListener {

    Api api = ApiClient.getClient();
    User user;

    Button btnGenerate;
    TextView tvDosenName, tvDate;
    Spinner spSubject, spClass;
    EditText etRoom, etTopic;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    public static final String BitmapValue = "bitmap";
    public static final String GenerateResponse = "GenerateResponse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        user = AppPreference.getUser(this);

        btnGenerate = findViewById(R.id.btnGenerate);
        tvDosenName = findViewById(R.id.tvDosenName);
        tvDate = findViewById(R.id.tvDate);
        spSubject = findViewById(R.id.spSubject);
        spClass = findViewById(R.id.spClass);
        etRoom = findViewById(R.id.etRoom);
        etTopic = findViewById(R.id.etTopic);

        tvDosenName.setText(user.nama);
        tvDate.setText(new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
                .format(Calendar.getInstance().getTime()));

        api.myLecture(user.noInduk).enqueue(new Callback<MyLectureResponse>() {
            @Override
            public void onResponse(Call<MyLectureResponse> call, Response<MyLectureResponse> response) {
                if(!response.body().error){
                    spSubject.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item, response.body().data));
                }else {
                    Toast.makeText(GenerateActivity.this, response.body().message,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyLectureResponse> call, Throwable t) {
                if(t instanceof UnknownHostException){
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }else {
                    t.printStackTrace();
                }
            }
        });

        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MyLectureResponse.MyLectureData data = (MyLectureResponse.MyLectureData)
                        adapterView.getSelectedItem();
                api.myClass(data.kode).enqueue(new Callback<MyClassResponse>() {
                    @Override
                    public void onResponse(Call<MyClassResponse> call, Response<MyClassResponse> response) {
                        spClass.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, response.body().data));
                    }

                    @Override
                    public void onFailure(Call<MyClassResponse> call, Throwable t) {
                        if(t instanceof UnknownHostException){
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }else {
                            t.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGenerate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnGenerate){
            MyClassResponse.MyClassData selectedClass =
                    (MyClassResponse.MyClassData) spClass.getSelectedItem();

            if(selectedClass == null){
                Toast.makeText(this, "Class didn't chosen yet", Toast.LENGTH_SHORT).show();
                return;
            }

            if(etTopic.getText().toString().equals("")){
                Toast.makeText(this, "Topic is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if(etRoom.getText().toString().equals("")){
                Toast.makeText(this, "Room is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            api.generateQrCode(selectedClass.idMatkul, etTopic.getText().toString(),
                    etRoom.getText().toString()).enqueue(new Callback<GenerateQrCodeResponse>() {
                @Override
                public void onResponse(Call<GenerateQrCodeResponse> call, Response<GenerateQrCodeResponse> response) {
                    if(!response.body().error){
                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int smallerDimension = width < height ? width : height;
                        smallerDimension = smallerDimension * 3 / 4;

                        qrgEncoder = new QRGEncoder(response.body().qrCode, null, QRGContents.Type.TEXT, smallerDimension);

                        try {
                            bitmap = qrgEncoder.encodeAsBitmap();
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            Intent intent = new Intent(GenerateActivity.this, ResultActivity.class);
                            intent.putExtra(BitmapValue, byteArray);
                            intent.putExtra(GenerateResponse, response.body());
                            startActivity(intent);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(GenerateActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GenerateQrCodeResponse> call, Throwable t) {
                    if(t instanceof UnknownHostException){
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }else {
                        t.printStackTrace();
                    }
                }
            });
        }
    }
}
