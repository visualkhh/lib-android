package khh.android.window;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class WindowUtil {

	public static void setTitle(Activity context , String titlemsg){
		context.setTitle(titlemsg);
	}
	public static void setTitle(Activity context , int layout_id){
		context.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,layout_id);
	}
	public static void setNoTitle(Activity context){
		setFeature(context, Window.FEATURE_NO_TITLE);
	}
	public static void setProgressTitle(Activity context){
		setFeature(context, Window.FEATURE_INDETERMINATE_PROGRESS);
	}
	public static void setFeature(Activity context ,int windowFeature){
//		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.requestWindowFeature(windowFeature);
	}
	
	
	
	public static void setLookWindow(Activity context){
		context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
}
