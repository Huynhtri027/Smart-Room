package com.smartroom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartroom.R;

public class TabFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View android = inflater.inflate(R.layout.fragment_no_network,
				container, false);

		return android;
	}
}