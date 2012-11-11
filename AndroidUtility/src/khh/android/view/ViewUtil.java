package khh.android.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

public class ViewUtil {
	public static View getRootView(Activity activity){
		return activity.getWindow().getDecorView().getRootView().getRootView();
	}
	
	public static Bitmap screenShot(View view){
//		 View v1 = view.getRootView();
		view.setDrawingCacheEnabled(true);
	     Bitmap bm = view.getDrawingCache();
//	     view.setDrawingCacheEnabled(false);
	     return bm;
	}
	
	public static int getViewWidth(View context){
		return context.getMeasuredWidth();
	}
	public static int getViewHeight(View context){
		return context.getMeasuredHeight();
	}
	
	
}
