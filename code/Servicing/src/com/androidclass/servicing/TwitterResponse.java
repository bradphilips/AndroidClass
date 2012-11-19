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
	@SerializedName("text") private String mText;
	public String getText() {
		return this.mText;
	}
	public void setText(String mText) {
		this.mText = mText;
	}
	
	public String toString() {
		return mText;
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

