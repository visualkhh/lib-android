package com.kdtandroid.listener;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



public class LeftRightDragListener implements OnTouchListener{
	private float beforeX = 0;
	OnDragListener listener = null;
	int sensitivity = 150;
	
	
	
	public void setSensitivity(int sensitivity){
		this.sensitivity = sensitivity;
	}
	public void setOnDragListener(OnDragListener listener){
		this.listener = listener;
	}
	

	
	public boolean onTouch(View view, MotionEvent event) {

		Log.d("LeftRightDragListener","---"+event.getAction());
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			beforeX = event.getX();
			return true;
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (beforeX == 0) {
				beforeX = event.getX();
			}
			return false;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			float t = beforeX - event.getX();
			if (t > sensitivity) {
				beforeX = event.getX();
				if(listener!=null)
				this.listener.onDrag(view,OnDragListener.LeftDrag);
			} else if (t < -1*sensitivity) {
				beforeX = event.getX();
				if(listener!=null)
				this.listener.onDrag(view,OnDragListener.RightDrag);
			} else {
				return false;
			}
		}
		return false;
		
	}
	


}
