package com.smartroom.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.model.PendingMessageModel;
import com.smartroom.utilities.Utils;

public class CheckNewMessageController {

	private static PendingMessageModel pendingMessage = null;
	private static JSONObject jresponse;
	public static String isUpdated = "NO";

	public static PendingMessageModel checkMessage() {

		RequestQueue queue = Volley.newRequestQueue(Utils.getCurrentActivity());

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.checkMessagetUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						pendingMessage = new PendingMessageModel();

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("bool").equals("true")) {
								pendingMessage
										.setAdvertReferenceNumber(jresponse
												.getString("advert_ref_id"));
								pendingMessage.setMessage(jresponse
										.getString("message"));
								pendingMessage.setMessageID(jresponse
										.getString("messageID"));
								pendingMessage.setSendeID(jresponse
										.getString("sender_id"));
								pendingMessage.setNotification(true);
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
					}
				}) {
			@Override
			protected Map<String, String> getParams() {

				Map<String, String> params = new HashMap<String, String>();
				params.put("email", SessionController.getUserProfile()
						.getEmail().toString());
				return params;
			}
		};
		queue.add(postRequest);

		return pendingMessage;
	}

	public static String approveNotificationMessage(
			final PendingMessageModel pmm) {

		RequestQueue queue = Volley.newRequestQueue(Utils.getCurrentActivity());

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.approveMessagetUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						isUpdated = response.toString();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(
								Utils.getCurrentActivity(),
								"Sorry there was a problem while trying to Update Notification!",
								Toast.LENGTH_LONG).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {

				Map<String, String> params = new HashMap<String, String>();
				params.put("messageID", pmm.getMessageID());
				return params;
			}
		};
		queue.add(postRequest);

		return isUpdated;
	}

}
