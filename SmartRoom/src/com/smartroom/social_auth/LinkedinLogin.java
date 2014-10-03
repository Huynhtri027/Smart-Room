package com.smartroom.social_auth;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.smartroom.controller.SessionController;
import com.smartroom.model.UserProfile;

public class LinkedinLogin extends Activity {

	SocialAuthAdapter adapter;
	private UserProfile userProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new SocialAuthAdapter(new ResponseListener());
		adapter.authorize(LinkedinLogin.this, Provider.LINKEDIN);
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
			// TODO Auto-generated method stub

		}

		@Override
		public void onExecute(String arg0, Profile profile) {
			// TODO Auto-generated method stub

			Toast.makeText(
					getApplicationContext(),
					"Successfully Logging with Facebook as : "
							+ profile.getFirstName() + " "
							+ profile.getLastName(), Toast.LENGTH_SHORT).show();

			userProfile = new UserProfile();
			userProfile.setEmail(profile.getEmail());
			userProfile.setFullName(profile.getFullName());
			userProfile.setImgURL(profile.getProfileImageURL());
			userProfile.setLoggedUsing("AUTH");
			userProfile.setPassword("");

			SessionController.setUserProfile(userProfile);
			SessionController.sessionStart(LinkedinLogin.this);

			finish();

		}
	}

}
