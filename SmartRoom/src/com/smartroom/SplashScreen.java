package com.smartroom;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartroom.activity.MainActivity;
import com.smartroom.activity.MemberActivity;

public class SplashScreen extends RoboActivity {

	private @InjectView(R.id.load_textview)
	TextView welcomeText;

	private ProgressBar spinner = null;

	Handler handler = new Handler();
	int progressStatus = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		spinner = (ProgressBar) this.findViewById(R.id.progressBar1);

		new Thread(new Runnable() {

			int i = 0;

			public void run() {
				while (progressStatus < 2) {
					progressStatus += doWork();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					handler.post(new Runnable() {
						public void run() {
							spinner.setProgress(progressStatus);
							i++;
						}
					});
				}

				SharedPreferences app_preferences = PreferenceManager
						.getDefaultSharedPreferences(SplashScreen.this);

				boolean isUserLoggedIn = app_preferences.getBoolean(
						"Online Status", false);

				if (isUserLoggedIn) {
					final Intent mainIntent = new Intent(SplashScreen.this,
							MemberActivity.class);
					SplashScreen.this.startActivity(mainIntent);
					SplashScreen.this.finish();
				} else {
					final Intent mainIntent = new Intent(SplashScreen.this,
							MainActivity.class);
					SplashScreen.this.startActivity(mainIntent);
					SplashScreen.this.finish();
				}

			}

			private int doWork() {

				return i++;
			}

		}).start();

	}

}
