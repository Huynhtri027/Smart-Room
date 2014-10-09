package com.smartroom.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.smartroom.R;
import com.smartroom.controller.AdvertController;
import com.smartroom.controller.SessionController;
import com.smartroom.model.HouseProperty;
import com.smartroom.model.LatitudeLocationModel;
import com.smartroom.service.GPSTrackerService;
import com.smartroom.utilities.DirectionsJSONParser;
import com.smartroom.utilities.GetAddress;
import com.smartroom.utilities.Utils;

public class ViewAdvertActivity extends Activity {

	private String advertiserPhoneNumber = "07473048636";
	String refID = "", msg = "", sender = "";
	private TextView fullNameTxt, telephoneTxt, refNumTxt, advertTitle,
			description;
	private TextView sellerType, bedNum, propType, rentAmount, priceFrequency,
			deposit;
	private TextView postcode, address, country, furnishing, availability,
			reference, parking, garden, garage, balcony, disabledAccess,
			broadband, bills;
	private String telToRing = null;
	private ImageView pic = null;
	private ScrollView sv = null;

	private HouseProperty viewProperty = null;
	private GoogleMap map;
	private ArrayList<LatLng> markerPoints;
	private static String mode = "walking";

	LatitudeLocationModel postcodeLocation = null;
	Handler handler = new Handler();
	Spinner sp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_advert);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();

		setUpViews();

		refID = intent.getStringExtra("advertReference");

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				try {
					viewProperty = AdvertController.getHouseProperty(refID);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (viewProperty != null) {
					initMap();
					if (viewProperty.isDisplayFullName()) {
						fullNameTxt.setText(viewProperty.getAdvertiserTitle()
								+ " " + viewProperty.getAdvertiserFullName());
					} else {
						fullNameTxt.setText("No name to display.");
					}
					if (viewProperty.isDisplayTelephone()) {
						telephoneTxt.setText(viewProperty
								.getAdvertiserTelephone());
						telToRing = viewProperty.getAdvertiserTelephone();
					} else {
						telephoneTxt.setText("No telephone to display.");
					}

					refNumTxt.setText("#" + refID);
					advertTitle.setText(viewProperty.getAdvertTtile());
					description.setText(viewProperty.getAdvertDescription());
					sellerType.setText(viewProperty.getSellerType());
					bedNum.setText(viewProperty.getBedNum());
					propType.setText(viewProperty.getPropertyType());
					rentAmount.setText(viewProperty.getRentAmount());
					priceFrequency.setText(viewProperty.getPriceFrequency());
					deposit.setText(viewProperty.getDepositAmount());
					postcode.setText(viewProperty.getPostcode());
					address.setText(viewProperty.getAddress());
					country.setText(viewProperty.getCountry());
					furnishing.setText(viewProperty.getFurnishing());
					availability.setText(viewProperty.getAvailabilityDate());
					if (viewProperty.isReferenceRequired()) {
						reference.setText("YES");
					} else {
						reference.setText("NO");
					}
					if (viewProperty.isParkingAvailable()) {
						parking.setText("YES");
					} else {
						parking.setText("NO");
					}

					if (viewProperty.isGardenAvailable()) {
						garden.setText("YES");
					} else {
						garden.setText("NO");
					}

					if (viewProperty.isGarageAvailable()) {
						garage.setText("YES");
					} else {
						garage.setText("NO");
					}

					if (viewProperty.isBalconyAvailable()) {
						balcony.setText("YES");
					} else {
						balcony.setText("NO");
					}

					if (viewProperty.isDisabledAcessAvailable()) {
						disabledAccess.setText("YES");
					} else {
						disabledAccess.setText("NO");
					}

					if (viewProperty.isBroadband()) {
						broadband.setText("YES");
					} else {
						broadband.setText("NO");
					}
					if (viewProperty.isBillIncluded()) {
						bills.setText("YES");
					} else {
						bills.setText("NO");
					}

					Drawable drawable = new BitmapDrawable(viewProperty
							.getHousePic());

					pic.setImageDrawable(drawable);
					handler.removeCallbacksAndMessages(null);

				} else {
					handler.postDelayed(this, 100);
				}

			}
		}, 100);

	}

	public void initMap() {

		// map.clear();

		try {

			postcodeLocation = GetAddress.postcodeToLatLon(
					ViewAdvertActivity.this, viewProperty.getPostcode());

			map = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.viewAdvertMap)).getMap();

			if (map != null) {

				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				map.setMyLocationEnabled(true);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(postcodeLocation.getLatitude(),
								postcodeLocation.getLongitude()), 16));

				map.clear();

				map.addMarker(new MarkerOptions()
						.title("Address: " + viewProperty.getAddress() + " \n"
								+ "Postcode: " + viewProperty.getPostcode())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.ic_map_marker))
						.anchor(0.0f, 1.0f)
						.position(
								new LatLng(postcodeLocation.getLatitude(),
										postcodeLocation.getLongitude())));

				addMakerEvent();

			} else {
				Toast.makeText(ViewAdvertActivity.this,
						"Unable to create Map!", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addMakerEvent() {
		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {

				Toast.makeText(
						ViewAdvertActivity.this,
						"Navigation start from your current location to "
								+ marker.getTitle(), Toast.LENGTH_SHORT).show();

				double currLatitude = 0.0;
				double currLongitude = 0.0;

				try {
					GPSTrackerService gps = new GPSTrackerService(
							ViewAdvertActivity.this);
					currLatitude = (double) gps.getLatitude();
					currLongitude = (double) gps.getLongitude();
				} catch (Exception e) {
					currLatitude = 51.5088655;
					currLongitude = -0.0690414000000601;
				}

				if (markerPoints.size() > 1) {
					markerPoints.clear();
					map.clear();
				}

				LatLng sourcepoint = new LatLng(currLatitude, currLongitude);
				LatLng destpoint = new LatLng(postcodeLocation.getLatitude(),
						postcodeLocation.getLongitude());
				markerPoints.add(sourcepoint);
				markerPoints.add(destpoint);

				MarkerOptions options = new MarkerOptions();

				// Setting the position of the marker
				options.position(sourcepoint);

				if (markerPoints.size() == 1) {
					options.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				} else if (markerPoints.size() == 2) {
					options.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				}

				map.addMarker(options);

				if (markerPoints.size() >= 2) {

					LatLng origin = markerPoints.get(0);
					LatLng dest = markerPoints.get(1);

					askMode(origin, dest);

				}

				return false;
			}

			private void askMode(final LatLng origin, final LatLng dest) {
				final Dialog dialog = new Dialog(ViewAdvertActivity.this);
				dialog.setContentView(R.layout.route_option);
				sp = (Spinner) dialog.findViewById(R.id.viewSpin);
				Button ok = (Button) dialog.findViewById(R.id.search_mode);
				ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mode = sp.getSelectedItem().toString();

						// Getting URL to the Google Directions API
						String url = getDirectionsUrl(origin, dest);

						DownloadTask downloadTask = new DownloadTask();

						// Start downloading json data from Google
						// Directions
						// API
						downloadTask.execute(url);
						dialog.dismiss();
					}
				});
				dialog.setTitle("Select Option");
				dialog.show();

			}

		});
	}

	private void setUpViews() {
		fullNameTxt = (TextView) this.findViewById(R.id.viewAdvertFullName);
		telephoneTxt = (TextView) this.findViewById(R.id.viewAdvertTelephone);
		refNumTxt = (TextView) this
				.findViewById(R.id.viewAdvertReferenceNumber);
		advertTitle = (TextView) this.findViewById(R.id.viewAdvertTitle);
		description = (TextView) this.findViewById(R.id.viewAdvertDescription);
		sellerType = (TextView) this.findViewById(R.id.viewAdvertSellerType);
		bedNum = (TextView) this.findViewById(R.id.viewAdvertBedNum);
		propType = (TextView) this.findViewById(R.id.viewAdvertPropertyType);
		rentAmount = (TextView) this.findViewById(R.id.viewAdvertRentAmount);
		priceFrequency = (TextView) this
				.findViewById(R.id.viewAdvertPriceFrequency);
		deposit = (TextView) this.findViewById(R.id.viewAdvertDepositAmount);
		postcode = (TextView) this.findViewById(R.id.viewAdvertPostcode);
		address = (TextView) this.findViewById(R.id.viewAdvertAddress);
		country = (TextView) this.findViewById(R.id.viewAdvertCountry);
		furnishing = (TextView) this.findViewById(R.id.viewAdvertFurnishing);
		availability = (TextView) this
				.findViewById(R.id.viewAdvertAvailabilityDate);
		reference = (TextView) this
				.findViewById(R.id.viewAdvertReferenceRequired);
		parking = (TextView) this.findViewById(R.id.viewAdvertParkingAvailable);
		garden = (TextView) this.findViewById(R.id.viewAdvertGardenAvailable);
		garage = (TextView) this.findViewById(R.id.viewAdvertGarageAvailable);
		balcony = (TextView) this.findViewById(R.id.viewAdvertBalconyAvailable);
		disabledAccess = (TextView) this
				.findViewById(R.id.viewAdvertDisabledAccess);
		broadband = (TextView) this.findViewById(R.id.viewAdvertBroadband);
		bills = (TextView) this.findViewById(R.id.viewAdvertBillIncluded);
		pic = (ImageView) this.findViewById(R.id.viewAdvertPic);

		sv = (ScrollView) findViewById(R.id.viewAdverscrollView);

		markerPoints = new ArrayList<LatLng>();

		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout1);
		rl.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// Disallow ScrollView to intercept touch events.
					sv.requestDisallowInterceptTouchEvent(true);
					// Disable touch on transparent view
					return false;

				case MotionEvent.ACTION_UP:
					// Allow ScrollView to intercept touch events.
					sv.requestDisallowInterceptTouchEvent(false);
					return true;

				case MotionEvent.ACTION_MOVE:
					sv.requestDisallowInterceptTouchEvent(true);
					return false;

				default:
					return true;
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.send_message:
			sendMessage();
			return true;
		case R.id.call_advertiser:
			callAdvertiser();
			return true;
		case R.id.share_advert:
			shareAdvert(6);
			return true;
		case R.id.save_ad:
			saveAd();
			return true;
		default:
			onBackPressed();
			return super.onOptionsItemSelected(item);
		}
	}

	private void saveAd() {

		Toast.makeText(ViewAdvertActivity.this, "Advert Saved!",
				Toast.LENGTH_SHORT).show();
	}

	private void shareAdvert(int refID) {

		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		String shareBody = "Hey Look this Advert on Smart Room. Advert Reference number is #"
				+ refID;
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"Smart Room");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}

	private void sendMessage() {

		final Dialog dialog = new Dialog(ViewAdvertActivity.this);
		dialog.setContentView(R.layout.send_message_custom);
		final EditText theMessage = (EditText) dialog
				.findViewById(R.id.sendCustomMessageTxt);

		Button send = (Button) dialog.findViewById(R.id.sendCustomMessageBtn);

		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				msg = theMessage.getText().toString();

				final ProgressDialog pDialog = new ProgressDialog(Utils
						.getCurrentActivity());
				pDialog.setMessage("Sending Message ...");
				pDialog.show();

				RequestQueue queue = Volley
						.newRequestQueue(ViewAdvertActivity.this);

				StringRequest postRequest = new StringRequest(
						Request.Method.POST, Utils.sendMessagetUrl,
						new Response.Listener<String>() {
							@Override
							public void onResponse(String response) {

								if (response.equals("0")) {
									Toast.makeText(
											Utils.getMainContext(),
											"Message Sending Failed: Please try again!",
											Toast.LENGTH_LONG).show();
									pDialog.hide();
									return;
								} else {

									Toast.makeText(Utils.getMainContext(),
											"Message Sent!", Toast.LENGTH_LONG)
											.show();
								}

								pDialog.hide();
								finish();

							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Toast.makeText(
										Utils.getCurrentActivity(),
										"Sorry there was a problem while trying to Send Message!",
										Toast.LENGTH_LONG).show();
								pDialog.hide();
								finish();
							}
						}) {
					@Override
					protected Map<String, String> getParams() {
						Map<String, String> params = new HashMap<String, String>();
						params.put("advertRefNum", refID);
						params.put("msg", msg);

						if (SessionController.getUserProfile() != null) {
							params.put("senderID", SessionController
									.getUserProfile().getEmail());
						} else {
							params.put("senderID", "Guest");
						}

						return params;
					}
				};
				queue.add(postRequest);

				Toast.makeText(ViewAdvertActivity.this, "Message Sent!",
						Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		dialog.setTitle("Send Message");
		dialog.show();

	}

	private void callAdvertiser() {

		if (telToRing == null) {
			Toast.makeText(ViewAdvertActivity.this,
					"Sorry You can't call the advertiser of this Post!",
					Toast.LENGTH_SHORT).show();
		} else {
			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ advertiserPhoneNumber));
			startActivity(callIntent);
		}

	}

	@Override
	public void onBackPressed() {
		finish();
	}

	private String getDirectionsUrl(LatLng origin, LatLng dest) {

		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor
				+ "&mode=" + mode;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;
		return url;
	}

	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTaskDirection parserTask = new ParserTaskDirection();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTaskDirection extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(5);
				lineOptions.color(Color.MAGENTA);

			}

			// Drawing polyline in the Google Map for the i-th route
			map.addPolyline(lineOptions);
		}
	}

	private String downloadUrl(String strUrl) throws IOException {
		Log.e("URL", strUrl);
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {

			URL url = new URL(strUrl);

			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.connect();

			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}

		return data;
	}
}