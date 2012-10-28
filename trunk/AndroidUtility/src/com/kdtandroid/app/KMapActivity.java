package com.kdtandroid.app;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.maps.MapActivity;

abstract public class KMapActivity extends MapActivity implements UIinterface {
	HashMap<Integer, View> componet_container = null;
	HashMap<Integer, Object> data_container = null;
	protected Activity  context = this;
	public Handler handler = new Handler();
	boolean isfinish=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if(isfinish==false)
		onPretreatment();
		
		super.onCreate(savedInstanceState);
		
		if(isfinish==false)
		onSettingComponent();
		if(isfinish==false)
		onSyncComponentData();
		if(isfinish==false)
		onAddComponentListener();
	}

	
	abstract public void onPretreatment(); // ��ó��.�����͵�..
	abstract public void onSettingComponent();  // ������Ʈ����
	abstract public void onSyncComponentData(); // ������Ʈ �����ũ
	abstract public void onAddComponentListener(); // ������Ʈ ������ ���
	
	final public void action(int requst){action(requst,null);}						//����
	abstract public void action(int requst,Object obj);// {}						//����
	final public void actionForResult(int requst,int result) {actionForResult(requst, result, null);}	//����
	abstract public void actionForResult(int requst,int result,Object obj);// {}	//����
	
	
	
	
	
	
	
	public void changeView(int gbtype) {}
	public View getComponent(int id) {
		if (componet_container == null) {
			componet_container = new HashMap<Integer, View>();
		}
		if (componet_container.get(new Integer(id)) == null) {
			componet_container.put(new Integer(id), context.findViewById(id));
		}
		return componet_container.get(new Integer(id));
	}
	public void setComponent(View view, int id) {
		view.setId(id);
		if (componet_container == null) {
			componet_container = new HashMap<Integer, View>();
		}
			componet_container.put(new Integer(id),view);
	}
	public Object getData(int id) {
		if (data_container == null) {
			data_container = new HashMap<Integer, Object>();
		}
		return data_container.get(new Integer(id));
	}
	public void setData(int id,Object object) {
		if (data_container == null) {
			data_container = new HashMap<Integer, Object>();
		}
			data_container.put(new Integer(id), object);
	
	}
	
	@Override
	public void finish() {
		isfinish=true;
		super.finish();
	}
	
	public void ok(View o){
		setResult(RESULT_OK);
		this.finish();
	}
	public void cancel(View o){
		setResult(RESULT_CANCELED);
		this.finish();
	}

	
	@Override
	protected boolean isLocationDisplayed() {
//		return super.isLocationDisplayed();
		return false;
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	
}
