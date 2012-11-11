package khh.android.app;

import java.util.List;

import android.content.Context;
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
}
