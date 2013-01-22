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

public class DragAndDropActivity extends Activity{
	private TextView option1;
	private TextView option2;
	private TextView option3;
	private TextView choice1;
	private TextView choice2;
	private TextView choice3;
	private Object tag = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.drag_and_drop_activity);
		initControls();
		//set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		
		//set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());
	}
	private void initControls(){
		//views to drag
		option1 = (TextView)findViewById(R.id.option_1);
		option2 = (TextView)findViewById(R.id.option_2);
		option3 = (TextView)findViewById(R.id.option_3);
		//views to drop onto
		choice1 = (TextView)findViewById(R.id.choice_1);
		choice2 = (TextView)findViewById(R.id.choice_2);
		choice3 = (TextView)findViewById(R.id.choice_3);
	}
	
	private final class ChoiceTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				//setup drag
				ClipData data = ClipData.newPlainText("", "");
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
			    case DragEvent.ACTION_DRAG_STARTED:
			        //no action necessary
			        break;
			    case DragEvent.ACTION_DRAG_ENTERED:
			        //no action necessary
			        break;
			    case DragEvent.ACTION_DRAG_EXITED:
			        //no action necessary
			        break;
			    case DragEvent.ACTION_DROP:
			        //handle the dragged view being dropped over a drop view
			    	View v = (View)event.getLocalState();
			    	//stop displaying the view where it was before it was dragged
			    	v.setVisibility(View.INVISIBLE);
			    	//view dragged item is being dropped on
			    	TextView dropTarget = (TextView)view;
			    	//view being dragged and dropped
			    	TextView dropped = (TextView) v;
			    	
			    	dropTarget.setText(dropped.getText());
			    	dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
			    	Object tag = dropTarget.getTag();
			    	
			    	if(tag != null){
			    		int existingID = (Integer)tag;
			    		findViewById(existingID).setVisibility(View.VISIBLE);
			    		
			    	}
			    	dropTarget.setTag(dropped.getId());
			        break;
			    case DragEvent.ACTION_DRAG_ENDED:
			        //no action necessary
			        break;
			    default:
			        break;
			}
			
			return true;
		}
		
	}
}
