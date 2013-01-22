package com.madefromcorn.runtracker;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import com.actionbarsherlock.app.ActionBar;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.madefromcorn.runtracker.fragments.RunFragment;
import com.madefromcorn.runtracker.utils.TabListener;
import roboguice.inject.ContentView;

@ContentView(R.layout.main)
public class MainActivity extends RoboSherlockFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION_CODES.HONEYCOMB > Build.VERSION.SDK_INT) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addActionTab(actionBar, "Run", null, RunFragment.class);
        addActionTab(actionBar, "Stats", null, RunFragment.class);
        addActionTab(actionBar, "Share", null, RunFragment.class);
    }

    protected void addActionTab(ActionBar actionBar, String name, Drawable icon, final Class<? extends Fragment> fragment) {
        ActionBar.Tab tab = actionBar.newTab();
        tab.setTabListener(new TabListener(this, name, fragment, null));

        tab.setText(name);
        tab.setIcon(icon);
        actionBar.addTab(tab);
    }
}

