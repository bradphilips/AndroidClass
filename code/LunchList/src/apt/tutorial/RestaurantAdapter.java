package apt.tutorial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

	public RestaurantAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_1);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflator = ((Activity) getContext()).getLayoutInflater();
		}

		return row;
	}
}
	