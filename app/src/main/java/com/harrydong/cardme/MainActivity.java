package com.harrydong.cardme;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.nfc.NdefRecord.createMime;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NfcAdapter.CreateNdefMessageCallback {
    private NfcAdapter mNfcAdapter;
    private TextView mOutputText;
    private EditText mInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View contentView = findViewById(R.id.toolbar);

        initializeComponents();

        //provides a notification as a snackbar if no NFC adapter
        if (mNfcAdapter == null) {
            //mOutputText.setText("This phone has not enabled NFC.");
            Snackbar.make(contentView,"No NFC adapter found.",Snackbar.LENGTH_SHORT);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Floating action button clicked!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //this method initializes the elements. This is where the elements in XML will be assigned to java variables.
    private void initializeComponents() {
        //mInputField = (EditText) findViewById(R.id.edt_input_beam);
        //mOutputText = (TextView) findViewById(R.id.received_txt_beam);

        //ImageView mTapImageBeam = (ImageView) findViewById(R.id.img_beam);
        //mTapImageBeam.setOnClickListener(this);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }



    @Override
    public void onResume() {
        super.onResume();

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    void processIntent(Intent intent) {
        Parcelable[] receivedMessages = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage message = (NdefMessage) receivedMessages[0];

        mOutputText.setText(new String(message.getRecords()[0].getPayload()));
    }

    @Override
    public void onPause() {
        super.onPause();

        mNfcAdapter.disableForegroundDispatch(this);
    }

    //this is a function which will be tied to a button
    @Override
    public void onClick(View v) {
        //send message
        mNfcAdapter.setNdefPushMessageCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = (mInputField.getText().toString());
        return new NdefMessage(
                new NdefRecord[]{createMime(
                        "application/vnd.com.example.android.beam", text.getBytes())
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
