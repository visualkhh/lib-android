package khh.android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BroadcastReceiverK_Base extends BroadcastReceiver {
//	<receiver android:name=".LocationLoggerServiceManager"
//		   android:enabled="true"
//		   android:exported="false"
//		   android:label="LocationLoggerServiceManager" >
//		  <intent-filter>
//		   <action android:name="android.intent.action.BOOT_COMPLETED" />
//		  </intent-filter>
//		</receiver>
//		
//		<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

	@Override
	//intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
	abstract public void onReceive(Context context, Intent intent);

}
