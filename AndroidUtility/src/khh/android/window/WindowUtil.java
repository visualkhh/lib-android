package khh.android.window;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class WindowUtil {

	public static void setTitle(Activity context , String titlemsg){
		context.setTitle(titlemsg);
	}
	public static void setTitle(Window window , int layout_id){
		window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,layout_id);
	}
	
	/*
	<activity android:name=".MyActivity"
	android:theme="@android:style/Theme.NoTitleBar"  >
	<item name="windowNoTitle">true</item>
	 */
	public static void hidenTitleBar(Activity context){
		setFeature(context, Window.FEATURE_NO_TITLE);
	}
	/*
	  <activity android:name=".MyActivity" android:theme="@android:style/Theme.NoTitleBar.Fullscreen"  >
	 <item name="windowFullscreen">true</item>
	 */
	public static void hidenStatusBar(Window window){
		window.setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
				);
	}
	public static void setProgressTitle(Activity context){
		setFeature(context, Window.FEATURE_INDETERMINATE_PROGRESS);
	}
	public static void setFeature(Activity context ,int windowFeature){
//		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.requestWindowFeature(windowFeature);
	}
	
	

	public static void lockWindow(Window window){
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//
//이렇게 선언해주는게 있습니다.
//FLAG_SHOW_WHEN_LOCKED 는 lock화면 띄우기 전에 자신의 화면을 먼저 띄운다는것이고
//FLAG_TURN_SCREEN_ON 은 화면을 켜주는것으로 알고있습니다.

//		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		window.addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED	//lock화면 뛰우기전에 항상나를먼저
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON	//화면을 켜줘라 내꺼
//				|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON		//Screen 을 켜진 상태로 유지한다. 
//				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD	//Keyguard를 해지한다. 
                );
	}
	
	//lock 이면 t
	public static boolean isLookWindow(Context context){
		KeyguardManager km = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
		return km.inKeyguardRestrictedInputMode();
	}
	
	public static void unLockWindow(Context context){
		KeyguardManager km = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock keyLock = km.newKeyguardLock(context.KEYGUARD_SERVICE);
		keyLock.disableKeyguard(); 
//		위에서 사용한 코드(KeyguardManager를 사용하려면 AndroidManifest.xml에서 퍼미션을 추가해줘야 사용 가능하다.
//				<uses-permission android:name="android.permission.DISABLE_KEYGUARD"/>
	}
	public static void lock(Context context){
		KeyguardManager km = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock keyLock = km.newKeyguardLock(context.KEYGUARD_SERVICE);
		keyLock.reenableKeyguard();
	}
	//onAttachedToWindow() 에서 추가해줘야함.
	public static void disableHomeKey(Window window){
		window.setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	}
	
	
	
	
	
	public static int getWindowWidth(Context context){
		Display display = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	public static int getWindowHeight(Context context){
		Display display = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
	
	
	
	
	
}
