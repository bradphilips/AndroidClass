package com.androidclass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Project: androidstuff
 * Author: Brad Philips
 * Date: 3/25/13
 * Time: 5:11 PM
 */
public class AndroidStuffAdapter extends ArrayAdapter<String> {
    private static final String TAG = AndroidStuffAdapter.class.getSimpleName();
    private String[] mListObjects = new String[]{
            "Something",
            "Something else",
            "Your Mama",
            "Anon's Mama"
    };

    public AndroidStuffAdapter(Context context) {
        super(context, R.layout.list_item);
    }

    @Override
    public String getItem(int position) {
        Log.d(TAG, String.format("getItem at position %d", position));
        return mListObjects[position];
    }

    @Override
    public int getCount() {
        return mListObjects.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView)view.findViewById(R.id.list_text);
        textView.setText(getItem(position));
        return view;
    }
}
