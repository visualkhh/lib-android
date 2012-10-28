package com.kdtandroid.view;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.kdtandroid.dialog.CustomDialog;
import com.kdtandroid.std.adapter.StdAdapter;
import com.kdtandroid.std.item.StdView;
import com.kdtandroid.std.item.StdView_Base;
import com.kdtandroid.util.AndroidUtility;


public class CustomSpinner extends LinearLayout  {

	public CustomSpinner(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public CustomSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public CustomSpinner(Context context,String title) {
		super(context);
		this.context = context;
		this.title=title;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CustomSpinner(Context context,StdAdapter adapter) {
		this(context, adapter, null);	
	}
	public CustomSpinner(Context context,StdAdapter adapter,String title) {
		super(context);
		this.context = context;
		this.adapter = adapter;
		this.title=title;
		accept();
	}
	
	private Context context = null;
	private StdAdapter adapter = null; 
	private int SELETED_INDEX=0;
	Dialog selectDialog = null;
	OnItemClickListener listen = null;
	String title=null;
	private ArrayList<View> icons;
	private ArrayList<View> btns;
	private View view;
	private boolean isClickable=true;
	private boolean isEnabled=true;

	
	private void accept() {
		
		if(adapter!=null){
			this.removeAllViews();
			this.setGravity(Gravity.CENTER);
//			ViewGroup.LayoutParams param  =   new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
//			this.setLayoutParams(param);
			View view = adapter.getItem(SELETED_INDEX).getView(context, StdView.VIEWTYPE.PREVIEW.getValue());
			if(getClickable()){
				view.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							if (adapter != null) {
								if(getEnabled()){
									getSelectDialog().show();
								}
							}
					}
				});
			};
			
			this.addView(view,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
			
			//이거추가.
			if(listen!=null){
				CustomAdapterView adapterview = new CustomAdapterView(context);
				adapterview.setAdapter(adapter);
				View stdview = adapterview.getAdapter().getItem(SELETED_INDEX).getView(context,StdView.VIEWTYPE.LIST.getValue());
				listen.onItemClick(adapterview, stdview, SELETED_INDEX, stdview.getId());
			}
		}
	}

	public void setAdapter (StdAdapter adapter){
		selectDialog=null;
		SELETED_INDEX=0;
		this.adapter = adapter;
		OnItemClickListener tempListen =listen;
		listen=null;
		accept();
		listen = tempListen;
	}
	public StdAdapter getAdapter(){
		return this.adapter;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	public void setIcons(ArrayList<View> icons){
		this.icons = icons;
	}
	public void setButtons(ArrayList<View> btns){
		this.btns = btns;
	}
	
	public void addView(View view){
		this.view = view;
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		boolean sw = true;
//		if(event!=null)
//		 super.onTouchEvent(event);
//		
//		if(sw==true){
//			if (adapter != null) {
//				getSelectDialog().show();
//			}
//		}
//		return sw;
//	}
	public void showDialog(){
		if (adapter != null) {
			getSelectDialog().show();
		}
	}
	
	private Dialog getSelectDialog(){
		if(selectDialog==null){
			LinearLayout container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
			if(view!=null){
				container.addView(view,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
			}
				ListView listview = AndroidUtility.creativeListView(context, 0);
				listview.setAdapter(adapter);
				
				final CustomDialog dialog = new CustomDialog(context);
				dialog.setTitle(title);
				dialog.setIcons(icons);
				dialog.setButtons(btns);
//				LayoutParams param = new LayoutParams(width, height);
//				dialog.setLayoutParams(param);
//				final Dialog dialog = AndroidUtility.creativeDialog(context,listview,title);
				listview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> adapter, View view, int position,
							long id) {
						StdAdapter myAdapter = (StdAdapter)adapter.getAdapter();
						SELETED_INDEX = position;
						accept();
//						if(listen!=null)
//							listen.onItemClick(adapter, view, position, id);
						dialog.dismiss();
					}
					
				});
				container.addView(listview);
				dialog.setContentView(container);
				selectDialog=dialog;
		}
		
		
		return selectDialog;
	}
	
	public void setOnItemClickListener(OnItemClickListener listen){
		this.listen = listen;
	}
	
	public int getSelectedIndex(){
		return SELETED_INDEX;
	}
	public Object getSelectedValue(){
		if(adapter==null)
			return null;
		return adapter.getItem(SELETED_INDEX).getValue();
	}
	public Object getSelectedKey(){
		if(adapter==null)
			return null;
		return adapter.getItem(SELETED_INDEX).getKey();
	}
	public View getSelectedView(){
		if(adapter==null)
			return null;
		return adapter.getItem(SELETED_INDEX).getView(context,StdView.VIEWTYPE.LIST.getValue());
	}
	public StdView_Base getSelectedItem(){
		if(adapter==null)
			return null;
		return adapter.getItem(SELETED_INDEX);
	}
	
	
	
	class CustomAdapterView extends AdapterView<StdAdapter>{
		BaseAdapter adapter=null;
		public CustomAdapterView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		@Override
		public StdAdapter getAdapter() {
			return (StdAdapter) adapter;
		}
		@Override
		public View getSelectedView() {
			return (View) super.getSelectedItem();
		}
		@Override
		public void setAdapter(StdAdapter adapter) {
			this.adapter = adapter;
		}
		@Override
		public void setSelection(int position) {
			
		}
		
	}
	
	
	public void changeIndex(int position){
		SELETED_INDEX = position ;
		accept();
	}
	public void setIndex(int position){
		SELETED_INDEX = position ;
		
		OnItemClickListener tempListen =listen;
		listen=null;
		accept();
		listen = tempListen;
		
	}
	
	@Override
	public void setClickable(boolean clickable) {
		super.setClickable(clickable);
		this.isClickable = clickable;
	}
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.isEnabled = enabled;
	}
	
	public boolean getClickable() {
	return	this.isClickable;// = clickable;
	}
	public boolean getEnabled() {
		return this.isEnabled ;
	}
}
