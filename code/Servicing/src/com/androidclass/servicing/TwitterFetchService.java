package com.androidclass.servicing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TwitterFetchService extends Service {
	private final String TAG = TwitterFetchService.class.getSimpleName();
	
	public static final String TWITTER_FETCH_SERVICE_BROADCAST = TwitterFetchService.class.getName() + "TWITTER_FETCH_SERVICE_BROADCAST";
	public static final String TWITTER_FETCH_SERVICE_RESPONSE_EXTRA = TwitterFetchService.class.getName() + "TWITTER_FETCH_SERVICE_RESPONSE_EXTRA";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "Started command to the service..");
		
		TwitterAsyncTask task = new TwitterAsyncTask(this);
		task.execute();
		
		return Service.START_REDELIVER_INTENT;
	}
		
	@Override
	public void onCreate() {
		Log.d(TAG, "I have created my service.");
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "I have bound to my service.");
		return null;
	}
}
