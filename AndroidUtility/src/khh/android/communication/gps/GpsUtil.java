package khh.android.communication.gps;

import com.google.android.maps.GeoPoint;

import khh.android.converting.ConveringUtil;
import khh.android.intent.IntentUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

public class GpsUtil {

	 public static boolean checkGpsService(Context context) {//GPS의 설정여부 확인 및 자동 설정 변경
         String gs = android.provider.Settings.Secure.getString(context.getContentResolver(),
         android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
         if (gs.indexOf("gps", 0) < 0) {
             return false;
             
//             // GPS OFF 일때 Dialog 띄워서 설정 화면으로 이동.
//             AlertDialog.Builder gsDialog = new AlertDialog.Builder(context);
//             gsDialog.setTitle("GPS Status OFF !!!");
//             gsDialog.setMessage("Change Setting !!");
//             gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                 public void onClick(DialogInterface dialog, int which) {
//                     // GPS설정 화면으로 이동
//                     Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                     intent.addCategory(Intent.CATEGORY_DEFAULT);
//                     context.startActivity(intent);
//                 }
//             }).create().show();
         } else {
             //Log.w("chkGpsService" , "status: on" );                 
             return true;
         }
     }    



		
	 public static Location getGPS(Context context){
		 return getGPS(context,null,null,null);
	 }
	public static Location getGPS(Context context,LocationListener listener, Integer sec, Integer meter){
        String contextstr = Context.LOCATION_SERVICE;//안드로이드 시스템으로 부터 LocationManager 서비스를 요청하여 할당
        LocationManager locationManager = (LocationManager)context.getSystemService(contextstr);
        //GPS 환경설정
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);       // 정확도
        criteria.setPowerRequirement(Criteria.POWER_LOW);   // 전원 소비량
        criteria.setAltitudeRequired(false);                // 고도, 높이 값을 얻어 올지를 결정
        criteria.setBearingRequired(false);                 // provider 기본 정보
        criteria.setSpeedRequired(false);                   //속도
        criteria.setCostAllowed(true);                      //위치 정보를 얻어 오는데 들어가는 금전적 비용
        //상기 조건을 이용하여 알맞은 GPS선택후 위치정보를 획득
        //manifest xml  : <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />//로케이션 메니저의 provider 
        String provider = locationManager.getBestProvider(criteria, true);
        
        if(listener!=null){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, sec, meter, listener);//현재정보를 업데이트
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, sec, meter, listener);//현재정보를 업데이트
        }
        
       return  locationManager.getLastKnownLocation(provider);
	}
	public static GeoPoint getGPSFromNetwork(Context context, LocationListener listener, int sec, int meter){
//		((LocationManager)context.getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude()*1E6),,(int)(((LocationManager)getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude()*1E6))
		 LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		   if(listener!=null)
	            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, sec, meter, listener);//현재정보를 업데이트
		   Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		   GeoPoint geo=null;
//		   if(location!=null){
			 double lat = location.getLatitude();
			 double lon = location.getLongitude();
			 geo=ConveringUtil.getGeoPoint(lat, lon);
//		   }
		   return geo;
	}
	
	public static void setGPSRemoveUpdate(Context context,LocationListener listener){
		String contextstr = Context.LOCATION_SERVICE;//안드로이드 시스템으로 부터 LocationManager 서비스를 요청하여 할당
		LocationManager locationManager = (LocationManager)context.getSystemService(contextstr);
		locationManager.removeUpdates(listener);
	}
}
