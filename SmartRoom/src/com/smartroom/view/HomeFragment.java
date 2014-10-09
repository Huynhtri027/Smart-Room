package com.smartroom.view;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.activity.MainActivity;
import com.smartroom.activity.SearchHouseActivity;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.service.GPSTrackerService;
import com.smartroom.utilities.Utils;

public class HomeFragment extends Fragment {
	private View parentView;
	private RadioButton radioCurrLocation = null;
	private RadioButton radioCustLocation = null;
	private RadioButton radioAllLocation = null;
	public static EditText searchLocation = null;
	private String searchQuery = null;
	private Button loginBtn = null, registerBtn = null, searchProperty,
			searchPeople;
	private EditText search = null;
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

		search = (EditText) parentView.findViewById(R.id.homesearchLocation);

		searchProperty = (Button) parentView.findViewById(R.id.homeHouseSearch);

		searchProperty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent searchHouseIntent = new Intent(getActivity(),
						SearchHouseActivity.class);
				searchQuery = getSearchQuery();
				if (searchQuery.equals("NO")) {
					return;
				}
				searchHouseIntent.putExtra("searchValue", searchQuery);
				getActivity().startActivity(searchHouseIntent);
			}
		});

		searchPeople = (Button) parentView.findViewById(R.id.homePeopleSearch);
		searchPeople.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				GPSTrackerService gpsTracker = new GPSTrackerService(
						getActivity());
				gpsTracker.getLatitude();
				gpsTracker.getLongitude();

				Toast.makeText(
						getActivity(),
						"Latitude: " + gpsTracker.getLatitude()
								+ " Longitude: " + gpsTracker.getLongitude(),
						Toast.LENGTH_SHORT).show();

				Toast.makeText(getActivity(), "No Functionality Added Yet!",
						Toast.LENGTH_SHORT).show();
			}
		});

		radioCurrLocation = (RadioButton) parentView.findViewById(R.id.currRadio);
		radioCurrLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				searchLocation = (EditText) parentView
						.findViewById(R.id.homesearchLocation);
				searchLocation.setText("");
				searchLocation.setVisibility(View.GONE);
				Utils.hideKeyboard(parentActivity.getApplicationContext(),
						searchLocation);
			}
		});

		radioCustLocation = (RadioButton) parentView.findViewById(R.id.customRadio);
		radioCustLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchLocation = (EditText) parentView
						.findViewById(R.id.homesearchLocation);
				searchLocation.setText("");
				searchLocation.setVisibility(View.VISIBLE);
				Utils.showKeyboard(parentActivity.getApplicationContext(),
						searchLocation);
			}
		});

		radioAllLocation = (RadioButton) parentView.findViewById(R.id.allRadio);
		radioAllLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchLocation = (EditText) parentView
						.findViewById(R.id.homesearchLocation);
				searchLocation.setText("");
				searchLocation.setVisibility(View.GONE);
				Utils.hideKeyboard(parentActivity.getApplicationContext(),
						searchLocation);
			}
		});

		loginBtn = (Button) parentView.findViewById(R.id.homeLoginBtn);
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
				.findViewById(R.id.homeCreateAccountButton);
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

	public String getSearchQuery() {
		if (radioAllLocation.isChecked()) {
			searchQuery = "";
		} else if (radioCurrLocation.isChecked()) {
			Toast.makeText(
					getActivity(),
					"Functionality Partially Completed! Not working at the Moment.",
					Toast.LENGTH_LONG).show();
			searchQuery = "NO";
		} else if (radioCustLocation.isChecked()) {
			searchQuery = search.getText().toString();
		}

		return searchQuery;
	}

}
