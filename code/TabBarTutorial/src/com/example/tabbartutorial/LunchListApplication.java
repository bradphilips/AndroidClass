package com.example.tabbartutorial;

import android.app.Application;
import android.widget.TabHost;

public class LunchListApplication extends Application {
	public static int TAB_LIST 		= 0;
	public static int TAB_DETAILS 	= 1;
	
	private TabHost mTabHost;
	private static LunchListApplication mApplication;
	
	public LunchListApplication() {
		mApplication = this;
	}
	
	public static LunchListApplication getApplication() {
		return mApplication;
	}

	public TabHost getTabHost() {
		return mTabHost;
	}

	public void setTabHost(TabHost tabHost) {
		mTabHost = tabHost;
	}
}
