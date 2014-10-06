package com.smartroom.view;

import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.controller.AdvertController;
import com.smartroom.model.HouseProperty;
import com.smartroom.utilities.Utils;

public class PropertyAdvertStep3Fragment extends Fragment {

	private View parentView;
	private Button propertyAdvertPostAdvertBtn = null;
	private EditText advertTitle, advertDescription, advertiserFullName,
			advertiserTelephone;
	private CheckBox displayName, displayTel;
	private Spinner advertiserTitle = null;
	private Bitmap bitmap = null;

	boolean postAdvertCheck = true;

	HouseProperty newHouse = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_advert_property_step3,
				container, false);

		setUpViews();
		addEvents();

		return parentView;
	}

	private void setUpViews() {

		advertiserTitle = (Spinner) parentView
				.findViewById(R.id.propertyAdvertiserTitle);
		advertTitle = (EditText) parentView
				.findViewById(R.id.propertyAdvertAdvertTitle);
		advertDescription = (EditText) parentView
				.findViewById(R.id.propertyAdvertPropertyDescription);
		advertiserFullName = (EditText) parentView
				.findViewById(R.id.propertyAdvertAdvertiserFullName);
		advertiserTelephone = (EditText) parentView
				.findViewById(R.id.propertyAdvertTelephone);

		displayName = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertDisplayName);

		displayTel = (CheckBox) parentView
				.findViewById(R.id.propertyAdvertDisplayTelephone);

		propertyAdvertPostAdvertBtn = (Button) parentView
				.findViewById(R.id.propertyAdvertPostAdvert);
	}

	private void addEvents() {

		propertyAdvertPostAdvertBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {


				if (verifyAdvert()) {
					newHouse = new HouseProperty();

					newHouse.setAdvertiserTitle(advertiserTitle
							.getSelectedItem().toString());
					newHouse.setAdvertTtile(advertTitle.getText().toString());

					newHouse.setAdvertDescription(advertDescription.getText()
							.toString());
					newHouse.setAdvertiserFullName(advertiserFullName.getText()
							.toString());
					newHouse.setAdvertiserTelephone(advertiserTelephone
							.getText().toString());

					newHouse.setAddress(PropertyAdvertStep2Fragment.propertyAdvertAddressTxt
							.getText().toString());

					newHouse.setAvailabilityDate(PropertyAdvertStep2Fragment.propertyAdvertDateTxt
							.getText().toString());
					newHouse.setBalconyAvailable(PropertyAdvertStep2Fragment.propertyAdvertBalcony
							.isChecked());
					newHouse.setBedNum(PropertyAdvertStep1Fragment.bedNum
							.getText().toString());
					newHouse.setBillIncluded(PropertyAdvertStep2Fragment.propertyAdvertBillIncluded
							.isChecked());
					newHouse.setBroadband(PropertyAdvertStep2Fragment.propertyAdvertBroadband
							.isChecked());
					newHouse.setCountry(PropertyAdvertStep2Fragment.propertyAdvertCountrySpinner
							.getSelectedItem().toString());
					newHouse.setDepositAmount(PropertyAdvertStep1Fragment.depositAmount
							.getText().toString());
					newHouse.setDisabledAcessAvailable(PropertyAdvertStep2Fragment.propertyAdvertDisableAccess
							.isChecked());
					newHouse.setDisplayFullName(displayName.isChecked());
					newHouse.setDisplayTelephone(displayTel.isChecked());
					newHouse.setFurnishing(PropertyAdvertStep2Fragment.propertyAdvertFurnishing
							.getSelectedItem().toString());
					newHouse.setGarageAvailable(PropertyAdvertStep2Fragment.propertyAdvertGarage
							.isChecked());
					newHouse.setGardenAvailable(PropertyAdvertStep2Fragment.propertyAdvertGarden
							.isChecked());
					newHouse.setParkingAvailable(PropertyAdvertStep2Fragment.propertyAdvertParking
							.isChecked());
					newHouse.setPostcode(PropertyAdvertStep2Fragment.propertyAdvertPostcodeTxt
							.getText().toString());
					newHouse.setPriceFrequency(PropertyAdvertStep1Fragment.priceFrequency
							.getSelectedItem().toString());
					newHouse.setPropertyType(PropertyAdvertStep1Fragment.propertyType
							.getSelectedItem().toString());
					newHouse.setReferenceRequired(PropertyAdvertStep2Fragment.propertyAdvertReference
							.isChecked());
					newHouse.setRentAmount(PropertyAdvertStep1Fragment.rentAmount
							.getText().toString());
					newHouse.setSellerType(PropertyAdvertStep1Fragment.sellerType
							.getSelectedItem().toString());

					if (PropertyAdvertStep2Fragment.propertyAdvertPicImg == null) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.ic_launcher);
					} else {
						BitmapDrawable drawable = (BitmapDrawable) PropertyAdvertStep2Fragment.propertyAdvertPicImg
								.getDrawable();
						bitmap = drawable.getBitmap();
					}

					newHouse.setHousePic(bitmap);

					try {
						Utils.setCurrentActivity(getActivity());
						AdvertController.postHouseProperty(newHouse);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
	}

	protected boolean verifyAdvert() {

		postAdvertCheck = true;

		if (PropertyAdvertStep1Fragment.rentAmount.getText().length() == 0) {
			PropertyAdvertStep1Fragment.rentAmount
					.setError("Please Specify Rent Amount Here");
			postAdvertCheck = false;
			Toast.makeText(getActivity(),
					"You must specify Rent Amount on Tab 1!",
					Toast.LENGTH_SHORT).show();
		}
		if (PropertyAdvertStep2Fragment.propertyAdvertPostcodeTxt.getText()
				.length() == 0) {
			PropertyAdvertStep2Fragment.propertyAdvertPostcodeTxt
					.setError("Please Specify Property Postcode Here");
			postAdvertCheck = false;
			Toast.makeText(getActivity(),
					"You must specify Property Postcode on Tab 2!",
					Toast.LENGTH_SHORT).show();
		}
		if (PropertyAdvertStep2Fragment.propertyAdvertAddressTxt.getText()
				.length() == 0) {
			PropertyAdvertStep2Fragment.propertyAdvertAddressTxt
					.setError("Please Specify Property Address Here");
			postAdvertCheck = false;
			Toast.makeText(getActivity(),
					"You must specify Property Address on Tab 2!",
					Toast.LENGTH_SHORT).show();
		}
		if (advertTitle.getText().length() == 0) {
			advertTitle.setError("Please Specify an Advert Title Here");
			postAdvertCheck = false;
			Toast.makeText(getActivity(),
					"You must specify Advert Title on Tab 3!",
					Toast.LENGTH_SHORT).show();
		}
		if (advertDescription.getText().length() == 0) {
			advertDescription
					.setError("Please Specify Advert Description Here");
			postAdvertCheck = false;
			Toast.makeText(getActivity(),
					"You must specify Advert Description on Tab 3!",
					Toast.LENGTH_SHORT).show();
		}

		return postAdvertCheck;
	}
}