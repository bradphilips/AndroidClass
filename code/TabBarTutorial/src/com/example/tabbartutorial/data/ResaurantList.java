package com.example.tabbartutorial.data;

import java.util.ArrayList;

public class ResaurantList {
	private static ResaurantList mSharedResaurantList;
	
	protected ArrayList<Restaurant> mRestaurants;
	private int mEditingPosition;
	
	public static ResaurantList getSharedResaurantList() {
		if(mSharedResaurantList == null) {
			mSharedResaurantList = new ResaurantList();
		}
		
		return mSharedResaurantList;
	}
	
	private ResaurantList() {
		mRestaurants = new ArrayList<Restaurant>();
		mEditingPosition = -1;
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return mRestaurants;
	}
	
	public void setEditing(int position) {
		mEditingPosition = position;
	}
	
	public Boolean isEditing() {
		return mEditingPosition != -1;
	}
	
	public Restaurant getEditingItem() {
		if (mEditingPosition == -1) {
			return null;
		}
		
		return mRestaurants.get(mEditingPosition);
	}
}
