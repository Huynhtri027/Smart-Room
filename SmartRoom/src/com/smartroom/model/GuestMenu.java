package com.smartroom.model;

import java.util.ArrayList;

import android.content.res.Resources;

import com.smartroom.R;

public class GuestMenu {
	
	private static ArrayList<Item> guestMenu = new ArrayList<Item>();
	
	private static Item newItem = null;
	
	public static ArrayList<Item> getMenuList(Resources resources) {
		
		guestMenu.clear();
		
		newItem = new Item();		
		
		newItem.setMenuTitle("Home");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_home));		
		guestMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Place Advert");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.add_advert));
		guestMenu.add(newItem);
		
		
		newItem = new Item();
		newItem.setMenuTitle("Preferences");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_preference));
		guestMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Rate App");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_rate));
		guestMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Help");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_help));
		guestMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("About Us");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_about));
		guestMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Contact Us");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_contact));
		guestMenu.add(newItem);
		
		return guestMenu;
	}
}
