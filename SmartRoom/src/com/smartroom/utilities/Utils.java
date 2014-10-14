package com.smartroom.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.smartroom.R;

/**
 * Utils.java
 * 
 * Purpose: To create static utility methods for different class functionality
 * 
 * @author Filippo Engidashet
 * @version 1.0
 * @since 2014-10-14
 */

public class Utils {

	/**
	 * Represents a Keyboard Manager.
	 */
	private static InputMethodManager imm = null;
	/**
	 * Represents a Context.
	 */
	public static Context mainContext = null;

	/**
	 * Represents a base url for web services.
	 */

	public static String BASE_URL = "http://10.0.16.26/smartroom/";

	/**
	 * Represents a various Service URLs used for web services.
	 */
	public static String loginUrl = BASE_URL + "verify_user.php";
	public static String registerUrl = BASE_URL + "create_user.php";
	public static String saveAdvertUrl = BASE_URL + "advert_house.php";
	public static String searchPropertytUrl = BASE_URL + "search_property.php";
	public static String sendMessagetUrl = BASE_URL + "save_advert_message.php";
	public static String checkMessagetUrl = BASE_URL + "check_message.php";
	public static String approveMessagetUrl = BASE_URL
			+ "approve_message_notification.php";
	public static String getPropertyByIDtUrl = BASE_URL
			+ "get_house_by_ref_id.php";
	public static String getMessagesUrl = BASE_URL + "get_messages.php";

	public static String picUrl = BASE_URL + "images/";

	public static String testUrl = BASE_URL + "test.php";

	/**
	 * Represents a Temporary Current Activity.
	 */

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

	/**
	 * This method takes two parameters and hides the onScreen Keyboard
	 * 
	 * @param context
	 *            set the Application Context to create a InputMethodManager
	 *            instance
	 * @param txt
	 *            the focused EditText Field
	 * @return void
	 * @see InputMethodManager
	 */

	public static void hideKeyboard(Context context, EditText txt) {
		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);
	}

	/**
	 * This method takes two parameters and displays the onScreen Keyboard
	 * 
	 * @param context
	 *            set the Application Context to create a InputMethodManager
	 *            instance
	 * @param txt
	 *            the focused EditText Field
	 * @return void
	 * @see InputMethodManager
	 */
	public static void showKeyboard(Context context, EditText txt) {
		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInputFromWindow(txt.getApplicationWindowToken(),
				InputMethodManager.SHOW_FORCED, 0);
		txt.requestFocus();
	}

	/**
	 * This method takes one parameters and deletes a Cache Directory
	 * 
	 * @param context
	 *            set the Application Context to create a File directory
	 * @return void
	 * @see File
	 */
	public static void clearCache(Context context) {
		try {
			File dir = context.getCacheDir();
			if (dir != null && dir.isDirectory()) {
				deleteDir(dir);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * This method takes one parameters and deletes a Cache Directory
	 * 
	 * @param dir
	 *            get a File from a specified directory
	 * @return boolean
	 * @see File
	 */
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

	/**
	 * This method takes one parameters and converts a Bitmap into
	 * BitmapDescriptor
	 * 
	 * @param context
	 *            set the Application Context to get a Content Resolver
	 * @return BitmapDescriptor
	 * @see Bitmap
	 */
	public static BitmapDescriptor getBitmapDescriptor(Context context) {

		final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 8;
		BitmapDescriptor bd = null;

		Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(
				context.getContentResolver(), R.drawable.ic_map_marker,
				MediaStore.Images.Thumbnails.MINI_KIND, bmOptions);
		if (bm != null) {
			bd = BitmapDescriptorFactory.fromBitmap(bm);
			if (bd != null) {

			}
		}
		return bd;
	}

	/**
	 * This method takes one parameters and returns a Bitmap image from a URL
	 * based on UserID
	 * 
	 * @param userID
	 *            set the Facebook user ID
	 * @return Bitmap
	 * @see URL
	 */
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

	/**
	 * This method takes one parameters and returns true if network is available
	 * 
	 * @param context
	 *            set the ConnectivityManager application context
	 * @return boolean
	 * @see NetworkInfo
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	/**
	 * This method takes no parameters and returns an ArrayList of Country List
	 * from Locale
	 * 
	 * @return ArrayList<String>
	 * 
	 * @see Locale
	 * @see ArrayList
	 * @see Collections
	 */
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

	/**
	 * This method takes one parameters and returns byte of an Image
	 * 
	 * @param bitmap
	 *            set the Bitmap to be converted into byte array
	 * @return byte[]
	 * 
	 * @see ByteArrayOutputStream
	 * @see CompressFormat
	 */
	public static byte[] getBytes(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		return stream.toByteArray();
	}

	/**
	 * This method takes one parameters and returns Bitmap Image
	 * 
	 * @param imageString
	 *            set the string to be converted into Bitmap
	 * @return Bitmap
	 * 
	 * @see BitmapFactory
	 * @see Base64
	 */
	public static Bitmap stringToBitmap(String imageString) {
		byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
	}

	/**
	 * This method takes one parameters and returns Bitmap String
	 * 
	 * @param imageBitmap
	 *            set the bitmap to be converted into string
	 * @return String
	 * 
	 * @see ByteArrayOutputStream
	 * @see Bitmap
	 * @see Base64
	 */

	public static String bitmapToString(Bitmap imageBitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);

		byte[] byte_arr = stream.toByteArray();

		String imageString = Base64.encodeToString(byte_arr, Base64.DEFAULT);
		return imageString;
	}

	/**
	 * This method takes one parameters and returns Bitmap
	 * 
	 * @param image
	 *            set the byte array image to be converted into Bitmap
	 * @return Bitmap
	 * 
	 * @see BitmapFactory
	 */
	public static Bitmap getPhoto(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

}
