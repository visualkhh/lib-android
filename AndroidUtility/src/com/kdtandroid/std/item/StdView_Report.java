package com.kdtandroid.std.item;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.kdtandroid.util.AndroidUtility;

public class StdView_Report<T>  extends StdView<T>{ 
	public enum COLOR{
		MAX(0xff5474ff),//blue
		MIN(Color.RED),
		BGCOLOR(0x00000000),
		TEXTCOLOR(0xffbababa);
		;
		
		private final int color;
		COLOR(int color){
			this.color=color;
		}
		public int getValue() {
			return color;
		}
	}
	
	
	boolean isHeader=false;
	public int backgroundColor=COLOR.BGCOLOR.getValue();
	public int textColor=COLOR.TEXTCOLOR.getValue();
	public int textSize=15;
	
	
	
	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setHeadType(boolean wantHeader){
		isHeader=wantHeader;
	}
	
	public void setBackGroundColor(int color){
		this.backgroundColor=color;
	}
	public void setTextColor(int color){
		this.textColor=color;
	}
	
	@Override
	public View getView(Context context, int viewType) {
		
		TextView text  = AndroidUtility.creativeTextView(context, getKey(), textSize, textColor,backgroundColor);
		text.setGravity(Gravity.CENTER);
		int rightpadding=0;
		if(isHeader){
			rightpadding=0;
			text.setGravity(Gravity.CENTER);
		}else{
			rightpadding=7;
			text.setGravity(Gravity.RIGHT|Gravity.CENTER);
		}
		text.setPadding(0, 10, rightpadding,10);
		
		return text;
	}
	
}
