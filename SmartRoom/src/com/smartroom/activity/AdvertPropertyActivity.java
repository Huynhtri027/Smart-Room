package com.smartroom.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.smartroom.R;
import com.smartroom.controller.TabPagerAdapter;

public class AdvertPropertyActivity extends FragmentActivity {
	ViewPager Tab;
	TabPagerAdapter TabAdapter;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advert_room_activity);
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		Tab = (ViewPager) findViewById(R.id.pager);
		Tab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar = getActionBar();
				actionBar.setSelectedNavigationItem(position);
			}
		});
		Tab.setAdapter(TabAdapter);
		actionBar = getActionBar();
		// Enable Tabs on Action Bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				Tab.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
		};
		// Add New Tab
		actionBar.addTab(actionBar.newTab().setText("New Advert")
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("About Property")
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Advert Details")
				.setTabListener(tabListener));
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle("Really Exit?")
				.setMessage("Are you sure you want to exit?")
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes, new OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						AdvertPropertyActivity.super.onBackPressed();
					}
				}).create().show();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		return true;// return true so that the menu pop up is opened

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (getParentActivityIntent() == null) {
				onBackPressed();
			} else {
				NavUtils.navigateUpFromSameTask(this);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}