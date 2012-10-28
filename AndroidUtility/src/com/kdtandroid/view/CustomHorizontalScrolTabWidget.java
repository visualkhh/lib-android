package com.kdtandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TabWidget;

public class CustomHorizontalScrolTabWidget  extends HorizontalScrollView{
	private TabWidget tabwidget = null;
	private int displaychild=1;
	public CustomHorizontalScrolTabWidget(Context context) {
		super(context);
		init(context,null);
	}

	public CustomHorizontalScrolTabWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}


	
	
	private void init(Context context, AttributeSet attrs) {
		tabwidget =  new TabWidget(context);
		tabwidget.setId(android.R.id.tabs);
		addView(tabwidget,LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		if(attrs!=null)
		displaychild = attrs.getAttributeIntValue(null, "displaychild", 1);
	}
	public void setDisplayChiledSize(int chiledsize){
		this.displaychild = chiledsize;
	}
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		try{
			ViewGroup direct  =tabwidget;
			int atViewWidthSize = getMeasuredWidth()/displaychild;
			int atViewHeightSize = getMeasuredHeight()/displaychild;
			
			Log.d("CustomHorizontal", "childsCnt"+")  "+direct.getChildCount()+" getMeasuredWidth() ="+ getMeasuredWidth()+" ) atTabViewWidthSize  "+atViewWidthSize+"      displayChiledSize"+displaychild);
			for(int i=0;i<direct.getChildCount();i++){
				View atChild = direct.getChildAt(i);
				ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)atChild.getLayoutParams();
//				if(getOrientation()==LinearLayout.HORIZONTAL)
					params.width=atViewWidthSize;//getMeasuredWidth();
//				else
//					params.height=atViewHeightSize;//getMeasuredWidth();
				
				atChild.setLayoutParams(params);
			}
		}catch(Exception e){
			
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
		 
}
