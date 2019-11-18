package com.stiki.mangab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stiki.mangab.R;
import com.stiki.mangab.model.ClassModel;
import com.stiki.mangab.model.RoomModel;
import com.stiki.mangab.model.SubjectModel;

public class LecturerActivity extends AppCompatActivity {

    CardView cvGenerate, cvHistory;
    ClassModel classModel;
    RoomModel roomModel;
    SubjectModel subjectModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        cvGenerate = findViewById(R.id.cvGenerate);
        cvHistory = findViewById(R.id.cvHistory);

        cvGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LecturerActivity.this, GenerateActivity.class);
                startActivity(intent);
            }
        });

        classModel = new ClassModel();
        classModel.setClassId("001");
        classModel.setClassName("TI");
        classModel.setClassId("002");
        classModel.setClassName("SI");
        classModel.setClassId("003");
        classModel.setClassName("TA");
        classModel.setClassId("004");
        classModel.setClassName("ABC");
        classModel.setClassId("005");
        classModel.setClassName("EF");
        classModel.setClassId("006");
        classModel.setClassName("UWU");
        classModel.setClassId("007");
        classModel.setClassName("OO");

        roomModel = new RoomModel();
        roomModel.setRoomId("001");
        roomModel.setRoomName("RUANG A");
        roomModel.setRoomId("002");
        roomModel.setRoomName("RUANG B");
        roomModel.setRoomId("003");
        roomModel.setRoomName("RUANG C");
        roomModel.setRoomId("004");
        roomModel.setRoomName("RUANG D");
        roomModel.setRoomId("005");
        roomModel.setRoomName("RUANG E");

        subjectModel = new SubjectModel();
        subjectModel.setSubjectId("001");
        subjectModel.setSubjectName("BAHASA");
        subjectModel.setSubjectId("002");
        subjectModel.setSubjectName("AGAMA");
        subjectModel.setSubjectId("003");
        subjectModel.setSubjectName("BAHASA JAWA");
        subjectModel.setSubjectId("004");
        subjectModel.setSubjectName("MATEMATIKA");

    }
}
