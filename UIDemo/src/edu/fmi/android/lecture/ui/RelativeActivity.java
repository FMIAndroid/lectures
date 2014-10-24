package edu.fmi.android.lecture.ui;

import edu.fmi.android.lecture.ui.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class RelativeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relative);

		final EditText email = (EditText) findViewById(R.id.email);
		final EditText name = (EditText) findViewById(R.id.name);

		email.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("COUNT = " + count);
				if (s.length() > 0 && !s.toString().contains("@")) {
					email.setError("Not valid");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("beforeTextChanged");

			}

			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("afterTextChanged");
			}
		});

		findViewById(R.id.test).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean okay = true;
				View field = null;

				// Clear all errors
				email.setError(null);
				name.setError(null);

				// Validate and set error on fields
				if (!email.getText().toString().contains("@")) {
					okay = false;
					field = email;
					email.setError("Error!");

					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							email.setError(null);
						}
					}, 5000);
				}

				if (!name.getText().toString().contains(" ")) {
					okay = false;
					field = name;
					name.setError("Error!");
				}	

				// ...

				if (!okay) {
					field.requestFocus();
				}
			}
		});
	}
}
