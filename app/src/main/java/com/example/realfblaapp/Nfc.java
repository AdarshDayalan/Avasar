package com.example.realfblaapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Nfc extends Activity {

    TextView textInfo;
    TextView textOut;
    NfcAdapter nfcAdapter;
    EditText idTxt;
    String nfcData;
    TextView timeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_main);
        textInfo = findViewById(R.id.textInfo);
        idTxt = findViewById(R.id.studentId);


    }



}