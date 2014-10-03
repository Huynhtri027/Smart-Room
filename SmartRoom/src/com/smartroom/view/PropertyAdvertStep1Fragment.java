package com.smartroom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.smartroom.R;

public class PropertyAdvertStep1Fragment extends Fragment {

	private View parentView;
	public static Spinner sellerType = null, propertyType = null,
			priceFrequency;
	public static EditText bedNum, rentAmount, depositAmount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_advert_property_step1,
				container, false);

		setUpViews();

		return parentView;
	}

	private void setUpViews() {
		sellerType = (Spinner) parentView
				.findViewById(R.id.propertyAdvertSellerType);
		propertyType = (Spinner) parentView
				.findViewById(R.id.propertyAdvertPropertyType);
		priceFrequency = (Spinner) parentView
				.findViewById(R.id.propertyAdvertPriceFrequency);

		bedNum = (EditText) parentView
				.findViewById(R.id.propertyAdvertBedNumber);
		rentAmount = (EditText) parentView
				.findViewById(R.id.propertyAdvertRentAmount);
		depositAmount = (EditText) parentView
				.findViewById(R.id.propertyAdvertDepositAmount);

	}
}