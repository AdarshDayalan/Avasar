package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;



public class Main extends AppCompatActivity {

    View attendanceView;
    View CalendarView;
    View AboutView;
    View ContactView;
    View attendanceDataView;

    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_main);

        attendanceView = findViewById(R.id.attendanceButton);
        CalendarView = findViewById(R.id.calendarButton);
        AboutView= findViewById(R.id.aboutButton);
        ContactView = findViewById(R.id.contactButton);
        attendanceDataView = findViewById(R.id.attendanceDataBtn);

        attendanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendanceMain();
            }
        });

        CalendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarMain();
            }
        });

        ContactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactMain();
            }
        });

        AboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutMain();
            }
        });

        attendanceDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendanceDataMain();
            }
        });

    }

    public void openAttendanceMain() {
        Intent intent = new Intent(this, Attendance.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(attendanceView, "AttendanceTxt"));
        startActivity(intent, options.toBundle());
    }

    public void openAttendanceDataMain() {
        Intent intent = new Intent(this, AttendanceData.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(attendanceDataView, "attendanceDataTxt"));
        startActivity(intent, options.toBundle());
    }

    public void openCalendarMain() {
        Intent intent = new Intent(this, Calendar.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(CalendarView, "CalendarTxt"));
        startActivity(intent, options.toBundle());
    }
    public void openContactMain() {
        Intent intent = new Intent(this, Contact.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(ContactView, "ContactTxt"));
        startActivity(intent, options.toBundle());
    }
    public void openAboutMain() {
        Intent intent = new Intent(this, About.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(AboutView, "AboutTxt"));
        startActivity(intent, options.toBundle());
    }
}
