package com.example.tabbartutorial.test;

import android.test.AndroidTestCase;

import com.example.tabbartutorial.data.Restaurant;
import com.example.tabbartutorial.data.RestaurantList;

public class RestaurantListTest extends AndroidTestCase {
	private RestaurantList mList;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mList = new RestaurantList();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mList.getRestaurants().clear();
	}
	
	public Restaurant createRestaurant(String name, String type, String address) {
		return new Restaurant(name, type, address);
	}

	public void createRestaurantList() {
		mList = new RestaurantList();
	}
	
	public void testAddRestaurant() {
		Restaurant restaurant = createRestaurant("Test", "Delivery", "123 DiGiorno");
		mList.getRestaurants().add(restaurant);
		assertEquals("Restaurants was not updated.", mList.getRestaurants().size(), 1);
		assertNull("Editing item was null.", mList.getEditingItem());
		
		Restaurant firstItem = mList.getRestaurants().get(0);
		assertNotNull(firstItem);
		assertEquals(firstItem.getName(), "Test");
		assertEquals(firstItem.getType(), "Delivery");
		assertEquals(firstItem.getAddress(), "123 DiGiorno");
	}
	
	public void testSetEditingItem() {
		Restaurant firstItem = createRestaurant("Test Not", "Pickup", "123 Dominos");
		Restaurant editingItem = createRestaurant("Test Editing", "Pickup", "123 Dominos");
		Restaurant anotherItem = createRestaurant("Test Not", "Delivery", "123 Whatever");
		mList.getRestaurants().add(firstItem);
		mList.getRestaurants().add(editingItem);
		mList.getRestaurants().add(anotherItem);
		
		mList.setEditing(1);
		Restaurant restaurant = mList.getEditingItem();
		assertEquals(editingItem, restaurant);
	}
}
