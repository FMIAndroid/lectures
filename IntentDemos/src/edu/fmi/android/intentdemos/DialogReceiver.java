package edu.fmi.android.intentdemos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DialogReceiver extends BroadcastReceiver {
	public DialogReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println(intent.getAction());
		final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
		
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}