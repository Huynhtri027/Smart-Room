package com.smartroom.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.smartroom.R;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.utilities.Utils;
import com.squareup.picasso.Picasso;

public class AskToLoginFragment extends Fragment {

	private View parentView;
	private Button loginBtn = null, registerBtn = null;
	private ImageView img = null;
	Handler handler = new Handler();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_ask_to_login,
				container, false);
		setUpViews();

		loadImages();

		return parentView;
	}

	private void loadImages() {
		Picasso.with(getActivity()).load(Utils.picUrl + "3.jpg").into(img);
	}

	private void setUpViews() {

		img = (ImageView) parentView.findViewById(R.id.imageView1ADV);

		loginBtn = (Button) parentView.findViewById(R.id.askLoginBtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = null;
				newFragment = new LoginFragment();

				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				FragmentManagerHelper.setFragmentTransaction(transaction);

				FragmentManagerHelper
						.replaceFragmentWithoutAnimation(newFragment);
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

				FragmentManagerHelper
						.replaceFragmentWithoutAnimation(newFragment);
				FragmentManagerHelper.setCurrentFragment(newFragment);
				FragmentManagerHelper.setFragmentType("NORMAL");
			}
		});

	}

}
