package com.kdtandroid.std.item;

import com.kdt.std.Standard;
import com.kdt.util.Utilities;
import com.kdtandroid.util.AndroidUtility;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public abstract class StdView_Base<K,T> extends Standard<K,T> {
	public enum VIEWTYPE {
		PREVIEW (Utilities.getNextNumber()),
		SELECTED (Utilities.getNextNumber()),
		LIST (Utilities.getNextNumber());
		private final int value;   
		VIEWTYPE(int value) {
			this.value = value;
		}
		public int getValue() { 
			return value; 
		}
	}
	public StdView_Base(){
	}
	public StdView_Base(K key,T value){
		super(key,value);
	}
	public final View getView(Context context){return getView(context,VIEWTYPE.LIST.getValue());};
	public abstract View getView(Context context,int viewType);
}
