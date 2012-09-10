package apt.tutorial;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

	public RestaurantAdapter(Context context, List<Restaurant> model) {
		super(context, android.R.layout.simple_list_item_1, model);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TextView name, address = null;
		ImageView icon = null;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.row, null);
		}

		name = (TextView) row.findViewById(R.id.title);
		address = (TextView) row.findViewById(R.id.address);
		icon = (ImageView) row.findViewById(R.id.icon);

		Restaurant restaurant = getItem(position);
		name.setText(restaurant.getName());
		address.setText(restaurant.getAddress());
		
		if (restaurant.getType().equals("sitdown")) {
			icon.setImageResource(R.drawable.ball_red);
			row.setBackgroundColor(getContext().getResources().getColor(R.color.red));
		} else if (restaurant.getType().equals("takeout")) {
			icon.setImageResource(R.drawable.ball_yellow);
			row.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
		} else {
			icon.setImageResource(R.drawable.ball_green);
			row.setBackgroundColor(getContext().getResources().getColor(R.color.green));
		}

		return row;
	}

}
