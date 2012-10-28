package com.kdtandroid;

import android.util.Log;
import android.view.View;

import com.kdtandroid.app.KActivity;
import com.kdtandroid.util.AndroidUtility;
import com.kdtandroid.view.PaintKView;

public class utilityindex extends KActivity {
	@Override
	public void action(int requst, Object obj) {
		
	}
	@Override
	public void actionForResult(int requst, int result, Object obj) {
	}
	@Override
	public void onAddComponentListener() {
	}
	@Override
	public void onSettingComponent() {
//		PaintKView a = new PaintKView(context);
//		a.setBackgroundColor(0xffff0000);
//		a.setLineColor(0xff0000ff);
//		setContentView(a);
		
		setContentView(R.layout.viewadd);
//		addContentView(new PaintKView(context), params);
	}
	
	@Override
	public void onSyncComponentData() {
	}
	
	
	
	boolean wifi=true;
	boolean mobile=false;
	
	public void onClick_wifi(View v){
		AndroidUtility.setWifiEnable(context, wifi);
		Log.d("a","onclickwifi");
		wifi=!wifi;
	}
	
	public void onClick_mobile(View v) throws Exception{
		AndroidUtility.setMobileEnable(context, mobile);
		Log.d("a","Mobile");
		mobile=!mobile;
		
	}
}