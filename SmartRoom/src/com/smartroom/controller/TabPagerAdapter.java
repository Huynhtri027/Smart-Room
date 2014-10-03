package com.smartroom.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.smartroom.view.PropertyAdvertStep1Fragment;
import com.smartroom.view.PropertyAdvertStep2Fragment;
import com.smartroom.view.PropertyAdvertStep3Fragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
	public TabPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:

			return new PropertyAdvertStep1Fragment();
		case 1:

			return new PropertyAdvertStep2Fragment();
		case 2:

			return new PropertyAdvertStep3Fragment();

		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3; // No of Tabs
	}
}