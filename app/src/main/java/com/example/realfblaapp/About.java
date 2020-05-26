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
        setContentView(R.layout.about_main);//

        AboutView = findViewById(R.id.aboutHead);

        final ImageButton backBtn = findViewById(R.id.backBtnAbout);//Creates button and binds it to the button found in layout
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });//Creates a click listener

        final Button officerBtn = findViewById(R.id.button3);//Creates button and binds it to the button found in layout
        officerBtn.setOnClickListener(new View.OnClickListener() {//Creates a click listener
            @Override
            public void onClick(View v) {
                officer();//Runs the function "officer()"
            }
        });

        final Button faqBtn = findViewById(R.id.faqButton);//Creates button and binds it to the button found in layout
        faqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faq();
            }
        });//Creates a click listener
        final Button termBtn = findViewById(R.id.termbutton);//Creates button and binds it to the button found in layout
        officerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                term(v);
            }
        });//Creates a click listener

    }

    public void facebook(View view){//function "facebook() creates an intent to open the webpage and starts the activity facebookIntent
    Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Warren-Township-High-School-FBLA-180628628665646/"));
    startActivity(facebookIntent);

    }
    public void term(View view){//function "term() creates an intent to open the webpage and starts the activity termIntent
        Intent termIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1k1FpRR4Njb47SRpcjDR6YxT6oSuzMVephge5GwaCpus/edit?usp=sharing"));
        startActivity(termIntent);

    }
    public void twitter(View view){//function "twitter() creates an intent to open the webpage and starts the activity twitterIntent
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

    public void faq() {
        Intent faqIntent = new Intent(this, faq.class);
        startActivity(faqIntent);
    }
  
    public void goBack() {
        Intent intent = new Intent(this, Main.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(AboutView, "AboutTxt"));
        startActivity(intent, options.toBundle());
    }

}


