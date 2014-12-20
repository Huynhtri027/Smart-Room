package com.smartroom.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.activity.MemberActivity;
import com.smartroom.model.UserProfile;
import com.smartroom.utilities.Utils;

public class AuthenticationController {

	private static UserProfile userProfile = null;
	private static JSONObject jresponse;

	public static void checkLogin(final String email, final String password) {

		final ProgressDialog pDialog = new ProgressDialog(
				Utils.getCurrentActivity());
		pDialog.setMessage("Logging In ...");
		pDialog.show();

		RequestQueue queue = Volley.newRequestQueue(Utils.getCurrentActivity());

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.loginUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						Log.d("Response", response);

						userProfile = new UserProfile();

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("email").equals("invalid")) {
								Toast.makeText(
										Utils.getMainContext(),
										"Login Failed: Email or Password is INVALID!",
										Toast.LENGTH_LONG).show();
								pDialog.hide();
								return;
							} else {

								Toast.makeText(Utils.getMainContext(),
										"Login Successful", Toast.LENGTH_SHORT)
										.show();

								userProfile.setEmail(jresponse
										.getString("email"));

								userProfile.setFullName(jresponse
										.getString("full_name"));
								userProfile.setImgURL(jresponse
										.getString("img_url"));
								userProfile.setLoggedUsing(jresponse
										.getString("looged_using"));
							}

						} catch (JSONException e) {
							Log.e("Volley Error", e.getMessage());
							e.printStackTrace();
						}

						SessionController.setUserProfile(userProfile);
						SessionController.sessionStart(Utils
								.getCurrentActivity());

						Intent loginIntent = new Intent(Utils.getMainContext(),
								MemberActivity.class);
						Utils.getMainContext().startActivity(loginIntent);
						Utils.getCurrentActivity().finish();

						pDialog.hide();

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(
								Utils.getCurrentActivity(),
								"Sorry there was a problem while trying to Login!",
								Toast.LENGTH_LONG).show();
						pDialog.hide();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("email", email);
				params.put("password", password);

				return params;
			}
		};
		queue.add(postRequest);

	}

	public static void saveToRemoteDatabase(final UserProfile user)
			throws JSONException {

		final ProgressDialog pDialog = new ProgressDialog(
				Utils.getCurrentActivity());
		pDialog.setMessage("Requesting Server for Registration ...");
		pDialog.show();

		RequestQueue queue = Volley.newRequestQueue(Utils.getCurrentActivity());

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.registerUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						Log.d("Response", response);

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("success").equals("0")) {
								Toast.makeText(Utils.getMainContext(),
										"" + jresponse.getString("message"),
										Toast.LENGTH_LONG).show();
								pDialog.hide();
								return;
							} else {
								Toast.makeText(Utils.getMainContext(),
										"Registered Successfully!",
										Toast.LENGTH_SHORT).show();

								userProfile = user;

								SessionController.setUserProfile(userProfile);
								SessionController.sessionStart(Utils
										.getCurrentActivity());

								Intent loginIntent = new Intent(
										Utils.getMainContext(),
										MemberActivity.class);

								Utils.getMainContext().startActivity(
										loginIntent);
								Utils.getCurrentActivity().finish();
							}

						} catch (JSONException e) {
							Log.e("Volley Error", e.getMessage());
							e.printStackTrace();
						}

						pDialog.hide();

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						Toast.makeText(
								Utils.getCurrentActivity(),
								"Sorry there was a problem while trying to Register!",
								Toast.LENGTH_LONG).show();
						pDialog.hide();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {

				Map<String, String> params = new HashMap<String, String>();
				params.put("fullname", user.getFullName());
				params.put("email", user.getEmail());
				params.put("password", user.getPassword());
				params.put("tenant", String.valueOf(user.isTenant()));
				params.put("landlord", String.valueOf(user.isLandlord()));
				params.put("image_url", user.getImgURL());
				params.put("loggedUsing", user.getLoggedUsing());

				return params;
			}
		};
		queue.add(postRequest);

	}

}
