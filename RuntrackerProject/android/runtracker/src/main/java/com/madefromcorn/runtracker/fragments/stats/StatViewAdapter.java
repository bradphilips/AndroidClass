package com.madefromcorn.runtracker.fragments.stats;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.inject.Inject;
import com.madefromcorn.runtracker.R;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 3/11/13
 * Time: 4:45 PM
 */
public class StatViewAdapter extends PagerAdapter implements IconPagerAdapter {
    private ArrayList<Integer> mViewResources;
    private Context mContext;
    private static final int [] ICONS = new int[] {
            R.drawable.squiggle,
            R.drawable.loopback,
            R.drawable.shuffle
    };

    @Inject
    public StatViewAdapter(Context context) {
        mContext = context;

        mViewResources = new ArrayList<Integer>();
        mViewResources.add(R.layout.fragment_stats_pace);
        mViewResources.add(R.layout.fragment_stats_pace);
        mViewResources.add(R.layout.fragment_stats_pace);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mViewResources.get(position), container, false);
        TextView pageNumber = (TextView) view.findViewById(R.id.page_number);
        pageNumber.setText(String.format("Page: %d", position));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }

    @Override
    public int getCount() {
        return mViewResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}