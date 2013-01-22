package com.xxx.stepfirstninja;

import android.app.Activity;
import android.os.Bundle;

public class DragAndDropActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.drag_and_drop_activity);
	}
}
