package edu.fmi.android.intentdemos;

import java.util.Date;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(MainActivity.this, "" + intent.hasExtra(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT).show();
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onDetailsShow(View v) {
    	Intent details = new Intent(this, DetailsActivity.class);
    	startActivity(Intent.createChooser(details, "Open with..."));
    }
    
    public void onLoadImage(View v) {
    	Intent image = new Intent(this, ShowImageActivity.class);
    	image.addCategory(Intent.CATEGORY_BROWSABLE);
    	image.putExtra("date", new Date().toString());
    	startActivity(image);
    }
    
    public void onBroadcast(View v) {
    	Intent broadcast = new Intent("edu.fmi.android.MESSAGE");
    	broadcast.setType("text/plain");
    	broadcast.putExtra(Intent.EXTRA_TEXT, "Hello World!");	
    	LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
    }
    
    public void onInnerBroadcast(View v) {
    	Intent broadcast = new Intent("edu.fmi.android.MESSAGE");
    	broadcast.setType("text/plain");
    	// broadcast.addCategory(Intent.CATEGORY_BROWSABLE);
    	broadcast.putExtra(Intent.EXTRA_TEXT, "Hello World!");	
    	sendBroadcast(broadcast);
    }
    
    public void onNotificationClick(View v) {
    	PendingIntent pending = PendingIntent.getActivity(this, 0, new Intent(this, ShowImageActivity.class), 0);
    	
    	Notification notification = new NotificationCompat.Builder(this)
    	.addAction(new Action(R.drawable.ic_launcher, "Image", pending))
    	.setAutoCancel(true)
    	.setOngoing(true)
    	.setTicker("Notification...").build();
    	
    	NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	
    	manager.notify(777, notification);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();    	
    	
    	IntentFilter filter = new IntentFilter("edu.fmi.android.MESSAGE");
    	try {
			filter.addDataType("text/plain");
		} catch (MalformedMimeTypeException e) {
			e.printStackTrace();
		}
    	registerReceiver(mReceiver, filter);
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	unregisterReceiver(mReceiver);
    }
}
