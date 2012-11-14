package com.androidclass.servicing;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TwitterResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("title") private String mTitle;
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public String toString() {
		return mTitle;
	}
	
	public static ArrayList<TwitterResponse> deserializeJson(String json) {
		Gson gson = new Gson();
		TwitterContainer container = gson.fromJson(json, TwitterContainer.class);
		return container.getEvents();
	}
	
	public class TwitterContainer implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@SerializedName("results") private ArrayList<TwitterResponse> mEvents;

		public ArrayList<TwitterResponse> getEvents() {
			return mEvents;
		}

		public void setEvents(ArrayList<TwitterResponse> mEvents) {
			this.mEvents = mEvents;
		}
	}
}

