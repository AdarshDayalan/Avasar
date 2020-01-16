//package com.example.realfblaapp;
//
//import android.app.Activity;
//import android.app.ActivityOptions;
//import android.content.Intent;
//import android.nfc.NdefMessage;
//import android.nfc.NdefRecord;
//import android.nfc.NfcAdapter;
//import android.nfc.NfcAdapter.CreateNdefMessageCallback;
//import android.nfc.NfcEvent;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.util.Log;
//import android.util.Pair;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import static android.content.ContentValues.TAG;
//
//public class Nfc extends Activity
//        implements CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
//
//    TextView textInfo;
//    TextView textOut;
//    TextView msg;
//
//    DatabaseReference databaseAttendance;
//
//    View NfcView;
//
//    NfcAdapter nfcAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.nfc_main);
//
//        databaseAttendance = FirebaseDatabase.getInstance().getReference("AttendanceData");
//
//        final ImageButton backBtn = findViewById(R.id.backBtnNfc);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goBack();
//            }
//        });
//
//        NfcView = findViewById(R.id.nfcHead);
//        textInfo = findViewById(R.id.textOut);
//        textOut = findViewById(R.id.textInfo);
//        msg = findViewById(R.id.msg);
//
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        if(nfcAdapter==null){
//            Toast.makeText(Nfc.this,
//                    "nfcAdapter==null, no NFC adapter exists",
//                    Toast.LENGTH_LONG).show();
//        }else{
//
////            Toast.makeText(Nfc .this,
////                    "Set Callback(s)",
////                    Toast.LENGTH_LONG).show();
//            nfcAdapter.setNdefPushMessageCallback(this, this);
//            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
//        }
//
//        // Read from the database
////        databaseAttendance.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // This method is called once with the initial value and again
////                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                Log.d(TAG, "Value is: " + value);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////                // Failed to read value
////                Log.w(TAG, "Failed to read value.", error.toException());
////            }
////        });
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intent = getIntent();
//        String action = intent.getAction();
//        if(action != null) {
//            if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
//                Parcelable[] parcelables =
//                        intent.getParcelableArrayExtra(
//                                NfcAdapter.EXTRA_NDEF_MESSAGES);
//                NdefMessage inNdefMessage = (NdefMessage) parcelables[0];
//                NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
//                NdefRecord NdefRecord_0 = inNdefRecords[0];
//                String inMsg = new String(NdefRecord_0.getPayload());
//                textInfo.setText(inMsg);
//                Toast.makeText(Nfc.this,
//                        inMsg,
//                        Toast.LENGTH_LONG).show();
//                parse(inMsg);
//            }
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        setIntent(intent);
//    }
//
//    @Override
//    public void onNdefPushComplete(NfcEvent event) {
//
//        final String eventString = "onNdefPushComplete\n" + event.toString();
//        runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(),
//                        eventString,
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    @Override
//    public NdefMessage createNdefMessage(NfcEvent event) {
//
//        String stringOut = textOut.getText().toString();
//        byte[] bytesOut = stringOut.getBytes();
//
//        NdefRecord ndefRecordOut = new NdefRecord(
//                NdefRecord.TNF_MIME_MEDIA,
//                "text/plain".getBytes(),
//                new byte[] {},
//                bytesOut);
//
//        NdefMessage ndefMessageout = new NdefMessage(ndefRecordOut);
//        return ndefMessageout;
//    }
//
//    public void parse(String message) {
//        String string = message;
//        int left = string.indexOf("(");
//        int right = string.indexOf(")");
//
//// pull out the text inside the parens
//        String sub = string.substring(left+1, right);
//
//        String idNum = string.substring(0, left);
//        String timeDate = sub;
//        String checkBoolean = string.substring(right+1);
//
//        String id = databaseAttendance.push().getKey();
//
//        AttendenceDatabase attendanceDatabase = new AttendenceDatabase(checkBoolean, idNum, timeDate);
//        databaseAttendance.child(id).setValue(attendanceDatabase);
//
//
//        if (checkBoolean.equals("0")) {
//            msg.setText(idNum + " has checked in at " + timeDate);
//        } else {
//            msg.setText(idNum + " has checked out at " + timeDate);
//        }
//    }
//
//    public void goBack() {
//        Intent intent = new Intent(this, Main.class);
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
//                Pair.create(NfcView, "NfcTxt"));
//        startActivity(intent, options.toBundle());
//    }
//
//}