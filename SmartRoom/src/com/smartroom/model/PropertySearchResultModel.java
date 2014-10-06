package com.smartroom.model;

import android.graphics.Bitmap;

public class PropertySearchResultModel {

	private String searchPropertyType = "", searchAddress = "", searchPrice = "", searchTitle = "",
			searchRefNumber = "";
	private Bitmap searchIcon;

	public String getSearchPropertyType() {
		return searchPropertyType;
	}

	public void setSearchPropertyType(String searchPropertyType) {
		this.searchPropertyType = searchPropertyType;
	}

	public String getSearchAddress() {
		return searchAddress;
	}

	public void setSearchAddress(String searchAddress) {
		this.searchAddress = searchAddress;
	}

	public String getSearchPrice() {
		return searchPrice;
	}

	public void setSearchPrice(String searchPrice) {
		this.searchPrice = searchPrice;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchRefNumber() {
		return searchRefNumber;
	}

	public void setSearchRefNumber(String searchRefNumber) {
		this.searchRefNumber = searchRefNumber;
	}

	public Bitmap getSearchIcon() {
		return searchIcon;
	}

	public void setSearchIcon(Bitmap searchIcon) {
		this.searchIcon = searchIcon;
	}

}
