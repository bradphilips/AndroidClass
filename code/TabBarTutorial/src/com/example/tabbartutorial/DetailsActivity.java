package com.example.tabbartutorial;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.tabbartutorial.data.RestaurantList;
import com.example.tabbartutorial.data.Restaurant;
import com.google.inject.Inject;

@ContentView(R.layout.details)
public class DetailsActivity extends RoboActivity {
	@InjectView(R.id.name) EditText mNameText;
	@InjectView(R.id.addr) EditText mAddressText;
	@InjectView(R.id.types) RadioGroup mTypesGroup;
	@InjectView(R.id.save) Button mSave;
	
	@Inject RestaurantList mRestaurantList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSave.setOnClickListener(onSave);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (mRestaurantList.isEditing()) {
			Restaurant restaurant = mRestaurantList.getEditingItem();
			mNameText.setText(restaurant.getName());
			mAddressText.setText(restaurant.getAddress());
			if(restaurant.getType().equalsIgnoreCase("sitdown")) {
				RadioButton radio = (RadioButton) mTypesGroup.findViewById(R.id.sitdown);
				radio.setSelected(true);
			} else if (restaurant.getType().equalsIgnoreCase("takeout")) {
				RadioButton radio = (RadioButton) mTypesGroup.findViewById(R.id.takeout);
				radio.setSelected(true);
			} else if (restaurant.getType().equalsIgnoreCase("delivery")) {
				RadioButton radio = (RadioButton) mTypesGroup.findViewById(R.id.delivery);
				radio.setSelected(true);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {
			Restaurant restaurant = null;
			if (mRestaurantList.isEditing()) {
				restaurant = mRestaurantList.getEditingItem();
			} else {
				restaurant = new Restaurant();
			}
			
			restaurant.setName(mNameText.getText().toString());
			restaurant.setAddress(mAddressText.getText().toString());
			
			switch (mTypesGroup.getCheckedRadioButtonId()) {
			case R.id.sitdown:
				restaurant.setType("sitdown");
				break;
			case R.id.takeout:
				restaurant.setType("takeout");
				break;
			case R.id.delivery:
				restaurant.setType("delivery");
				break;
			}
			
			if (mRestaurantList.isEditing() == false) {
				mRestaurantList.getRestaurants().add(restaurant);
			} else {
				mRestaurantList.setEditing(-1);
			}
			
			mRestaurantList.saveRestaurants();
			
			//Clear the form...
			mNameText.setText("");
			mAddressText.setText("");
			mTypesGroup.setSelected(false);
			
			LunchListApplication application = (LunchListApplication) getApplication();	
			TabHost host = application.getTabHost();
			host.setCurrentTab(LunchListApplication.TAB_LIST);
		}
	};
}
