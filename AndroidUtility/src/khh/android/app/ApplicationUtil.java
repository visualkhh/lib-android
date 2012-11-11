package khh.android.app;

import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class ApplicationUtil {
	public static List<ApplicationInfo> getInstalledApplications(Context context){
		final PackageManager pm = context.getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		return list;
//		for (ApplicationInfo applicationInfo : list) {
//			String name = String.valueOf(applicationInfo.loadLabel(pm)); // ���대�
//			String pName = applicationInfo.packageName; // ���⑦�吏�
//			Drawable iconDrawable = applicationInfo.loadIcon(pm); // �����肄�
//		}
	}
	
	public static void executeApplication(Context context, String pkg , String fullclasspath){
//		ComponentName compName = new ComponentName("com.google.android.gm",
//	    "com.google.android.gm.ConversationListActivityGmail");
//	  Intent actIntent = new Intent(Intent.ACTION_MAIN);
//	  actIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//	  actIntent.setComponent(compName);
//	  startActivity(actIntent);
		Intent intent = new Intent();
		  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  intent.setComponent(new ComponentName(pkg, fullclasspath));
		context.startActivity(intent);
	}
	public static void killApplication(Context context, String packageName ){
		ActivityManager mActivityManager=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		try{
			mActivityManager.restartPackage(packageName);                  //2.1 version
		}catch(Exception e){
			mActivityManager.killBackgroundProcesses(packageName);   //2.2 version
		}
	}
	
	public static ComponentName getComponentName(String pkg,String fullclasspath){
		return new ComponentName(pkg, fullclasspath);
	}
}
