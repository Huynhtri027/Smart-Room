package com.smartroom.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.smartroom.R;
import com.smartroom.activity.MainActivity;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.utilities.Utils;

public class HomeFragment extends Fragment {
	private View parentView;
	private RadioButton radioCurrLocation = null;
	private RadioButton radioCustLocation = null;
	public static EditText searchLocation = null;
	private Button loginBtn = null, registerBtn = null;
	MainActivity parentActivity = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_home, container, false);

		setUpViews();
		return parentView;
	}

	private void setUpViews() {

		parentActivity = (MainActivity) getActivity();

		radioCurrLocation = (RadioButton) parentView.findViewById(R.id.radio0);
		radioCurrLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				searchLocation = (EditText) parentView
						.findViewById(R.id.searchLocation);
				searchLocation.setVisibility(View.GONE);
				Utils.hideKeyboard(parentActivity.getApplicationContext(),
						searchLocation);
			}
		});

		radioCustLocation = (RadioButton) parentView.findViewById(R.id.radio1);
		radioCustLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchLocation = (EditText) parentView
						.findViewById(R.id.searchLocation);
				searchLocation.setVisibility(View.VISIBLE);
				Utils.showKeyboard(parentActivity.getApplicationContext(),
						searchLocation);
			}
		});

		loginBtn = (Button) parentView.findViewById(R.id.homeLoginBtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = null;
				newFragment = new LoginFragment();

				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);
				
				FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});

		registerBtn = (Button) parentView.findViewById(R.id.homeCreateAccountButton);
		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = null;
				newFragment = new RegisterFragment();
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});

	}

}
