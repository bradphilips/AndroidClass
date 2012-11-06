package com.androidclass.servicing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class TwitterFetchService extends Service {
	private final String TAG = TwitterFetchService.class.getSimpleName();
	
	public static final String TWITTER_FETCH_SERVICE_BROADCAST = TwitterFetchService.class.getName() + "TWITTER_FETCH_SERVICE_BROADCAST";
	public static final String TWITTER_FETCH_SERVICE_RESPONSE_EXTRA = TwitterFetchService.class.getName() + "TWITTER_FETCH_SERVICE_RESPONSE_EXTRA";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "Started command to the service..");
		
		mTwitterTask.execute();
		return Service.START_REDELIVER_INTENT;
	}
	
	private AsyncTask<Void, Void, Void> mTwitterTask = new AsyncTask<Void, Void, Void>() {
		@Override
		protected Void doInBackground(Void... params) {
			Log.d(TAG, "Excellent...");
			HttpClient client = new DefaultHttpClient();
			HttpGet fetchRequest = new HttpGet("http://search.twitter.com/search.json?lang=en&q=%23android");
			try {
				HttpResponse response = client.execute(fetchRequest);
				String jsonString = convertStreamToString(response.getEntity().getContent());
				ArrayList<TwitterResponse> twitterResponse = TwitterResponse.deserializeJson(jsonString);
				Intent broadcast = new Intent(TwitterFetchService.TWITTER_FETCH_SERVICE_BROADCAST);
				broadcast.putExtra(TwitterFetchService.TWITTER_FETCH_SERVICE_RESPONSE_EXTRA, twitterResponse);
				sendBroadcast(broadcast);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		private String convertStreamToString(InputStream is) {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    try {
		        while ((line = reader.readLine()) != null) {
		            sb.append((line + "\n"));
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            is.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    return sb.toString();
		}
	};
	
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
