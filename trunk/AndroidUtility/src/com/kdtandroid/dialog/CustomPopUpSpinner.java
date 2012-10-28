package com.kdtandroid.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.kdtandroid.std.adapter.StdAdapter;
import com.kdtandroid.std.item.StdView;
import com.kdtandroid.std.item.StdView_Base;
import com.kdtandroid.util.AndroidUtility;

public class CustomPopUpSpinner  {
	private Activity context = null;
	private StdAdapter items = null; 
	private int SELETED_INDEX=0;
	Dialog selectDialog = null;
	OnItemClickListener listen = null;
	String title=null;
	public CustomPopUpSpinner(Activity context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public CustomPopUpSpinner(Activity context,String title) {
		this.context = context;
		this.title =  title;
		// TODO Auto-generated constructor stub
	}

	public CustomPopUpSpinner(Activity context,StdAdapter adapter) {
		this(context, adapter, null);
	}
	public CustomPopUpSpinner(Activity context,StdAdapter adapter,String title) {
		this.context = context;
		this.items = adapter;
		this.title=title;
		accept();
		// TODO Auto-generated constructor stub
	}
	public void accept() {
		
		// TODO Auto-generated method stub
		
	}

	public void setAdapter (StdAdapter adapter){
		selectDialog=null;
		SELETED_INDEX=0;
		this.items = adapter;
		accept();
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void showDialog(){
		if (items != null) {
			getSelectDialog().show();
		}
	}
	
	private Dialog getSelectDialog(){
		if(selectDialog==null){
				ListView listview = AndroidUtility.creativeListView(context, 0);
				listview.setAdapter(items);
				final Dialog dialog = AndroidUtility.creativeDialog(context,listview,title);
				listview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> adapter, View view, int position,
							long id) {
						StdAdapter myAdapter = (StdAdapter)adapter.getAdapter();
						SELETED_INDEX = position;
						accept();
						if(listen!=null)
							listen.onItemClick(adapter, view, position, id);
						dialog.dismiss();
					}
					
				});
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
		return items.getItem(SELETED_INDEX).getValue();
	}
	public Object getSelectedKey(){
		return items.getItem(SELETED_INDEX).getKey();
	}
	public View getSelectedView(){
		return items.getItem(SELETED_INDEX).getView(context,StdView.VIEWTYPE.LIST.getValue());
	}
	public StdView_Base getSelectedItem(){
		return items.getItem(SELETED_INDEX);
	}
}
 
