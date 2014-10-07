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
import com.smartroom.model.MessageModel;

public class MessagesAdapter extends ArrayAdapter<MessageModel> {

	private ArrayList<MessageModel> listData = null;
	Context context = null;

	public MessagesAdapter(Context context, ArrayList<MessageModel> listData) {
		super(context, R.layout.message_list_itesm, listData);
		this.context = context;
		this.listData = listData;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			convertView = vi.inflate(R.layout.message_list_itesm, null);

			holder = new ViewHolder();
			holder.messageDate = (TextView) convertView
					.findViewById(R.id.viewMessageDate);
			holder.messageBody = (TextView) convertView
					.findViewById(R.id.viewMessageMessageBody);
			holder.messageSender = (TextView) convertView
					.findViewById(R.id.viewMessageSender);
			holder.messageRefNum = (TextView) convertView
					.findViewById(R.id.viewMessageAdvertReference);
			holder.messageIcon = (ImageView) convertView
					.findViewById(R.id.viewMessageIcon);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		MessageModel messageItem = (MessageModel) listData.get(position);

		holder.messageDate.setText("Date and Time: "
				+ messageItem.getPostDate());
		holder.messageBody.setText("Received Message: "
				+ messageItem.getMessage());
		holder.messageSender.setText("Sender ID: " + messageItem.getSenderID());
		holder.messageRefNum.setText("Reference Number: #"
				+ messageItem.getAdvertReference());

		Drawable drawable = new BitmapDrawable(messageItem.getMessageIcon());

		holder.messageIcon.setImageDrawable(drawable);

		return convertView;
	}

	static class ViewHolder {
		TextView messageDate;
		TextView messageBody;
		TextView messageSender;
		TextView messageRefNum;
		ImageView messageIcon;
	}

}