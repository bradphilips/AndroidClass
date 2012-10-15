package com.example.tabbartutorial;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.tabbartutorial.data.RestaurantList;
import com.google.inject.Inject;

@ContentView(R.layout.list)
public class RestaurantListActivity extends RoboActivity {
	private RestaurantAdapter mAdapter;
	
	@Inject protected RestaurantList mRestaurantList;
	@InjectView(R.id.restaurants) private ListView mRestaurantListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new RestaurantAdapter(this, mRestaurantList.getRestaurants());
		
		mRestaurantListView.setAdapter(mAdapter);
		mRestaurantListView.setOnItemClickListener(mItemClickListener);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	
	private OnItemClickListener mItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
			mRestaurantList.setEditing(position);
			
			LunchListApplication application = (LunchListApplication) getApplication();	
			TabHost host = application.getTabHost();
			host.setCurrentTab(LunchListApplication.TAB_DETAILS);
		}
	};
}
