package com.smartroom.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.activity.SearchHouseActivity;
import com.smartroom.utilities.Utils;

public class SearchFragment extends Fragment {
	private View parentView;
	private Button searchProperty, searchPeople;
	private EditText search = null;
	private RadioButton radioCurrLocation = null;
	private RadioButton radioCustLocation = null;
	private RadioButton radioAllLocation = null;
	private String searchQuery = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_search, container,
				false);

		setUpViews();

		return parentView;
	}

	private void setUpViews() {

		search = (EditText) parentView.findViewById(R.id.searchLocation);

		searchProperty = (Button) parentView
				.findViewById(R.id.loggedHouseSearch);
		searchProperty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent searchHouseIntent = new Intent(getActivity(),
						SearchHouseActivity.class);
				searchQuery = getSearchQuery();
				if (searchQuery.equals("NO")) {
					return;
				}
				searchHouseIntent.putExtra("searchValue", searchQuery);
				getActivity().startActivity(searchHouseIntent);
			}
		});

		searchPeople = (Button) parentView
				.findViewById(R.id.loggedPeopleSearch);
		searchPeople.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "No Functionality Added Yet!",
						Toast.LENGTH_LONG).show();
			}
		});

		radioCurrLocation = (RadioButton) parentView
				.findViewById(R.id.loggedRadio0);
		radioCurrLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				search = (EditText) parentView
						.findViewById(R.id.searchLocation);
				search.setText("");
				search.setVisibility(View.GONE);
				Utils.hideKeyboard(getActivity(), search);
			}
		});

		radioCustLocation = (RadioButton) parentView
				.findViewById(R.id.loggedRadio1);
		radioCustLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search = (EditText) parentView
						.findViewById(R.id.searchLocation);
				search.setText("");
				search.setVisibility(View.VISIBLE);
				Utils.showKeyboard(getActivity(), search);
			}
		});

		radioAllLocation = (RadioButton) parentView
				.findViewById(R.id.loggedRadio2);
		radioAllLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search = (EditText) parentView
						.findViewById(R.id.searchLocation);
				search.setText("");
				search.setVisibility(View.GONE);
				Utils.hideKeyboard(getActivity(), search);
			}
		});

	}

	public String getSearchQuery() {
		if (radioAllLocation.isChecked()) {
			searchQuery = "";
		} else if (radioCurrLocation.isChecked()) {
			Toast.makeText(
					getActivity(),
					"Functionality Partially Completed! Not working at the Moment.",
					Toast.LENGTH_LONG).show();
			searchQuery = "NO";
		} else if (radioCustLocation.isChecked()) {
			searchQuery = search.getText().toString();
		}

		return searchQuery;
	}
}
