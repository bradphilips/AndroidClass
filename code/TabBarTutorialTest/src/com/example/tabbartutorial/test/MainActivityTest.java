package com.example.tabbartutorial.test;

import android.content.Context;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.TabHost;

import com.example.tabbartutorial.DetailsActivity;
import com.example.tabbartutorial.MainActivity;
import com.example.tabbartutorial.R;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
	public MainActivityTest(Class<MainActivity> activityClass) {
		super(activityClass);
	}

	public void testTabHost() {
		TabHost tabhost = getActivity().getTabHost();
		assertNotNull("Tabhost was null.. you are screwed.", tabhost);
		tabhost.setCurrentTab(1);
		
		Context context = tabhost.getContext();
		assertEquals(context.getClass(), DetailsActivity.class);
		DetailsActivity details = (DetailsActivity)context;
		Button saveButton = (Button) details.findViewById(R.id.save);
		assertNotNull("Save button was null.. go somewhere else...", saveButton);
	}
}
