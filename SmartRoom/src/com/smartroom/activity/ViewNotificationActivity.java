package com.smartroom.activity;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.smartroom.R;
import com.smartroom.model.PendingMessageModel;

public class ViewNotificationActivity extends Activity {

	private PendingMessageModel notificationMessage = null;
	private ArrayList<PendingMessageModel> notificationList = null;
	private LinearLayout notificationLayout = null;
	private LinearLayout.LayoutParams params = null;
	private LinearLayout LL = null;
	private TextView notificationRefTxt = null, notificationMsgTxt = null,
			notificationMsgIDTxt = null, notificationSenderIDTxt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notification);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();

		notificationList = (ArrayList<PendingMessageModel>) intent
				.getSerializableExtra("key");

		MemberActivity.mNotificationCount = 0;
		MemberActivity.pendingMessageList.clear();

		notificationLayout = (LinearLayout) findViewById(R.id.notificationLayout);

		Iterator<PendingMessageModel> iterator = notificationList.iterator();

		while (iterator.hasNext()) {
			notificationMessage = iterator.next();

			LL = new LinearLayout(ViewNotificationActivity.this);
			LL.setOrientation(LinearLayout.VERTICAL);
			LL.setBackground(getResources().getDrawable(R.drawable.border));
			LL.setPadding(10, 10, 10, 10);
			
			params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 0, 0, 10);
			LL.setLayoutParams(params);

			notificationRefTxt = new TextView(ViewNotificationActivity.this);
			notificationMsgTxt = new TextView(ViewNotificationActivity.this);
			notificationMsgIDTxt = new TextView(ViewNotificationActivity.this);
			notificationSenderIDTxt = new TextView(
					ViewNotificationActivity.this);

			notificationRefTxt.setTextColor(Color.WHITE);
			notificationMsgTxt.setTextColor(Color.WHITE);
			notificationMsgIDTxt.setTextColor(Color.WHITE);
			notificationSenderIDTxt.setTextColor(Color.WHITE);

			notificationRefTxt
					.setText("Notification ReferenceNumber: #"
							+ notificationMessage.getAdvertReferenceNumber()
									.toString());
			notificationMsgTxt.setText("Received Message: "
					+ notificationMessage.getMessage().toString());
			notificationMsgIDTxt.setText("Message ID: #"
					+ notificationMessage.getMessageID().toString());
			notificationSenderIDTxt.setText("Sender: "
					+ notificationMessage.getSendeID().toString());

			LL.addView(notificationRefTxt);
			LL.addView(notificationMsgTxt);
			LL.addView(notificationMsgIDTxt);
			LL.addView(notificationSenderIDTxt);

			notificationLayout.addView(LL);

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		default:
			onBackPressed();
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}
}
