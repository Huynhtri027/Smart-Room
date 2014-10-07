package com.smartroom.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.smartroom.controller.CheckNewMessageController;
import com.smartroom.controller.SessionController;
import com.smartroom.model.PendingMessageModel;

public class MessageHandlerService extends Service {

	MessageCheckThread messageCheckThread;
	PendingMessageModel pmm = null;

	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		messageCheckThread = new MessageCheckThread();
		messageCheckThread.start();
		return 0;
	}

	@Override
	public void onDestroy() {
		messageCheckThread.stopThread();
		super.onDestroy();
	}

	class MessageCheckThread extends Thread {

		public MessageCheckThread() {
			super();
		}

		public void stopThread() {

		}

		@Override
		public void run() {
			super.run();

			while (true) {

				if (SessionController.getUserProfile() == null) {

				} else {
					pmm = CheckNewMessageController.checkMessage();
					sendBroadcast(new Intent().putExtra("messageStatus", pmm)
							.setAction("statusUpdate"));
				}

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
