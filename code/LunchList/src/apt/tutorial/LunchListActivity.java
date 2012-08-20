package apt.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class LunchListActivity extends TabActivity {
	List<Restaurant> model = new ArrayList<Restaurant>();
	RestaurantAdapter adapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(onSave);
		
		createResturants();
		createTabControls();
	}
	
	protected AdapterView.OnItemClickListener mAdapterOnClick = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
			Restaurant resturant = (Restaurant) adapter.getItemAtPosition(index); 
		}
	};

	private void createTabControls() {
		ListView list = (ListView) findViewById(R.id.restaurants);
		adapter = new RestaurantAdapter(this);
		list.setAdapter(adapter);

		TabHost.TabSpec spec = getTabHost().newTabSpec("ListTab");
		spec.setContent(R.id.restaurants);
		spec.setIndicator("List", getResources().getDrawable(R.drawable.list));
		getTabHost().addTab(spec);
		
		spec = getTabHost().newTabSpec("DetailsTab");
		spec.setContent(R.id.details);
		spec.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
		getTabHost().setCurrentTab(0);
	}

	private void createResturants() {
		Restaurant bangkokThai = new Restaurant();
		bangkokThai.setName("Bangkok Thai");
		bangkokThai.setAddress("Mayfield Road");
		
		Restaurant austins = new Restaurant();
		austins.setName("Austins");
		austins.setAddress("Mayfield Road");
		
		model.add(bangkokThai);
		model.add(austins);
	}

	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {
			Restaurant r = new Restaurant();
			EditText name = (EditText) findViewById(R.id.name);
			EditText address = (EditText) findViewById(R.id.addr);
			r.setName(name.getText().toString());
			r.setAddress(address.getText().toString());
			RadioGroup types = (RadioGroup) findViewById(R.id.types);

			switch (types.getCheckedRadioButtonId()) {
			case R.id.sitdown:
				r.setType("sitdown");
				break;
			case R.id.takeout:
				r.setType("takeout");
				break;
			case R.id.delivery:
				r.setType("delivery");
				break;
			}

			adapter.add(r);
		}
	};

	static class RestaurantHolder {
		private TextView name = null;
		private TextView address = null;
		private ImageView icon = null;

		RestaurantHolder(View row) {
			name = (TextView) row.findViewById(R.id.title);
			address = (TextView) row.findViewById(R.id.address);
			icon = (ImageView) row.findViewById(R.id.icon);
		}

		void populateFrom(Restaurant r, View row, Context mContext) {
			name.setText(r.getName());
			address.setText(r.getAddress());
			if (r.getType().equals("sitdown")) {
				icon.setImageResource(R.drawable.ball_red);
				row.setBackgroundColor(mContext.getResources().getColor(
						R.color.red));
			} else if (r.getType().equals("takeout")) {
				icon.setImageResource(R.drawable.ball_yellow);
				row.setBackgroundColor(mContext.getResources().getColor(
						R.color.yellow));
			} else {
				icon.setImageResource(R.drawable.ball_green);
				row.setBackgroundColor(mContext.getResources().getColor(
						R.color.green));
			}
		}
	}

}