package com.example.tabbartutorial;

import android.app.Application;
import android.widget.TabHost;

public class LunchListApplication extends Application {
	public static int TAB_LIST 		= 0;
	public static int TAB_DETAILS 	= 1;
	
	private static TabHost mTabHost;

	public static TabHost getTabHost() {
		return mTabHost;
	}

	public static void setTabHost(TabHost tabHost) {
		mTabHost = tabHost;
	}
}
