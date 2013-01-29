package com.madefromcorn.runtracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.madefromcorn.runtracker.R;
import com.madefromcorn.runtracker.uicommon.PaceSpinner;
import roboguice.inject.InjectView;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 1/7/13
 * Time: 8:01 PM
 */
public class RunFragment extends RoboSherlockFragment {

    @InjectView(R.id.pace_spinner)
    private PaceSpinner mPaceSpinner;
    @InjectView(R.id.distance_spinner)
    private Spinner mDistanceSpinner;
    @InjectView(R.id.run_button)
    private Button mRunButton;

    private static final String DIALOG_TAG = RunFragment.class.getName() + ".DIALOG_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_run, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
