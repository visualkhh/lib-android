package khh.android.alert;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class AlertUtil {
	public static void showToast( Context context,String title){
		showToast(context,title,Toast.LENGTH_SHORT);
	}
	public static void showToast( Context context,String title,int tosttime){
		Toast toast = Toast.makeText(context, title, tosttime);
		 toast.setGravity( Gravity.CENTER , 0, 0 );
		 toast.show();
	}
	public static void showToast( Context context,View view){
		 Toast toast = new Toast(context);
	        toast.setView(view);
	        toast.setGravity( Gravity.CENTER , 0, 0 );
	        toast.setDuration(Toast.LENGTH_LONG);
	        toast.show();
	}
}
