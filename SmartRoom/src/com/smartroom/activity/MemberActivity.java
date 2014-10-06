package com.smartroom.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.controller.CheckNewMessageController;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.controller.ItemAdapter;
import com.smartroom.controller.SessionController;
import com.smartroom.model.Item;
import com.smartroom.model.MemberMenu;
import com.smartroom.model.PendingMessageModel;
import com.smartroom.model.UserProfile;
import com.smartroom.service.GPSTrackerService;
import com.smartroom.service.MessageHandlerService;
import com.smartroom.utilities.Utils;
import com.smartroom.view.AboutFragment;
import com.smartroom.view.ContactFragment;
import com.smartroom.view.EditProfileFragment;
import com.smartroom.view.HelpFragment;
import com.smartroom.view.HomeFragment;
import com.smartroom.view.SearchFragment;
import com.smartroom.view.SelectAdvertFragment;
import com.smartroom.view.SettingsFragment;

public class MemberActivity extends FragmentActivity {

	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	ArrayList<Item> menu = null;
	ItemAdapter itemAdapter = null;
	FragmentTransaction transaction = null;
	Fragment newFragment = null;
	GPSTrackerService gps;
	Button logoutSession = null;
	ImageView profilePic = null;
	TextView profileName = null;
	public static int mNotificationCount = 0;

	Fragment fr = null;
	int count = 0;
	boolean verifyLogout = false;
	UserProfile userProfile = null;
	private Intent messageIntent = null;
	private String approveNotification = null;
	public static ArrayList<PendingMessageModel> pendingMessageList = new ArrayList<PendingMessageModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged_user);

		menu = MemberMenu.getMenuList(getResources());

		final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Utils.setCurrentActivity(MemberActivity.this);
		Utils.setMainContext(MemberActivity.this);

		FragmentManagerHelper.setUserStatus(true);

		netReceiver = new MessageCheckReciver();

		IntentFilter filter = new IntentFilter();
		filter.addAction("statusUpdate");
		registerReceiver(netReceiver, filter);

		messageIntent = new Intent(getApplicationContext(),
				MessageHandlerService.class);
		startService(messageIntent);

		setupView();

		itemAdapter = new ItemAdapter(MemberActivity.this, menu);

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

		if (FragmentManagerHelper.getCurrentLoggedUserFragment() == null) {

			newFragment = new SearchFragment();

			FragmentManagerHelper.replaceFragment(newFragment);
			FragmentManagerHelper.setCurrentLoggedUserFragment(newFragment);
			FragmentManagerHelper.setFragmentType("NORMAL");
		} else {

			newFragment = FragmentManagerHelper.getCurrentLoggedUserFragment();

			FragmentManagerHelper.replaceFragment(newFragment);
			FragmentManagerHelper.setCurrentLoggedUserFragment(newFragment);
			FragmentManagerHelper.setFragmentType("NORMAL");

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

				if (menu.getText().toString().equals("Messages")) {

					// MemberActivity.notifyMessage();
					Toast.makeText(Utils.getCurrentActivity(),
							"Functionality Not Yet Done!", Toast.LENGTH_LONG)
							.show();

				} else if (menu.getText().toString().equals("Search")) {

					newFragment = new SearchFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Place Advert")) {

					newFragment = new SelectAdvertFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("My Adverts")) {

					newFragment = new HelpFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				else if (menu.getText().toString().equals("Saved Adverts")) {

					newFragment = new HelpFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				else if (menu.getText().toString().equals("Edit Profile")) {

					newFragment = new EditProfileFragment();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				else if (menu.getText().toString().equals("Settings")) {

					newFragment = new SettingsFragment();
					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Rate App")) {

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

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				else if (menu.getText().toString().equals("About Us")) {

					newFragment = new AboutFragment();
					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				} else if (menu.getText().toString().equals("Contact Us")) {

					newFragment = new ContactFragment();
					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper
							.setCurrentLoggedUserFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");

				}

				drawerLayout.closeDrawers();

			}
		});
	}

	private void setupView() {

		profilePic = (ImageView) findViewById(R.id.loggedUserPic);
		profileName = (TextView) findViewById(R.id.loggedUserFullName);

		if (SessionController.getUserProfile() != null) {
			try {

				userProfile = SessionController.getUserProfile();

				profileName.setText("" + userProfile.getFullName());

				if ((userProfile.getImgURL() == null)
						|| (userProfile.getImgURL().equals("NO_URL"))) {
					profilePic.setImageDrawable(getResources().getDrawable(
							R.drawable.no_pic));
				} else if (userProfile.getLoggedUsing().equals("FACEBOOK_AUTH")) {
					String id = userProfile.getId();
					try {
						Bitmap bitmap = Utils.getFacebookProfilePicture(id);
						profilePic.setImageBitmap(bitmap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// URL img_value = null;
					// try {
					//
					// img_value = new URL(userProfile.getImgURL()
					// + "?type=large");
					// Bitmap mIcon1 = BitmapFactory.decodeStream(img_value
					// .openConnection().getInputStream());
					// profilePic.setImageBitmap(mIcon1);
					// } catch (Exception e) {
					// Log.e("Pic Url: ", "" + userProfile.getImgURL() + " "
					// + "ERROR: " + e.getMessage() + " URL == "
					// + img_value.toString());
					// profilePic.setImageDrawable(getResources().getDrawable(
					// R.drawable.no_pic));
					// }

				}

			} catch (Exception e) {
				Log.e("ERROR", e.getMessage());
			}

		}

		logoutSession = (Button) findViewById(R.id.logoutSession);
		logoutSession.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				verifyLogout = false;

				AlertDialog dialog = new AlertDialog.Builder(
						MemberActivity.this).create();
				dialog.setTitle("Logout Confirmation");
				dialog.setMessage("Are you sure to Logout?");
				dialog.setCancelable(true);
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int buttonId) {
								verifyLogout = false;
								Toast.makeText(MemberActivity.this,
										"" + verifyLogout, Toast.LENGTH_LONG)
										.show();
							}
						});
				dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int buttonId) {
								verifyLogout = true;

								FragmentManagerHelper
										.setCurrentLoggedUserFragment(null);
								FragmentManagerHelper.setUserStatus(false);

								newFragment = new HomeFragment();
								FragmentManagerHelper
										.setCurrentFragment(newFragment);

								SessionController.setUserProfile(null);
								SessionController
										.sessionStop(MemberActivity.this);

								try {
									SessionController.getAdapter().signOut(
											MemberActivity.this,
											SessionController
													.getSocialProvider()
													.toString());
								} catch (Exception e) {
									Log.e("Pic Url: ", "" + e.getMessage());
								}
								try {
									unregisterReceiver(netReceiver);
								} catch (Exception e) {
								}
								stopService(messageIntent);
								Intent intent = new Intent(MemberActivity.this,
										MainActivity.class);
								intent.putExtra("logout", true);
								startActivity(intent);
								finish();
							}
						});

				dialog.setIcon(android.R.drawable.ic_dialog_alert);
				dialog.show();

			}
		});
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

	public static void notifyMessage(final PendingMessageModel response) {

		final Intent mNotificationIntent;
		final PendingIntent mContentIntent;
		final CharSequence tickerText = "Smart Room Notification";
		final CharSequence contentText = "New Message Received";
		final int MY_NOTIFICATION_ID = 1;

		// Notification Sound and Vibration on Arrival
		final Uri soundURI = Uri.parse("android.resource://com.smartroom/raw/"
				+ R.raw.notification_sound);
		final long[] mVibratePattern = { 0, 200, 200, 300 };

		RemoteViews mContentView = new RemoteViews("com.smartroom",
				R.layout.custom_notification);

		pendingMessageList.add(response);

		mNotificationIntent = new Intent(Utils.getCurrentActivity(),
				ViewNotificationActivity.class);

		mNotificationIntent.putExtra("key", pendingMessageList);

		mContentIntent = PendingIntent.getActivity(Utils.getCurrentActivity(),
				0, mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

		mContentView.setTextViewText(R.id.text, contentText + " ("
				+ ++mNotificationCount + ")");

		Notification.Builder notificationBuilder = new Notification.Builder(
				Utils.getCurrentActivity()).setTicker(tickerText)
				.setSmallIcon(R.drawable.ic_launcher).setAutoCancel(true)
				.setContentIntent(mContentIntent).setSound(soundURI)
				.setVibrate(mVibratePattern).setContent(mContentView);

		// Pass the Notification to the NotificationManager:
		NotificationManager mNotificationManager = (NotificationManager) Utils
				.getCurrentActivity().getSystemService(
						Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(MY_NOTIFICATION_ID,
				notificationBuilder.build());
	}

	class MessageCheckReciver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			try {

				PendingMessageModel response = (PendingMessageModel) arg1
						.getSerializableExtra("messageStatus");

				if (response.isNotification()) {
					
					approveNotification = null;
					
					approveNotification = CheckNewMessageController
							.approveNotificationMessage(response);

					if (approveNotification.equals("1")) {
						displayDialog(response);
					}

				}
			}

			catch (Exception e) {
				Log.e("error", e.toString());
			}

		}
	}

	public void displayDialog(final PendingMessageModel response) {
		MemberActivity.notifyMessage(response);
	}

	MessageCheckReciver netReceiver;
}
