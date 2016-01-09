package khh.android.intent;

import java.io.File;
import java.io.Serializable;

import com.kdtandroid.util.ActivityManager;
import com.kdtandroid.util.Class;
import com.kdtandroid.util.Exception;
import com.kdtandroid.util.String;

import android.app.Activity;
import android.app.PendingIntent;
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
	
	
	public static void goProgram(Context context, String pkg , String fullclasspath){
		Intent intent = new Intent();
		  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  intent.setComponent(new ComponentName(pkg, fullclasspath));
		context.startActivity(intent);
	}
	public static void killProgram(Context context, String packageName ){
		ActivityManager mActivityManager=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		try{
			mActivityManager.restartPackage(packageName);                  //2.1 version
		}catch(Exception e){
			mActivityManager.killBackgroundProcesses(packageName);   //2.2 version
		}
	}
	
	
	public static void goPage(Context context, Class gopage){
		Intent intent = new Intent(context, gopage);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName , String extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName , int extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName ,  Serializable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName ,  Parcelable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	
	public static void openPDF(Context context,File file){
		Uri path = Uri.fromFile(file);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(path, "application/pdf");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	//result는  호출자의    onActivityResult 메서드로 넣어준다.  caller는 오버라이등해놓아라.
	public static void resultSetParameter(Activity context,int resultCode){
		context.setResult(resultCode);
//		Intent intent = new Intent(context, gopage);
//		context.startActivity(intent);
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , String extraValue){
		Intent intent = new Intent();
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , int extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , int[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , double extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , double[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , float extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , float[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName ,  Parcelable extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	
	public static void goPageForResult(Activity context,int requstcode, Class gopage){
		Intent intent = new Intent(context, gopage);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , String extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , Parcelable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , int extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goDial(Context context,String dial_number){
		Intent phonepassIntent =  new Intent();	
		phonepassIntent.setAction(Intent.ACTION_DIAL); 
		phonepassIntent.setData(Uri.parse("tel:"+dial_number));
	    context.startActivity(phonepassIntent);
	}
	 public void goGpsSetting(Context context){
         // GPS OFF 일때 Dialog 띄워서 설정 화면으로 이동.
         // GPS설정 화면으로 이동
        // Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		  Intent intent = IntentUtil.getLocationSettingIntent();
         intent.addCategory(Intent.CATEGORY_DEFAULT);
         context.startActivity(intent);
	 }
	public static Parcelable requestGetParameter_toParcelable(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getParcelableExtra(extraName);
	}
	public static int requestGetParameter_toInt(Activity context,String extraName,int defaultValue){
		Intent intent = context.getIntent();
		return intent.getIntExtra(extraName, defaultValue);
	}
	public static double requestGetParameter_toDouble(Activity context,String extraName,double defaultValue){
		Intent intent = context.getIntent();
		return intent.getDoubleExtra(extraName, defaultValue);
	}
	public static String requestGetParameter_toString(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringExtra(extraName);
	}
	public static String[] requestGetParameter_toStringArray(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringArrayExtra(extraName);
	}
	
	//조건맞을때 발생시킨다
	public static PendingIntent setPendingIntentBroadcast(Context context,int id, Intent intent){
		PendingIntent pintent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		return pintent;
	}
	
}
