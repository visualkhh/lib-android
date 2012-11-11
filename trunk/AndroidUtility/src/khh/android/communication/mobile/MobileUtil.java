package khh.android.communication.mobile;

import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;

public class MobileUtil {
	public static void setMobileEnable(Context context, boolean dataconnectivity) throws Exception { 
//		<uses-permission android:name="android.permission.MODIFY_PHONE_STATE" />
//		<uses-permission android:name="android.permission.READ_PHONE_STATE" />
		TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		@SuppressWarnings("rawtypes")
		Class c = Class.forName(tm.getClass().getName()); 
		Method m = c.getDeclaredMethod("getITelephony"); 
		m.setAccessible(true); 
		com.android.internal.telephony.ITelephony telephonyService; 
		telephonyService = (com.android.internal.telephony.ITelephony)m.invoke(tm); 

		if(dataconnectivity) {
		telephonyService.enableDataConnectivity();
		} else {
		telephonyService.disableDataConnectivity();
		}
		}
}
