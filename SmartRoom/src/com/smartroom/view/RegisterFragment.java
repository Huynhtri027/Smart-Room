package com.smartroom.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.smartroom.R;
import com.smartroom.activity.MainActivity;
import com.smartroom.controller.AuthenticationController;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.model.UserProfile;
import com.smartroom.utilities.Utils;

public class RegisterFragment extends Fragment {

	private View parentView;
	public static Context registerContext = null;

	EditText fullNameTxt, emailTxt, passwordTxt, confirmPassTxt;
	CheckBox asTenant, asLandlord;
	Button signUp = null;
	RequestQueue queue = null;
	MainActivity parentActivity = null;
	
	Fragment newFragment = null;
	FragmentTransaction transaction = null;
	
	private UserProfile userProfile = null; 

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		addEvents();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_register, container,
				false);
		parentActivity = (MainActivity) getActivity();
		setupView();

		return parentView;
	}

	private void setupView() {
		fullNameTxt = (EditText) parentView.findViewById(R.id.registerFullName);
		emailTxt = (EditText) parentView.findViewById(R.id.registerEmail);
		passwordTxt = (EditText) parentView.findViewById(R.id.registerPassword);
		confirmPassTxt = (EditText) parentView
				.findViewById(R.id.registerConfirmPassword);

		asTenant = (CheckBox) parentView.findViewById(R.id.registerAsTenant);
		asLandlord = (CheckBox) parentView
				.findViewById(R.id.registerAsLandlord);

		signUp = (Button) parentView.findViewById(R.id.registerButton);

	}

	private void addEvents() {
		signUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkFields()) {

					try {
						String fullName = fullNameTxt.getText().toString();
						String email = emailTxt.getText().toString();
						String password = passwordTxt.getText().toString();
						boolean asLan = asLandlord.isChecked();
						boolean isTen = asTenant.isChecked();
						
						userProfile = new UserProfile();
						
						userProfile.setEmail(email);
						userProfile.setFullName(fullName);
						userProfile.setImgURL("NO_URL");
						userProfile.setLoggedUsing("REGISTRATION_FORM");
						userProfile.setPassword(password);
						
						userProfile.setTenant(isTen);
						userProfile.setLandlord(asLan);
						
						boolean netCheck = Utils.isNetworkAvailable(getActivity());
						if(netCheck) {
							AuthenticationController.saveToRemoteDatabase(userProfile);
						}
						else {
							transaction = getFragmentManager().beginTransaction();
							FragmentManagerHelper.setFragmentTransaction(transaction);

							newFragment = new NoNetwork();

							FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
							FragmentManagerHelper.setCurrentFragment(newFragment);
							FragmentManagerHelper.setFragmentType("NORMAL");
						}
						

					} catch (Exception e) {
						Toast.makeText(Utils.getMainContext(),
								"ERROR: " + e.getMessage(), Toast.LENGTH_SHORT)
								.show();
						Log.e("ERROR", e.getMessage());
					}

				}
			}

			private boolean checkFields() {
				boolean registrationCheck = true;
				if (fullNameTxt.getText().length() == 0) {
					fullNameTxt.setError("Please Specify your Full Name Here");
					registrationCheck = false;
				}
				if (emailTxt.getText().length() == 0) {
					emailTxt.setError("Please Specify a valid Email Here");
					registrationCheck = false;
				}
				if (passwordTxt.getText().length() == 0) {
					passwordTxt.setError("Please Specify a Password Here");
					registrationCheck = false;
				}
				if (confirmPassTxt.getText().length() == 0) {
					confirmPassTxt
							.setError("Please Confirm your Password Here");
					registrationCheck = false;
				}

				if (!passwordTxt.getText().toString()
						.equals(confirmPassTxt.getText().toString())) {
					passwordTxt.setError("Sorry Passwords does not Match!");
					passwordTxt.setText("");
					confirmPassTxt.setError("Sorry Passwords does not Match!");
					confirmPassTxt.setText("");
					registrationCheck = false;
				}
				if (!asTenant.isChecked() && !asLandlord.isChecked()) {
					asTenant.setError("At Least Check One");
					asLandlord.setError("At Least Check One");
					Toast.makeText(
							Utils.getMainContext(),
							"You need to at least be registered as Tenant or Landlord.",
							Toast.LENGTH_LONG).show();
					registrationCheck = false;
				}
				return registrationCheck;
			}
		});
	}

}
