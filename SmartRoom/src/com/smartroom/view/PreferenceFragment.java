package com.smartroom.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.controller.DBHelper;
import com.smartroom.controller.FragmentManagerHelper;
import com.smartroom.model.FilterPreferenceModel;

public class PreferenceFragment extends Fragment {

	private View parentView;
	private EditText minPrice = null, maxPrice = null, minBed = null,
			maxBed = null, searchDistance = null;
	private Spinner sellerType = null, propertyType = null, addedDate = null,
			sortBy = null, priceFrequency = null;

	private Button savePreferences = null;

	private DBHelper dbHelper = null;
	private FilterPreferenceModel preference = null;

	private ArrayAdapter<String> arrayAdapter;
	private int pos = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_preference, container,
				false);

		dbHelper = new DBHelper(getActivity());
		dbHelper.open();
		this.preference = dbHelper.retrivePreferences();
		dbHelper.close();

		setUpViews();
		initializePreference();
		return parentView;
	}

	private void initializePreference() {

		minPrice.setText(preference.getMinPrice());
		maxPrice.setText(preference.getMaxPrice());
		minBed.setText(preference.getMinBed());
		maxBed.setText(preference.getMaxBed());
		searchDistance.setText(preference.getSearchDistance());

		arrayAdapter = (ArrayAdapter) sellerType.getAdapter();
		pos = arrayAdapter.getPosition(preference.getSellerType());
		sellerType.setSelection(pos);

		arrayAdapter = (ArrayAdapter) propertyType.getAdapter();
		pos = arrayAdapter.getPosition(preference.getPropertyType());
		propertyType.setSelection(pos);

		arrayAdapter = (ArrayAdapter) addedDate.getAdapter();
		pos = arrayAdapter.getPosition(preference.getAddedDate());
		sellerType.setSelection(pos);

		arrayAdapter = (ArrayAdapter) sortBy.getAdapter();
		pos = arrayAdapter.getPosition(preference.getSortBy());
		sortBy.setSelection(pos);

		arrayAdapter = (ArrayAdapter) priceFrequency.getAdapter();
		pos = arrayAdapter.getPosition(preference.getPriceFrequency());
		priceFrequency.setSelection(pos);

		savePreferences.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				preference = new FilterPreferenceModel();
				preference.setAddedDate(addedDate.getSelectedItem().toString());
				preference.setMaxBed(maxBed.getText().toString());
				preference.setMaxPrice(maxPrice.getText().toString());
				preference.setMinBed(minBed.getText().toString());
				preference.setMinPrice(minPrice.getText().toString());

				preference.setPriceFrequency(priceFrequency.getSelectedItem()
						.toString());
				preference.setPropertyType(propertyType.getSelectedItem()
						.toString());
				preference.setSearchDistance(searchDistance.getText()
						.toString());
				preference.setSellerType(sellerType.getSelectedItem()
						.toString());
				preference.setSortBy(sortBy.getSelectedItem().toString());

				dbHelper.open();
				dbHelper.updateCustDetails(preference);
				dbHelper.close();
				Toast.makeText(getActivity(),
						"Preferences has been Successfully Updated!",
						Toast.LENGTH_SHORT).show();

				Fragment newFragment = null;
				newFragment = new HomeFragment();

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

	private void setUpViews() {

		savePreferences = (Button) parentView.findViewById(R.id.preferenceSave);

		minPrice = (EditText) parentView.findViewById(R.id.preferenceMinPrice);
		maxPrice = (EditText) parentView.findViewById(R.id.preferenceMaxPrice);
		minBed = (EditText) parentView.findViewById(R.id.preferenceMinBed);
		maxBed = (EditText) parentView.findViewById(R.id.preferenceMaxBed);
		searchDistance = (EditText) parentView
				.findViewById(R.id.preferenceSearchDistance);
		sellerType = (Spinner) parentView
				.findViewById(R.id.preferenceSellerType);
		propertyType = (Spinner) parentView
				.findViewById(R.id.preferencePropertyType);
		addedDate = (Spinner) parentView.findViewById(R.id.preferenceAddedDate);
		sortBy = (Spinner) parentView.findViewById(R.id.preferenceSortBy);
		priceFrequency = (Spinner) parentView
				.findViewById(R.id.preferencePriceFrequency);
	}

}
