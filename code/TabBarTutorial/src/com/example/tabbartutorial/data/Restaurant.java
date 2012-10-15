package com.example.tabbartutorial.data;

import java.io.Serializable;

public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mName = "";
	private String mAddress = "";
	private String mType = "";

	public Restaurant() {
		
	}
	
	public Restaurant(String name, String type, String address) {
		mName = name;
		mType = type;
		mAddress = address;
	}

	public String getName() {
		return (mName);
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getAddress() {
		return (mAddress);
	}

	public void setAddress(String address) {
		this.mAddress = address;
	}

	public String getType() {
		return (mType);
	}

	public void setType(String type) {
		this.mType = type;
	}

	public String toString() {
		return getName();
	}
}
