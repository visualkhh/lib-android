package com.kdtandroid.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class CustomFlipper extends FrameLayout {
	static public class LayoutParams extends LinearLayout.LayoutParams{
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}
		public LayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}
		public LayoutParams(int arg0, int arg1, float arg2) {
			super(arg0, arg1, arg2);
			// TODO Auto-generated constructor stub
		}
	public LayoutParams(LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
		public LayoutParams(MarginLayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
	}
	//시간
	final private double accelerationDelay_mm=160;
	//스탭 몇번만에 갈꺼냐
	final private  int step=30;
	//가중치 높으면 점점.
	final private  double wi=0.1;
	
	private Handler handle =new Handler();
	private LinearLayout navigation_container = null;
	private LinearLayout container = null;
	private CustomHorizontalScrollView content_horizontal_container = null;
	private LinearLayout content_item_container = null;
	private Context context = null;
	private static int index=0;
	public CustomFlipper(Context context) {
		super(context);
		this.context = context;
		init();
	}
	

	public CustomFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	public CustomFlipper(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	
	private void init() {
		Log.d("CustomFlipper","init");
		CustomFlipper.index=0;
		
		content_horizontal_container = new CustomHorizontalScrollView(context);
		content_horizontal_container.setHorizontalFadingEdgeEnabled(false);
//		content_horizontal_container.setHorizontalScrollBarEnabled(false);
		content_horizontal_container.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));
		
//		content_horizontal_container.setBackgroundColor(0xFFFF0000);
		
		
		//**************
		container = new LinearLayout(this.context);
		LinearLayout.LayoutParams params_container = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT, 0.0F);
		params_container.leftMargin  = 0;
		params_container.rightMargin = 0;
		params_container.topMargin = 0;
		params_container.bottomMargin = 0;
//		params.gravity = gravity;
		container.setLayoutParams(params_container);
		container.setGravity(Gravity.CENTER);
		container.setOrientation(LinearLayout.HORIZONTAL);		
//		container.setPadding(10, 10, 10, 10);
		//**************
		
		
		content_item_container = new LinearLayout(this.context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT, 0.0F);
		params.leftMargin  = 0;
		params.rightMargin = 0;
		params.topMargin = 0;
		params.bottomMargin = 0;
//		params.gravity = gravity;
		content_item_container.setLayoutParams(params);
		content_item_container.setGravity(Gravity.CENTER);
		content_item_container.setOrientation(LinearLayout.HORIZONTAL);
//		content_item_container.setPadding(10, 10, 10, 10);
		
		
		//addViews 
		content_horizontal_container.addView(container);
		container.addView(content_item_container);
		
		//listener
		content_horizontal_container.setOnTouchListener(Listener_touch);
		super.addView(content_horizontal_container);
		
	}
	
	
public void addView(View child) {
	// TODO Auto-generated method stub
	addView(child,null);
}
	
	public void addView(View child, LayoutParams paramsin) {
		int pading=0;
		LinearLayout atContainer = new LinearLayout(this.context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT, 0.0F);
		params.leftMargin  = pading;
		params.rightMargin = pading;
		params.topMargin = pading;
		params.bottomMargin = pading;
		atContainer.setLayoutParams(params);
		atContainer.setGravity(Gravity.LEFT|Gravity.TOP);
		atContainer.setOrientation(LinearLayout.VERTICAL);
//		atContainer.addView(AndroidUtility.creativeTextView(context, "a"));					//이것두.
		if(paramsin!=null)
		atContainer.addView(child,paramsin);					//이것두.
		else
		atContainer.addView(child);					//이것두.
			
//		content_item_container.addView(atContainer);//이게문제네
//		atContainer.setBackgroundColor(Color.CYAN);
//		
		
//		atContainer.setPadding(10, 10, 10, 10);
		
		
//		content_item_container.addView(child);
//		content_item_container.addView(AndroidUtility.creativeTextView(context, "a"));
		content_item_container.addView(atContainer);
//		container.addView(child);
//		super.addView(atContainer);
//		super.addView(child);
	};
	
	float moveAction_x=0;
	float downAction_x=0;
	long downTime=0;
	long upTime=0;
	
	OnTouchListener Listener_touch = new OnTouchListener(){
		public boolean onTouch(View v, MotionEvent event) {
			
			Log.d("CustomFlipper","event  "+event.getAction());
//			
			if(event.getAction() == event.ACTION_DOWN){
				downTime = System.currentTimeMillis();
				downAction_x = event.getX();
				moveAction_x = event.getX();
				Log.d("CustomFlipper","onTouchEvent Down  "+moveAction_x);
				
				return true;
			}
			if(event.getAction() == event.ACTION_UP){
				upTime = System.currentTimeMillis();
				long differenceTime = Math.abs(upTime-downTime);
				float differenceValue = downAction_x-event.getX();
				int thisIndex = getNeighborIndex();

				Log.d("CustomFlipper","onTouchEventUP");
				
				
				//차이가없을땐 그냥 인덱   차이있으면 글로가
				if(differenceTime<accelerationDelay_mm){
					if(differenceValue>0){//++
						thisIndex=index;
						thisIndex++;
					}else if(differenceValue<0){ //--
						thisIndex=index;
						thisIndex--;
					}
				}
				goIndexPage(thisIndex);
				
			}
			if(event.getAction() == event.ACTION_MOVE){
				
				float differenceValue = moveAction_x-event.getX();
				moveAction_x =event.getX();
				Log.d("CustomFlipper",moveAction_x+"onTouchEvent MOVE  "+event.getX());
				setHorizontal_apeend_x(differenceValue);
			}
			
			return true;
			
		}

		
	
		
	};

	
	
	
	
	
	public void goIndexPage(int input_index) {
		
			
		
		if(input_index<0)
			input_index=0;
		if(input_index >= getContentChildCount())
			input_index=getContentChildCount()-1;
		
		
		final int index = input_index;
		
		Thread thread =new Thread(){
			public void run() {

				
				

				double [] exp = new double[step]; 
				double sum=0;
					
				for(int i=0;i<step;i++){
					double expval = Math.exp(-(wi*(i+1)));
					exp[i]=expval;
					sum+=expval;
				}
				for(int i=0;i<step;i++){
					exp[i]=exp[i]/sum;
				}
				
				float charwidtg  =   (  index*getMeasuredWidth()  )   -   getHorizontal_x();
				for(int i=0;i<step;i++){
					final double weight_value = charwidtg*exp[i];
					
					handle.post(new Runnable() {
						public void run() {
							setHorizontal_apeend_x(weight_value);
//							Log.d("CustomFlipper",weight_value+" GoPage GetIndex "+index);
							
						}
					});
							
							try {
								sleep(5);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				}
					
				handle.post(new Runnable() {
					public void run() {
						setHorizontal_absx((  index*getMeasuredWidth()  ) );
						int inputIndex = index;
						setLastIndex(inputIndex);
					}
				});
					
			};
		};
		thread.setDaemon(true);
		thread.start();
}
	
	
	
		
		private int getNeighborIndex() {
			float at_view_point = getViewCenterPoint(); //지금 사용자가 보고있는 위치    (맨왼쪽+절반사이즈)
			float horizontal_child_cnt = getContentChildCount();
			int thisIndex=0;
			for(int i = 0 ; i <horizontal_child_cnt;i++ ){
				float left_x =i*getMeasuredWidth();
				float right_x =left_x+getMeasuredWidth();
				
				if(left_x<at_view_point && right_x>at_view_point){
					thisIndex=i;
				}
			}
			return thisIndex;
		}

	
	private float getViewCenterPoint(){
		float halfSize = getMeasuredWidth()/2;	//한장의 화면의 절반사이즈
		float horizontal_x = getHorizontal_x();	//화면에 맨왼쪽
		float at_view_point = horizontal_x+halfSize; //지금 사용자가 보고있는 위치    (맨왼쪽+절반사이즈)
		return at_view_point;
	}
	
	
	
	public int getIndex(){
		return this.index;
	}
	
	
	
	
	/////////
	public void setBackgroundResourceContent(int rsc){
		content_item_container.setBackgroundResource(rsc);
	}
	public void setBackgroundDrawableContent(Drawable draw){
		content_item_container.setBackgroundDrawable(draw);
	}
	public void setBackgroundColorContent(int color){
		content_item_container.setBackgroundColor(color);
	}
	////////
	
	
	private void setLastIndex(int index){
		this.index = index;
	}
	
	private float getContentWidth(){
		float inner_full_width = getMeasuredWidth()*getContentChildCount(); //전체사이즈
		return inner_full_width;
		
	}
	private int getContentChildCount(){
		return content_item_container.getChildCount();
	}
	
	
	private float getHorizontal_x(){
		return content_horizontal_container.getScrollX();
	}
	
	private void setHorizontal_absx(int i) {
		content_horizontal_container.scrollTo( i, content_horizontal_container.getScrollY());
		content_horizontal_container.invalidate();
	}
	
	private void setHorizontal_apeend_x(double i) {
//		Log.d("CustomFlipper",content_horizontal_container.getScrollX()+"   "+i);
		content_horizontal_container.scrollTo((int) ((int)content_horizontal_container.getScrollX()+i), content_horizontal_container.getScrollY());
		content_horizontal_container.invalidate();
	}


	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		for(int i=0;i<getContentChildCount();i++){
			View atChild = content_item_container.getChildAt(i);
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)atChild.getLayoutParams();
			params.width=getMeasuredWidth();
			params.height=ViewGroup.LayoutParams.FILL_PARENT;
//			params.height=440;
			atChild.setLayoutParams(params);
//			setHorizontal_absx((  getNeighborIndex()*getMeasuredWidth()  ) );
//			setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
		}
		
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)content_item_container.getLayoutParams();
		params.width=(int) getContentWidth();
		params.height=ViewGroup.LayoutParams.FILL_PARENT;
		content_item_container.setLayoutParams(params);
		
		int getIndex = getIndex();
		goIndexPage(getIndex);
		
//		this.width=getMeasuredWidth();
//		this.height=getMeasuredHeight();
//		Log.d("CustomFlipper","onMeasure");
//		Log.d("CustomFlipper",widthMeasureSpec+"  "+heightMeasureSpec+"  ----- "+getMeasuredWidth()+"  "+getMeasuredHeight());
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		
	}
	
	
	
	class CustomHorizontalScrollView extends HorizontalScrollView{
		public CustomHorizontalScrollView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		//자식중에 클릭이벤트가 먹혀있으면  터치가 Horizontal쪽에 down 이 전달이 안된다 그래서  자식 이벤트 검사한다.
		@Override
		public boolean onInterceptTouchEvent(MotionEvent event) {
		Log.d("-CustomHorizontalScrollView","---"+event.getAction());
		if(event.getAction() == event.ACTION_DOWN){
		downTime = System.currentTimeMillis();
		downAction_x = event.getX();
		moveAction_x = event.getX();
		Log.d("CustomHorizontalScrollView","onTouchEvent Down  "+moveAction_x);
		
		return super.onInterceptTouchEvent(event);
		}
			return super.onInterceptTouchEvent(event);
		}
		
	}
	
	
	
	
	
	

}
