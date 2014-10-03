package com.smartroom.view;

import org.brickred.socialauth.android.SocialAuthAdapter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.controller.AuthenticationController;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.social_auth.FacebookLogin;
import com.smartroom.social_auth.TwitterLogin;
import com.smartroom.utilities.Utils;

public class LoginFragment extends Fragment {
	private View parentView;
	SocialAuthAdapter adapter = null;

	EditText emailTxt, passTxt;
	Button clearBtn, loginBtn, registerBtn, loginWithFB, loginWithGoogle,
			loginWithTwitter, loginWithLinkedin;
	Fragment newFragment = null;
	FragmentTransaction transaction = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater
				.inflate(R.layout.fragment_login, container, false);
		setUpViews();

		loginAndRegisterEvents();
		socialAppLogin();
		return parentView;
	}

	private void socialAppLogin() {

		loginWithFB = (Button) parentView.findViewById(R.id.facebookLogin);
		loginWithFB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				boolean netCheck = Utils.isNetworkAvailable(getActivity());
				if(netCheck) {
					Intent intent = new Intent(getActivity()
							.getApplicationContext(), FacebookLogin.class);
					startActivity(intent);
				}
				else {
					transaction = getFragmentManager().beginTransaction();
					FragmentManagerHelper.setFragmentTransaction(transaction);

					newFragment = new NoNetwork();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");
				}			
				
			}
		});

		loginWithGoogle = (Button) parentView.findViewById(R.id.googleLogin);
		loginWithGoogle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean netCheck = Utils.isNetworkAvailable(getActivity());
				if(netCheck) {
					
					Toast.makeText(getActivity().getApplicationContext(),
							"Functionality, Partially Completed",
							Toast.LENGTH_LONG).show();
					return;
					
//					Intent intent = new Intent(getActivity()
//							.getApplicationContext(), GooglePlus.class);
//					startActivity(intent);
					
				}
				else {
					transaction = getFragmentManager().beginTransaction();
					FragmentManagerHelper.setFragmentTransaction(transaction);

					newFragment = new NoNetwork();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");
				}
			}
		});

		loginWithTwitter = (Button) parentView.findViewById(R.id.twitterLogin);
		loginWithTwitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				boolean netCheck = Utils.isNetworkAvailable(getActivity());
				if(netCheck) {
					Intent intent = new Intent(getActivity()
							.getApplicationContext(), TwitterLogin.class);
					startActivity(intent);
				}
				else {
					transaction = getFragmentManager().beginTransaction();
					FragmentManagerHelper.setFragmentTransaction(transaction);

					newFragment = new NoNetwork();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");
				}
			}
		});

		loginWithLinkedin = (Button) parentView
				.findViewById(R.id.linkedinLogin);
		loginWithLinkedin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				boolean netCheck = Utils.isNetworkAvailable(getActivity());
				if(netCheck) {
					Toast.makeText(getActivity()
							.getApplicationContext(), "Functionality, Partially Completed", Toast.LENGTH_LONG).show(); 
					return;
					// Intent intent = new Intent(getActivity()
					// .getApplicationContext(), LinkedinLogin.class);
					// startActivity(intent);
				}
				else {
					transaction = getFragmentManager().beginTransaction();
					FragmentManagerHelper.setFragmentTransaction(transaction);

					newFragment = new NoNetwork();

					FragmentManagerHelper.replaceFragment(newFragment);
					FragmentManagerHelper.setCurrentFragment(newFragment);
					FragmentManagerHelper.setFragmentType("NORMAL");
				}
			}
		});

	}

	private void setUpViews() {
		emailTxt = (EditText) parentView.findViewById(R.id.loginEmail);
		passTxt = (EditText) parentView.findViewById(R.id.loginPassword);

		clearBtn = (Button) parentView.findViewById(R.id.clearLogin);
		loginBtn = (Button) parentView.findViewById(R.id.loginBtn);
		registerBtn = (Button) parentView
				.findViewById(R.id.createAccountButton);
		loginWithFB = (Button) parentView.findViewById(R.id.facebookLogin);
		loginWithGoogle = (Button) parentView.findViewById(R.id.googleLogin);
		loginWithTwitter = (Button) parentView.findViewById(R.id.twitterLogin);
		loginWithLinkedin = (Button) parentView
				.findViewById(R.id.linkedinLogin);
	}

	private void loginAndRegisterEvents() {
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkFields()) {

					final String email = emailTxt.getText().toString();
					final String password = passTxt.getText().toString();

					boolean netCheck = Utils.isNetworkAvailable(getActivity());
					if(netCheck) {
						AuthenticationController.checkLogin(email, password);
					}
					else {
						transaction = getFragmentManager().beginTransaction();
						FragmentManagerHelper.setFragmentTransaction(transaction);

						newFragment = new NoNetwork();

						FragmentManagerHelper.replaceFragment(newFragment);
						FragmentManagerHelper.setCurrentFragment(newFragment);
						FragmentManagerHelper.setFragmentType("NORMAL");
					}
				}
			}
		});

		clearBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				emailTxt.setText("");
				passTxt.setText("");
			}
		});
		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				transaction = getFragmentManager().beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				newFragment = new RegisterFragment();

				FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});
	}

	private boolean checkFields() {
		boolean registrationCheck = true;
		if (emailTxt.getText().length() == 0) {
			emailTxt.setError("Please Specify a Valid Email Here");
			registrationCheck = false;
		}
		if (passTxt.getText().length() == 0) {
			passTxt.setError("Please Specify a Valid Password Here");
			registrationCheck = false;
		}
		return registrationCheck;
	}
}
