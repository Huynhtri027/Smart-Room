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

public class GooglePlus extends Activity {

	public static SocialAuthAdapter adapter;
	private UserProfile userProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {

			Utils.setCurrentActivity(GooglePlus.this);
			Utils.setMainContext(GooglePlus.this);

			adapter = new SocialAuthAdapter(new ResponseListener());
			adapter.addCallBack(Provider.GOOGLEPLUS,
					"http://theappexperts.co.uk/oauth2callback");
			adapter.authorize(
					this,
					org.brickred.socialauth.android.SocialAuthAdapter.Provider.GOOGLEPLUS);

			SessionController.setAdapter(adapter);
			SessionController.setSocialProvider(Provider.GOOGLEPLUS);

		} catch (Exception e) {

			Toast.makeText(getApplicationContext(),
					"Exception " + e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private final class ResponseListener implements DialogListener {
		public void onComplete(Bundle values) {
			adapter.getUserProfileAsync(new ProfileDataListener());

		}

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "linkedin back",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "linkedin cancel",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "linked in error",
					Toast.LENGTH_SHORT).show();

		}

	}

	private final class ProfileDataListener implements
			SocialAuthListener<Profile> {

		@Override
		public void onError(SocialAuthError arg0) {

		}

		@Override
		public void onExecute(String arg0, Profile profile) {

			userProfile = new UserProfile();

			userProfile.setId(profile.getValidatedId());
			userProfile.setEmail(profile.getEmail().toString());
			userProfile.setFullName(profile.getFullName().toString());
			userProfile.setImgURL(profile.getProfileImageURL().toString());
			userProfile.setLoggedUsing("GOOGLEPLUS_AUTH");
			userProfile.setPassword("");

			SessionController.setUserProfile(userProfile);
			SessionController.sessionStart(GooglePlus.this);
			SessionController.setSocialProfile(profile);

			FragmentManagerHelper.setCurrentFragment(null);
			FragmentManagerHelper.setUserStatus(true);

			try {
				Intent loginWithFacebook = new Intent(GooglePlus.this,
						MemberActivity.class);

				startActivity(loginWithFacebook);
				finish();

			} catch (Exception e) {
				Log.e("Login Error: ", e.getMessage());
			}

		}

	}

}
