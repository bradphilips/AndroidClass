package com.androidclass.findmeoryou;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MainActivity extends MapActivity implements LocationListener{
	private final class OverlayExtension extends ItemizedOverlay<OverlayItem> {
		private OverlayItem mItem;

		public OverlayExtension(Drawable defaultMarker) {
			super(defaultMarker);
		}

		public OverlayExtension(Drawable drawable, OverlayItem item) {
			this(drawable);
			mItem = item;
		}

		@Override
		public boolean onTap(GeoPoint p, MapView mapView) {
			Toast.makeText(MainActivity.this, "Thanks for touching " + mItem.getTitle() + "...", Toast.LENGTH_LONG).show();
			return super.onTap(p, mapView);
		}

		@Override
		protected OverlayItem createItem(int i) {
			return getItem(i);
		}

		@Override
		public int size() {
			return 0;
		}
	}

	protected LocationManager mLocationManager;
	protected String mCurrentProvider;
	protected MapView mMapView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.mapview);
		setupLocationUpdates();
	}

	private void setupLocationUpdates() {
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.NO_REQUIREMENT);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		String provider = mLocationManager.getBestProvider(criteria, true);
		if(provider == null) {
			mCurrentProvider = null;
			Toast.makeText(this, "NO PROVIDER ENABLED DORK", Toast.LENGTH_LONG).show();
		} else {
			mCurrentProvider = provider;
			mLocationManager.requestLocationUpdates(provider, 0, 0, this);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setupLocationUpdates();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
		int latE6 = (int)(location.getLatitude() * 1E6), lngE6 = (int)(location.getLongitude() * 1E6);
		GeoPoint point = new GeoPoint(latE6, lngE6);
		OverlayItem item = new OverlayItem(point, "Me or You", null);
		Overlay overlay = new OverlayExtension(getResources().getDrawable(android.R.drawable.btn_star), item);
		mMapView.getOverlays().add(overlay);
		mMapView.getController().animateTo(point);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Provider:" + provider + " was disabled", Toast.LENGTH_SHORT).show();
		if(mCurrentProvider == provider) {
			setupLocationUpdates();
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Provider:" + provider + " was enabled", Toast.LENGTH_SHORT).show();
		if(mCurrentProvider == null) {
			setupLocationUpdates();
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
