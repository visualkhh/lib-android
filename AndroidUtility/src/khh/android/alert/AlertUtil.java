package khh.android.alert;

import com.kdtandroid.util.Class;
import com.kdtandroid.util.Intent;
import com.kdtandroid.util.Notification;
import com.kdtandroid.util.NotificationManager;
import com.kdtandroid.util.PendingIntent;
import com.kdtandroid.util.String;

import android.app.AlertDialog;
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
	
	
	 public static  NotificationManager showNotification(Context context, String title, String message,String alertmessage,int icon,Class forwordActivity) {
		 NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		 
		 // The PendingIntent to launch our activity if the user selects this notification
	        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
	                new Intent(context, forwordActivity), 0);
	        // construct the Notification object.
	        Notification notif = new Notification(icon, alertmessage,
	                System.currentTimeMillis());
	        
	        // Set the info for the views that show in the notification panel.
	        notif.setLatestEventInfo(context, title, message, contentIntent);
	        
	        // after a 100ms delay, vibrate for 250ms, pause for 100 ms and
	        // then vibrate for 500ms.
	        notif.vibrate = new long[] { 100, 250, 100, 500};
	        
	        // Note that we use R.layout.incoming_message_panel as the ID for
	        // the notification.  It could be any integer you want, but we use
	        // the convention of using a resource id for a string related to
	        // the notification.  It will always be a unique number within your
	        // application.
	        nm.notify(1, notif);
	        return nm;
	 }
	
	 public static void clearNotification(NotificationManager nm){
		 nm.cancelAll();
	 }
	public static void showAlert( Context context, String title, String message )
	{
	    AlertDialog.Builder alert = new AlertDialog.Builder(context);  

		alert.setIcon(android.R.drawable.ic_dialog_alert);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setPositiveButton("OK", null);
		alert.show();
		
		/*
//                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    adb.setTitle( R.string.Location_fail_title );
//                    adb.setMessage(R.string.Location_fail_message);
//                    adb.show();
		 */
/*		
		Toast ast = Toast.makeText( context, message, duration);
		ast.setGravity(Gravity.CENTER, 0, 0);
		ast.show();
		
		AlertDialog.Builder alert = new AlertDialog.Builder(context);  
		alert.setIcon(icon);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setPositiveButton("OK", null);
		alert.setNeutralButton(arg0, arg1);
		alert.setNegativeButton(arg0, arg1);
		alert.show();
		
*/		
	}
	public static AlertDialog.Builder creativeAlertDialog(Context context){
		AlertDialog.Builder alert = new AlertDialog.Builder(context);  
		return alert;
	}

	
}
