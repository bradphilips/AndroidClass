package com.androidclass.servicing;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ListActivity {

    protected static final String TAG = MainActivity.class.getSimpleName();
    protected TwitterResponseReciever mReceiver;
    protected ProgressDialog mProgress;
    protected EditText mSearchCriteria;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(mRefreshOnClick);
        mSearchCriteria = (EditText)findViewById(R.id.search_criteria);
        
        ArrayAdapter<TwitterResponse> adapter = new ArrayAdapter<TwitterResponse>(this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        
        mReceiver = new TwitterResponseReciever();
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
			mProgress = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
			mProgress.setTitle("Loading");
			mProgress.setMessage("Please wait...");
			mProgress.show();
			
			Intent intent = new Intent(MainActivity.this, TwitterFetchService.class);
			intent.putExtra(TwitterFetchService.TWITTER_FETCH_SERVICE_SEARCH_EXTRA, 
					mSearchCriteria.getText().toString());
			startService(intent);
		}
	};
	
	private class TwitterResponseReciever extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			MainActivity.this.updateList(intent);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateList(Intent intent) {
		mProgress.dismiss();
		mProgress = null;
		Log.d(TAG, "Has Extras: " + intent.hasExtra(TwitterFetchService.TWITTER_FETCH_SERVICE_RESPONSE_EXTRA));
		ArrayList<TwitterResponse> tweets = (ArrayList<TwitterResponse>) intent.getExtras().get(TwitterFetchService.TWITTER_FETCH_SERVICE_RESPONSE_EXTRA);
		ArrayAdapter<TwitterResponse> adapter = (ArrayAdapter<TwitterResponse>) getListAdapter();
		adapter.clear();
		
		for(TwitterResponse tweet : tweets) {
			adapter.add(tweet);
		}
		adapter.notifyDataSetChanged();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
