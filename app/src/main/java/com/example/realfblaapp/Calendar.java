package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    View Calendar_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);

        Calendar_View = findViewById(R.id.calendarHead);

        final ImageButton backBtn = findViewById(R.id.backBtnCalendar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

//        CalendarView cv = (android.widget.CalendarView) this.findViewById(R.id.calendarView);
//        ViewGroup vg = (ViewGroup) cv.getChildAt(0);
//        View child = vg.getChildAt(0);
//
//        if(child instanceof TextView) {
//            ((TextView)child).setTextColor(getResources().getColor(R.color.darkBlue));
//        }
    }

    public void goBack() {
        Intent intent = new Intent(this, Main.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(Calendar_View, "CalendarTxt"));
        startActivity(intent, options.toBundle());
    }
    public void competition(View view){
        Intent competitionIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSci-Sn_Cumod9eaEGm9ieSvZhoBD9D0MHIPKn1p9DO32HhK_g/viewform"));
        startActivity(competitionIntent);

    }
    public void volunteer(View view){
        Intent volunteerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfwlWEqJzfkHvZAmqw7agKGw8RKVAo4FjML1oHYb3FYmyu-rw/viewform"));
        startActivity(volunteerIntent);

    }
}