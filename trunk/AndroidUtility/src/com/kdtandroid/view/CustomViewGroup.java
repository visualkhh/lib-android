package com.kdtandroid.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kdt.util.Utilities;

public class CustomViewGroup extends ViewGroup {
	private String TAG=Utilities.getClassName();
	public CustomViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//	    final int width = MeasureSpec.getSize(widthMeasureSpec);
//	    final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//	    if (widthMode != MeasureSpec.EXACTLY) {
//	      throw new IllegalStateException("Workspace can only be used in EXACTLY mode.");
//	    }
//
//	    final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//	    if (heightMode != MeasureSpec.EXACTLY) {
//	      throw new IllegalStateException("Workspace can only be used in EXACTLY mode.");
//	    }

	    // The children are given the same width and height as the workspace
	    final int count = getChildCount();
	    Log.d(TAG,count+"");
	    for (int i = 0; i < count; i++) {
	      getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
	    }
	}
	
	int i=0;
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		Log.d(TAG,changed+"    "+l+"  "+t+"   "+r+"   "+b+"        "+getChildCount());
		int height = getMeasuredHeight();
		int childTop = 0;
	    final int count = getChildCount();
	    for (int i = 0; i < count; i++) {
	      final View child = getChildAt(i);
	      if (child.getVisibility() != View.GONE) {
	        final int childWidth = child.getMeasuredWidth();
	        final int childheight = child.getMeasuredHeight();
	        child.layout(0, childTop, childWidth, childTop+childheight);
//	        child.layout(childLeft, 0, childLeft + childWidth, child.getMeasuredHeight());
//	        Log.d(TAG,l+"  "+ childTop+"  "+ child.getMeasuredWidth()+"  "+ child.getMeasuredHeight());
	        childTop +=childheight;// child.getMeasuredHeight();
	      }
	    }
	}
	

}
