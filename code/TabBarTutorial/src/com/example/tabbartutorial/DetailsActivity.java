package com.example.tabbartutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.tabbartutorial.data.ResaurantList;
import com.example.tabbartutorial.data.Restaurant;

public class DetailsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(onSave);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		ResaurantList sharedList = ResaurantList.getSharedResaurantList();
		if (sharedList.isEditing()) {
			EditText name = (EditText) findViewById(R.id.name);
			EditText address = (EditText) findViewById(R.id.addr);
			RadioGroup types = (RadioGroup) findViewById(R.id.types);
			
			Restaurant restaurant = sharedList.getEditingItem();
			name.setText(restaurant.getName());
			address.setText(restaurant.getAddress());
			if(restaurant.getType().equalsIgnoreCase("sitdown")) {
				RadioButton radio = (RadioButton) types.findViewById(R.id.sitdown);
				radio.setSelected(true);
			} else if (restaurant.getType().equalsIgnoreCase("takeout")) {
				RadioButton radio = (RadioButton) types.findViewById(R.id.takeout);
				radio.setSelected(true);
			} else if (restaurant.getType().equalsIgnoreCase("delivery")) {
				RadioButton radio = (RadioButton) types.findViewById(R.id.delivery);
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
			ResaurantList sharedList = ResaurantList.getSharedResaurantList();
			Restaurant restaurant = null;
			if (sharedList.isEditing()) {
				restaurant = sharedList.getEditingItem();
			} else {
				restaurant = new Restaurant();
			}
			
			EditText name = (EditText) findViewById(R.id.name);
			EditText address = (EditText) findViewById(R.id.addr);
			RadioGroup types = (RadioGroup) findViewById(R.id.types);
			
			restaurant.setName(name.getText().toString());
			restaurant.setAddress(address.getText().toString());
			
			switch (types.getCheckedRadioButtonId()) {
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
			
			if (sharedList.isEditing() == false) {
				sharedList.getRestaurants().add(restaurant);
			} else {
				sharedList.setEditing(-1);
			}
			
			//Clear the form...
			name.setText("");
			address.setText("");
			types.setSelected(false);
			
			TabHost host = LunchListApplication.getTabHost();
			host.setCurrentTab(LunchListApplication.TAB_LIST);
		}
	};
}
