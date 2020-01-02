package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Main extends AppCompatActivity {


    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_main);

        Button attendanceBtn = findViewById(R.id.attendanceButton);
        attendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendanceMain();
            }
        });

        Button calendarBtn = findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarMain();
            }
        });

        Button contactBtn = findViewById(R.id.contactButton);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactMain();
            }
        });

        Button aboutBtn = findViewById(R.id.aboutButton);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutMain();
            }
        });
    }

    public void openAttendanceMain() {
        Intent intent = new Intent(this, Attendance.class);
        startActivity(intent);
    }
    public void openCalendarMain() {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
    public void openContactMain() {
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
    }
    public void openAboutMain() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
