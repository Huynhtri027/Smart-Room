package com.smartroom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.smartroom.view.PropertyAdvertStep2Fragment;

public class CapturePictureActivity extends Activity {

	int CAMERA_PIC_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent camera_intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(camera_intent, CAMERA_PIC_REQUEST);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
				PropertyAdvertStep2Fragment.propertyAdvertPicImg
						.setImageBitmap(thumbnail);
				finish();
			}
		default:
			Toast.makeText(
					CapturePictureActivity.this,
					"You Canceled to take picture, Default picture will be applied!",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
