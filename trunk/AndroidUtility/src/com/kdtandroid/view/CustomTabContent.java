package com.kdtandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CustomTabContent extends FrameLayout{
	public CustomTabContent(Context context) {
		super(context);
		init();
	}
	public CustomTabContent(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public CustomTabContent(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	private void init() {
		setId(android.R.id.tabcontent);
	}


	

}
