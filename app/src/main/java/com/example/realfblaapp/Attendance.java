package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
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

public class Attendance extends AppCompatActivity
        implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
    private static final String FILE_NAME = "example.txt";
    NfcAdapter nfcAdapter;
    EditText idTxt;
    private String nfcData;
    TextView timeTxt;
    boolean sendData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_main);

        idTxt = findViewById(R.id.studentId);
        load(idTxt);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            Toast.makeText(Attendance.this,
                    "nfcAdapter==null, no NFC adapter exists",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Attendance.this,
                    "Set Callback(s)",
                    Toast.LENGTH_LONG).show();

                nfcAdapter.setNdefPushMessageCallback(this, this);
                nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }

        Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               idTxt = findViewById(R.id.studentId);
               if (idTxt != null && idTxt.length() == 6 ) {
                   save(idTxt);
                    timeTxt = findViewById(R.id.time);

                   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                   String currentDateAndTime = sdf.format(new Date());

                   nfcData = currentDateAndTime + ", " + idTxt.getText().toString() + ", 0";

                   timeTxt.setText(nfcData);
                   Toast.makeText(getApplicationContext(), nfcData, Toast.LENGTH_LONG).show();
                   sendData = true;
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
                    nfcData = currentDateAndTime + ", " + idTxt.getText().toString() + ", 1";
                    timeTxt.setText(nfcData);
                    sendData = true;
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

    @Override
    protected void onResume() {
        super.onResume();
//        android.intent.action.Main
//        Intent intent = getIntent();
//        String action = intent.getAction();
//        Toast.makeText(Nfc.this, action, Toast.LENGTH_LONG).show();
//        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){
//            Parcelable[] parcelables =
//                    intent.getParcelableArrayExtra(
//                            NfcAdapter.EXTRA_NDEF_MESSAGES);
//            NdefMessage inNdefMessage = (NdefMessage)parcelables[0];
//            NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
//            NdefRecord NdefRecord_0 = inNdefRecords[0];
//            String inMsg = new String(NdefRecord_0.getPayload());
//            textInfo.setText(inMsg);
//        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {

        final String eventString = "onNdefPushComplete\n" + event.toString();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        eventString,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        String stringOut = nfcData;

        byte[] bytesOut = stringOut.getBytes();

        NdefRecord ndefRecordOut = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA,
                "text/plain".getBytes(),
                new byte[] {},
                bytesOut);

        NdefMessage ndefMessageout = new NdefMessage(ndefRecordOut);
        return ndefMessageout;
    }

}
