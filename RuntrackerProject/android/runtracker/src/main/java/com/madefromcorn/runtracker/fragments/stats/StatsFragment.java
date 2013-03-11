package com.madefromcorn.runtracker.fragments.stats;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.madefromcorn.runtracker.R;
import com.viewpagerindicator.UnderlinePageIndicator;
import roboguice.inject.InjectView;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 3/11/13
 * Time: 4:17 PM
 */
public class StatsFragment extends RoboSherlockFragment {
    @InjectView(R.id.viewpager)
    private ViewPager mViewPager;
    @InjectView(R.id.viewpager_indicator)
    private UnderlinePageIndicator mPageIndicator;
    private StatViewAdapter mStatsViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStatsViewAdapter = new StatViewAdapter(getSherlockActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mStatsViewAdapter);

        mPageIndicator.setViewPager(mViewPager);
    }
}
