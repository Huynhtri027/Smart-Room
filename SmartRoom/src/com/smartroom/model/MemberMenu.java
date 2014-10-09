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
		
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_message));
		newItem.setCounterVisible(true);
		newItem.setMessageNumber(1);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_message));		
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_search));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_search));
		memberMenu.add(newItem);
		
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_advert));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.add_advert));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_myAdverts));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_saved_ad));
		memberMenu.add(newItem);
		
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_savedAds));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_saved));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_edit));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_edit_profile));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_preference));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_settings));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_rate));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_rate));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_help));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_help));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_about));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_about));
		memberMenu.add(newItem);
		
		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.logged_menu_contact));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_contact));
		memberMenu.add(newItem);
		
		return memberMenu;
	}
}
