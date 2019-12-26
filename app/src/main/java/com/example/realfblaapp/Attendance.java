package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);
        Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

           }
       });

    }}
