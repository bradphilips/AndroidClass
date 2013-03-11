package com.madefromcorn.runtracker.fragments.stats;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Project: runtracker-parent
 * Author: Brad Philips
 * Date: 3/11/13
 * Time: 4:45 PM
 */
public class StatViewAdapter extends FragmentPagerAdapter {
    private ArrayList<Class<StatsPaceFragment>> mFragments;

    public StatViewAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Class<StatsPaceFragment>>();
        mFragments.add(StatsPaceFragment.class);
        mFragments.add(StatsPaceFragment.class);
        mFragments.add(StatsPaceFragment.class);
    }

    @Override
    public Fragment getItem(int i) {   //DOES NOT WORK!!  Cannot nest fragments!!!  Try generic PagerAdapter
        Class<StatsPaceFragment> fragmentClass = mFragments.get(i);
        try {
            StatsPaceFragment paceFragment = fragmentClass.newInstance();
            paceFragment.setPageNumber(i);
            return paceFragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
