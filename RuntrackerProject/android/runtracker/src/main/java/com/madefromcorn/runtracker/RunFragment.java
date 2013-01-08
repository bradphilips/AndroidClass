package com.madefromcorn.runtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 1/7/13
 * Time: 8:01 PM
 */
public class RunFragment extends RoboSherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_run, container, false);
        return layout;
    }
}
