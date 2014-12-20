package com.smartroom.view;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.smartroom.R;
import com.smartroom.activity.BrowsePictureActivity;
import com.smartroom.activity.CapturePictureActivity;
import com.smartroom.utilities.Utils;

public class PropertyAdvertStep2Fragment extends Fragment {

	private View parentView;
	Button setAvailabilityDateBtn = null, propertyAdvertTakePicBtn = null,
			propertyAdvertBrowsePicBtn = null;

	private int mYear;
	private int mMonth;
	private int mDay;

	public static ImageView propertyAdvertPicImg = null;
	public static EditText propertyAdvertPostcodeTxt, propertyAdvertAddressTxt,
			propertyAdvertDateTxt;
	public static Spinner propertyAdvertCountrySpinner = null,
			propertyAdvertFurnishing;
	public static CheckBox propertyAdvertReference, propertyAdvertParking,
			propertyAdvertGarden, propertyAdvertGarage, propertyAdvertBalcony,
			propertyAdvertDisableAccess, propertyAdvertBroadband,
			propertyAdvertBillIncluded;

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_advert_property_step2,
				container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {

		propertyAdvertPostcodeTxt = (EditText) parentView
				.findViewById(R.id.propertyAdvertPostCode);
		propertyAdvertAddressTxt = (EditText) parentView
				.findViewById(R.id.propertyAdvertAddress);

		propertyAdvertCountrySpinner = (Spinner) parentView
				.findViewById(R.id.propertyAdvertCountry);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, Utils.getCountryList());

		propertyAdvertCountrySpinner.setAdapter(adapter);
		String DEFAULT_LOCAL = getActivity().getResources().getConfiguration().locale
				.toString();
		propertyAdvertCountrySpinner.setSelection(adapter
				.getPosition(DEFAULT_LOCAL));

		propertyAdvertDateTxt = (EditText) parentView
				.findViewById(R.id.propertyAdvertDate);

		setAvailabilityDateBtn = (Button) parentView
				.findViewById(R.id.setAvailabilityDate);

		propertyAdvertReference = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertReferenceRequired);

		propertyAdvertParking = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertParking);

		propertyAdvertGarden = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertGardenRoofTerrace);
		propertyAdvertGarage = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertGarage);

		propertyAdvertBalcony = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertBalconyPatio);
		propertyAdvertDisableAccess = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertDisabledAccess);
		propertyAdvertBroadband = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertBroadband);
		propertyAdvertBillIncluded = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertBillIncluded);

		setAvailabilityDateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				updateDisplay();
				
				DatePickerDialog dp = new DatePickerDialog(getActivity(),
						mDateSetListener, mYear, mMonth, mDay);
				dp.show();
				
			}
		});

		propertyAdvertFurnishing = (Spinner) parentView
				.findViewById(R.id.propertyAdvertFurnishing);

		propertyAdvertPicImg = (ImageView) parentView
				.findViewById(R.id.propertyAdvertPic);

		propertyAdvertTakePicBtn = (Button) parentView
				.findViewById(R.id.propertyAdvertTakePic);
		propertyAdvertTakePicBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent takePic = new Intent(getActivity(),
						CapturePictureActivity.class);
				startActivity(takePic);

			}
		});

		propertyAdvertBrowsePicBtn = (Button) parentView
				.findViewById(R.id.propertyAdvertBrowsePic);
		propertyAdvertBrowsePicBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent takePic = new Intent(getActivity(),
						BrowsePictureActivity.class);
				startActivity(takePic);

			}
		});

	}

	private void updateDisplay() {
		propertyAdvertDateTxt.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-").append(mDay).append("-")
				.append(mYear).append(" "));
	}
}