package com.smartroom.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.smartroom.R;
import com.smartroom.controller.FragmentManagerHelper;

public class AskToLoginFragment extends Fragment {

	private View parentView;
	private Button loginBtn = null, registerBtn = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_ask_to_login,
				container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {

		loginBtn = (Button) parentView.findViewById(R.id.askLoginBtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = null;
				newFragment = new LoginFragment();

				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});

		registerBtn = (Button) parentView
				.findViewById(R.id.askCreateAccountButton);
		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = null;
				newFragment = new RegisterFragment();

				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				FragmentManagerHelper.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});

	}

}
