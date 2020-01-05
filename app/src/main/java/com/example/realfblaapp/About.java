package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
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

        final Button officerBtn = findViewById(R.id.button3);
        officerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                officer();
            }
        });
    }

    public void facebook(View view){
    Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Warren-Township-High-School-FBLA-180628628665646/"));
    startActivity(facebookIntent);

    }
    public void twitter(View view){
        Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/warrenfbla?lang=en/"));
        startActivity(twitterIntent);

    }
    public void instagram(View view){
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/warrenfbla/"));
        startActivity(instagramIntent);

    }
    public void website(View view){
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fbla-pbl.org/"));
        startActivity(websiteIntent);

    }
    public void join(View view){
        Intent joinIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSc_GMeDCzFGmfsxtGk7y_mz2mmZpHxwOQipZlvm94KqRSb4HQ/viewform"));
        startActivity(joinIntent);

    }

    public void officer() {
        Intent officerIntent = new Intent(this, Officers.class);
        startActivity(officerIntent);
    }
  
    public void goBack() {
        Intent intent = new Intent(this, Main.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(AboutView, "AboutTxt"));
        startActivity(intent, options.toBundle());
    }

}


