package khh.android.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

public class IntentUtil {
	
	public static Intent getIntent(Context context, Class activitys){
		Intent intent = new Intent(context, activitys);
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
	
	public static Intent getMarketIntent(String pkgname){
//		 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")); 
		return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+pkgname));
	}
	public static Intent getLocationSettingIntent(){
		return new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	}
	
}
