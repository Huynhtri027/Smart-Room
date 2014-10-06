package com.smartroom.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Contacts.People;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.smartroom.R;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.controller.ItemAdapter;
import com.smartroom.model.GuestMenu;
import com.smartroom.model.Item;
import com.smartroom.service.GPSTrackerService;
import com.smartroom.utilities.Utils;
import com.smartroom.view.AboutFragment;
import com.smartroom.view.AskToLoginFragment;
import com.smartroom.view.ContactFragment;
import com.smartroom.view.HelpFragment;
import com.smartroom.view.HomeFragment;
import com.smartroom.view.PreferenceFragment;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	ArrayList<Item> menu = null;
	ItemAdapter itemAdapter = null;
	FragmentTransaction transaction = null;
	Fragment newFragment = null;
	GPSTrackerService gps;

	Fragment fr = null;
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Utils.setCurrentActivity(MainActivity.this);
		Utils.setMainContext(MainActivity.this);

		menu = GuestMenu.getMenuList(getResources());
		final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		itemAdapter = new ItemAdapter(MainActivity.this, menu);

		drawerListView = (ListView) findViewById(R.id.left_drawer);

		drawerListView.setAdapter(itemAdapter);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				try {
					Utils.hideKeyboard(getApplicationContext(),
							HomeFragment.searchLocation);
				} catch (Exception e) {
				}
				invalidateOptionsMenu();
			}
		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		transaction = getFragmentManager().beginTransaction();
		FragmentManagerHelper.setFragmentTransaction(transaction);

		Fragment newFragment = null;

		if (FragmentManagerHelper.getCurrentFragment() == null) {
			newFragment = new HomeFragment();
			changeFragment(newFragment);
			FragmentManagerHelper.setCurrentFragment(newFragment);

		} else {
			changeFragment(FragmentManagerHelper.getCurrentFragment());
			FragmentManagerHelper.setCurrentFragment(FragmentManagerHelper
					.getCurrentFragment());
		}

		drawerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				TextView menu = (TextView) view.findViewById(R.id.menuTxt);

				getActionBar().setTitle(menu.getText().toString());

				transaction = getFragmentManager().beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				Fragment newFragment = null;

				if (menu.getText().toString().equals("Home")) {
					newFragment = new HomeFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("About Us")) {

					newFragment = new AboutFragment();
					changeFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Contact Us")) {

					newFragment = new ContactFragment();
					changeFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Share App")) {

					// Intent intent = new Intent(Intent.ACTION_PICK,
					// ContactsContract.Contacts.CONTENT_URI);
					// startActivityForResult(intent, 0);

					Intent sharingIntent = new Intent(
							android.content.Intent.ACTION_SEND);
					sharingIntent.setType("text/plain");
					String shareBody = "Hey I am using Smart Room Search. Download it from http://www.google.com!";
					sharingIntent.putExtra(
							android.content.Intent.EXTRA_SUBJECT, "Smart Room");
					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							shareBody);
					startActivity(Intent.createChooser(sharingIntent,
							"Share via"));

				}

				else if (menu.getText().toString().equals("Rate App")) {

					final String my_package_name = "com.smartroom";
					String url = "";

					try {
						getPackageManager().getPackageInfo(
								"com.android.vending", 0);

						url = "market://details?id=" + my_package_name;
					} catch (final Exception e) {
						url = "https://play.google.com/store/apps/details?id="
								+ my_package_name;
					}
					final Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(url));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					startActivity(intent);

				}

				else if (menu.getText().toString().equals("Help")) {

					newFragment = new HelpFragment();
					changeFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				else if (menu.getText().toString().equals("Preferences")) {

					newFragment = new PreferenceFragment();
					changeFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Place Advert")) {

					newFragment = new AskToLoginFragment();
					changeFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");
				} else {
					newFragment = new HomeFragment();
					changeFragment(newFragment);
				}

				drawerLayout.closeDrawers();

			}
		});
	}

	@SuppressWarnings("unused")
	private void addFragment(Fragment targetFragment) {

		getFragmentManager().beginTransaction()
				.add(R.id.content_frame, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	private void changeFragment(Fragment targetFragment) {
		getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			Uri contactData = data.getData();
			Cursor c = managedQuery(contactData, null, null, null, null);
			if (c.moveToFirst()) {
				String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
				// TODO Whatever you want to do with the selected contact name.
			}
		}

	}
}