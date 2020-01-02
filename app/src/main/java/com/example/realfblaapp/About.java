package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);
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


}
