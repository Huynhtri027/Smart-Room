package com.smartroom.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Utils {

	private static InputMethodManager imm = null;
	public static Context mainContext = null;
	public static String loginUrl = "http://10.0.16.26/smartroom/verify_user.php";
	public static String registerUrl = "http://10.0.16.26/smartroom/create_user.php";
	public static Activity currentActivity = null;

	public static Activity getCurrentActivity() {
		return currentActivity;
	}

	public static void setCurrentActivity(Activity currentActivity) {
		Utils.currentActivity = currentActivity;
	}

	public static Context getMainContext() {
		return mainContext;
	}

	public static void setMainContext(Context mainContext) {
		Utils.mainContext = mainContext;
	}

	public static void hideKeyboard(Context context, EditText txt) {
		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);
	}

	public static void showKeyboard(Context context, EditText txt) {
		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInputFromWindow(txt.getApplicationWindowToken(),
				InputMethodManager.SHOW_FORCED, 0);
		txt.requestFocus();
	}

	public static void clearCache(Context context) {
		try {
			File dir = context.getCacheDir();
			if (dir != null && dir.isDirectory()) {
				deleteDir(dir);
			}
		} catch (Exception e) {
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

	public static Bitmap getFacebookProfilePicture(String userID) {

		Bitmap bitmap = null;

		try {
			URL imageURL = new URL("https://graph.facebook.com/" + userID
					+ "/picture?type=large");
			bitmap = BitmapFactory.decodeStream(imageURL.openConnection()
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;

	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static ArrayList<String> getCountryList() {
		Locale[] locale = Locale.getAvailableLocales();
		ArrayList<String> countries = new ArrayList<String>();
		String country;
		for (Locale loc : locale) {
			country = loc.getDisplayCountry();
			if (country.length() > 0 && !countries.contains(country)) {
				countries.add(country);
			}
		}
		Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
		return countries;
	}	
}
