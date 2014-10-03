package com.smartroom.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartroom.R;

public class SettingsFragment extends Fragment {
	private View parentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_settings, container,
				false);
		return parentView;
	}

}
