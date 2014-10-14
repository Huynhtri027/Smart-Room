package com.smartroom.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartroom.R;
import com.smartroom.controller.DBHelper;
import com.smartroom.controller.HouseSearchResultAdapter;
import com.smartroom.model.FilterPreferenceModel;
import com.smartroom.model.PropertySearchResultModel;
import com.smartroom.utilities.Utils;

public class SearchHouseActivity extends Activity {

	private ListView searchResultList = null;
	HouseSearchResultAdapter adapter = null;
	private ArrayList<PropertySearchResultModel> searchResult = new ArrayList<PropertySearchResultModel>();
	private RequestQueue mQueue;
	private PropertySearchResultModel newResult = null;
	JSONArray results;
	private JSONObject jresponse;
	private TextView houseSearchResultCount = null;
	private DBHelper dbHelper = null;
	private FilterPreferenceModel preference = null;
	private String searchVal = null;
	private int count = 0;

	ImageButton refresh = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_search_result);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		searchVal = intent.getStringExtra("searchValue");

		dbHelper = new DBHelper(SearchHouseActivity.this);
		dbHelper.open();
		this.preference = dbHelper.retrivePreferences();
		dbHelper.close();

		setUpViews();

		adapter = new HouseSearchResultAdapter(SearchHouseActivity.this,
				searchResult);
		searchResultList.setAdapter(adapter);
		fetchPropertyResult();
		searchResultList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				TextView referenceNumber = (TextView) v
						.findViewById(R.id.searchHouseReferenceNumber);

				String refId = referenceNumber.getText().toString();

				Intent viewAdvert = new Intent(SearchHouseActivity.this,
						ViewAdvertActivity.class);
				viewAdvert.putExtra("advertReference", refId);
				startActivity(viewAdvert);

			}
		});

	}

	/**
	 * This is the fetchPropertyResult method which shows a ProgressDialog while
	 * fetching JSON data from Apache MySQL Server.
	 * 
	 * @return void.
	 * 
	 */

	public void fetchPropertyResult() {

		houseSearchResultCount.setText("Found Result: 0");

		mQueue = Volley.newRequestQueue(SearchHouseActivity.this);

		final ProgressDialog pDialog = new ProgressDialog(
				SearchHouseActivity.this);
		pDialog.setMessage("Fetching Property Result....");
		pDialog.setCancelable(true);
		pDialog.show();

		StringRequest postRequest = new StringRequest(Request.Method.POST,
				Utils.searchPropertytUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						try {
							jresponse = new JSONObject(response);

							if (jresponse.getString("success").equals("0")) {
								Toast.makeText(Utils.getMainContext(),
										"Sorry No Property Result Found!",
										Toast.LENGTH_LONG).show();
								pDialog.hide();
								return;
							} else {

								results = jresponse
										.getJSONArray("property_adverts");

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
									count++;

								}
								if (count == 0) {
									Toast.makeText(
											Utils.getCurrentActivity(),
											"Sorry No Property Search Results Found!",
											Toast.LENGTH_LONG).show();
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
								"Sorry there was a problem while fetching Property Search Results!",
								Toast.LENGTH_LONG).show();
						pDialog.hide();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();

				params.put("searchValue", searchVal);
				params.put("minPrice", preference.getMinPrice());
				params.put("maxPrice", preference.getMaxPrice());
				params.put("minBed", preference.getMinBed());
				params.put("maxBed", preference.getMaxBed());
				params.put("sellerType", preference.getSellerType());
				params.put("propertyType", preference.getPropertyType());
				params.put("searchDistance", preference.getSearchDistance());
				params.put("addedDate", preference.getAddedDate());
				params.put("sortBy", preference.getSortBy());
				params.put("priceFrequency", preference.getPriceFrequency());

				return params;
			}
		};

		mQueue.add(postRequest);

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
