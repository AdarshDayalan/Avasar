package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;

public class Calendar extends AppCompatActivity {

    View CalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);

        CalendarView = findViewById(R.id.calendarHead);

        final ImageButton backBtn = findViewById(R.id.backBtnCalendar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent(this, Main.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(CalendarView, "CalendarTxt"));
        startActivity(intent, options.toBundle());
    }
}
