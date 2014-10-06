package com.smartroom.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartroom.R;
import com.smartroom.activity.SearchHouseActivity;

public class SearchFragment extends Fragment {
	private View parentView;
	private Button searchProperty;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_search, container,
				false);

		setUpViews();

		return parentView;
	}

	private void setUpViews() {
		searchProperty = (Button) parentView
				.findViewById(R.id.loggedHouseSearch);

		searchProperty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent searchHouseIntent = new Intent(getActivity(),
						SearchHouseActivity.class);
				getActivity().startActivity(searchHouseIntent);
			}
		});
	}

}
