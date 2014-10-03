package com.smartroom.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.smartroom.R;

public class Map extends Fragment {

	private GoogleMap map;
	private View parentView;

	public static Context context = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (parentView != null) {
			ViewGroup parent = (ViewGroup) parentView.getParent();
			if (parent != null) {
				parent.removeView(parentView);
			}
		}
		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

		


		/*
		 * Handler handler = new Handler(); handler.postDelayed(new Runnable()
		 * 
		 * @Override public void run() { GoogleMap googleMap =
		 * SupportMapFragment.newInstance(new
		 * GoogleMapOptions().zOrderOnTop(true)).getMap(); FragmentTransaction
		 * ft = getFragmentManager().beginTransaction();
		 * ft.replace(R.id.map_content, fragment); ft.commit(); if(googleMap !=
		 * null) { googleMap.addMarker(new
		 * MarkerOptions().position(result)).setVisible(true);
		 * 
		 * // Move the camera instantly to location with a zoom of 15.
		 * googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(result, 15));
		 * 
		 * // Zoom in, animating the camera.
		 * googleMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
		 * 
		 * googleMap.getUiSettings().setZoomControlsEnabled(false);
		 * googleMap.getUiSettings().setCompassEnabled(false);
		 * googleMap.getUiSettings().setMyLocationButtonEnabled(false);
		 * 
		 * handler.removeCallbacksAndMessages(null); }
		 * 
		 * else { handler.postDelayed(this, 500); } }, 500); }
		 */

		parentView = inflater.inflate(R.layout.fragment_map, container, false);
		setUpViews();
		return parentView;
	}

	private void setUpViews() {

	}

	private void initilizeMap() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (map == null) {
				Toast.makeText(context, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initilizeMap();
	}
}
