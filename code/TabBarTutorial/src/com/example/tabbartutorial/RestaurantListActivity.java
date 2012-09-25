package com.example.tabbartutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.tabbartutorial.data.ResaurantList;

public class RestaurantListActivity extends Activity {
	private RestaurantAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		ResaurantList restaurantList = ResaurantList.getSharedResaurantList();
		mAdapter = new RestaurantAdapter(this, restaurantList.getRestaurants());
		
		ListView list = (ListView) findViewById(R.id.restaurants);
		
		list.setAdapter(mAdapter);
		list.setOnItemClickListener(mItemClickListener);
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
			ResaurantList restaurantList = ResaurantList.getSharedResaurantList();
			restaurantList.setEditing(position);
			
			TabHost tabHost = LunchListApplication.getTabHost();
			tabHost.setCurrentTab(LunchListApplication.TAB_DETAILS);
		}
	};
}
