package com.smartroom.utilities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.smartroom.model.LatitudeLocationModel;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

public class GetAddress {

	private static LatitudeLocationModel LatLon = null;

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
			Toast.makeText(context, "PostCode: " + zipcode, Toast.LENGTH_SHORT)
					.show();
			Log.i("[ Addresses ] ", "Address: " + address + ", PostCode: "
					+ zipcode + ", City: " + city + ", State: " + state
					+ "\nFull Address: " + addr);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static LatitudeLocationModel postcodeToLatLon(Context mContext,
			String postcode) {

		LatLon = new LatitudeLocationModel();

		final Geocoder geocoder = new Geocoder(mContext);

		try {
			List<Address> addresses = geocoder.getFromLocationName(postcode, 1);

			if (addresses != null && !addresses.isEmpty()) {
				Address address = addresses.get(0);

				LatLon.setLatitude(address.getLatitude());
				LatLon.setLongitude(address.getLongitude());

			} else {
				Toast.makeText(mContext, "Unable to geocode Post Code",
						Toast.LENGTH_LONG).show();
			}
		} catch (IOException e) {
		}

		return LatLon;
	}

}
