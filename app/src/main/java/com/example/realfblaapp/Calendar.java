package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
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