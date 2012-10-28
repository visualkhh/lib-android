package com.kdtandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

import com.kdt.util.Utilities;

public class CustomView extends View{
private String TAG=Utilities.getClassName();
	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		   int measuredWidth = measure(widthMeasureSpec);
		    int measuredHeight = measure(heightMeasureSpec);
		    int d = Math.min(measuredWidth, measuredHeight);
		    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		    setMeasuredDimension(d, d);    
		    Log.d(TAG,measuredWidth+"  "+measuredHeight+"  "+d);
	}
	private int measure(int measureSpec) {
	    int result = 0; 

	    // Decode the measurement specifications.
	    int specMode = MeasureSpec.getMode(measureSpec);
	    int specSize = MeasureSpec.getSize(measureSpec); 

	    if (specMode == MeasureSpec.UNSPECIFIED) {
	      // Return a default size of 200 if no bounds are specified. 
	      result = 200;
	    } else {
	      // As you want to fill the available space
	      // always return the full available bounds.
	      result = specSize;
	    } 
	    return result;
	  }
	
//	  private int measure(int measureSpec) {
//		    int result = 0; 
//
//		    // Decode the measurement specifications.
//		    int specMode = MeasureSpec.getMode(measureSpec);
//		    int specSize = MeasureSpec.getSize(measureSpec); 
//
//		    if (specMode == MeasureSpec.UNSPECIFIED) {
//		      // Return a default size of 200 if no bounds are specified. 
//		      result = 200;
//		    } else {
//		      // As you want to fill the available space
//		      // always return the full available bounds.
//		      result = specSize;
//		    } 
//		    return result;
//		  }
	
	@Override
		protected void onDraw(Canvas canvas) {
		Paint circlePaint;
	    circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    circlePaint.setColor(Color.RED);
	    circlePaint.setStrokeWidth(1);
	    circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    
	    Log.d(TAG,getMeasuredWidth()+"   "+getMeasuredHeight());
	    int px = getMeasuredWidth() / 2;
	    int py = getMeasuredHeight() / 2 ;
	    
	    int radius = Math.min(px, py);
	    canvas.drawCircle(px, py, radius, circlePaint);
	    canvas.save();
		}
}
