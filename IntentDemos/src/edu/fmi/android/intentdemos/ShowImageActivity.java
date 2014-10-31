package edu.fmi.android.intentdemos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowImageActivity extends Activity {
	
	private static final int REQUEST_IMAGE = 777;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_image);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		if (getIntent().hasExtra("date")) {
			final TextView view = (TextView) findViewById(R.id.date);
			view.setText(getIntent().getStringExtra("date"));
		}
	}
	
	public void loadImage(View v) {
		Intent load = new Intent(Intent.ACTION_GET_CONTENT);
		load.setType("image/*");
		
		startActivityForResult(load, REQUEST_IMAGE);
		//						^ Intent.createChooser(...)
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_IMAGE) {				
				Uri image = data.getData();
				Log.w("ShowImageActivity", image.getPath());
				
				final ImageView view = (ImageView) findViewById(R.id.image);
				view.setImageURI(image);
			}
		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Why....", Toast.LENGTH_SHORT).show();
		}
	}
}












