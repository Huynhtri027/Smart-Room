package com.smartroom.utilities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

public class GetAddress {

	public static void showAddress(Context context, double latitude,
			double longitude) {
		Geocoder geoCoder = new Geocoder(context, Locale.getDefault());

		try {
			List<Address> addresses = geoCoder.getFromLocation(latitude,
					longitude, 50);
			Address address = null;
			String addr = "";
			String zipcode = "";
			String city = "";
			String state = "";

			if (addresses != null && addresses.size() > 0) {
				addr = addresses.get(0).getAddressLine(0) + ","
						+ addresses.get(0).getSubAdminArea();
				city = addresses.get(0).getLocality();
				state = addresses.get(0).getAdminArea();

				for (int i = 0; i < addresses.size(); i++) {
					address = addresses.get(i);
					if (address.getPostalCode() != null) {
						zipcode = address.getPostalCode();
						break;
					}

				}
			}
			Toast.makeText(context,"PostCode: " + zipcode,
					Toast.LENGTH_SHORT).show();
			Log.i("[ Addresses ] ", "Address: " + address + ", PostCode: "
					+ zipcode + ", City: " + city + ", State: " + state
					+ "\nFull Address: " + addr);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getAreaCode(Context context, double latitude,
			double longitude) {

		String areaCode = null;

		Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(latitude,
					longitude, 1);

			String add = "";
			if (addresses.size() > 0) {
				for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++) {
					add += addresses.get(0).getAddressLine(i) + "\n";
					areaCode = addresses.get(0).getAddressLine(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return areaCode;
	}

}
