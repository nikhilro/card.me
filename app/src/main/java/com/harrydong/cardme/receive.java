package com.schatten.espen.androidbeam;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        initializeComponents();

        if (mNfcAdapter == null) {
            mOutputText.setText("This phone has not enabled NFC.");
        }

    }

    private void initializeComponents() {
        mInputField = (EditText) findViewById(R.id.edt_input_beam);
        mOutputText = (TextView) findViewById(R.id.received_txt_beam);

        ImageView mTapImageBeam = (ImageView) findViewById(R.id.img_beam);
        mTapImageBeam.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_beam:
                mNfcAdapter.setNdefPushMessageCallback(this, this);
                break;
        }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = (mInputField.getText().toString());
        return new NdefMessage(
                new NdefRecord[]{createMime(
                        "application/vnd.com.example.android.beam", text.getBytes())
                });
    }
}