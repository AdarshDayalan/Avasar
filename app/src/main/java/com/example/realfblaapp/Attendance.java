package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);
        Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               EditText idTxt = findViewById(R.id.studentId);
               int idNum = Integer.parseInt(idTxt.getText().toString());
               TextView timeTxt = findViewById(R.id.time);

               SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
               String currentDateAndTime = sdf.format(new Date());
               timeTxt.setText(currentDateAndTime + ", " + idNum);
           }
       });

    }}
