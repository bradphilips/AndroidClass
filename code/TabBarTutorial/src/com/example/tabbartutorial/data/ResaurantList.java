package com.example.tabbartutorial.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import com.example.tabbartutorial.LunchListApplication;

import android.content.Context;

public class ResaurantList {
	private static ResaurantList mSharedResaurantList;
	
	protected ArrayList<Restaurant> mRestaurants;
	private int mEditingPosition;
	
	public static ResaurantList getSharedResaurantList() {
		if(mSharedResaurantList == null) {
			mSharedResaurantList = new ResaurantList();
			try {
				Context context = LunchListApplication.getApplication();
				String fileName = context.getApplicationContext().getPackageName() + "RestaurantList";
				FileInputStream stream = context.openFileInput(fileName);
				ObjectInputStream serializer = new ObjectInputStream(stream);
				
				@SuppressWarnings("unchecked")
				ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) serializer.readObject();
				mSharedResaurantList.setRestaurants(restaurants);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return mSharedResaurantList;
	}
	
	public void saveRestaurants() {
		Context context = LunchListApplication.getApplication();
		String fileName = context.getApplicationContext().getPackageName() + "RestaurantList";
		
		try {
			FileOutputStream stream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream output = new ObjectOutputStream(stream);
			output.writeObject(mRestaurants);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ResaurantList() {
		mRestaurants = new ArrayList<Restaurant>();
		mEditingPosition = -1;
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return mRestaurants;
	}
	
	protected void setRestaurants(ArrayList<Restaurant> restaurants) {
		mRestaurants = restaurants;
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
