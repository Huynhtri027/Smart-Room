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

		newItem.setMenuTitle(resources.getString(R.string.menu_home));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_home));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_advert));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.add_advert));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_preference));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_preference));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_share));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_share));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_rate));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_rate));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_help));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_help));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_about));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_about));
		guestMenu.add(newItem);

		newItem = new Item();
		newItem.setMenuTitle(resources.getString(R.string.menu_contact));
		newItem.setCounterVisible(false);
		newItem.setIcon(resources.getDrawable(R.drawable.ic_contact));
		guestMenu.add(newItem);

		return guestMenu;
	}
}
