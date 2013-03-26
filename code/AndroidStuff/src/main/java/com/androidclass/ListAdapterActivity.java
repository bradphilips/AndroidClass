package com.androidclass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Project: androidstuff
 * Author: Brad Philips
 * Date: 3/25/13
 * Time: 4:28 PM
 */
public class ListAdapterActivity extends Activity {
    private String [] mListObjects = new String[] {
            "Something",
            "Something else",
            "Your Mama"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_adapter_view);

        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mListObjects);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
