package com.kdtandroid.dialog;

import java.util.ArrayList;

import android.R.color;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.LineBackgroundSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kdtandroid.util.AndroidUtility;

public class CustomDialog extends Dialog{
	Context context;
	Dialog dialog=this;
	FrameLayout dialog_container;
	Drawable dialog_background;
	LinearLayout top_container;
	TextView title_text;
	
	
	LinearLayout center_container;
	
	LinearLayout bottom_container;
	LinearLayout button_container;
	private LinearLayout title_container;
	ImageView close_img;
	
	Button close_btn;
	
	
	int dialog_colorfilter= 0xffffffff;
	int lineColor	=0xff777777;
	int fontColor	=0xFFAAAAAA;
	int titleBgcolor = 0x44000000;
	int titleTextSize; //=(int) AndroidUtility.getDipToPixel(context,20);
	int iconSize;// =(int) AndroidUtility.getDipToPixel(context,20);
	int closebtn_size=30;//안써
	private boolean watnCancleButtonVisible=true;
	
	public CustomDialog(Context context) {
		super(context);
		this.context=context;
		init();
	}
	protected CustomDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context=context;
		init();
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
		this.context=context;
		init();
	}

	public void setLayoutParams(LayoutParams param){
		
		int width =  param.width;
		int height = param.height;
		
//		LinearLayout dialog_layout = (LinearLayout) dialog.findViewById(R.id.dialog_layout);
		if(ViewGroup.LayoutParams.FILL_PARENT==width)
			width=(int) (AndroidUtility.getWindowWidth(context)-0);
//			width=(int) (getWindowWidth(context)/1.46);
		if(ViewGroup.LayoutParams.FILL_PARENT==height)
			height=(int) (AndroidUtility.getWindowHeight(context)-AndroidUtility.getDipToPixel(context, 17));
//			height=(int) (getWindowHeight(context)/1.53);
		
		Log.d("Size ","width"+width+ "    height"+height);
//		setLinearLayoutparam(dialog_layout, width, height, 0, LinearLayout.VERTICAL);
		AndroidUtility.setParent_Layoutparam(dialog_container, width, height);
		
	}
	public void setBackgroundColor(int bg){
		
		AndroidUtility.setColorFilter(this.dialog_background, bg);
		dialog_container.invalidate();
	}
	public void setBackgroundDrawable(Drawable bg){
		if(bg==null)
			return;
		
		this.dialog_background=bg;
		dialog_container.invalidate();
	}
	public void setTitle(String title){
		if(title==null)
			return;
		title_text.setText(title);
		title_text.setVisibility(View.VISIBLE);
//		title_container.removeAllViews();
//		LinearLayout line_top = AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
//		line_top.setBackgroundColor(lineColor);
//		title_text = AndroidUtility.creativeTextView(context, title,titleTextSize,fontColor);
//		title_container.addView(title_text);
//		title_container.addView(line_top,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,2));
//		title_container.setVisibility(View.VISIBLE);
		
	}
	@Override
	public void setContentView(View view) {
		if(view==null)
			return;
		
		center_container.removeAllViews();
		center_container.addView(view,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		AndroidUtility.setParent_Weight(center_container, 1);
		center_container.setVisibility(View.VISIBLE);
	};
	
	public void setIcons(ArrayList<View> buttons){
		if(buttons==null)
			return;
		for(int  i = 0 ; i <buttons.size();i++)
			title_container.addView(buttons.get(i),0+i,AndroidUtility.creativeLinearLayoutParam(iconSize, iconSize,5,5,5,5));
			
	}
	public void setButtons(ArrayList<View> buttons){
		if(buttons==null)
			return;
		for(int  i = 0 ; i <buttons.size();i++)
			button_container.addView(buttons.get(i),0+i);
			
		AndroidUtility.setChild_LinearLayoutparam(button_container, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1,5,5,2,2);
	}

	private void init(){
		titleTextSize =(int) AndroidUtility.getDipToPixel(context,15);
		iconSize =(int) AndroidUtility.getDipToPixel(context,30);
		
		
		
		
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		dialog.setCanceledOnTouchOutside(true);//외부클릭시 닫침
		//dialog.setCancelable();  뒤로가기안되게..
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
		

//		dialog.setTitle("aaaaaaaa");
		
		dialog_background =AndroidUtility.getDrawable(context, android.R.drawable.alert_dark_frame);
		AndroidUtility.setColorFilter(dialog_background, dialog_colorfilter);
		

		

		
		dialog_container 	= AndroidUtility.creativeFrameLayout(context);
		LinearLayout dialog_layout 		= AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER,dialog_background);
		close_img 			= AndroidUtility.creativeImageView(context, android.R.drawable.presence_offline);
		dialog_container.addView(dialog_layout,AndroidUtility.creativeFrameLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//		dialog_container.addView(close_img,AndroidUtility.creativeFrameLayoutParam(closebtn_size, closebtn_size,Gravity.RIGHT|Gravity.TOP));
		dialog_container.addView(close_img,AndroidUtility.creativeFrameLayoutParam(closebtn_size,closebtn_size,Gravity.RIGHT|Gravity.TOP));
		close_img.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		close_img.setVisibility(View.GONE);
		
		
		dialog_layout.setPadding(20,20,20,20);
		
		////title
		LinearLayout line_top = AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
		line_top.setBackgroundColor(lineColor);
		
		top_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
		title_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
		
		title_text = AndroidUtility.creativeTextView(context, "",titleTextSize,fontColor);
		title_text.setGravity(Gravity.CENTER);
		title_container.addView(title_text,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,1f));
		
		top_container.addView(title_container,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		top_container.addView(line_top,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,1));
		
		top_container.setBackgroundColor(titleBgcolor);
		dialog_layout.addView(top_container,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		title_text.setVisibility(View.GONE);
//		title_container.setVisibility(View.GONE);
//		title_container.setVisibility(View.VISIBLE);
		
		

		
		
		
		
		
		////center
//		if(addView!=null){
		center_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
//		center_container.addView(addView,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//		setParent_Weight(center_container, 1);
		dialog_layout.addView(center_container,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		center_container.setVisibility(View.GONE);
//		}
		
		
		////bottom
//		if(buttons!=null){
			bottom_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER,0x55000000);
			LinearLayout line_bottom = AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
			line_bottom.setBackgroundColor(lineColor);
			
			button_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
			button_container.setPadding(0, 5, 0, 1);
			
			////btnclose
			close_btn 			= creativeCloseBtn();
			button_container.addView(close_btn);
			
			AndroidUtility.setChild_LinearLayoutparam(button_container, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1f);
			
			bottom_container.addView(line_bottom,LayoutParams.FILL_PARENT,1);
			bottom_container.addView(button_container,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,5,5,2,4));
			
			dialog_layout.addView(bottom_container,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//			button_container.setVisibility(View.VISIBLE);
//		}
	
			
		
		
		super.setContentView(dialog_container);
		
		
		int width =  ViewGroup.LayoutParams.WRAP_CONTENT;
		int height = ViewGroup.LayoutParams.WRAP_CONTENT;
		
//		LinearLayout dialog_layout = (LinearLayout) dialog.findViewById(R.id.dialog_layout);
		if(ViewGroup.LayoutParams.FILL_PARENT==width)
			width=(int) (AndroidUtility.getWindowWidth(context)-0);
//			width=(int) (getWindowWidth(context)/1.46);
		if(ViewGroup.LayoutParams.FILL_PARENT==height)
			height=(int) (AndroidUtility.getWindowHeight(context)-AndroidUtility.getDipToPixel(context, 17));
//			height=(int) (getWindowHeight(context)/1.53);
		
//		Log.d("Size ","width"+width+ "    height"+height);
//		setLinearLayoutparam(dialog_layout, width, height, 0, LinearLayout.VERTICAL);
		AndroidUtility.setParent_Layoutparam(dialog_container, width, height);

		
		
	}

//	public void setCloseVisible(boolean wantVisible){
//		if(wantVisible){
//			close_img.setVisibility(View.VISIBLE);
//		}else{
//			close_img.setVisibility(View.GONE);
//		}
//	}
	
//	private Drawable closebg;
//	private int closetextcolor=Color.BLACK;
//	private int closetextsize=-1;
//	public void setCloseButton_BG(Drawable bg){
//		closebg=bg;
//	}
//	public void setCloseButton_TextColor(int color){
//		closetextcolor=color;
//	}
	
	private Button creativeCloseBtn(){
		String close_str = AndroidUtility.getString(context, android.R.string.cancel);
		close_btn 			= AndroidUtility.creativeButton(context,close_str );
//		AndroidUtility.setAlpha(close_btn.getBackground(),200);
		close_btn.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		return close_btn;
	}
	
	public void setCancleButtonVisible(boolean watnCancleButtonVisible){
		if(watnCancleButtonVisible)
		close_btn.setVisibility(View.VISIBLE);
		else
		close_btn.setVisibility(View.GONE);
		
		
		this.watnCancleButtonVisible = watnCancleButtonVisible;
	}
	
	
	public void dismiss(View v){
		this.dismiss();
	}
//	

}
