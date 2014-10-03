package com.smartroom.controller;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.smartroom.model.UserProfile;

public class SessionController {

	private static UserProfile userProfile = null;
	public static SharedPreferences app_preferences = null;
	private static Profile socialProfile = null;
	public static SocialAuthAdapter adapter = null;
	public static Provider socialProvider = null;

	

	public static Provider getSocialProvider() {
		return socialProvider;
	}

	public static void setSocialProvider(Provider socialProvider) {
		SessionController.socialProvider = socialProvider;
	}

	public static SocialAuthAdapter getAdapter() {
		return adapter;
	}

	public static void setAdapter(SocialAuthAdapter adapter) {
		SessionController.adapter = adapter;
	}

	public static Profile getSocialProfile() {
		return socialProfile;
	}

	public static void setSocialProfile(Profile socialProfile) {
		SessionController.socialProfile = socialProfile;
	}

	public static UserProfile getUserProfile() {
		return userProfile;
	}

	public static void setUserProfile(UserProfile userProfile) {
		SessionController.userProfile = userProfile;
	}

	public static void sessionStart(Context context) {
		app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putBoolean("Online Status", true);
		editor.commit();
	}

	public static void sessionStop(Context context) {
		app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putBoolean("Online Status", false);
		editor.commit();
	}

}
