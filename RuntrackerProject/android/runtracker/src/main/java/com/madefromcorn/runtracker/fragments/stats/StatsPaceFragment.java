package com.madefromcorn.runtracker.fragments.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.madefromcorn.runtracker.R;
import roboguice.inject.InjectView;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 3/11/13
 * Time: 4:46 PM
 */
public class StatsPaceFragment extends RoboSherlockFragment {
    @InjectView(R.id.page_number)
    private TextView mPageNumber;

    private int mPageIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_pace, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPageNumber.setText(String.format("Page: %d", mPageIndex));
    }

    public void setPageNumber(int page) {
        mPageIndex = page;
    }
}
