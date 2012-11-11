package khh.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class ActivityUtil {
	public static void goActivity(Context context, Intent intent){
		context.startActivity(intent);
	}
	
	public static void goActivityNewTask(Context context, Class gopage){
		Intent intent = new Intent(context, gopage);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void goActivity(Context context, Class gopage){
		Intent intent = new Intent(context, gopage);
		context.startActivity(intent);
	}
	public static void goActivity(Context context,Class gopage,String extraName , String extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goActivity(Context context,Class gopage,String extraName , int extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goActivity(Context context,Class gopage,String extraName ,  Parcelable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	
}
