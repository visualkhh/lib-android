package com.kdtandroid.listener;

import com.kdtandroid.util.AndroidUtility;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AlphaListener implements OnTouchListener {
	int left = 0;
	int top = 0;
	int width = 0;
	int height = 0;
	Drawable draw=null;
	public boolean onTouch(View v, MotionEvent event) {
//		Log.d("AlphaListener", "event "+event.getAction());
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			Log.d("AlphaListener", "DOWN");
			
			try{
				draw = ((ImageView)v).getDrawable();
			}catch(ClassCastException e){
				draw = v.getBackground();
				LinearLayout t = null;
//				e.printStackTrace();
			}finally{
				try{
				AndroidUtility.setAlpha(draw, 100);
				}catch(NullPointerException e){
//					e.printStackTrace();
				}
			}
			
			this.left	=  v.getLeft();
			this.top	=  v.getTop();
			this.width	=  v.getWidth();
			this.height	=  v.getHeight();
			return false;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
//			Log.d("AlphaListener", "UP");
			AndroidUtility.setAlpha(draw, 255);
			return false;
		}
		if (event.getAction() == MotionEvent.ACTION_CANCEL) {
//			Log.d("AlphaListener", "ACTION_CANCEL");
			AndroidUtility.setAlpha(draw, 255);
			return false;
		}
//		if (event.getAction() == MotionEvent.ACTION_MOVE) {
////			Log.d("MOVE", "MOVE");
//			if( 	( this.left < event.getX() )			&& 
//					( this.left+this.width > event.getX() )	&&
//					( this.top  < event.getY() )			&&
//					( this.top+this.height > event.getY() )			
//				){
//				Log.d("MOVE", "MOVE1");
//				return false;
//			}else{
//				Log.d("MOVE", "MOVE2");
//				
//				KGlobal.setAlpha(draw, 255);
//				return false;
//			}
//		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
//				Log.d("AlphaListener", "MOVE");
				AndroidUtility.setAlpha(draw, 255);
				return false;
			
		}
		
		return false;
	}

	
}
