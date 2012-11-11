package khh.android.communication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class CommunicationUtil {
	public static int availableCommunication(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		  boolean isWifiAvail = ni.isAvailable();
		  boolean isWifiConn = ni.isConnected();
		  ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		  boolean isMobileAvail = ni.isAvailable();
		  boolean isMobileConn = ni.isConnected();
		  String status = "WiFi\nAvail = " + isWifiAvail + "\nConn = "
		    + isWifiConn + "\nMobile\nAvail = " + isMobileAvail
		    + "\nConn = " + isMobileConn + "\n";
		  Log.d("Communication State! ",status);
		  if(isWifiAvail == true && isWifiConn == true){
			  return ConnectivityManager.TYPE_WIFI;
		  }else if(isMobileAvail == true && isMobileConn == true){
			  return ConnectivityManager.TYPE_MOBILE;
		  }else{
			  return -1;
		  }
	}
}
