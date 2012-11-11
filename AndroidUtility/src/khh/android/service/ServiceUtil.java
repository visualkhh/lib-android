package khh.android.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

public class ServiceUtil {

//    <service android:name="SmartLookService">
//    <intent-filter>
//           <action android:name="khh.android.look"/>
//           <category android:name="android.intent.category.DEFAULT"/>
//    </intent-filter>
//		</service>
	//serviceIntent 에있는 class는 Service를 상속받은 것만된다.
	public static ComponentName startService(Activity context,Intent serviceIntent){
		return context.startService(serviceIntent);
	}
	public static boolean stopService(Activity context,Intent serviceIntent){
		return context.stopService(serviceIntent);
	}
}
