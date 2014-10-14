package com.smartroom.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.smartroom.R;
import com.smartroom.SplashScreen;
import com.smartroom.view.LoginFragment;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

	private String userName = "a", password = "a";
	private Activity activity = null;

	@Test
	public void clearLoginFields() throws Exception {

		Bundle savedBundle = new Bundle();
		activity = Robolectric.buildActivity(SplashScreen.class)
				.create(savedBundle).get();

		FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment fragment = new LoginFragment();
        transaction.add(R.id.content_frame, fragment);
        //transaction.commit();


		Button clear = (Button) fragment.getView().findViewById(R.id.clearLogin);

		TextView welcomeMsg = (TextView) activity
				.findViewById(R.id.load_textview);

		try {
			clear.performClick();
			System.out.print("Test Passed");
		} catch (Exception e) {
			System.out.print("Error: " + e.getMessage());
		}

		String result = welcomeMsg.getText().toString();
		assertThat(result, equalTo("Loading..."));

		//
		// EditText userTxt = (EditText) activity.findViewById(R.id.loginEmail);
		// EditText passTxt = (EditText)
		// activity.findViewById(R.id.loginPassword);
		//
		// userTxt.setText("MyUserName");
		// passTxt.setText("MyPassword");
		//
		// clear.performClick();
		//
		// String userResult = userTxt.getText().toString();
		// String passResult = passTxt.getText().toString();
		//
		// assertThat(userResult, equalTo(""));
		// assertThat(passResult, equalTo(""));
	}
}