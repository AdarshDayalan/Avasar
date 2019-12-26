package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Attendance extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText idTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_main);

        idTxt = findViewById(R.id.studentId);
        load(idTxt);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC available", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
        }

        Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               idTxt = findViewById(R.id.studentId);
               if (idTxt != null && idTxt.length() == 6 ) {
                   save(idTxt);

                   TextView timeTxt = findViewById(R.id.time);

                   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                   String currentDateAndTime = sdf.format(new Date());
                   timeTxt.setText(currentDateAndTime + ", " + idTxt.getText().toString() + ", 0");
               }
               else{
                   Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
               }
           }
       });

        Button checkOutBtn = (Button) findViewById(R.id.checkOutButton);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTxt = findViewById(R.id.studentId);
                if (idTxt != null && idTxt.length() == 6 ) {
                    save(idTxt);

                    TextView timeTxt = findViewById(R.id.time);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                    String currentDateAndTime = sdf.format(new Date());
                    timeTxt.setText(currentDateAndTime + ", " + idTxt.getText().toString() + ", 1");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button clearBtn = findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFile();
            }
        });
    }

    public void save(View v) {

        String text = idTxt.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

//            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME,
//                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("");
            }

            idTxt.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clearFile() {
        File dir = getFilesDir();
        File file = new File(dir, FILE_NAME);
        deleteFile("example.txt");
        idTxt.setText("");
    }

}
