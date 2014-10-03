package com.smartroom.model;

import java.util.ArrayList;

import android.content.res.Resources;

import com.smartroom.R;

public class MemberMenu {
	
	private static ArrayList<Item> memberMenu = new ArrayList<Item>();
	
	private static Item newItem = null;
	
	public static ArrayList<Item> getMenuList(Resources resources) {
		
		memberMenu.clear();
		
		newItem = new Item();		
		
		newItem.setMenuTitle("Messages");
		newItem.setCounterVisible(true);
		newItem.setMessageNumber(1);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_message));		
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Search");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_search));
		memberMenu.add(newItem);
		
		
		newItem = new Item();
		newItem.setMenuTitle("Place Advert");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.add_advert));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("My Adverts");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_saved_ad));
		memberMenu.add(newItem);
		
		
		newItem = new Item();
		newItem.setMenuTitle("Saved Adverts");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_saved));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Edit Profile");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_edit_profile));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Settings");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_settings));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Rate App");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_rate));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Help");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_help));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("About Us");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_about));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle("Contact Us");
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_contact));
		memberMenu.add(newItem);
		
		return memberMenu;
	}
}
