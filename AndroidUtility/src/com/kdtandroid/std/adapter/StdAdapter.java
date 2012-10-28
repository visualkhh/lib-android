package com.kdtandroid.std.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kdtandroid.std.item.StdView_Base;


	public class StdAdapter extends BaseAdapter {
	private Context context;
private ArrayList<StdView_Base> list;
//	LayoutInflater Inflater;

//	public CustomViewadapter(Context context,ArrayList<KCustomViewAdapter_DTO> list) {

	public StdAdapter(){
		
	}
	public StdAdapter(Context context){
		this.context = context;
	}

	public StdAdapter(Context context,StdView_Base[] list_arr) {
		ArrayList <StdView_Base> list = new ArrayList<StdView_Base>();
		for(int i = 0 ; i <list_arr.length;i++)
			list.add(list_arr[i]);
		this.context = context;
		this.list = list;
	}
	public StdAdapter(Context context,ArrayList<StdView_Base> list) {
		this.context = context;
//		Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
	}

	public int getCount() {
		if(list==null)
			return 0;
		
		return list.size();
	}

	public StdView_Base getItem(int position) {
		if(list==null)
			return null;
		
		return list.get(position);
	}
	public StdView_Base getItem(String key) {
		if(list==null)
			return null;
		
		for(int  i = 0 ; i  < this.list.size();i++){
			if(this.list.get(i).getKey().equals(key) || this.list.get(i).getKey()==key)
				return list.get(i);
		}
		return null;
//		return list.get(position);
	}
	public void setItem(int position,StdView_Base item) {
		list.add(position,item);
	}
	public void setItem(StdView_Base item) {
		list.add(item);
	}
	public ArrayList<StdView_Base> getList() {
		return list;
	}
	

	public long getItemId(int position) {
		return position;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		if(context==null || list==null || list.size()<=0){
			return null;
		}
		if(convertView==null){
			convertView = list.get(position).getView(context,StdView_Base.VIEWTYPE.LIST.getValue());
		}
		return convertView;
		
	}
}