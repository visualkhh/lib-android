package com.kdtandroid.std.item;

import com.kdt.std.Standard;
import com.kdt.util.Utilities;
import com.kdtandroid.util.AndroidUtility;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class StdView<T> extends StdView_Base<String,T> {

	public StdView(){
	}
	public StdView(String key,T value){
		super(key,value);
	}
	
	@Override
	public String getKey() {
		return super.getKey();
	}
	
	public View getView(Context context,int viewType){
		TextView view=null;
		if(VIEWTYPE.PREVIEW.getValue() == viewType){
			view = AndroidUtility.creativeTextView(context, getKey(), (int)AndroidUtility.getDipToPixel(context, 10), Color.WHITE);
		}else if(VIEWTYPE.LIST.getValue() == viewType){
			view = AndroidUtility.creativeTextView(context, getKey(), (int)AndroidUtility.getDipToPixel(context, 18), Color.WHITE);
		}
		view.setGravity(Gravity.CENTER);
		return view;
	}
}
