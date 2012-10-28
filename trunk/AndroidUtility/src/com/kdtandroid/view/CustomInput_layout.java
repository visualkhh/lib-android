package com.kdtandroid.view;

import com.kdtandroid.util.AndroidUtility;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomInput_layout extends LinearLayout
{
	Context context;
	TextView title_text;
	EditText value_edit;
	EditText value1_edit;
	EditText value2_edit;
	TextView split1_text;
	TextView split2_text;
	TextView sign_text;
	LinearLayout line;
	int linecolor = 0xff717070;
	private String split="";
	public CustomInput_layout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		init();
		
	}
	public CustomInput_layout(Context context) {
		super(context);
		this.context=context;
		init();
	}
	
	private void init() {
		int titlesize=(int) AndroidUtility.getDipToPixel(context, 13);
		int valuesize=(int) AndroidUtility.getDipToPixel(context, 13);
		int signsize=(int)  AndroidUtility.getDipToPixel(context, 10);
		
		int titlewidth=(int)AndroidUtility.getDipToPixel(context, 110);
		int signwidth=(int)AndroidUtility.getDipToPixel(context, 40);

		setOrientation(VERTICAL);
		setGravity(Gravity.CENTER);
		
		LinearLayout sub_layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
		sub_layout.setPadding(5, 0, 5, 0);
		
		title_text = AndroidUtility.creativeTextView(context,"",titlesize);
		title_text.setGravity(Gravity.LEFT|Gravity.CENTER);
		title_text.setVisibility(View.GONE);
		
		value_edit = AndroidUtility.creativeEditText(context, "",valuesize);
		AndroidUtility.setColorFilter(value_edit.getBackground(), 0x22000000, PorterDuff.Mode.SRC_ATOP);
		value_edit.setSingleLine();
		value_edit.setGravity(Gravity.RIGHT|Gravity.CENTER);
		value_edit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		value_edit.setVisibility(View.GONE);
	
		value1_edit = AndroidUtility.creativeEditText(context, "",valuesize);
		AndroidUtility.setColorFilter(value1_edit.getBackground(), 0x22000000, PorterDuff.Mode.SRC_ATOP);
		value1_edit.setSingleLine();
		value1_edit.setGravity(Gravity.RIGHT|Gravity.CENTER);
		value1_edit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		split1_text	= AndroidUtility.creativeTextView(context,split,titlesize);
		value1_edit.setVisibility(GONE);
		split1_text.setVisibility(GONE);
	
		
		
		value2_edit = AndroidUtility.creativeEditText(context, "",valuesize);
		AndroidUtility.setColorFilter(value2_edit.getBackground(), 0x22000000, PorterDuff.Mode.SRC_ATOP);
		value2_edit.setSingleLine();
		value2_edit.setGravity(Gravity.RIGHT|Gravity.CENTER);
		value2_edit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		split2_text	= AndroidUtility.creativeTextView(context,split,titlesize);
		value2_edit.setVisibility(GONE);
		split2_text.setVisibility(GONE);
		
		
		
		
		
		
		sign_text=null;
		sign_text 	= AndroidUtility.creativeTextView(context,"",signsize);
		
		sign_text.setGravity(Gravity.RIGHT|Gravity.CENTER);
		sign_text.setVisibility(View.GONE);

		
//		if(orientation == LinearLayout.HORIZONTAL){
			sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(titlewidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
			sub_layout.addView(value_edit,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
			
			sub_layout.addView(split1_text,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,0F));
			sub_layout.addView(value1_edit,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
			sub_layout.addView(split2_text,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,0F));
			sub_layout.addView(value2_edit,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
//		}else{
//			sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//			sub_layout.addView(input_edit,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//		}
		
		
//		if(sign_text!=null)
		sub_layout.addView(sign_text,AndroidUtility.creativeLinearLayoutParam(signwidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
//		input_edit.setId(id);
		
		addView(sub_layout,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,2,2,5,5));
		line = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
		line.setBackgroundColor(linecolor);
		addView(line,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, 1,5,5,10,10));
	}

	public void setTitle(String title){
		title_text.setText(title);
		title_text.setVisibility(VISIBLE);
	}
	public void setTitleColor(int color){
		title_text.setTextColor(color);
	}
	public String getTitle(){
		return title_text.getText()+"";
	}

	public void setValue(String value){
		value_edit.setText(value);
		value_edit.setVisibility(VISIBLE);
	}

	
	public void setSign(String sign){
		if(sign==null){
			sign_text.setVisibility(GONE);
		}else{
		sign_text.setText(sign);
		sign_text.setVisibility(VISIBLE);
		}
	}
	public String getSign(){
		return sign_text.getText()+"";
	}
	public void setTitleSize(float title_size){
		title_text.setTextSize(title_size);
	}
	public float getTitleSize(){
		return title_text.getTextSize();
	}
	
	public String getValue(){
		return value_edit.getText()+"";
	}
	public void setValueSize(float value_size){
		value_edit.setTextSize(value_size);
	}
	public void setValueTextColor(int value_color){
		value_edit.setTextColor(value_color);
	}
	public void setValueBackground(Drawable bg){
		value_edit.setBackgroundDrawable(bg);
	}
	public float getValueSize(){
		return value_edit.getTextSize();
	}
	

	
	public void setValue1(String value){
		value1_edit.setText(value);
		split1_text.setVisibility(VISIBLE);
		value1_edit.setVisibility(VISIBLE);
	}
	public String getValue1(){
		return value1_edit.getText()+"";
	}
	public void setValue1Size(float value_size){
		value1_edit.setTextSize(value_size);
	}
	public void setValue1TextColor(int value_color){
		value1_edit.setTextColor(value_color);
	}
	public void setValue1Background(Drawable bg){
		value1_edit.setBackgroundDrawable(bg);
	}
	public float getValue1Size(){
		return value1_edit.getTextSize();
	}
	
	
	
	
	
	
	public void setValue2(String value){
		value2_edit.setText(value);
		split2_text.setVisibility(VISIBLE);
		value2_edit.setVisibility(VISIBLE);
	}
	public String getValue2(){
		return value2_edit.getText()+"";
	}
	public void setValue2Size(float value_size){
		value2_edit.setTextSize(value_size);
	}
	public void setValue2TextColor(int value_color){
		value2_edit.setTextColor(value_color);
	}
	public void setValue2Background(Drawable bg){
		value2_edit.setBackgroundDrawable(bg);
	}
	public float getValue2Size(){
		return value2_edit.getTextSize();
	}
	
	
	
	
	
	
	
	
	
	
	public void setSignSize(float sign_size){
		sign_text.setTextSize(sign_size);
	}
	public float getSignSize(){
		return sign_text.getTextSize();
	}
	public void setSplitString(String split){
		this.split = split;
	}

//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
////	    final int width = MeasureSpec.getSize(widthMeasureSpec);
////	    final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
////	    if (widthMode != MeasureSpec.EXACTLY) {
////	      throw new IllegalStateException("Workspace can only be used in EXACTLY mode.");
////	    }
////
////	    final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
////	    if (heightMode != MeasureSpec.EXACTLY) {
////	      throw new IllegalStateException("Workspace can only be used in EXACTLY mode.");
////	    }
//
//	    // The children are given the same width and height as the workspace
//	    final int count = getChildCount();
//	    for (int i = 0; i < count; i++) {
//	      getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
//	    }
//	}
	
	
	public void setInputType(int inputtype){
		value_edit.setInputType(inputtype);
	}
	public void setImeOptions(int imeOptions){
		value_edit.setImeOptions(imeOptions);
		
	}
	
	
	
}
