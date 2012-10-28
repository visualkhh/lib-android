package com.khmandroid.hraphics;

import android.graphics.Point;
import android.graphics.PointF;

public class PointK extends PointF {
	private boolean draw = false;

	public PointK(float x, float y) {
		set(x, y);
		
	}
	public PointK(float x, float y,boolean draw) {
		set(x, y,draw);
		
	}
	public void set(float x,float y, boolean draw){
	 set(x,y);
	 set(draw);
	}
	
	public void set(boolean draw){
		this.draw = draw;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public boolean getDraw(){
		return draw;
	}
}
