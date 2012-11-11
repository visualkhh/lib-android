package khh.android.setting.bright;

import android.content.Context;
import android.provider.Settings;

public class BrightUtil {
	public static void setBrightness(Context context,int value){
//		0~255
		  // 밝기 값에 value 값을 적용한다. ( value : 0~ 255 값 )
		setBrightnessMode(context,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL); //수동모드
	    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
//	    WindowManager.LayoutParams lp=w.getAttributes();
//		lp.screenBrightness = (float)temp; 
//		w.setAttributes(lp);
		
//	Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL //수동
//	Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;//오토
//		<uses-permission android:name=”android.permission.HARDWARE_TEST”></uses-permission>
	}
	public static void setBrightnessMode(Context context,int mode){
		Settings.System.putInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE,
				mode);
	}
}
