package com.smartroom.controller;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartroom.R;
import com.smartroom.model.PropertySearchResultModel;

public class HouseSearchResultAdapter extends
		ArrayAdapter<PropertySearchResultModel> {

	private ArrayList<PropertySearchResultModel> listData = null;
	Context context = null;

	public HouseSearchResultAdapter(Context context,
			ArrayList<PropertySearchResultModel> listData) {
		super(context, R.layout.house_search_list_item, listData);
		this.context = context;
		this.listData = listData;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		

		if (convertView == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			convertView = vi.inflate(R.layout.house_search_list_item, null);

			holder = new ViewHolder();
			holder.SHPropType = (TextView) convertView
					.findViewById(R.id.searchHousePropertyType);
			holder.SHPropAddress = (TextView) convertView
					.findViewById(R.id.searchHouseAddress);
			holder.SHPropPrice = (TextView) convertView
					.findViewById(R.id.searchHousePrice);
			holder.SHPropTitle = (TextView) convertView
					.findViewById(R.id.searchHouseTitle);
			holder.SHPropRefNum = (TextView) convertView
					.findViewById(R.id.searchHouseReferenceNumber);
			holder.SHPropIcon = (ImageView) convertView
					.findViewById(R.id.searchHousePhoto);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PropertySearchResultModel newSearchResultItem = (PropertySearchResultModel) listData
				.get(position);

		holder.SHPropType.setText(newSearchResultItem.getSearchPropertyType());
		holder.SHPropAddress.setText(newSearchResultItem.getSearchAddress());
		holder.SHPropPrice.setText(newSearchResultItem.getSearchPrice());
		holder.SHPropTitle.setText(newSearchResultItem.getSearchTitle());
		holder.SHPropRefNum.setText(newSearchResultItem.getSearchRefNumber());


		Drawable drawable = new BitmapDrawable(
				newSearchResultItem.getSearchIcon());

		holder.SHPropIcon.setImageDrawable(drawable);

		return convertView;
	}

	static class ViewHolder {
		TextView SHPropType;
		TextView SHPropAddress;
		TextView SHPropPrice;
		TextView SHPropTitle;
		TextView SHPropRefNum;
		ImageView SHPropIcon;
	}

}