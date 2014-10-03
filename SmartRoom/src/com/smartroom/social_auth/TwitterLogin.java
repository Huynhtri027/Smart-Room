package com.smartroom.social_auth;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.smartroom.activity.MemberActivity;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.controller.SessionController;
import com.smartroom.model.UserProfile;
import com.smartroom.utilities.Utils;

public class TwitterLogin extends Activity {

	public static SocialAuthAdapter adapter;
	private UserProfile userProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Utils.setCurrentActivity(TwitterLogin.this);
		Utils.setMainContext(TwitterLogin.this);

		adapter = new SocialAuthAdapter(new ResponseListener());
		adapter.authorize(TwitterLogin.this, Provider.TWITTER);

		SessionController.setAdapter(adapter);
		SessionController.setSocialProvider(Provider.TWITTER);
	}

	private final class ResponseListener implements DialogListener {
		public void onComplete(Bundle values) {
			adapter.getUserProfileAsync(new ProfileDataListener());

		}

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Twitter back",
					Toast.LENGTH_SHORT).show();
			finish();

		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Twitter cancel",
					Toast.LENGTH_SHORT).show();
			finish();

		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			System.out.println("Twitter error" + arg0.getMessage().toString());
			// Toast.makeText(Utils.getCurrentActivity(),
			// "Twitter error"+arg0.getMessage().toString(),
			// Toast.LENGTH_SHORT).show();
			finish();

		}

	}

	private final class ProfileDataListener implements
			SocialAuthListener<Profile> {

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onExecute(String arg0, Profile profile) {
			// TODO Auto-generated method stub

			userProfile = new UserProfile();

			userProfile.setId(profile.getValidatedId());
			userProfile.setEmail(profile.getEmail().toString());
			userProfile.setFullName(profile.getFullName().toString());
			userProfile.setImgURL(profile.getProfileImageURL().toString());
			userProfile.setLoggedUsing("TWITTER_AUTH");
			userProfile.setPassword("");

			SessionController.setUserProfile(userProfile);
			SessionController.sessionStart(TwitterLogin.this);
			SessionController.setSocialProfile(profile);

			FragmentManagerHelper.setCurrentFragment(null);
			FragmentManagerHelper.setUserStatus(true);

			try {
				Intent loginWithFacebook = new Intent(TwitterLogin.this,
						MemberActivity.class);

				startActivity(loginWithFacebook);
				finish();

			} catch (Exception e) {
				Log.e("Login Error: ", e.getMessage());
			}

		}

	}

}
