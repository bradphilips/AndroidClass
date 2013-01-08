package com.madefromcorn.runtracker.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.ActionBar;

public class TabListener implements ActionBar.TabListener {
  private final FragmentActivity mActivity;
  private final String mTag;
  private final Class<? extends Fragment> mClass;
  private final Bundle mBundle;
  private Fragment mFragment;

  public TabListener(FragmentActivity activity, String tag, Class<? extends Fragment> clz, Bundle args) {
    mActivity = activity;
    mTag = tag;
    mClass = clz;
    mBundle = args;

    FragmentTransaction fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();

    // Check to see if we already have a fragment for this tab, probably
    // from a previously saved state.  If so, deactivate it, because our
    // initial state is that a tab isn't shown.
    mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
    if (mFragment != null && !mFragment.isDetached()) {
      fragmentTransaction.detach(mFragment);
    }
    fragmentTransaction.commit();
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    mFragment = Fragment.instantiate(mActivity, mClass.getName(), mBundle);
    fragmentTransaction.add(android.R.id.content, mFragment, mTag);
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    if (mFragment != null) {
      fragmentTransaction.detach(mFragment);
    }
  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
  }
}
