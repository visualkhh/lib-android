package khh.android.setting.vibrator;

import android.content.Context;
import android.os.Vibrator;

public class VibratorUtil {
	public static void setVibrator(Context context,long mms){
		Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(mms);
	}
}
