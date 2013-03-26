package com.androidclass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Project: androidstuff
 * Author: Brad Philips
 * Date: 3/25/13
 * Time: 5:27 PM
 */
public class CustomListAdapterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_adapter_view);

        ListAdapter adapter = new AndroidStuffAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
