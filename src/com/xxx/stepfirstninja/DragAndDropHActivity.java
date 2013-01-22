package com.xxx.stepfirstninja;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class DragAndDropHActivity extends Activity{
	private TextView option1;
	private TextView option2;
	private TextView option3;
	private TextView choice1;
	private TextView choice2;
	private TextView choice3;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.drag_and_drop_horizontal_activity);
		initControls();
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());
	}
	
	private void initControls(){
		option1 = (TextView)findViewById(R.id.hoption1);
		option2 = (TextView)findViewById(R.id.hoption2);
		option3 = (TextView)findViewById(R.id.hoption3);
		choice1 = (TextView)findViewById(R.id.hchoice1);
		choice2 = (TextView)findViewById(R.id.hchoice2);
		choice3 = (TextView)findViewById(R.id.hchoice3);
	}
	private final class ChoiceTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				ClipData data = ClipData.newPlainText("a", "b");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				view.startDrag(data, shadowBuilder, view, 0);
				return true;
			} 
			return false;
		} 
	}
	private class ChoiceDragListener implements OnDragListener{

		@Override
		public boolean onDrag(View view, DragEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case DragEvent.ACTION_DROP:
				View v = (View)event.getLocalState();
				v.setVisibility(View.INVISIBLE);
				
				TextView dropTarget = (TextView)view;
				TextView dropped = (TextView)v;
				
				dropTarget.setText(dropped.getText());
				dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
				Object tag = dropTarget.getTag();
				if(tag != null){
					int existID = (Integer)tag;
					findViewById(existID).setVisibility(View.VISIBLE);
				}
				dropTarget.setTag(dropped.getId());
				break; 
			}
			return true;
		}
		
	}
}
