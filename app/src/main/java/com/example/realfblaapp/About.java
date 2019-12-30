package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;

public class About extends AppCompatActivity {

    View AboutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);

        AboutView = findViewById(R.id.aboutHead);

        final ImageButton backBtn = findViewById(R.id.backBtnAbout);
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
                Pair.create(AboutView, "AboutTxt"));
        startActivity(intent, options.toBundle());
    }


}
