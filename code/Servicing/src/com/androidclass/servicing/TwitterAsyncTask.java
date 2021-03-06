package com.androidclass.servicing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class TwitterAsyncTask extends AsyncTask<String, Void, Void> {
	private static final String TAG = TwitterAsyncTask.class.getSimpleName();
	private Context mContext;
	private static HttpClient mClient;

	public TwitterAsyncTask(Context context) {
		mContext = context;
	}
	@Override
	protected Void doInBackground(String... params) {
		String searchCriteria = params.length > 0 ? URLEncoder.encode(params[0]) : "%23android";
		Log.d(TAG, "Excellent...");
		HttpClient client = getClient();
		HttpGet fetchRequest = new HttpGet(mContext.getString(R.string.twitter_url) + searchCriteria);
		try {
			HttpResponse response = client.execute(fetchRequest);
			String jsonString = convertStreamToString(response.getEntity().getContent());
			ArrayList<TwitterResponse> twitterResponse = TwitterResponse.deserializeJson(jsonString);
			Intent broadcast = new Intent(TwitterFetchService.TWITTER_FETCH_SERVICE_BROADCAST);
			broadcast.putExtra(TwitterFetchService.TWITTER_FETCH_SERVICE_RESPONSE_EXTRA, twitterResponse);
			mContext.sendBroadcast(broadcast);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private HttpClient getClient() {
		if (mClient == null) {
			mClient = new DefaultHttpClient();
		}
		return mClient;
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

}
