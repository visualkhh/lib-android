package khh.android.key;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public class KeyUtil {
	//
	public static void lockHomeKey(Window window){
		window.setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	}
	public static void lockKeyguard(Context context ){
		setKeyguardLook(context,true);
	}
	public static void unlockKeyguard(Context context ){
		setKeyguardLook(context,false);
	}
	public static void setKeyguardLook(Context context , boolean sw){
		KeyguardManager manager = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);  
		KeyguardLock lock = manager.newKeyguardLock(context.KEYGUARD_SERVICE);  
		if(sw){
			lock.reenableKeyguard();  
			//<uses-permission android:name="android.permission.ENABLE_KEYGUARD" />
		}else{
			lock.disableKeyguard();
			//<uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
		}
	}
	
}
