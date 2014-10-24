package edu.fmi.android.lecture.ui;

import edu.fmi.android.lecture.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mRelativeLayoutButton;
	private Button mFrameLayoutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRelativeLayoutButton = (Button) findViewById(R.id.relativelayout);

		mFrameLayoutButton = (Button) findViewById(R.id.framelayout);
		mFrameLayoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent frame = new Intent(MainActivity.this, FrameActivity.class);
				// Alternative:					^ getApplicationContext();
				startActivity(frame);
			}
		});

		findViewById(R.id.kaboom).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// AND after 5 seconds
				SystemClock.sleep(10000);
				// ^ same as Thread.sleep but without need of try-catch
				Toast.makeText(MainActivity.this, "Download ready!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	// onClick defined in XML
	public void relativeLayout(View v) {
		Toast.makeText(this, "Clicked relative layout!", Toast.LENGTH_SHORT).show();

		final Intent relative = new Intent(this, RelativeActivity.class);
		startActivity(relative);
	}
}
