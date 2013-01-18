package com.xxx.stepfirstninja;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;

public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(getIntent().getExtras().getInt("notificationID"));
		
	}
 

}
