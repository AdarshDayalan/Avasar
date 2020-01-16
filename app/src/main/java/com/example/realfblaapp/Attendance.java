package com.example.realfblaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    TextView timeTxt;
    ImageButton crossImg;
    ImageButton checkImg;
    TextView nfcStatus;
    View attendanceView;

    private String nfcData;
    boolean sendData = false;
    int numOfChar;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_main);

        idTxt = findViewById(R.id.studentId);
        crossImg = findViewById(R.id.crossImg);
        checkImg = findViewById(R.id.check);
        nfcStatus = findViewById(R.id.NfcStat);
        attendanceView = findViewById(R.id.attendance);

        Toast.makeText(getApplicationContext(), "Check In or Out to ready Nfc", Toast.LENGTH_LONG).show();

        load(idTxt);

        final Button checkOutBtn = (Button) findViewById(R.id.checkOutButton);
//        final Button clearBtn = findViewById(R.id.clearBtn);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            Toast.makeText(Attendance.this,
                    "nfcAdapter==null, no NFC adapter exists",
                    Toast.LENGTH_LONG).show();
        }else{

                nfcAdapter.setNdefPushMessageCallback(this, this);
                nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }

        final Button checkInBtn = (Button) findViewById(R.id.checkInButton);
        checkInBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               idTxt = findViewById(R.id.studentId);
               if (idTxt != null && idTxt.length() == 6 ) {
                   save(idTxt);
                    timeTxt = findViewById(R.id.time);

                   sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
                   String currentDateAndTime = sdf.format(new Date());

                   nfcData = idTxt.getText().toString() +  "(" + currentDateAndTime + ")0";

                   timeTxt.setText(nfcData);
//                   Toast.makeText(getApplicationContext(), nfcData, Toast.LENGTH_LONG).show();
                   sendData = true;

                   Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_anim);
                   Animation growAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_anim);

                   crossImg.startAnimation(animation);
                   checkImg.startAnimation(growAnimation);

                   nfcStatus.setText("Nfc Ready");

               }
               else{
                   Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
               }
           }
       });


        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTxt = findViewById(R.id.studentId);
                if (idTxt != null && idTxt.length() == 6 ) {
                    save(idTxt);

                    TextView timeTxt = findViewById(R.id.time);

                    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
                    String currentDateAndTime = sdf.format(new Date());
                    nfcData = idTxt.getText().toString() +  "(" + currentDateAndTime + ")1";
//                    Toast.makeText(getApplicationContext(), nfcData, Toast.LENGTH_LONG).show();
                    timeTxt.setText(nfcData);
                    sendData = true;

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_anim);
                    Animation growAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_anim);

                    crossImg.startAnimation(animation);
                    checkImg.startAnimation(growAnimation);

                    nfcStatus.setText("Nfc Ready");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid ID number. Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });


//        clearBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearFile();
//            }
//        });

        final ImageButton backBtn = findViewById(R.id.backBtnAttendance);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        idTxt.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                numOfChar = (s.toString().length());
                if (numOfChar == 6) {
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(idTxt.getWindowToken(), 0);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
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
        if (timeTxt != null) {
            timeTxt.setText("");
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_anim);
        Animation growAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_anim);

        crossImg.startAnimation(growAnimation);
        checkImg.startAnimation(animation);

        final String eventString = "Nfc Data sent!";
        final String nfcNotSent = "Nfc not ready";

        nfcStatus.setText("Nfc not ready");

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

    public void goBack() {
        Intent intent = new Intent(this, Main.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(attendanceView, "AttendanceTxt"));
        startActivity(intent, options.toBundle());
    }
}