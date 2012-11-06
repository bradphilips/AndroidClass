package com.androidclass.servicing;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    protected static final String TAG = MainActivity.class.getSimpleName();
    protected UpdateReceiver mReceiver;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(mRefreshOnClick);
        mReceiver = new UpdateReceiver();
        IntentFilter filter = new IntentFilter(TwitterFetchService.TWITTER_FETCH_SERVICE_BROADCAST);
        registerReceiver(mReceiver, filter);
    }
	
	@Override
	protected void onPause() {
		unregisterReceiver(mReceiver);
		super.onPause();
	}

    private OnClickListener mRefreshOnClick = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Log.d(TAG, "Calling service...");
			Intent intent = new Intent(MainActivity.this, TwitterFetchService.class);
			startService(intent);
		}
	};
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
