package com.smartroom.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.model.HouseProperty;
import com.smartroom.utilities.Utils;

public class AdvertController {

	private static HouseProperty theNewHouse = null;
	private static JSONObject jresponse;

	public static void postHouseProperty(HouseProperty newHouse)
			throws JSONException {

		if (SessionController.isUserStatus()) {
			final ProgressDialog pDialog = new ProgressDialog(
					Utils.getCurrentActivity());
			pDialog.setMessage("Requesting Server for Saving Advert ...");
			pDialog.show();

			theNewHouse = newHouse;

			RequestQueue queue = Volley.newRequestQueue(Utils
					.getCurrentActivity());

			StringRequest postRequest = new StringRequest(Request.Method.POST,
					Utils.saveAdvertUrl, new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {

							Log.d("Response", response);

							pDialog.hide();
							int responseCode = Integer.parseInt(response
									.toString());

							if (responseCode == 1) {
								Toast.makeText(Utils.getMainContext(),
										"Advert Added Successfully!",
										Toast.LENGTH_LONG).show();
								Utils.getCurrentActivity().finish();

							} else {
								Toast.makeText(
										Utils.getMainContext(),
										"Failed to Save Advert, Please Try Again! ",
										Toast.LENGTH_LONG).show();
							}

						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {

							Toast.makeText(
									Utils.getCurrentActivity(),
									"Sorry there was a problem while trying to Post the Advert! "
											+ error.getMessage().toString(),
									Toast.LENGTH_LONG).show();
							pDialog.hide();
						}
					}) {
				@Override
				protected Map<String, String> getParams() {

					Map<String, String> params = new HashMap<String, String>();

					params.put("email", SessionController.getUserProfile()
							.getEmail());

					params.put("address", theNewHouse.getAddress());
					params.put("description",
							theNewHouse.getAdvertDescription());
					params.put("fullname", theNewHouse.getAdvertiserFullName());
					params.put("telephone",
							theNewHouse.getAdvertiserTelephone());
					params.put("advertiser_title",
							theNewHouse.getAdvertiserTitle());
					params.put("title", theNewHouse.getAdvertTtile());
					params.put("availability_date",
							theNewHouse.getAvailabilityDate());
					params.put("bed_num", theNewHouse.getBedNum());
					params.put("country", theNewHouse.getCountry());
					params.put("deposit", theNewHouse.getDepositAmount());
					params.put("furnishing", theNewHouse.getFurnishing());
					params.put("postcode", theNewHouse.getPostcode());
					params.put("price_frequency",
							theNewHouse.getPriceFrequency());
					params.put("property_type", theNewHouse.getPropertyType());
					params.put("rent_amount", theNewHouse.getRentAmount());
					params.put("seller_type", theNewHouse.getSellerType());

					params.put("balcony",
							String.valueOf(theNewHouse.isBalconyAvailable()));
					params.put("bill_included",
							String.valueOf(theNewHouse.isBillIncluded()));
					params.put("broadband",
							String.valueOf(theNewHouse.isBroadband()));
					params.put("disabled_access", String.valueOf(theNewHouse
							.isDisabledAcessAvailable()));
					params.put("display_fullname",
							String.valueOf(theNewHouse.isDisplayFullName()));
					params.put("display_telephone",
							String.valueOf(theNewHouse.isDisplayTelephone()));
					params.put("garage",
							String.valueOf(theNewHouse.isGarageAvailable()));
					params.put("garden",
							String.valueOf(theNewHouse.isGardenAvailable()));
					params.put("parking",
							String.valueOf(theNewHouse.isParkingAvailable()));
					params.put("reference",
							String.valueOf(theNewHouse.isReferenceRequired()));

					String image = Utils.bitmapToString(theNewHouse
							.getHousePic());

					params.put("image", image);

					return params;
				}
			};
			queue.add(postRequest);

		} else {
			Toast.makeText(Utils.getMainContext(),
					"You need to Login Properly To Post and Advert!",
					Toast.LENGTH_LONG).show();
		}

	}

	public static HouseProperty getHouseProperty(
			final String propertyReferencyID) throws JSONException {

		RequestQueue queue = Volley.newRequestQueue(Utils.getCurrentActivity());

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.getPropertyByIDtUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						theNewHouse = new HouseProperty();

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("bool").equals("true")) {

								Bitmap img = Utils.stringToBitmap(jresponse
										.getString("image"));

								theNewHouse.setAddress(jresponse
										.getString("address"));
								theNewHouse.setAdvertDescription(jresponse
										.getString("advert_description"));
								theNewHouse.setAdvertiserFullName(jresponse
										.getString("advertiser_fullname"));
								theNewHouse.setAdvertiserTelephone(jresponse
										.getString("advertiser_telephone"));
								theNewHouse.setAdvertiserTitle(jresponse
										.getString("advertiser_title"));
								theNewHouse.setAdvertTtile(jresponse
										.getString("advert_title"));
								theNewHouse.setAvailabilityDate(jresponse
										.getString("available_date"));

								if (jresponse.getString("balcony_available")
										.equals("true")) {
									theNewHouse.setBalconyAvailable(true);
								} else {
									theNewHouse.setBalconyAvailable(false);
								}

								theNewHouse.setBedNum(jresponse
										.getString("bed_num"));

								if (jresponse.getString("bill_included")
										.equals("true")) {
									theNewHouse.setBillIncluded(true);
								} else {
									theNewHouse.setBillIncluded(false);
								}

								if (jresponse.getString("broadband_available")
										.equals("true")) {
									theNewHouse.setBroadband(true);
								} else {
									theNewHouse.setBroadband(false);
								}

								theNewHouse.setCountry(jresponse
										.getString("country"));
								theNewHouse.setDepositAmount(jresponse
										.getString("deposit_amount"));

								if (jresponse.getString(
										"disabled_access_available").equals(
										"true")) {
									theNewHouse.setDisabledAcessAvailable(true);
								} else {
									theNewHouse
											.setDisabledAcessAvailable(false);
								}

								if (jresponse.getString("display_telephone")
										.equals("true")) {
									theNewHouse.setDisplayTelephone(true);
								} else {
									theNewHouse.setDisplayTelephone(false);
								}
								
								if (jresponse.getString("display_name")
										.equals("true")) {
									theNewHouse.setDisplayFullName(true);
								} else {
									theNewHouse.setDisplayFullName(false);
								}


								theNewHouse.setFurnishing(jresponse
										.getString("furnishing"));

								if (jresponse.getString("garage_available")
										.equals("true")) {
									theNewHouse.setGarageAvailable(true);
								} else {
									theNewHouse.setGarageAvailable(false);
								}

								if (jresponse.getString("garden_available")
										.equals("true")) {
									theNewHouse.setGardenAvailable(true);
								} else {
									theNewHouse.setGardenAvailable(false);
								}

								theNewHouse.setHousePic(img);

								if (jresponse.getString("parking_available")
										.equals("true")) {
									theNewHouse.setParkingAvailable(true);
								} else {
									theNewHouse.setParkingAvailable(false);
								}

								theNewHouse.setPostcode(jresponse
										.getString("postcode"));
								theNewHouse.setPriceFrequency(jresponse
										.getString("price_frequency"));
								theNewHouse.setPropertyType(jresponse
										.getString("property_type"));

								if (jresponse.getString("reference_required")
										.equals("true")) {
									theNewHouse.setReferenceRequired(true);
								} else {
									theNewHouse.setReferenceRequired(false);
								}

								theNewHouse.setRentAmount(jresponse
										.getString("rent_amount"));
								theNewHouse.setSellerType(jresponse
										.getString("seller_type"));

							} else {
								Toast.makeText(Utils.getCurrentActivity(),
										"No Property Values Found!",
										Toast.LENGTH_LONG).show();
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(
								Utils.getCurrentActivity(),
								"Sorry there was a problem while Getting Property Information From Server!",
								Toast.LENGTH_LONG).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {

				Map<String, String> params = new HashMap<String, String>();
				params.put("refID", propertyReferencyID);
				return params;
			}
		};
		queue.add(postRequest);

		return theNewHouse;
	}
}
