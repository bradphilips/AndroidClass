package com.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Project: androidstuff
 * Author: Brad Philips
 * Date: 4/1/13
 * Time: 4:12 PM
 */
public class AndroidIntentsActivity extends Activity {
    private EditText mToText;
    private EditText mMessageText;

    private static final int REQUEST_EMAIL = 1000;
    private static final int REQUEST_SMS = 2000;
    private static final int REQUEST_SCAN = 3000;
    private static final String TAG = AndroidIntentsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_intents);

        Button emailButton = (Button)findViewById(R.id.submit_intense_email);
        Button smsButton = (Button)findViewById(R.id.submit_intense_sms);
        Button barcodeButton = (Button)findViewById(R.id.submit_intense_barcode);

        mToText = (EditText)findViewById(R.id.to_edit_text);
        mMessageText = (EditText)findViewById(R.id.message_edit_text);

        emailButton.setOnClickListener(mSendEmailOnClick);
        smsButton.setOnClickListener(mSendSmsOnClick);
        barcodeButton.setOnClickListener(mScanBarcodeOnClick);
    }

    private View.OnClickListener mSendEmailOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
            sendEmailIntent.setType("plain/text");
            sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, mToText.getText());
            sendEmailIntent.putExtra(Intent.EXTRA_TEXT, mMessageText.getText());
            startActivityForResult(sendEmailIntent, REQUEST_EMAIL);
        }
    };

    private View.OnClickListener mSendSmsOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent sendSMSIntent = new Intent(Intent.ACTION_VIEW);
            sendSMSIntent.setType("vnd.android-dir/mms-sms");
            sendSMSIntent.putExtra(Intent.EXTRA_TEXT, mMessageText.getText());
            sendSMSIntent.setData(Uri.fromParts("sms", mToText.getText().toString(), null));
            startActivityForResult(sendSMSIntent, REQUEST_SMS);
        }
    };

    private View.OnClickListener mScanBarcodeOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.setPackage("com.google.zxing.client.android");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_SCAN);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, String.format("Request %d called back with result %d", requestCode, resultCode));
        if (requestCode == REQUEST_SCAN) {
            String barcodeResult = data.getStringExtra("SCAN_RESULT");
            mMessageText.setText(barcodeResult);
        }
    }
}
