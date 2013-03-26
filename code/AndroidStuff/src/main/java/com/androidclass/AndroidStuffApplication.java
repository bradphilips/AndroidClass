package com.androidclass;

import android.app.Application;
import android.util.Log;

/**
 * Project: androidstuff
 * Author: Brad Philips
 * Date: 3/25/13
 * Time: 4:40 PM
 */
public class AndroidStuffApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("APP", "B-RAD is on...");
    }
}
