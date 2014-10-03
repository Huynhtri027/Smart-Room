package com.smartroom.model;

import android.graphics.drawable.Drawable;

public class Item {

	String menuTitle;
	int messageNumber = 0;
	private boolean counterVisible = false;
	Drawable icon;

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public boolean isCounterVisible() {
		return counterVisible;
	}

	public void setCounterVisible(boolean counterVisible) {
		this.counterVisible = counterVisible;
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

}
