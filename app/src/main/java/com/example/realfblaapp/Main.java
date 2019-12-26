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

        Button attendanceBtn = (Button) findViewById(R.id.attendanceButton);
        attendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendanceMain();
            }
        });
    }

    public void openAttendanceMain() {
        Intent intent = new Intent(this, Attendance.class);
        startActivity(intent);
    }
}
