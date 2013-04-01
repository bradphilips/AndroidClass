package com.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AndroidStuffActivity extends Activity {

    private static String TAG = "androidstuff";
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button listActivityButton = (Button)findViewById(R.id.goto_list);
        Button customListActivityButton = (Button)findViewById(R.id.goto_custom_list);
        Button intenseOnActivityButton = (Button)findViewById(R.id.goto_intense_example);
        listActivityButton.setOnClickListener(mListActivityOnClick);
        customListActivityButton.setOnClickListener(mCustomListActivityOnClick);
        intenseOnActivityButton.setOnClickListener(mIntenseActivityOnClick);
    }

    private View.OnClickListener mListActivityOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(AndroidStuffActivity.this, ListAdapterActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mCustomListActivityOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(AndroidStuffActivity.this, CustomListAdapterActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mIntenseActivityOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(AndroidStuffActivity.this, AndroidIntentsActivity.class);
            startActivity(intent);
        }
    };

}

