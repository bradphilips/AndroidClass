package com.example.tabbartutorial;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();

		// tab for list
		TabSpec list = tabHost.newTabSpec("list");
		list.setIndicator("List", getResources().getDrawable(R.drawable.list));
		Intent listIntent = new Intent(this, RestaurantListActivity.class);
		list.setContent(listIntent);
		tabHost.addTab(list);

		// tab for list
		TabSpec details = tabHost.newTabSpec("details");
		details.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
		Intent detailsIntent = new Intent(this, DetailsActivity.class);
		details.setContent(detailsIntent);
		tabHost.addTab(details);
		
		LunchListApplication.setTabHost(tabHost);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
