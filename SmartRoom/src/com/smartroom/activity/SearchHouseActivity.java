package com.smartroom.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.R;
import com.smartroom.controller.HouseSearchResultAdapter;
import com.smartroom.model.PropertySearchResultModel;
import com.smartroom.utilities.Utils;

public class SearchHouseActivity extends Activity {

	private ListView searchResultList = null;
	HouseSearchResultAdapter adapter = null;
	private ArrayList<PropertySearchResultModel> searchResult = new ArrayList<PropertySearchResultModel>();
	private RequestQueue mQueue;
	private PropertySearchResultModel newResult = null;
	JSONArray results;
	private TextView houseSearchResultCount = null;

	ImageButton refresh = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_search_result);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		setUpViews();

		adapter = new HouseSearchResultAdapter(SearchHouseActivity.this,
				searchResult);
		searchResultList.setAdapter(adapter);
		fetchPropertyResult();
		searchResultList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				TextView referenceNumber = (TextView) v.findViewById(R.id.searchHouseReferenceNumber);

				String refId = referenceNumber.getText().toString();
				
				Intent viewAdvert = new Intent(SearchHouseActivity.this, ViewAdvertActivity.class);
				viewAdvert.putExtra("advertReference", refId);
				startActivity(viewAdvert);
				
				
			}
		});

	}

	public void fetchPropertyResult() {

		mQueue = Volley.newRequestQueue(SearchHouseActivity.this);

		final ProgressDialog pDialog = new ProgressDialog(
				SearchHouseActivity.this);
		pDialog.setMessage("Fetching Property Result....");
		pDialog.setCancelable(true);
		pDialog.show();
		
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, Utils.searchPropertytUrl, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject json) {

						try {
							int success = json.getInt("success");

							Log.e("error", "here 1");
							
							if (success == 1) {

								results = json.getJSONArray("property_adverts");

								for (int i = 0; i < results.length(); i++) {

									JSONObject jsonObjectRos = results
											.getJSONObject(i);

									String tempReference = jsonObjectRos
											.getString("advert_reference");

									String tempPropertyType = jsonObjectRos
											.getString("property_type");
									String tempRentAmount = jsonObjectRos
											.getString("rent_amount");
									String tempAddress = jsonObjectRos
											.getString("address");
									String tempAdvertTitle = jsonObjectRos
											.getString("advert_title");

									Bitmap img = Utils
											.stringToBitmap(jsonObjectRos
													.getString("image"));

									newResult = new PropertySearchResultModel();

									newResult.setSearchRefNumber(tempReference);

									newResult
											.setSearchPropertyType(tempPropertyType);

									newResult.setSearchPrice(tempRentAmount);
									newResult.setSearchAddress(tempAddress);
									newResult.setSearchIcon(img);
									newResult.setSearchTitle(tempAdvertTitle);

									// searchResult.add(newResult);
									
									adapter.add(newResult);
									adapter.notifyDataSetChanged();
									houseSearchResultCount
											.setText("Found Result: " + (i + 1));
								}

							}

						} catch (JSONException e) {
							e.printStackTrace();
						}
						pDialog.hide();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(SearchHouseActivity.this,
								"Error " + error.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});

		mQueue.add(jsonObjectRequest);
	}

	private void setUpViews() {
		searchResultList = (ListView) this
				.findViewById(R.id.searchResultListView);
		houseSearchResultCount = (TextView) this
				.findViewById(R.id.houseSearchResultCount);

		refresh = (ImageButton) this.findViewById(R.id.search_result_refresh);
		refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.clear();
				fetchPropertyResult();
			}
		});
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			onBackPressed();
		}
		return false;
	}
}
