package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;

public class Contact extends AppCompatActivity {
    View ContactView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_main);

        ContactView = findViewById(R.id.contactHead);

        final ImageButton backBtn = findViewById(R.id.backBtnContact);
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
                Pair.create(ContactView, "ContactTxt"));
        startActivity(intent, options.toBundle());
    }
}
