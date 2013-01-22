package com.xxx.stepfirstninja;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {
	String tag = "Lifecycle";
	Button btnDialog;
	Button btnDialog2;
	CharSequence[] items = {"Google", "Apple", "Microsoft"};
	boolean[] itemsChecked  = new boolean[items.length];
	Activity activity;
	ProgressDialog progressDialog;
	int notificationID = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		activity = this;
		initControls();
		funcControls();
		Log.e(tag, "in the onCreate() event");
	}
	public void initControls(){
		btnDialog = (Button)findViewById(R.id.btn_dialog);
		btnDialog2 = (Button)findViewById(R.id.btn_dialog2);
	}
	public void funcControls(){
		btnDialog.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
		btnDialog2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ProgressDialog dialog = ProgressDialog.show(activity, "Doing something", "Please Wait!", true);
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(4000);
							dialog.dismiss();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}).start();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void onClick3(View view){
		showDialog(1);        
        progressDialog.setProgress(0);        
		new Thread(new Runnable(){
		            public void run(){
		                for (int i=1; i<=15; i++) {
		                    try { 
		                        Thread.sleep(1000); 
								progressDialog.incrementProgressBy((int)(100/15));
		                    } catch (InterruptedException e) {                    
		                        e.printStackTrace();
		                    }
		                }
		                progressDialog.dismiss();
		            }
		        }).start();
	}
	public void onClick(View view){
		displayNotification();
	}
	public void displayNotification(){
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationID", notificationID);
		PendingIntent pendingIntent =
	            PendingIntent.getActivity(this, 0, intent, 0);
	        NotificationManager nm = (NotificationManager)
	            getSystemService(NOTIFICATION_SERVICE); 
	        Notification notif = new Notification(
	            R.drawable.ic_launcher, 
	            "Reminder: Meeting starts in 5 minutes",
	            System.currentTimeMillis());
	        CharSequence from = "System Alarm";
	        CharSequence message = "Meeting with customer at 3pm...";
	        
	        notif.setLatestEventInfo(this, from, message, pendingIntent);
			//---100ms delay, vibrate for 250ms, pause for 100 ms and
			// then vibrate for 500ms---
	        notif.vibrate = new long[] { 100, 250, 100, 500};
	        nm.notify(notificationID, notif); 
	}
	@Override
	protected Dialog onCreateDialog(int id){
		switch (id) {
		case 0:
			return new AlertDialog.Builder(this).
					setIcon(R.drawable.ic_launcher).
					setTitle("This is a dialog with a simple text....").
					setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(), "OK clicked!", Toast.LENGTH_SHORT).show();
						} 
					}).
					setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
						}
					}).
					setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(),
									items[which] + (isChecked ? " checked!":" unchecked!"), 
		                            Toast.LENGTH_SHORT).show();
						}
					}).create(); 
		case 1:
			progressDialog = new ProgressDialog(this);
			progressDialog.setIcon(R.drawable.ic_launcher);
			progressDialog.setTitle("Downloading...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getBaseContext(), "OK clicked!", 
							Toast.LENGTH_SHORT).show();
				}
			}); 
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getBaseContext(), "Cancel clicked!", 
							Toast.LENGTH_SHORT).show();
				}
			});
			return progressDialog; 
		default:
			break;
		}
		return null;
	}
	
	public void onClickDragAndDrop(View view){
		Intent intent = new Intent(this, DragAndDropActivity.class);
		startActivity(intent);
	}
	public void onStart(){
		super.onStart();
		Log.e(tag, "in the onStart() event");
	}
	public void onResume(){
		super.onResume();
		Log.e(tag, "in the onResume() event");
	}
	public void onPause(){
		super.onPause();
		Log.e(tag, "in the onPause() event");
	}
	public void onStop(){
		super.onStop();
		Log.e(tag, "in the onStop() event");
	}
	public void onRestart(){
		super.onRestart();
		Log.e(tag, "in the onRestart() event");
	}
	public void onDestroy(){
		super.onDestroy();
		Log.e(tag, "in the onStop() event");
	}
	

}
