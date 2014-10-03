package com.smartroom.model;

public class UserProfile {

	private String fullName, email, password, imgURL, loggedUsing, id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private boolean tenant, landlord;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getLoggedUsing() {
		return loggedUsing;
	}

	public void setLoggedUsing(String loggedUsing) {
		this.loggedUsing = loggedUsing;
	}

	public boolean isTenant() {
		return tenant;
	}

	public void setTenant(boolean tenant) {
		this.tenant = tenant;
	}

	public boolean isLandlord() {
		return landlord;
	}

	public void setLandlord(boolean landlord) {
		this.landlord = landlord;
	}

}
