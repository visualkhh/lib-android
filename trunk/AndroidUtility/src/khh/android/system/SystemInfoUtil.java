package khh.android.system;

import android.os.Build;

public class SystemInfoUtil {
	public static String getSystemInfo(){
		String a="BOARD"+ Build.BOARD;
		  a+="BRAND"+ Build.BRAND;
		  a+="CPU_ABI"+ Build.CPU_ABI;
		  a+="DEVICE"+ Build.DEVICE;
		  a+="DISPLAY"+ Build.DISPLAY;
		  a+="FINGERPRINT"+ Build.FINGERPRINT;
		  a+="HOST"+ Build.HOST;
		  a+="ID"+ Build.ID;
		  a+="MANUFACTURER"+ Build.MANUFACTURER;
		  a+="MODEL"+ Build.MODEL;
		  a+="PRODUCT"+ Build.PRODUCT;
		  a+="TAGS"+ Build.TAGS;
		  a+="TYPE"+ Build.TYPE;
		  a+="USER"+ Build.USER;
		  return a;

	}
}
