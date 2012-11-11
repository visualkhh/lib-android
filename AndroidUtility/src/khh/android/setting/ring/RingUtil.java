package khh.android.setting.ring;

import android.content.Context;
import android.media.AudioManager;

public class RingUtil {
	public static void setRingMode(Context context,int mode){
		AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		mgr.setRingerMode(mode);
//		AudioManager.RINGER_MODE_NORMAL;//벨소리모드
//		AudioManager.RINGER_MODE_VIBRATE;//진동
//		AudioManager.RINGER_MODE_SILENT;//무음
		}
}
