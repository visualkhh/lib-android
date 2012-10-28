package com.kdtandroid.util.display;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AndroidDisplayUtil {
	public static Bitmap screenShot(View view){
//		 View v1 = view.getRootView();
		view.setDrawingCacheEnabled(true);
	     Bitmap bm = view.getDrawingCache();
//	     view.setDrawingCacheEnabled(false);
	     return bm;
	}
	
	public static View getRootView(Activity activity){
		return activity.getWindow().getDecorView().getRootView().getRootView();
	}
	
	/*
	<activity android:name=".MyActivity"
	android:theme="@android:style/Theme.NoTitleBar"  >
	<item name="windowNoTitle">true</item>
	 */
	public static void hidenTitleBar(Activity context){
		setWindowFeature(context, Window.FEATURE_NO_TITLE);
	}
	public static void setWindowFeature(Activity context ,int windowFeature){
//		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.requestWindowFeature(windowFeature);
	}
	
	/*
	  <activity android:name=".MyActivity" android:theme="@android:style/Theme.NoTitleBar.Fullscreen"  >
	 <item name="windowFullscreen">true</item>
	 */
	public static void hidenStatusBar(Activity context){
		context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
