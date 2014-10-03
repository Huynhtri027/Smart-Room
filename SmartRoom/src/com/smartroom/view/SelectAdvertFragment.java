package com.smartroom.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartroom.R;
import com.smartroom.activity.AdvertMyselfActivity;
import com.smartroom.activity.MemberActivity;
import com.smartroom.activity.AdvertPropertyActivity;
import com.smartroom.utilities.Utils;

public class SelectAdvertFragment extends Fragment {
	private View parentView;

	Button advertProp = null;
	Button advertMe = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_advert, container,
				false);

		setUpViews();
		addEvents();

		return parentView;
	}

	private void setUpViews() {
		advertProp = (Button) parentView.findViewById(R.id.advertProperty);
		advertMe = (Button) parentView.findViewById(R.id.advertMe);
	}

	private void addEvents() {
		advertProp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent propertyAdvert = new Intent(Utils
							.getCurrentActivity(), AdvertPropertyActivity.class);
					startActivity(propertyAdvert);
				} catch (Exception e) {
					Log.e("ERROR", e.getMessage());
				}
			}
		});
		advertMe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent advertMyself = new Intent(
							Utils.getCurrentActivity(),
							AdvertMyselfActivity.class);
					startActivity(advertMyself);
				} catch (Exception e) {
					Log.e("ERROR", e.getMessage());
				}

			}
		});
	}
}
