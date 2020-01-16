package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ManualAttendance extends AppCompatActivity {

    EditText idTxt;

    private String nfcData;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_attendance);

        idTxt = findViewById(R.id.studentId);

        final Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTxt = findViewById(R.id.studentId);
                if (idTxt != null && idTxt.length() == 6 ) {

                    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
                    String currentDateAndTime = sdf.format(new Date());

                    nfcData = idTxt.getText().toString() +  "(" + currentDateAndTime + ")0";

                   Toast.makeText(getApplicationContext(), nfcData, Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button checkOutBtn = (Button) findViewById(R.id.checkOutButton);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTxt = findViewById(R.id.studentId);
                if (idTxt != null && idTxt.length() == 6 ) {

                    TextView timeTxt = findViewById(R.id.time);

                    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
                    String currentDateAndTime = sdf.format(new Date());
                    nfcData = idTxt.getText().toString() +  "(" + currentDateAndTime + ")1";
                    Toast.makeText(getApplicationContext(), nfcData, Toast.LENGTH_LONG).show();
                    timeTxt.setText(nfcData);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
