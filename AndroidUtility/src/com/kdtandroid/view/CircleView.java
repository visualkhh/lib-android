package com.kdtandroid.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.kdt.std.realworld.TPoint;
import com.kdt.util.Utilities;



public class CircleView extends AbsoluteLayout {
	Context context=null;
	private Paint circlePaint;
	private Paint markerPaint;
	ArrayList<View> items;
	ArrayList<TPoint> fullpoint = new ArrayList<TPoint>();
	boolean drawsw =true;
	boolean pointsw =true;
	double gakdo = 0;
	double gak =0;
	double apgak =0;
	int centerX=0;
	int centerY=0;
	int radius=0;
	int offset = 0 ;
	int ro=0;
	int top_x=-1000;
	Handler callback_handler =null;

	public CircleView(Context context,ArrayList<View> items,int offset) {
		super(context);
		this.items = items;
		this.context = context;
		circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    circlePaint.setColor(Color.GRAY);
	    circlePaint.setStrokeWidth(1);
	    circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    markerPaint.setColor(Color.WHITE);
	    
	    this.gak =  (double)360/items.size();
//	    this.gakdo = -90;//+(1-this.gak);
	    this.gakdo = -90+(1-this.gak);
		this.offset = offset;

		   for (int i = 0; i < items.size() ; i++) {
			      View btn = items.get(i);
			      addView(btn);
		   }
		
	}
	
	public void setHandler(Handler handler){
		callback_handler = handler;
	}
	
 
	@Override
	protected void onDraw(Canvas canvas) {
//		Log.d("Circle","Draw");
  		this.centerX = getMeasuredWidth() / 2;
		this.centerY = getMeasuredHeight() / 2;
		this.radius = Math.min(centerX, centerY);
		this.radius = this.radius + this.offset;

		
		// Draw the background 
//		canvas.drawCircle(centerX, centerY, radius, circlePaint);

		
		 // Draw the marker every 15 degrees and a text every 45.
//		double gak = -(270/2);
	    for (int i = 0; i < items.size() ; i++) {
	      // Draw a marker.
//		      canvas.drawLine(px, py-radius, px, py-radius+10, markerPaint);
	    	
	    	if(drawsw){
	    			ViewGroup btn = (ViewGroup)items.get(i);
//				      btn.setText("a"+i);
				      AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams)btn.getLayoutParams();
//				      params.width=50;
//				      params.height=50;
				      int xoff = btn.getWidth()/2;
				      int yoff = btn.getHeight()/2;
				      
				      
//				      Log.d("width height","width"+params.width+"  "+params.height);
				      
//				      int radiusX = radius/4;
//				      int radiusY = (int) (radius+(radius/1.8));
				      int radiusX = radius;
				      int radiusY = radius;
				      TPoint point =  Utilities.getRotatePosition(centerX,centerY,radiusX,radiusY,gakdo);
				      params.x=point.x-xoff;
				      params.y=point.y-yoff;
				      if(pointsw){
				    	  fullpoint.add(point);
				      }
				      btn.setLayoutParams(params);
				      gakdo = gakdo+ gak;
				      
				      
//				      ImageView img  =((ImageView)btn.findViewById(com.kdt.m2m.R.id.icon_img));
//				      TextView text  =((TextView)btn.findViewById(com.kdt.m2m.R.id.servicename_text));
//				      FrameLayout icon_container  =((FrameLayout )btn.findViewById(com.kdt.m2m.R.id.icon_container));
				      
//				      Drawable draw= img.getDrawable();
//					     int alpha = (int) Utilities.getProportional(centerX,radiusX,255,point.x);
//					     int imgSize = (int) Utilities.getProportional(centerX+30,radiusX,310,point.x);
//					     Log.d("Imag",alpha+"    "+imgSize);
//				      Log.d("parameter"+alpha,""+point.y+" ("+centerY+" + "+radiusY+"))");
//				    	  draw.setAlpha(alpha);
//				    	  LinearLayout.LayoutParams paramsss = (LinearLayout.LayoutParams)icon_container.getLayoutParams();
//				    	  paramsss.width=imgSize;
//				    	  paramsss.height=imgSize;
//				    	  icon_container.setLayoutParams(paramsss);
				    
//				      if(alpha<100){
//				    	  text.setVisibility(View.GONE);
//				      }else{
//				    	  text.setVisibility(View.VISIBLE);
//				      }
				    	  
				    	  
//				      if(params.y<centerY){
//				    	  
////				    	  radius : 255   = x:a
////				    	  radius/255   =   x/a
//				    	  
//				    	  
////				    	  (1-params.y/(centerY+radiusY))*255
//				    	  double rrr  =255*Math.abs(params.y-centerY);
//				    	  int gama = (int) (rrr/(radius/2));
//				    	  draw.setAlpha(gama);
//				      }else{
//				    	  draw.setAlpha(255);
//				      }
//		      canvas.rotate(185, px, py);
	      // Draw the cardinal points
		      canvas.save();
//		      canvas.translate(0, 4);
		      canvas.restore();
//		      canvas.rotate(gak, px, py);
	    	}
	    }
	    pointsw = false;
	    drawsw = false;
	    
	}
	
	public int getNextIndex(){
		int index = getSelectedIndex();
		index++;
		if(index>=items.size()){
			index=0;
		}
//		Log.d("NextIndex",index+"");
		return index;
	}
	public int getPreviousIndex(){
		int index = getSelectedIndex();
		index--;
		if(index<0){
			index=items.size()+1;
		}
		return index;
	}
	
	
	public View getNextView(){
		return items.get(getNextIndex());
	}
	public View getPreviousView(){
		return items.get(getPreviousIndex());
	}
	
	
	
	
	Handler handler = new Handler();
	
	public void nextItem(){

	      
		Thread t = new Thread(){
			double  namgak=gak;
			double  hangak=0;
			public void run() {
				while(true){
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						public void run() {
							namgak  = (namgak /10);
							hangak += namgak;
							gakdo+=namgak;
							drawsw=true;
							namgak=gak-hangak;
//							Log.d("run",namgak+"   "+gakdo);
							invalidate();
						}
					});

					if ((int) (namgak) == 0) {
						handler.post(new Runnable() {
							public void run() {
								gakdo+=(gak-hangak);
								drawsw=true;
								invalidate();
							}
						});
						break;
					}
					
				}
				
//				Log.d("PageChange","InMethod");
				if(callback_handler!=null){
					Message msg = new Message();
					msg.what = 0;
					msg.arg1 = getSelectedIndex();
					Log.d("Circle inner Send Message","--Msg.what "+msg.what+"  Msg.arg1"+msg.arg1);
					callback_handler.sendMessage(msg);
				}
			};
		};
		t.setDaemon(true);
		t.start();
	}
	
	
	public void previousItem(){
		Thread t = new Thread(){
			double  namgak=gak;
			double  hangak=0;
			public void run() {
				while(true){
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						public void run() {
							namgak  = namgak /10;
							hangak += namgak;
							gakdo-=namgak;
							drawsw=true;
							namgak=gak-hangak;
//							Log.d("run",namgak+"   "+gakdo);
							invalidate();

						}
					});

					if ((int) (namgak) == 0) {
						handler.post(new Runnable() {
							public void run() {
								gakdo-=(gak-hangak);
								drawsw=true;
								invalidate();
							}
						});
						break;
					}
					
				}
				
//				Log.d("PageChange","InMethod");
				if(callback_handler!=null){
					Message msg = new Message();
					msg.what = 0;
					msg.arg1 = getSelectedIndex();
					callback_handler.sendMessage(msg);
				}
			};
		};
		t.setDaemon(true);
		t.start();
	}
	
	
	public void changePosition(float f){
		gakdo+=f;
		drawsw=true;
		invalidate();
		
	}
	
	public View getSelectedView(){
		return items.get(getSelectedIndex());
	}
	public View getView(int position){
		return items.get(position);
	}
	
	public int getSelectedIndex(){
		double [] y =new double[items.size()];
		for(int i=0;i<items.size();i++){
			y[i]=((AbsoluteLayout.LayoutParams)items.get(i).getLayoutParams()).y;
		}
		int r = Utilities.getMinIndex(y);
//		Log.d("SelectedIndex",r+"");
		return r;
	}
	
//	int beforeY=0;
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
////		return super.onTouchEvent(event);
//    			Log.d("Action","   -  " +event.getAction());
//    			if(event.ACTION_DOWN == event.getAction()){
//    				beforeY =   (int) event.getY();
//    				Log.d("Down","Donw"+ beforeY);
//    				return true;
//    			}
//    			if(event.ACTION_MOVE ==event.getAction()){
//    				changePosition(1-(beforeY - event.getY()));
//    				Log.d("Change","Change"+beforeY+"    "+event.getY());
//    				beforeY = (int) event.getY();
//    				return false;
//    			}
//    			return true;
//	}
}
