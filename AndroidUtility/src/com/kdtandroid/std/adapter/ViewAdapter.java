package com.kdtandroid.std.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kdtandroid.std.item.StdView;


	public class ViewAdapter extends BaseAdapter {
	private Context context;
private ArrayList<View> list;
//	LayoutInflater Inflater;

//	public CustomViewadapter(Context context,ArrayList<KCustomViewAdapter_DTO> list) {


	public ViewAdapter(Context context,View[] list_arr) {
		ArrayList <View> list = new ArrayList<View>();
		for(int i = 0 ; i <list_arr.length;i++)
			list.add(list_arr[i]);
		this.context = context;
		this.list = list;
	}
	public ViewAdapter(Context context,ArrayList<View> list) {
		this.context = context;
//		Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
	}

	public int getCount() {
		return list.size();
	}

	public View getItem(int position) {
		return list.get(position);
	}
	public ArrayList<View> getList() {
		return list;
	}
	

	public long getItemId(int position) {
		return position;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
			convertView = list.get(position);
		return convertView;
	}
}