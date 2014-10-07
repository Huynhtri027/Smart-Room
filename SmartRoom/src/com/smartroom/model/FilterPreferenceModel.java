package com.smartroom.model;

public class FilterPreferenceModel {

	private String minPrice = "", maxPrice = "", minBed = "", maxBed = "",
			sellerType = "", propertyType = "", searchDistance = "",
			addedDate = "", sortBy = "", priceFrequency = "";

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinBed() {
		return minBed;
	}

	public void setMinBed(String minBed) {
		this.minBed = minBed;
	}

	public String getMaxBed() {
		return maxBed;
	}

	public void setMaxBed(String maxBed) {
		this.maxBed = maxBed;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getSearchDistance() {
		return searchDistance;
	}

	public void setSearchDistance(String searchDistance) {
		this.searchDistance = searchDistance;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getPriceFrequency() {
		return priceFrequency;
	}

	public void setPriceFrequency(String priceFrequency) {
		this.priceFrequency = priceFrequency;
	}

}
