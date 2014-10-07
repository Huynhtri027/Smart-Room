package com.smartroom.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.R;
import com.smartroom.controller.MessagesAdapter;
import com.smartroom.controller.SessionController;
import com.smartroom.model.MessageModel;
import com.smartroom.utilities.Utils;

public class ViewMessagesFragment extends Fragment {

	private View parentView;
	private TextView messageCount = null;
	private ListView messagesListView = null;

	MessagesAdapter adapter = null;
	private ArrayList<MessageModel> messageResult = new ArrayList<MessageModel>();
	private RequestQueue mQueue;
	private MessageModel newMessage = null;
	private JSONObject jresponse;
	JSONArray results;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_view_messages,
				container, false);

		setUpViews();

		adapter = new MessagesAdapter(getActivity(), messageResult);
		messagesListView.setAdapter(adapter);
		fetchMessageResult();

		return parentView;
	}

	private void setUpViews() {
		messageCount = (TextView) parentView
				.findViewById(R.id.viewMessageResultCount);
		messagesListView = (ListView) parentView
				.findViewById(R.id.viewMessageResultListView);
	}

	public void fetchMessageResult() {

		mQueue = Volley.newRequestQueue(getActivity());

		final ProgressDialog pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Fetching Messages....");
		pDialog.setCancelable(true);
		pDialog.show();

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.getMessagesUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("bool").equals("false")) {
								Toast.makeText(
										Utils.getMainContext(),
										"Sorry you don't have received messages yetD!",
										Toast.LENGTH_LONG).show();
								pDialog.hide();
								return;
							} else {

								results = jresponse.getJSONArray("messages");

								for (int i = 0; i < results.length(); i++) {

									JSONObject jsonObjectRos = results
											.getJSONObject(i);

									String date = jsonObjectRos
											.getString("date");
									String senderID = jsonObjectRos
											.getString("sender_id");
									String message = jsonObjectRos
											.getString("message");

									String advertReference = jsonObjectRos
											.getString("advert_ref_id");
									Bitmap img = Utils
											.stringToBitmap(jsonObjectRos
													.getString("image"));

									newMessage = new MessageModel();

									newMessage.setPostDate(date);
									newMessage.setSenderID(senderID);
									newMessage.setMessage(message);
									newMessage
											.setAdvertReference(advertReference);
									newMessage.setMessageIcon(img);

									adapter.add(newMessage);
									messageCount.setText((i + 1)
											+ " Coversations");
									adapter.notifyDataSetChanged();

								}

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
								"Sorry there was a problem while fetching Messages!",
								Toast.LENGTH_LONG).show();
						pDialog.hide();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();

				if (SessionController.getUserProfile() != null) {
					params.put("email", SessionController.getUserProfile()
							.getEmail());
				}
				return params;
			}
		};

		mQueue.add(postRequest);
	}
}
