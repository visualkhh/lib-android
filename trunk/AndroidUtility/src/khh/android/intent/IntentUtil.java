package khh.android.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

public class IntentUtil {
	
	
//	Intent-Filter 값
//	Intent 클래스의 정의 AndroidManifest 일치값 설명
//	ACTION_MAIN android.intent.action.MAIN 시작(루트) 액티비티
//	ACTION_VIEW android.intent.action.VIEW 해당 자원을 보여주는 액션
//	ACTION_CALL android.intent.action.CALL 전화를 거는 액션
//	ACTION_DIAL android.intent.action.DIAL 전화 다이얼이 나오는 액션
//	ACTION_SEND android.intent.action.SEND 문자,메일등 데이터를 보내는 액션
//	ACTION_SCREEN_ON android.intent.action.SCREEN_ON 화면이 켜지는 액션
//	ACTION_BATTERY_LOW android.intent.action.BATTERY_LOW 배터리 부족 액
	
	
	
	public static Intent getIntent(Context context, Class activitys){
		Intent intent = new Intent(context, activitys);
		return intent;
	}
	public static Intent getIntent(String action){
		Intent intent = new Intent(action);
		return intent;
	}
	
	public static Parcelable getParcelableExtra(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getParcelableExtra(extraName);
	}
	public static String getStringExtra(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringExtra(extraName);
	}
	public static String[] getStringArrayExtra(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringArrayExtra(extraName);
	}
	public static int getIntExtra(Activity context,String extraName,int defaultValue){
		Intent intent = context.getIntent();
		return intent.getIntExtra(extraName, defaultValue);
	}
	public static double getDoubleExtra(Activity context,String extraName,double defaultValue){
		Intent intent = context.getIntent();
		return intent.getDoubleExtra(extraName, defaultValue);
	}
	public static Bundle getExtras(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getExtras();
	}
	
	public static Intent getMarketIntentFromPkg(String pkgname){
//		 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")); 
		return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+pkgname));
	}
	public static Intent getMarketIntentFromOwner(String owner){
//		 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")); 
		return new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:"+owner));
	}
	public static Intent getMarketIntentFromKeyword(String keyword){
//		 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")); 
		return new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q="+keyword));
	}
	public static Intent getLocationSettingIntent(){
		return new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	}
	public static Intent getIntent(ComponentName cn){
		return new Intent().setComponent(cn);
	}
	
}
