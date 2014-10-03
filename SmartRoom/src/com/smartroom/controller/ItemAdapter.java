package com.smartroom.controller;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartroom.R;
import com.smartroom.model.Item;

public class ItemAdapter extends ArrayAdapter<Item> {

	Context context = null;
	ArrayList<Item> items = null;

	public ItemAdapter(Context context, ArrayList<Item> items) {
		super(context, R.layout.menu_list_item, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;

		if (row == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			row = vi.inflate(R.layout.menu_list_item, null);

		}

		Item currentItem = items.get(position);

		ImageView icon = (ImageView) row.findViewById(R.id.iconMenu);
		TextView menuTxt = (TextView) row.findViewById(R.id.menuTxt);

		icon.setImageDrawable(currentItem.getIcon());
		menuTxt.setText(currentItem.getMenuTitle());


		return row;
	}

}
