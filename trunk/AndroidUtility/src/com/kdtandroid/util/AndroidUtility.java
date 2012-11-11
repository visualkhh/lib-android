package com.kdtandroid.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.app.KeyguardManager.KeyguardLock;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ZoomButtonsController;
import android.widget.TabHost.TabSpec;
import android.widget.ZoomButtonsController.OnZoomListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.kdt.util.Utilities;
import com.kdt.util.xml.XMLparser;
import com.kdtandroid.dialog.CustomDialog;
import com.kdtandroid.listener.AlphaListener;
import com.kdtandroid.map.wearther.GeoToWeather;
import com.kdtandroid.map.wearther.Weather;
import com.kdtandroid.std.adapter.StdAdapter;
import com.kdtandroid.view.CustomFlipper;
import com.kdtandroid.view.CustomSpinner;

public class AndroidUtility {
//	public static String mapKey="0UWMQwxgOV3fpL_h2ETFv3wvl6yLBa5lBIapj6g";
//	public static String mapKey="0lGutmCGHYElty5wwuwVf067QqEXESJ6FivrL0g";
	public static String TAG="AndroidUtility";
	
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

	
	
	public static Intent addRegisterReceiver(Context context,BroadcastReceiver broadcastreceiver,IntentFilter filter){
		return context.registerReceiver(broadcastreceiver,filter);
	}
	
	
	public static TabHost creativeTabFrame(ActivityGroup activityGroup,ViewGroup child_container){
		TabHost tabhost = new TabHost(activityGroup.getApplicationContext());
		tabhost.setId(android.R.id.tabhost);
		
//		LinearLayout layout = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL,Gravity.CENTER);
//		
//		TabWidget tabwidget = new TabWidget(context);
//		tabwidget.setId(android.R.id.tabs);
		
		
		
		
		
//		FrameLayout tabcontent = AndroidUtility.creativeFrameLayout(context);
//		tabcontent.setId(android.R.id.tabcontent);
		
//		layout.addView(tabwidget,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//		layout.addView(tabcontent,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		tabhost.addView(child_container,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		tabhost.setup(activityGroup.getLocalActivityManager());
		
		
//		 tabhost.setup();
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab1", "Name tab1", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab2", "Name tab2", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab3", "Name tab3", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab4", "Name tab4", AndroidUtility.getDrawable(context, R.drawable.icon));
		
		return tabhost;
//		TabHost tabhost = new TabHost(context);
//		tabhost.setId(android.R.id.tabhost);
//		
//		
//		LinearLayout layout = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL,Gravity.CENTER);
//		
//		TabWidget tabwidget = new TabWidget(context);
//		tabwidget.setId(android.R.id.tabs);
//		
//		
//		
//		
//		
//		FrameLayout tabcontent = AndroidUtility.creativeFrameLayout(context);
//		tabcontent.setId(android.R.id.tabcontent);
//		
//		layout.addView(tabwidget,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//		layout.addView(tabcontent,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		
//		tabhost.addView(layout,AndroidUtility.creativeLinearLayoutParam(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		tabhost.setup(getLocalActivityManager());
//		
//		
////		 tabhost.setup();
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab1", "Name tab1", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab2", "Name tab2", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab3", "Name tab3", AndroidUtility.getDrawable(context, R.drawable.icon));
//		AndroidUtility.addTab(tabhost, AndroidUtility.getIntent(context, SolraLocation.class), "tab4", "Name tab4", AndroidUtility.getDrawable(context, R.drawable.icon));
//		
//		setContentView(tabhost);
	}
	public static MapView creativeMapView(Context context,String mapKey,boolean setSatellite,boolean setStreeView,boolean setBuiltInZoomControls){
		
		MapView mapview  = creativeMapView(context,mapKey);
		mapview.setSatellite(setSatellite);
		mapview.setStreetView(setStreeView);
		mapview.setBuiltInZoomControls(setBuiltInZoomControls);
		return mapview;
	}
	public static MapView creativeMapView(Context context,String mapKey){
		MapView mapview = new MapView(context,mapKey);
		mapview.setClickable(true);
		return mapview;
	}
	
	
	public static Weather  getWeather(Context context,double lat,double lon){
		return	getGeoToWeather(context, lat, lon).getWeather();
	}
	public static GeoToWeather getGeoToWeather(Context context,double lat,double lon){
		Geocoder t_Geocoder = new Geocoder(context);
		GeoToWeather gtw = new GeoToWeather(t_Geocoder, getGeoPoint(lat, lon));
		return gtw;
	}
	
	
	
	
	
	public static MyLocationOverlay setMapMyLocationOverlay(Context context,MapView mapview,boolean wantCompass,boolean wantMyLocation ){
		MyLocationOverlay myLocationOverlay =  creativeMyLocationOverlay(context, mapview);
		if(wantCompass)
			myLocationOverlay.enableCompass();
		else
			myLocationOverlay.disableCompass();		
		
		if(wantMyLocation)
			myLocationOverlay.enableMyLocation();
		else
			myLocationOverlay.disableMyLocation();
		
		mapview.getOverlays().add(myLocationOverlay);
		
		return myLocationOverlay;
	}

	public static MapController setMapZoomLevel (MapView mapView,int zoomLevel){
		MapController mapcontroller = mapView.getController();
		mapcontroller.setZoom(zoomLevel);
		mapView.invalidate();
		return mapcontroller;
	}
	
	public static MapController getMapController (MapView mapView){
		MapController mapcontroller = mapView.getController();
		return mapcontroller;
	}	
	public static MyLocationOverlay creativeMyLocationOverlay (Context context, MapView mapview){
		return    new MyLocationOverlay(context, mapview);
	}
	
	//maputil
	public static void setMapMove(MapView mapview , double lat, double lon) {
		setMapMove(mapview,lat,lon,-1);
	}
	public static void setMapMove(MapView mapview , double lat, double lon,int zoomLevel) {
		MapController controller = getMapController(mapview);
		if(0<zoomLevel)
		setMapZoomLevel(mapview, zoomLevel);
		controller.animateTo(getGeoPoint(lat,lon) );	
		mapview.invalidate();
	}

	public static void setMapOnZoomListener(MapView mapview, OnZoomListener listener){
		getMapZoomButtonsColtroll(mapview).setOnZoomListener(listener);
	}
	public static ZoomButtonsController getMapZoomButtonsColtroll(MapView mapview ){
		ZoomButtonsController  mapZoomControl = mapview.getZoomButtonsController();
		return mapZoomControl;
	}
	public static GeoPoint getGeoPoint(double lat, double lon){
		return new GeoPoint((int)(lat * 1E6), (int)(lon*1E6));
	}
	public static GeoPoint getGeoPoint(Location location){
		return getGeoPoint(location.getLatitude(), location.getLongitude());
	}
//	public static GeoPoint getGeoPoint(Direction direction){
//		return getGeoPoint(direction.getLatitude(), direction.getLongitude());
//	}
	
	public static void setKeyboardVisible(Context context,View view,boolean wantShowKeyboard){
		InputMethodManager mgr = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
		// only will trigger it if no physical keyboard is open
		if(wantShowKeyboard){
			mgr.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
		}else{
	//		숨기기
			mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	public static Location getLocation(Address addr){
		Location location = new Location(LocationManager.NETWORK_PROVIDER);
		location.setLatitude(addr.getLatitude());
		location.setLongitude(addr.getLongitude());
		return location;
	}
	public static Location getLocation(GeoPoint geo){
		return getLocation(geo,LocationManager.NETWORK_PROVIDER);
	}
	public static Location getLocation(GeoPoint geo,String provider){
//		LocationManager.NETWORK_PROVIDER
		Location location = new Location(provider);
		location.setLatitude(geo.getLatitudeE6()/1E6);
		location.setLongitude(geo.getLongitudeE6()/1E6);
		return location;
	}
	

	public static String getAddress_toString(Address adr){
		StringBuilder geoString = new StringBuilder();
    	for(int y = 0 ; y<adr.getMaxAddressLineIndex();y++)
    		geoString.append(adr.getAddressLine(y)).append("\n");
    		
    	if (adr.getLocality() != null) geoString.append(adr.getLocality()).append(" "); //서울특별시
    	if (adr.getThoroughfare() != null) geoString.append(adr.getThoroughfare()).append(" ");		//방학1동
    	if (adr.getFeatureName () != null) geoString.append(adr.getFeatureName ());		//12-22 번지
    	if (adr.getSubAdminArea  () != null) geoString.append(adr.getSubAdminArea  ());		//getSubAdminArea 
    	if (adr.getPremises  () != null) geoString.append(adr.getPremises  ());		//12-22
//		    	if (adr.getPostalCode  () != null) geoString.append(adr.getPostalCode  ());		//12-22
		//    	if (adr.getSubThoroughfare   () != null) geoString.append(adr.getSubThoroughfare   ());		//12-22
		//    	if (adr.getThoroughfare    () != null) geoString.append(adr.getThoroughfare    ());		//12-22
		//    	if (adr.getUrl    () != null) geoString.append(adr.getUrl    ());		//12-22
		//    	if (adr.getCountryName () != null) geoString.append(adr.getCountryName ());		//대한민국
		//    	if (!"".equals(geoString.toString())) geoString.append("\n");
		//    	geoString.append("위도 : ").append(lat).append(" ,경도 : ").append(lng);
    	return geoString.toString();
	}
	public static ArrayList<String> getAddress_toString(List<Address> addrlist){
		
		ArrayList<String> adr_string =new ArrayList<String>();
//    	Geocoder goecoder = new Geocoder(context,
//    	Locale.getDefault());
    	for(int  i = 0 ; i < addrlist.size();i++){
			StringBuilder geoString = new StringBuilder();
			Address adr =addrlist.get(i);
		    	adr_string.add(getAddress_toString(adr));
    	}
    	return adr_string;
	}
//	public static ArrayList<com.kdt.std.realworld.Address> getAddress_toObj(List<Address> addrlist){
//		ArrayList<com.kdt.std.realworld.Address> adrlist = new ArrayList<com.kdt.std.realworld.Address>(); 
//    	for(int  i = 0 ; i < addrlist.size();i++){
//    		com.kdt.std.realworld.Address addr= new com.kdt.std.realworld.Address();
//    		Address adr =addrlist.get(i);
//		    	if (adr.getLocality() != null) addr.setSi(adr.getLocality()); //서울특별시
//				if (adr.getThoroughfare() != null) addr.setDong(adr.getThoroughfare());		//방학1동
//				if (adr.getFeatureName () != null) addr.setBunji(adr.getFeatureName ());		//12-22 번지
//				if (adr.getFeatureName () != null) addr.setPostcode(adr.getPostalCode());		//우편
//		    	
//		//    	if (adr.getPostalCode  () != null) geoString.append(adr.getPostalCode  ());		//12-22
//		//    	if (adr.getSubThoroughfare   () != null) geoString.append(adr.getSubThoroughfare   ());		//12-22
//		//    	if (adr.getThoroughfare    () != null) geoString.append(adr.getThoroughfare    ());		//12-22
//		//    	if (adr.getUrl    () != null) geoString.append(adr.getUrl    ());		//12-22
//		//    	if (adr.getCountryName () != null) geoString.append(adr.getCountryName ());		//대한민국
//		//    	if (!"".equals(geoString.toString())) geoString.append("\n");
//		//    	geoString.append("위도 : ").append(lat).append(" ,경도 : ").append(lng);
//		    	adrlist.add(addr);
//    	}
//    	return adrlist;
//	}
	
	public static List<Address> getAddress(Context context,Location location ,int wantSize){
		return getAddress(context, location.getLatitude(), location.getLongitude(),wantSize);
	}
	public static List<Address> getAddress(Context context,double lat, double lng,int wantSize ){
	  	Geocoder goecoder = new Geocoder(context,
	  	    	Locale.getDefault());

	  	    	List<Address> adr=null;
				try {
					adr = goecoder.getFromLocation(lat,
					lng, wantSize);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	return adr;
	}
	public static List<Address> getAddress(Context context,String findName ,int wantSize ){
		List<Address> adr = null;
//		StringBuilder geoString = new StringBuilder();
		try {
			Geocoder goecoder = new Geocoder(context, Locale.getDefault());
			adr = goecoder.getFromLocationName(findName, wantSize);
		}catch(Exception e){
			return null;
		}
		return adr;//etAddress_toString(context,adr.getLatitude(),adr.getLongitude());
	}

	


	public static ViewFlipper creativeFlipper(Context context,ArrayList<View> addView,int in_ani,int out_ani){
		ViewFlipper flipper = creativeFlipper(context);
		for(int i = 0 ; i  < addView.size();i++)
			flipper.addView(addView.get(i));
		setAnimation(context, flipper,in_ani, out_ani);
		return flipper;
	}
	
	public static ViewFlipper creativeFlipper(Context context){
		return	 new ViewFlipper(context);
	}
	public static ViewFlipper creativeFlipper(Context context,int in_ani,int out_ani){
		ViewFlipper root = new ViewFlipper(context);
		setAnimation(context,root,in_ani,out_ani);
//		ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(
//				width_parent,
//				height_parent);
//		params.leftMargin  = left_margin;
//		params.rightMargin = right_margin;
//		params.topMargin = top_margin;
//		params.bottomMargin = bottom_margin;
//		root.setLayoutParams(params);
//		root.setGravity(gravity);
//		root.setOrientation(orientation);
		return root;
	}
	
	
	
	public static Gallery creativeGallery(Context context,int gravity){
		Gallery root = new Gallery(context);
		root.setGravity(gravity);
		return root;//creativeGallery(context, gravity,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
	}

	
	
	
	
//	public static void setParent_MapViewLayoutparam(View view,int width_parent,int height_parent,float weight){
//		return setParent_LinearLayoutparam(view, width_parent, height_parent, weight,-1,0,0,0,0);
//	}
	public static void setChild_MapViewLayoutparam(ViewGroup layout,int width_parent,int height_parent,GeoPoint geopoint,int  mapViewParamsGravity){
		for(int i = 0 ; i<layout.getChildCount();i++){
			View view= layout.getChildAt(i);
			MapView.LayoutParams param = new MapView.LayoutParams(
					width_parent,
					height_parent, 
					geopoint, mapViewParamsGravity  //MapView.LayoutParams.TOP_LEFT
            );
			view.setLayoutParams(param);
		}
//		MapView.LayoutParams.TOP_LEFT
	}
	public static void setParent_MapViewLayoutparam(View view,int width_parent,int height_parent,GeoPoint geopoint,int  mapViewParamsGravity){
//		MapView.LayoutParams mapViewParams = (LayoutParams) view.getLayoutParams();
		MapView.LayoutParams param = new MapView.LayoutParams(
				width_parent,
				height_parent, 
				geopoint, mapViewParamsGravity  //MapView.LayoutParams.TOP_LEFT
        );
		param.width = width_parent;
		param.height=height_parent;
		param.point =geopoint;
		param.alignment = mapViewParamsGravity; //MapView.LayoutParams.TOP_LEFT
		view.setLayoutParams(param);
	}
	
	public static MapView.LayoutParams creativeMapViewLayoutparam(int width_parent,int height_parent,GeoPoint geopoint,int  mapViewParamsGravity){
//		MapView.LayoutParams mapViewParams = (LayoutParams) view.getLayoutParams();
		MapView.LayoutParams param = new MapView.LayoutParams(
				width_parent,
				height_parent, 
				geopoint, mapViewParamsGravity  //MapView.LayoutParams.TOP_LEFT
		);
//		param.width = width_parent;
//		param.height=height_parent;
//		param.point =geopoint;
//		param.alignment = mapViewParamsGravity; //MapView.LayoutParams.TOP_LEFT
		return param;
	}
	
	
	public static ViewGroup.LayoutParams setViewGroup_Layoutparam(View view,int width_parent,int height_parent){
		ViewGroup.LayoutParams param = new ViewGroup.LayoutParams (width_parent, height_parent);
		view.setLayoutParams(param);
		return param;
	}
	
	
	public static InputStream getRawResources(Context context,int ref){
		return context.getResources().openRawResource(ref);
	}
	public static Resources getResources(Context context){
		return context.getResources();
	}
	public static Drawable getDrawable(Context context, int res){
		Resources resource =getResources(context);//.getResources();
		Drawable d = resource.getDrawable(res);
		return  setClearFilter(d);
	}
	public static XmlResourceParser  getXml(Context context, int res){
		Resources resource =getResources(context);//.getResources();
		return  resource.getXml(res);
	}
	
	public static Bitmap getBitmap(Context context, int imgres){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgres);
		return bitmap;
	}
	public static String getString(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getString(res).toString();
	}
	public static String[] getStringArray(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getStringArray(res);
	}
	public static String getString(Context context, int res ,Object... arg){
		Resources resource =context.getResources();
		return  resource.getString(res,arg).toString();
	}
	public static int getColor(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getColor(res);
	}
	public static int getInteger(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getInteger(res);
	}
	
	
	
	public static ViewGroup.LayoutParams setParent_Layoutparam(View view,int width_parent,int height_parent){
		ViewGroup.LayoutParams param = (ViewGroup.LayoutParams )view.getLayoutParams();
//		ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(width_parent, height_parent);
		param.width=width_parent;
		param.height= height_parent;
		view.setLayoutParams(param);
		return param;
	}

	
	public static void setChild_LinearLayoutparam(LinearLayout layout,int width_parent,int height_parent,float weight){
		 setChild_LinearLayoutparam(layout, width_parent, height_parent, weight,0,0,0,0);
	}
	public static void setChild_LinearLayoutparam(LinearLayout layout,int width_parent,int height_parent,float weight,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		for(int i = 0 ; i<layout.getChildCount();i++){
			View view= layout.getChildAt(i);
//			LinearLayout.LayoutParams param = (LinearLayout.LayoutParams )view.getLayoutParams();
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width_parent, height_parent);
			param.width=width_parent;
			param.height= height_parent;
			param.weight=weight;
			param.leftMargin = leftMargin;
			param.rightMargin = rightMargin;
			param.topMargin = topMargin;
			param.bottomMargin = bottomMargin;
			view.setLayoutParams(param);
		}
	}
	
	public static void setChild_TableRowLayoutparam(TableRow layout,int width_parent,int height_parent,float weight){
		setChild_TableRowLayoutparam(layout, width_parent, height_parent, weight,0,0,0,0);
	}
	public static void setChild_TableRowLayoutparam(TableRow layout,int width_parent,int height_parent,float weight,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		for(int i = 0 ; i<layout.getChildCount();i++){
			View view= layout.getChildAt(i);
			setTableRowLayoutparam(view, width_parent, height_parent, weight, leftMargin, rightMargin, topMargin, bottomMargin);
//			view.setLayoutParams(param);
		}
	}
	
	
	public static void setChild_Layoutparam(ViewGroup layout,int width_parent,int height_parent){
		for(int i = 0 ; i<layout.getChildCount();i++){
			View view= layout.getChildAt(i);
//			ViewGroup.LayoutParams param = (ViewGroup.LayoutParams )view.getLayoutParams();
			ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(width_parent,height_parent);
			param.width=width_parent;
			param.height= height_parent;
			view.setLayoutParams(param);
		}
	}
	public static ViewGroup.LayoutParams setLinearLayoutparam(LinearLayout view,int width_parent,int height_parent,float weight,int orientation){
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width_parent, height_parent,weight);  
		param.width=width_parent;
		param.height= height_parent;
		view.setOrientation(orientation);
		view.setLayoutParams(param);
		return view.getLayoutParams();
	}
	public static ViewGroup.LayoutParams setParent_LinearLayoutparam(View view,int width_parent,int height_parent,float weight){
		return setParent_LinearLayoutparam(view, width_parent, height_parent, weight,-1,0,0,0,0);
	}
	public static ViewGroup.LayoutParams setParent_LinearLayoutparam(View view,int width_parent,int height_parent,float weight,int gravity){
		return setParent_LinearLayoutparam(view, width_parent, height_parent, weight,gravity,0,0,0,0);
	}
	public static ViewGroup.LayoutParams setParent_LinearLayoutparam(View view,int width_parent,int height_parent,float weight,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		return setParent_LinearLayoutparam(view, width_parent, height_parent, weight, -1, leftMargin, rightMargin, topMargin, bottomMargin);
	}
	public static ViewGroup.LayoutParams setParent_LinearLayoutparam(View view,int width_parent,int height_parent,float weight,int gravity,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		
		
		LinearLayout.LayoutParams param = (LinearLayout.LayoutParams )view.getLayoutParams();
//		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width_parent,height_parent);
		param.width=width_parent;
		param.height= height_parent;
		param.weight=weight;
		
		if(gravity>0)
		param.gravity=gravity;
		
		param.leftMargin = leftMargin;
		param.rightMargin = rightMargin;
		param.topMargin = topMargin;
		param.bottomMargin = bottomMargin;
		view.setLayoutParams(param);
		return param;
	}
	
	
	public static ViewGroup.LayoutParams setTableRowLayoutparam(View view,int width_parent,int height_parent,float weight){
		return setTableRowLayoutparam(view, width_parent, height_parent, weight,0,0,0,0);
	}
//	public static ViewGroup.LayoutParams setParent_TableRowLayoutparam(View view,int width_parent,int height_parent,float weight,int gravity){
//		return setParent_TableRowLayoutparam(view, width_parent, height_parent, weight,gravity,0,0,0,0);
//	}
	public static ViewGroup.LayoutParams setTableRowLayoutparam(View view,int width_parent,int height_parent,float weight,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		
		
//		LinearLayout.LayoutParams param = (LinearLayout.LayoutParams )view.getLayoutParams();
		TableRow.LayoutParams param = new TableRow.LayoutParams();
//		if(width_parent!=0)
		param.width=width_parent;
//		if(height_parent!=0)
		param.height= height_parent;
		param.weight=weight;
		param.leftMargin = leftMargin;
		param.rightMargin = rightMargin;
		param.topMargin = topMargin;
		param.bottomMargin = bottomMargin;
		view.setLayoutParams(param);
		return param;
	}
	
	
	public static GeoPoint getGPS_MapCenter(MapView map){
		GeoPoint centerPoint = map.getProjection().fromPixels(map.getWidth()/2, map.getBottom()/2);
		return centerPoint;
	}
    public static GeoPoint getGPS(MapView map,float x, float y){
    	GeoPoint point = map.getProjection().fromPixels((int) x,
    			(int) y); //눌려있던 지점을 위도 경도로 바꿔준다.
		return point;
    }
	
//    public static GeoPoint getGPS(Context context,String findName ){
//		Address adr = null;
//		try {
//			Geocoder goecoder = new Geocoder(context, Locale.getDefault());
//			adr = goecoder.getFromLocationName(findName, 1).get(0);
//			if(adr==null)
//				return null;
//		} catch (Exception e) {
//		}
//		return getGeoPoint(adr.getLatitude(), adr.getLongitude());
//	}
	
	
	
	
	public static Location getGPS(Activity context){
		return getGPS(context,null,0,0);
	}
	
	public static GeoPoint getGPS_FromNetwork(Context context, LocationListener listener, int sec, int meter){
//		((LocationManager)context.getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude()*1E6),,(int)(((LocationManager)getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude()*1E6))
		 LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		 
		   if(listener!=null)
	            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, sec, meter, listener);//현재정보를 업데이트
		   
		   Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		   GeoPoint geo=null;
//		   if(location!=null){
			 double lat = location.getLatitude();
			 double lon = location.getLongitude();
			 geo=getGeoPoint(lat, lon);
//		   }
		   return geo;
	}
	
	public static void setGPS_removeUpdate(Context context,LocationListener listener){
		String contextstr = Context.LOCATION_SERVICE;//안드로이드 시스템으로 부터 LocationManager 서비스를 요청하여 할당
		LocationManager locationManager = (LocationManager)context.getSystemService(contextstr);
		locationManager.removeUpdates(listener);
	}
	public static Location getGPS(Context context,LocationListener listener,int sec,int meter){
        
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
        Log.i(TAG, "provider:"+provider);
        if(provider == null){//GPS 장치가 없는 휴대폰이거나 설정이 꺼져있는 경우 바로 alert 처리하거나 GPS 설정으로 이동
            boolean result = checkGpsService(context);
            if(result){
            	getGPS(context,listener,sec,meter);
            }
             return getLocation(getGPS_FromNetwork(context,listener,sec,meter), LocationManager.NETWORK_PROVIDER);
        }else{
           Location location = locationManager.getLastKnownLocation(provider);//가장 최근의 로케이션을 가져온다. 안드로이드 폰이 꺼져있었거나 다른 위치로 이동한 경우 값이 없다.
            //location = locationManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER );
            //이럴경우는 NETWORK_PROVIDER 로 부터 새로운 location을 지정 받는다.
            //특정조건(시간, 거리)이 되면  Listener를 invoke 시킨다.: 여기서는 1초 마다 5km)
           if(listener!=null)
            locationManager.requestLocationUpdates(provider, sec, meter, listener);//현재정보를 업데이트
             
//           Log.i(TAG, "location == null:"+provider+"    "+(location == null)+" "+location.toString());
           
            if(location == null){
                location = locationManager.getLastKnownLocation(provider);
//                Log.i(TAG, "location2 == null:"+provider+"    "+(location == null)+" "+location.toString());
                if(location == null){//그래도 null인경우 alert;
//                	Log.i(TAG, "location3 == null:"+provider+"    "+(location == null)+" "+location.toString() );
                	 return getLocation(getGPS_FromNetwork(context,listener,sec,meter), LocationManager.NETWORK_PROVIDER);
//                    Log.w(TAG, "get Location From GPS Fail !!!!!");
//                    AlertDialog.Builder adb = new AlertDialog.Builder(ProjectMyPosition.this);
//                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    adb.setTitle( R.string.Location_fail_title );
//                    adb.setMessage(R.string.Location_fail_message);
//                    adb.show();
                }

            }
//            Log.i(TAG, "location return == null:"+provider+"    "+(location.toString()));
            return location;
        }
//		 LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
//		 
//		 Criteria criteria = new Criteria();
//		  criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		  criteria.setAltitudeRequired(false);
//		  criteria.setBearingRequired(false);
//		  criteria.setCostAllowed(true);
//		  criteria.setPowerRequirement(Criteria.POWER_LOW);
//
////			Iterator<String> providers = locationManager.getAllProviders().iterator();
////			// GPS 정보를 얻기위한 프로바이더 검색
////			while(providers.hasNext()) {Log.d("Test", "provider " + providers.next());}
//				
//		  
//		  String provider = locationManager.getBestProvider(criteria, true);
//			if(provider==null){
//				return null;
//			}
//		  Location location = locationManager.getLastKnownLocation(provider);
//		//위치 매니저에 위치 리스너를 셋팅한다.
//		//위치 리스너에서 10000ms (10초) 마다 100미터 이상 이동이 발견되면 업데이트를 하려한다.
//		  if(listener!=null)
//		  locationManager.requestLocationUpdates(provider, sec, meter, listener); //업데이트할때 리스너 달수있는 구분.
//		  
//		  if(location==null)
//			  return null;
////		  location.getLatitude()  이처럼 함수 불러다 사용
////		  location.getLongitude()
//	    return location;
	}
	
	
	 private static boolean checkGpsService(final Context context) {//GPS의 설정여부 확인 및 자동 설정 변경
         String gs = android.provider.Settings.Secure.getString(context.getContentResolver(),
         android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
         Log.w(TAG , "chkGpsService get GPs Service" );
          
         if (gs.indexOf("gps", 0) < 0) {
             Log.w("chkGpsService" , "status: off" );
             // GPS OFF 일때 Dialog 띄워서 설정 화면으로 이동.
             AlertDialog.Builder gsDialog = new AlertDialog.Builder(context);
             gsDialog.setTitle("GPS Status OFF !!!");
             gsDialog.setMessage("Change Setting !!");
             gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                     // GPS설정 화면으로 이동
                     Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                     intent.addCategory(Intent.CATEGORY_DEFAULT);
                     context.startActivity(intent);
                 }
             }).create().show();
             return false;
         } else {
             Log.w("chkGpsService" , "status: on" );                 
             return true;
         }
     }     
	
	
	
	
	
	
	
	
	
	
	
	public static ViewGroup.LayoutParams setParent_FrameLayoutparam(View view,int width_parent,int height_parent){
		return setParent_FrameLayoutparam(view, width_parent, height_parent, 0,0,0,0);
	}
	public static ViewGroup.LayoutParams setParent_FrameLayoutparam(View view,int width_parent,int height_parent,int leftMargin,int rightMargin,int topMargin,int bottomMargin){
		FrameLayout.LayoutParams param = (FrameLayout.LayoutParams )view.getLayoutParams();
		param.width=width_parent;
		param.height= height_parent;
		param.leftMargin = leftMargin;
		param.rightMargin = rightMargin;
		param.topMargin = topMargin;
		param.bottomMargin = bottomMargin;
		view.setLayoutParams(param);
		return param;
	}
 
	public static void setParent_Weight(LinearLayout layout,float weight){
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
			params.weight  = weight;
			layout.setLayoutParams(params);
	}
	public static void setChild_Weight(LinearLayout layout,float weight){
		for(int i =0;i<layout.getChildCount();i++){
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getChildAt(i).getLayoutParams();
			params.weight  = weight;
			layout.getChildAt(i).setLayoutParams(params);
		}
	}
	
	public static LinearLayout creativeLinearLayout_Freame(Context context,int orientation,int suborientation,int step){
	
		LinearLayout container = creativeLinearLayout(context,orientation, Gravity.CENTER);
		for(int i = 0 ; i < step  ;i ++){
			LinearLayout temp_container = creativeLinearLayout(context, suborientation, Gravity.LEFT);
			LinearLayout.LayoutParams subparam = creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT,Gravity.CENTER);
			temp_container.setId(i);
			container.addView(temp_container,subparam);
		}
		if(orientation==LinearLayout.VERTICAL)
			setChild_LinearLayoutparam(container, ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, 1);
		else
			setChild_LinearLayoutparam(container, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.FILL_PARENT, 1);
	return container;	
	}
	

	
//	//아래쪽은 차후 차차 바꿔서 이거쓰지말아야 한다.
//	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,int width_parent,int height_parent,int backcolor){
//		LinearLayout layout = creativeLinearLayout(context, orientation, gravity, width_parent, height_parent, 0, 0,0,0);
//		layout.setBackgroundColor(backcolor);
//		return layout;
//	}
//	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,int width_parent,int height_parent,Drawable backgroundimg){
//		LinearLayout layout = creativeLinearLayout(context, orientation, gravity, width_parent, height_parent, 0, 0,0,0);
//		layout.setBackgroundDrawable(backgroundimg);
//		return layout;
//	}
//	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity){
//		return creativeLinearLayout(context, orientation, gravity,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
//	}
//	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,int width_parent,int height_parent){
//		return creativeLinearLayout(context, orientation, gravity, width_parent, height_parent, 0, 0,0,0);
//	}
//	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,int width_parent,int height_parent,int left_margin,int right_margin,int top_margin,int bottom_margin){
//		LinearLayout root = new LinearLayout(context);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//				width_parent,
//				height_parent, 0.0F);
//		params.leftMargin  = left_margin;
//		params.rightMargin = right_margin;
//		params.topMargin = top_margin;
//		params.bottomMargin = bottom_margin;
////		params.gravity = gravity;
//		root.setLayoutParams(params);
//		root.setGravity(gravity);
//		root.setOrientation(orientation);
//		return root;
//	}
	
	
	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity){
		LinearLayout layout = creativeLinearLayout(context, orientation, gravity,null);
		return layout;
	}
	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,int backgroundcolor){
		LinearLayout layout = creativeLinearLayout(context, orientation, gravity,null);
		layout.setBackgroundColor(backgroundcolor);
		return layout;
	}
	public static LinearLayout creativeLinearLayout(Context context,int orientation,int gravity,Drawable backgroundimg){
		LinearLayout root = new LinearLayout(context);
		root.setOrientation(orientation);
		root.setGravity(gravity);
		root.setBackgroundDrawable(backgroundimg);
		return root;
	}
	
	

	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent){
		return creativeLinearLayoutParam(width_parent, height_parent, -99);
	}
	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent,int gravity){
		return creativeLinearLayoutParam(width_parent, height_parent, 0.0F,gravity);
	}
	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent,float weight){
		return creativeLinearLayoutParam(width_parent, height_parent, weight,-99);
	}
	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent,float weight,int gravity){
		return creativeLinearLayoutParam(width_parent, height_parent,weight,gravity, 0, 0,0,0);
	}

	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent,int left_margin,int right_margin,int top_margin,int bottom_margin){
		return creativeLinearLayoutParam(width_parent, height_parent, 0.0F, -99, left_margin, right_margin, top_margin, bottom_margin);
	}
	public static LinearLayout.LayoutParams creativeLinearLayoutParam(int width_parent,int height_parent,float weight,int gravity,int left_margin,int right_margin,int top_margin,int bottom_margin){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				width_parent,
				height_parent, weight);
		params.leftMargin  = left_margin;
		params.rightMargin = right_margin;
		params.topMargin = top_margin;
		params.bottomMargin = bottom_margin;
		if(gravity>-10)
		params.gravity = gravity;
		return params;
	}
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent){
		return creativeCustomFlipperParam(width_parent, height_parent, -99);
	}
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent,int gravity){
		return creativeCustomFlipperParam(width_parent, height_parent, 0.0F,gravity);
	}
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent,float weight){
		return creativeCustomFlipperParam(width_parent, height_parent, weight,-99);
	}
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent,float weight,int gravity){
		return creativeCustomFlipperParam(width_parent, height_parent,weight,gravity, 0, 0,0,0);
	}
	
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent,int left_margin,int right_margin,int top_margin,int bottom_margin){
		return creativeCustomFlipperParam(width_parent, height_parent, 0.0F, -99, left_margin, right_margin, top_margin, bottom_margin);
	}
	public static CustomFlipper.LayoutParams creativeCustomFlipperParam(int width_parent,int height_parent,float weight,int gravity,int left_margin,int right_margin,int top_margin,int bottom_margin){
		CustomFlipper.LayoutParams params = new CustomFlipper.LayoutParams(width_parent, height_parent, weight);		
		params.leftMargin  = left_margin;
		params.rightMargin = right_margin;
		params.topMargin = top_margin;
		params.bottomMargin = bottom_margin;
		if(gravity>-10)
		params.gravity = gravity;
		return params;
	}
	
	
	
	public static ViewGroup.LayoutParams creativeViewGroupLayoutParam(int width_parent,int height_parent){
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				width_parent,
				height_parent);
		return params;
	}
	
	
	
	
	public static TableLayout creativeTableLayout(Context context){
		TableLayout root = new TableLayout(context);
//		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
//				width_parent,
//				height_parent, 0.0F);
//		params.leftMargin  = left_margin;
//		params.rightMargin = right_margin;
//		params.topMargin = top_margin;
//		params.bottomMargin = bottom_margin;
//		root.setLayoutParams(params);
		
		return root;
	}
	public static TableRow creativeTableRow(Context context){
		TableRow root = new TableRow(context);
//		TableRow.LayoutParams params = new TableRow.LayoutParams(
//				width_parent,
//				height_parent, 0.0F);
//		params.leftMargin  = left_margin;
//		params.rightMargin = right_margin;
//		params.topMargin = top_margin;
////		params.gravity = Gravity.CENTER;
//		params.bottomMargin = bottom_margin;
//		root.setLayoutParams(params);
		
		return root;
	}
	
	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent){
		return creativeTableLayoutParam(width_parent, height_parent, -99);
	}
	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent,int gravity){
		return creativeTableLayoutParam(width_parent, height_parent, 0.0F,gravity);
	}
	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent,float weight){
		return creativeTableLayoutParam(width_parent, height_parent, weight,-99);
	}
	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent,float weight,int gravity){
		return creativeTableLayoutParam(width_parent, height_parent,weight,gravity, 0, 0,0,0);
	}

	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent,int left_margin,int right_margin,int top_margin,int bottom_margin){
		return creativeTableLayoutParam(width_parent, height_parent, 0.0F, -99, left_margin, right_margin, top_margin, bottom_margin);
	}
	public static LinearLayout.LayoutParams creativeTableLayoutParam(int width_parent,int height_parent,float weight,int gravity,int left_margin,int right_margin,int top_margin,int bottom_margin){
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
				width_parent,
				height_parent, weight);
		params.leftMargin  = left_margin;
		params.rightMargin = right_margin;
		params.topMargin = top_margin;
		params.bottomMargin = bottom_margin;
		if(gravity>-10)
		params.gravity = gravity;
		return params;
	}
	
	
//	public static ScrollView creativeScrollView(Context context){
//		return creativeScrollView(context,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
//	}
	public static ScrollView creativeScrollView(Context context){
		
		ScrollView root = new ScrollView(context);
//		ScrollView.LayoutParams params = new ScrollView.LayoutParams(
//				width_parent,
//				height_parent);
//		
//		root.setLayoutParams(params);
		return root;
	}
	
	
	public static FrameLayout creativeFrameLayout(Context context){
		FrameLayout root = new FrameLayout(context);
//		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//				width_parent,
//				height_parent);
//		root.setLayoutParams(params);
		return root;
	}
	public static FrameLayout creativeFrameLayout(Context context,Drawable background){
		FrameLayout root = creativeFrameLayout(context);
		root.setBackgroundDrawable(background);
		return root;
	}

	
	
	public static FrameLayout.LayoutParams creativeFrameLayoutParam(int width_parent,int height_parent){
		return creativeFrameLayoutParam(width_parent, height_parent,-99);
	}
	public static FrameLayout.LayoutParams creativeFrameLayoutParam(int width_parent,int height_parent,int gravity){
		return creativeFrameLayoutParam(width_parent, height_parent,gravity, 0, 0, 0, 0);
	}
	public static FrameLayout.LayoutParams creativeFrameLayoutParam(int width_parent,int height_parent,int left_margin,int right_margin,int top_margin,int bottom_margin){
		return creativeFrameLayoutParam(width_parent, height_parent, -99, left_margin, right_margin, top_margin, bottom_margin);
	}
	public static FrameLayout.LayoutParams creativeFrameLayoutParam(int width_parent,int height_parent,int gravity,int left_margin,int right_margin,int top_margin,int bottom_margin){
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				width_parent,
				height_parent);
		params.leftMargin = left_margin;
		params.rightMargin = right_margin;
		params.topMargin = top_margin;
		params.bottomMargin = bottom_margin;
		if(gravity>-10)
		params.gravity = gravity;
		return params;
	}
	
	
	
	
	
	public static ImageView creativeImageView(Context context, int imgrsc){
		return creativeImageView(context, getDrawable(context, imgrsc));
	}
	public static ImageView creativeImageView(Context context, Drawable imgrsc){
		ImageView root = new ImageView(context);
		root.setImageDrawable(imgrsc);
//		root.setMinimumHeight(height);
//		root.setMaxHeight(height);
//		root.setMinimumWidth(width);
//		root.setMaxWidth(width);
		return root;
	}

	public static void setChildsAlphaListener(ViewGroup group){
//		for(int i = 0 ;i< group.getChildCount();i++)
//			group.getChildAt(i).setPressed(true);
		for(int i = 0 ; i < group.getTouchables().size();i++){
			group.getTouchables().get(i).setOnTouchListener(new AlphaListener());
		}
	}
	
	
	
	public static Drawable setAlpha(Drawable draw,int alpha ){
		draw.setAlpha(alpha);
		draw.invalidateSelf();
		return draw;
	}
	public static void setFilter(Drawable draw,ColorFilter filter ){
		draw.setColorFilter( filter);
	}
	public static Drawable setClearFilter(Drawable draw){
		draw.clearColorFilter();
		draw.invalidateSelf();
		return draw;
	}
	public static void setGrayFilter(Drawable draw){
		ColorMatrix cm = new ColorMatrix(
				new float[] {
						0.299f, 0.587f, 0.114f, 0, 0,
						0.299f, 0.587f, 0.114f, 0, 0,
						0.299f, 0.587f, 0.114f, 0, 0,
						0, 0, 0, 1, 0 }
		);
		setMatrixColorFilter(cm,draw);
	}
	
	public static void setReverseFilter(Drawable draw){
		ColorMatrix cm = new ColorMatrix(
				new float[] {
						-1, 0, 0, 0, 255,
						0, -1, 0, 0, 255,
						0, 0, -1, 0, 255,
						0, 0, 0, 1, 0 }
		);
		setMatrixColorFilter(cm,draw);
	}
	
	public  static Drawable setMatrixColorFilter(ColorMatrix matrix,Drawable draw){
		draw.setColorFilter( new ColorMatrixColorFilter(matrix));
		draw.invalidateSelf();
		return draw;
	}
	public  static Drawable setColorFilter(Drawable draw,int color) {
//		Mode t = PorterDuff.Mode.MULTIPLY;
//        PorterDuff.Mode.SRC_ATOP,
//        PorterDuff.Mode.MULTIPLY,
		return setColorFilter(draw,color, PorterDuff.Mode.MULTIPLY);
	}
	public  static Drawable setColorFilter(Drawable draw,int color, PorterDuff.Mode mod) {
		draw.setColorFilter(new PorterDuffColorFilter(color, mod));
		draw.invalidateSelf();
		return draw;
	}
	
	public static void setVibrator(Context context,long mms){
		Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(mms);
	}
	public static void setRingMode(Context context,int mode){
	AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	mgr.setRingerMode(mode);
//	AudioManager.RINGER_MODE_NORMAL;//벨소리모드
//	AudioManager.RINGER_MODE_VIBRATE;//진동
//	AudioManager.RINGER_MODE_SILENT;//무음
	}
	public static void setBrightnessMode(Context context,int mode){
		Settings.System.putInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE,
				mode);
//	Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL //수동
//	Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;//오토
	}
	public static void setBrightness(Context context,int value){
//		0~255
		  // 밝기 값에 value 값을 적용한다. ( value : 0~ 255 값 )
		setBrightnessMode(context,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL); //수동모드
	    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
//	    WindowManager.LayoutParams lp=w.getAttributes();
//		lp.screenBrightness = (float)temp; 
//		w.setAttributes(lp);
		
//	Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL //수동
//	Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;//오토
//		<uses-permission android:name=”android.permission.HARDWARE_TEST”></uses-permission>
	}

	
	public static List<ApplicationInfo> getInstalledApplications(Context context){
		final PackageManager pm = context.getPackageManager();
		List<ApplicationInfo> list = pm.getInstalledApplications(0);
		return list;
//		for (ApplicationInfo applicationInfo : list) {
//			String name = String.valueOf(applicationInfo.loadLabel(pm)); // 앱 이름
//			String pName = applicationInfo.packageName; // 앱 패키지
//			Drawable iconDrawable = applicationInfo.loadIcon(pm); // 앱 아이콘
//		}
	}
	
	
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
	public static int getDipToPixel(Context context,float dip){
		Resources r = context.getResources(); 
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics()); 
		return (int)px;
	}
	
	public static EditText creativeEditText(Context context, String text){
		return creativeEditText(context, text, 15, Color.BLACK);
	}

	public static EditText creativeEditText(Context context, String text,int fontsize){
		return creativeEditText(context, text, fontsize, Color.BLACK);
	}

	public static EditText creativeEditText(Context context, String text,int fontsize,int fontcolor){
		return creativeEditText(context, text, fontsize, fontcolor, null);
	}
	public static EditText creativeEditText(Context context, String text,int fontsize,int fontcolor,String hintText){
		return creativeEditText(context, text, fontsize, fontcolor, hintText, Gravity.LEFT|Gravity.CENTER);
	}
	public static EditText creativeEditText(Context context, String text,int fontsize,int fontcolor,String hintText,int gravity){
		EditText root =new EditText(context);
		root.setText(text);
		root.setTextSize(fontsize);
		root.setGravity(gravity);
		root.setTextColor(fontcolor);
		root.setHint(hintText);
		return root;
	}
	
	
	public static View creativeLayout(Context context,int layout){
		LayoutInflater inflater	=	(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(layout, null);
	}
	
	public static TextView creativeTextView(Context context, String text){
		return creativeTextView(context, text, 15, Color.WHITE);
	}

	public static TextView creativeTextView(Context context, String text,int fontsize){
		return creativeTextView(context, text, fontsize, Color.WHITE);
	}

	public static TextView creativeTextView(Context context, String text,int fontsize,int fontcolor){
		return creativeTextView(context, text, fontsize, fontcolor, null);
	}
	public static TextView creativeTextView(Context context, String text,int fontsize,int fontcolor,Drawable backgroundres){
		return creativeTextView(context, text, fontsize, fontcolor, backgroundres,-1);
	}
	public static TextView creativeTextView(Context context, String text,int fontsize,int fontcolor,Drawable backgroundres,int gravity){
		TextView root =new TextView(context);
		root.setText(text);
		root.setTextSize(fontsize);
		if(gravity>=0)
		root.setGravity(gravity);
		root.setTextColor(fontcolor);
		root.setBackgroundDrawable(backgroundres);
		return root;
	}
	public static TextView creativeTextView(Context context, String text,int fontsize,int fontcolor,int bgcolor){
		return creativeTextView(context, text, fontsize, fontcolor, bgcolor,-1);
	}
	public static TextView creativeTextView(Context context, String text,int fontsize,int fontcolor,int bgcolor,int gravity){
		TextView root =new TextView(context);
		root.setText(text);
		root.setTextSize(fontsize);
		if(gravity>=0)
		root.setGravity(gravity);
		root.setTextColor(fontcolor);
		root.setBackgroundColor(bgcolor);
		return root;
	}
	public static TextView creativeTextView(Context context, Spanned text){
		return creativeTextView(context, text, 15, Color.WHITE);
	}
	
	public static TextView creativeTextView(Context context, Spanned text,int fontsize){
		return creativeTextView(context, text, fontsize, Color.WHITE);
	}
	
	public static TextView creativeTextView(Context context, Spanned text,int fontsize,int fontcolor){
		return creativeTextView(context, text, fontsize, fontcolor, 0);
	}
	public static TextView creativeTextView(Context context, Spanned text,int fontsize,int fontcolor,int background_res){
		TextView root =new TextView(context);
		root.setText(text);
		root.setTextSize(fontsize);
		root.setGravity(Gravity.CENTER);
		root.setTextColor(fontcolor);
		if(background_res>0){
			try{
				root.setBackgroundResource(background_res);
			}catch(Exception e){
				root.setBackgroundColor(background_res);
			}
		}
		return root;
	}
	
	
	
	
public static CustomDialog creativeDialog(Context context,View addView){
	return creativeDialog(context, addView, null);
}
public static CustomDialog creativeDialog(Context context,View addView,String title){
	return creativeDialog(context, addView, title, null);
}
public static CustomDialog creativeDialog(Context context,View addView,String title,ArrayList<View> button){
	return creativeDialog(context, addView, title, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,button);
}
public static CustomDialog creativeDialog(Context context,View addView,String title,int width, int height){
	return creativeDialog(context, addView, title, width,height,null);
}
public static CustomDialog creativeDialog(Context context,View addView,String title,int width, int height,ArrayList<View> buttons){
	CustomDialog dialog = new CustomDialog(context);
	dialog.setTitle(title);
	dialog.setContentView(addView);
	dialog.setButtons(buttons);
	LayoutParams param = new LayoutParams(width, height);
	dialog.setLayoutParams(param);
	return dialog;
}

	
public static Dialog creativeDialog(Context context,int layout){
	Dialog dialog  = new Dialog(context);
	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	dialog.setContentView(layout);
	return dialog;
}
 

	
	
	
	
	
	public static ViewGroup addView(ViewGroup viewgroup,View view, ViewGroup.LayoutParams param){
//        ((ViewFlipper)getComponent(R.id.custom_flipper)).addView(listview,
//				new ViewGroup.LayoutParams(
//								ViewGroup.LayoutParams.FILL_PARENT,
//								ViewGroup.LayoutParams.FILL_PARENT));		
		viewgroup.addView(view,param);		
		return viewgroup;
	}
	public static void setTitle(Activity context , String titlemsg){
		context.setTitle(titlemsg);
	}
	public static void setTitle(Activity context , int reglayout){
		context.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,reglayout);
	}
	public static void setWindowNoTitle(Activity context){
		setWindowFeature(context, Window.FEATURE_NO_TITLE);
	}
	public static void setWindowFeature(Activity context ,int windowFeature){
//		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.requestWindowFeature(windowFeature);
	}
	
	
	
	public static  ProgressDialog creativeProgressBar (Context context){
        return creativeProgressBar(context,null);
	}
	public static  ProgressDialog creativeProgressBar (Context context,String comment){
		ProgressDialog dialog = new ProgressDialog(context);
		if(comment==null)
			dialog.setMessage("Please wait while loading...");
		else
			dialog.setMessage(comment);
			
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		return dialog;
	}
	
	//핸폰 사용중인 통신
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
	public static void setMobileEnable(Context context, boolean dataconnectivity) throws Exception { 
//		<uses-permission android:name="android.permission.MODIFY_PHONE_STATE" />
//		<uses-permission android:name="android.permission.READ_PHONE_STATE" />
		TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		@SuppressWarnings("rawtypes")
		Class c = Class.forName(tm.getClass().getName()); 
		Method m = c.getDeclaredMethod("getITelephony"); 
		m.setAccessible(true); 
		com.android.internal.telephony.ITelephony telephonyService; 
		telephonyService = (com.android.internal.telephony.ITelephony)m.invoke(tm); 

		if(dataconnectivity) {
		telephonyService.enableDataConnectivity();
		} else {
		telephonyService.disableDataConnectivity();
		}
		}
	public static void setWifiEnable(Context context, boolean sw){
//		<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
//		<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		boolean enabled = wifiManager.isWifiEnabled();
			wifiManager.setWifiEnabled(sw);
	}
	public static boolean getWifiEnableState(Context context, boolean sw){
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		boolean enabled = wifiManager.isWifiEnabled();
		return enabled;
	}

	
	public static void setBluetoothEnable( boolean sw){
	    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
	    
	    //현재 Bluetooth가 켜져 있는지, 혹은 켜는 중인지 확인 한다. 
			if(sw)     
			{
				adapter.enable();     // Bluetooth On
			}
			else
			{
				adapter.disable();   // Bluetooth Off
			}
	}
	public static int getBluetoothStatus(){
	    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
	    return adapter.getState();
//	    //현재 Bluetooth가 켜져 있는지, 혹은 켜는 중인지 확인 한다. 
//			if(adapter.getState() == BluetoothAdapter.STATE_TURNING_ON || 
//					    adapter.getState() == BluetoothAdapter.STATE_ON)     
//			{
//				adapter.disable();   // Bluetooth Off
//			}
//			else
//			{
//				           adapter.enable();     // Bluetooth On
//			}
	}
	
	public static void setKeyguardLookEnable(Context context , boolean sw){
		KeyguardManager manager = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);  
		KeyguardLock lock = manager.newKeyguardLock(context.KEYGUARD_SERVICE);  
		if(sw){
			lock.reenableKeyguard();  
			//<uses-permission android:name="android.permission.ENABLE_KEYGUARD" />
		}else{
			lock.disableKeyguard();
			//<uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
		}
	}

	
	public static int getOrientation(Activity context){
//		int rotation = AndroidUtility.getRotation(context);
//		int degrees = 0;
//		switch (rotation) {
//		case Surface.ROTATION_0: degrees = 0; break;
//		case Surface.ROTATION_90: degrees = 90; break;
//		case Surface.ROTATION_180: degrees = 180; break;
//		case Surface.ROTATION_270: degrees = 270; break;
//		}
		return context.getWindowManager ().getDefaultDisplay ().getOrientation ();
	}
	
	public static String getPhoneNumber(Context context)
	{
        TelephonyManager tMgr =(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
        String phone = "";
        try{
	        if(tMgr.getLine1Number()!=null){
	        	phone = tMgr.getLine1Number();
	        }
	        if(phone.length()==10)
	        	return phone;
	        	
	        	
	        phone = phone.substring(phone.length()-10,phone.length());
	        phone="0"+phone;
	        
        }catch(Exception e){
        	e.printStackTrace();
        }
//        Log.d("Phone Number ",phone);
//        phone="01050950425";
//        phone="01100110011";
        return phone;
	}
	
	public static boolean getGPS_isEnable(Context context){
		LocationManager locationMgr = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		return locationMgr.isProviderEnabled(locationMgr.GPS_PROVIDER);
	}
	public static boolean getNatwork_isEnable(Context context){
		LocationManager locationMgr = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		return locationMgr.isProviderEnabled(locationMgr.NETWORK_PROVIDER);
	}
	
	public static void goLocationSettingPage(Context context){
		Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		
		context.startActivity(intent);
//		context.startActivity(new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS));
	}
	public static void goLanguageSettingPage(Context context){
		context.startActivity(new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS));
	}
	
	public static void goProgram(Context context, String pkg , String fullclasspath){
		Intent intent = new Intent();
		  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  intent.setComponent(new ComponentName(pkg, fullclasspath));
		context.startActivity(intent);
	}
	public static void killProgram(Context context, String packageName ){
		ActivityManager mActivityManager=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		try{
			mActivityManager.restartPackage(packageName);                  //2.1 version
		}catch(Exception e){
			mActivityManager.killBackgroundProcesses(packageName);   //2.2 version
		}
	}
	
	public static void goPage(Context context, Class gopage){
		Intent intent = new Intent(context, gopage);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName , String extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName , int extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	public static void goPage(Context context,Class gopage,String extraName ,  Parcelable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivity(intent);
	}
	
	
	public static void resultSetParameter(Activity context,int resultCode){
		context.setResult(resultCode);
//		Intent intent = new Intent(context, gopage);
//		context.startActivity(intent);
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , String extraValue){
		Intent intent = new Intent();
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , int extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , int[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , double extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , double[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , float extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName , float[] extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	public static void resultSetParameter(Activity context,int resultCode,String extraName ,  Parcelable extraValue){
		Intent intent = new Intent(); 
		intent.putExtra(extraName,extraValue);
		context.setResult(resultCode,intent);		
	}
	
	
	public static void goPageForResult(Activity context,int requstcode, Class gopage){
		Intent intent = new Intent(context, gopage);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , String extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , Parcelable extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	public static void goPageForResult(Activity context,int requstcode,Class gopage,String extraName , int extraValue){
		Intent intent = new Intent(context, gopage); 
		intent.putExtra(extraName,extraValue);
		context.startActivityForResult(intent,requstcode);
	}
	
	public static void goDial(Context context,String dial_number){
		Intent phonepassIntent =  new Intent();	
		phonepassIntent.setAction(Intent.ACTION_DIAL); 
		phonepassIntent.setData(Uri.parse("tel:"+dial_number));
	    context.startActivity(phonepassIntent);
	}
	
	
	
	public static Intent getIntent(Context context, Class activitys){
		Intent intent = new Intent(context, activitys);
		return intent;
	}
	public static Parcelable requestGetParameter_toParcelable(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getParcelableExtra(extraName);
	}
	public static int requestGetParameter_toInt(Activity context,String extraName,int defaultValue){
		Intent intent = context.getIntent();
		return intent.getIntExtra(extraName, defaultValue);
	}
	public static double requestGetParameter_toDouble(Activity context,String extraName,double defaultValue){
		Intent intent = context.getIntent();
		return intent.getDoubleExtra(extraName, defaultValue);
	}
	public static String requestGetParameter_toString(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringExtra(extraName);
	}
	public static String[] requestGetParameter_toStringArray(Activity context,String extraName){
		Intent intent = context.getIntent();
		return intent.getStringArrayExtra(extraName);
	}
	

	
	
	public static void goTabChange(TabHost tabhost,String tabname){
		tabhost.setCurrentTabByTag(tabname);
	}
	public static void goTabChange(TabHost tabhost,int index){
		tabhost.setCurrentTab(index);
	}
//	public static void goTabChange(Activity context,String tabname){
//		TabActivity p = (TabActivity)context.getParent();
//		p.getTabHost().setCurrentTabByTag(tabname);
////		customDB c = (customDB) p.getCurrentActivity();
////		c.importData(atItem);
//	}
//	public static void goTabChange(Activity context,int index){
//		TabActivity p = (TabActivity)context.getParent();
//		p.getTabHost().setCurrentTab(index);
//	}
	
	public static Context getContext(Activity activity){
		return activity.getApplicationContext();
	}
	
	//tab

	public static TabHost getTabHost(TabActivity tabactivity){
		return tabactivity.getTabHost();
	}
	public static TabWidget getTabWidget(TabHost tabhost){
		return tabhost.getTabWidget();
	}
//	public static void addTab(Context context,TabHost tabhost,Class target,String tagname,String tabname,int icon){
//		addTab(tabhost, getIntent(context, target), tagname, tabname, icon);
//	}
	public static void addTab(TabHost tabhost,Intent target,String tagname,View view){
//		Drawable res  =  getDrawable(tabactivity, icon);
//		TabHost tabhost=getTabHost(tabactivity);
		TabSpec ts = tabhost.newTabSpec(tagname);
	
		ts.setIndicator(view);
		
		ts.setContent(target);
		
		target.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);		//리프레쉬.
		
		tabhost.addTab(ts);
	}
	public static void addTab(TabHost tabhost,Intent target,String tagname,String tabname,Drawable icon){
//		Drawable res  =  getDrawable(tabactivity, icon);
//		TabHost tabhost=getTabHost(tabactivity);
		TabSpec ts = tabhost.newTabSpec(tagname);
		if(icon==null)
			ts.setIndicator(tabname);
		else
			ts.setIndicator(tabname, icon);
		
		ts.setContent(target);
		
		target.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);		//리프레쉬.
		
		tabhost.addTab(ts);
	}
	
	
	
public static ListView creativeListView(Context context){
	return  creativeListView(context, 1);
}
public static ListView creativeListView(Context context, int dividerheight){
	ListView listview = new ListView(context);
	listview.setScrollingCacheEnabled(false);
	listview.setLayoutParams(new ViewGroup.LayoutParams(
										ViewGroup.LayoutParams.FILL_PARENT,
										ViewGroup.LayoutParams.FILL_PARENT));
	listview.setDividerHeight(0);
	return listview;
}


public static LinearLayout creativeRow(Context context,String[] columninfo,int textSize,int[] textColor,int gravity,	float[] weight,int width_parent, int height_parent) {
//	View[] views = new View[columnname.length];
//	float[] weight={11,10,10,10,10};

//	LinearLayout tableHeadlayout = KGlobal.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
	LinearLayout tableHeadlayout = creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
//	tableHeadlayout.setBackgroundResource(R.drawable.tablehead);
	for(int i =0 ; i < columninfo.length;i++){
		TextView text = creativeTextView(context, columninfo[i],textSize,textColor[i]);
		text.setGravity(gravity);
		LinearLayout hline =  creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
		hline.setBackgroundColor(0xff555555);
		
		if(i!=0)
			tableHeadlayout.addView(hline,creativeLinearLayoutParam(Gravity.LEFT,1,ViewGroup.LayoutParams.FILL_PARENT));
		
		tableHeadlayout.addView(text);
		if(weight==null)
			setParent_LinearLayoutparam(text,ViewGroup.LayoutParams.FILL_PARENT ,ViewGroup.LayoutParams.FILL_PARENT,1);
		else
			setParent_LinearLayoutparam(text,ViewGroup.LayoutParams.FILL_PARENT ,ViewGroup.LayoutParams.FILL_PARENT,weight[i]);
//		views[i] = text;
	}
	return tableHeadlayout;
}
public static Button creativeButton (Context context,String text){
	Button btn =new Button(context);
	btn.setText(text);
	return btn;
}
public static Button creativeButton (Context context,String text,int textColor){
	Button btn = creativeButton(context,text);
	btn.setTextColor(textColor);
	return btn;
}
public static Button creativeButton (Context context,String text,int textColor,int textsize){
	Button btn = creativeButton(context,text);
	btn.setTextColor(textColor);
	btn.setTextSize(textsize);
	return btn;
}
public static Button creativeButton (Context context,String text,int textcolor,Drawable background_res){
	Button btn = creativeButton(context,text);
	btn.setBackgroundDrawable(background_res);
	btn.setTextColor(textcolor);
	return btn;
}
public static Button creativeButton (Context context,String text,int textcolor,int textsize,Drawable background_res){
	Button btn = creativeButton(context,text);
	btn.setBackgroundDrawable(background_res);
	btn.setTextSize( textsize);
	btn.setTextColor(textcolor);
	return btn;
}

public static ImageButton creativeImageButton (Context context,Drawable img){
	ImageButton btn =new ImageButton(context);
	btn.setImageDrawable(img);
	return btn;
}
public static ImageButton creativeImageButton (Context context,Drawable img,Drawable background_res){
	ImageButton btn = creativeImageButton(context,img);
	btn.setBackgroundDrawable(background_res);
	return btn;
}



//public static Button creativeButton (Context context,String text,int textcolor,int background_color){
//	Button btn = creativeButton(context,text);
//	btn.setBackgroundColor(background_color);
//	btn.setTextColor(textcolor);
//	return btn;
//}
public static Button creativeButton (Context context,String text,int textcolor,Drawable background_res,int width,int height){
	Button btn = creativeButton(context,text,textcolor,background_res);
	btn.setWidth(width);
	btn.setHeight(height);
	return btn;
}
public static ToggleButton creativeToggleButton (Context context,String text){
	ToggleButton btn =new ToggleButton(context);
	btn.setText(text);
	return btn;
}
public static ToggleButton creativeToggleButton (Context context,String text,int textColor){
	ToggleButton btn = creativeToggleButton(context,text);
	btn.setTextColor(textColor);
	return btn;
}
public static ToggleButton creativeToggleButton (Context context,String text,int textcolor,Drawable background_res){
	ToggleButton btn = creativeToggleButton(context,text);
	btn.setBackgroundDrawable(background_res);
	btn.setTextColor(textcolor);
	return btn;
}
public static ToggleButton creativeToggleButton (Context context,String text,int textcolor,Drawable background_res,int width,int height){
	ToggleButton btn = creativeToggleButton(context,text,textcolor,background_res);
	btn.setWidth(width);
	btn.setHeight(height);
	return btn;
}



public static void setChildsOnClickListener(ViewGroup group,OnClickListener listener){
//	for(int i = 0 ;i< group.getChildCount();i++)
//		group.getChildAt(i).setPressed(true);
	for(int i = 0 ; i < group.getTouchables().size();i++){
		group.getTouchables().get(i).setOnClickListener(listener);
	}
}
public static Dialog backgroundProcess(Context context,final Runnable run){
	return backgroundProcess(context, run,null);
}
public static Dialog backgroundProcess(Context context,final Runnable run,String loadingComment){
	return backgroundProcess(context,run,true,loadingComment);
}
public static Dialog backgroundProcess(Context context,final Runnable run,final boolean showDialog,String loadingComment){
	final ProgressDialog progressdialog = creativeProgressBar(context,loadingComment);
	if(showDialog)
	progressdialog.show();
	Runnable wrapper =new Runnable() {
		public void run() {
			run.run();
			if(showDialog)
			progressdialog.dismiss();
		}
	};
	Thread thread = new Thread(wrapper);
	thread.setDaemon(true);
	thread.start();
	return progressdialog;
}






public static void setAnimation(Context context,ViewAnimator victim,int in_animRrs,int out_animRrs) {
//	victim.setInAnimation(AnimationUtils.loadAnimation(context, animRrs));
		victim.setInAnimation(context, in_animRrs);
		victim.setOutAnimation(context, out_animRrs);
}
public static Animation getAnimation(Context context,int anim_res) {
	return AnimationUtils.loadAnimation(context,anim_res);
}

 
public static void goMarket(Context context,String pkgname){
//	 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")); 
	 Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+pkgname)); 
	  context.startActivity(marketIntent);
}



public static int getViewWidth(View context){
	return context.getMeasuredWidth();
}
public static int getViewHeight(View context){
	return context.getMeasuredHeight();
}
public static int getWindowWidth(Context context){
	Display display = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)).getDefaultDisplay();
	return display.getWidth();
}
public static int getWindowHeight(Context context){
	Display display = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)).getDefaultDisplay();
	return display.getHeight();
}
public static String checkVersion(String pkgname) throws IOException, XPathExpressionException, SAXException,NoClassDefFoundError{
	String finalVersion =null;
		URL url = new URL("http://m.gemsplus.co.kr:8080/smart/api/androidmarket.jsp?query="+pkgname);
		XMLparser parser = new XMLparser(url);
		finalVersion = parser.getString("/apps/app/version");
	return finalVersion;
}
/////////frame , layout
public static View creativeLayout_input(Context context,String title,String value, String sign, int id) {
	return creativeLayout_input(context, title, value, sign,LinearLayout.HORIZONTAL, id);
}
public static View creativeLayout_input(Context context,String title,String value, String sign,int orientation, int id) {
	int titlesize=(int) getDipToPixel(context, 13);
	int valuesize=(int) getDipToPixel(context, 13);
	int signsize=(int) getDipToPixel(context, 10);
	int linecolor = 0xff044a74;
	int titlewidth=(int)AndroidUtility.getDipToPixel(context, 110);
	int signwidth=(int)AndroidUtility.getDipToPixel(context, 40);
	
	LinearLayout layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	
	LinearLayout sub_layout =AndroidUtility.creativeLinearLayout(context, orientation, Gravity.CENTER);
	sub_layout.setPadding(5, 0, 5, 0);
	
	TextView title_text = AndroidUtility.creativeTextView(context,title,titlesize);
	title_text.setGravity(Gravity.LEFT|Gravity.CENTER);
	EditText input 		= AndroidUtility.creativeEditText(context, value,valuesize);
	input.setSingleLine();
	input.setGravity(Gravity.RIGHT|Gravity.CENTER);
	input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
	TextView sign_text=null;
	if(sign!=null){
	sign_text 	= AndroidUtility.creativeTextView(context,sign,signsize);
	sign_text.setGravity(Gravity.RIGHT|Gravity.CENTER);
	}
	
	if(orientation == LinearLayout.HORIZONTAL){
		sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(titlewidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
		sub_layout.addView(input,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
	}else{
		sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		sub_layout.addView(input,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	}
	
	
	if(sign_text!=null)
	sub_layout.addView(sign_text,AndroidUtility.creativeLinearLayoutParam(signwidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
	input.setId(id);
	
	layout.addView(sub_layout,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,2,2,5,5));
	LinearLayout line = creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	line.setBackgroundColor(linecolor);
	layout.addView(line,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, 1,5,5,10,10));
	return layout;
}
public static View creativeLayout_inputSpinner(Context context,String title, StdAdapter adapter, String sign,int id) {
	int titlesize=(int) getDipToPixel(context, 13);
	int valuesize=(int) getDipToPixel(context, 10);
	int signsize=(int) getDipToPixel(context, 10);
	int linecolor = 0xff858585;
	int titlewidth=(int)AndroidUtility.getDipToPixel(context, 110);
	int signwidth=(int)AndroidUtility.getDipToPixel(context, 40);
	
	
	LinearLayout layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	
	LinearLayout sub_layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
	sub_layout.setPadding(5, 0, 5, 0);
//	ImageView img = creativeImageView(context, android.R.drawable.presence_invisible);
//	sub_layout.addView(img);
	
	TextView title_text = AndroidUtility.creativeTextView(context,title,titlesize);
	title_text.setGravity(Gravity.LEFT|Gravity.CENTER);
	CustomSpinner input 		= new CustomSpinner(context,adapter);
	TextView sign_text=null;
	if(sign!=null){
		sign_text 	= AndroidUtility.creativeTextView(context,sign,signsize);
		sign_text.setGravity(Gravity.RIGHT|Gravity.CENTER);
	}
	sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(titlewidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
	sub_layout.addView(input,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
	if(sign_text!=null)
		sub_layout.addView(sign_text,AndroidUtility.creativeLinearLayoutParam(signwidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
	
	input.setId(id);
	// TODO Auto-generated method stub
	
	layout.addView(sub_layout,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,2,2,5,5));
	LinearLayout line = creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	line.setBackgroundColor(linecolor);
	layout.addView(line,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, 1,2,2,10,0));
	return layout;
}
public static LinearLayout creativeLayout_text(Context context,String title, String value,String sign,int id) {
	int titlesize=(int) getDipToPixel(context, 13);
	int valuesize=(int) getDipToPixel(context, 18);
	int signsize=(int) getDipToPixel(context, 10);
	int linecolor = 0xff858585;
	int titlewidth=(int)AndroidUtility.getDipToPixel(context, 110);
	int signwidth=(int)AndroidUtility.getDipToPixel(context, 40);
	
	
	LinearLayout layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	
	
	LinearLayout sub_layout =AndroidUtility.creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER);
	sub_layout.setPadding(5, 0, 5, 0);
//	ImageView img = creativeImageView(context, android.R.drawable.presence_invisible);
//	sub_layout.addView(img);
	
	TextView title_text = AndroidUtility.creativeTextView(context,title,titlesize);
	title_text.setGravity(Gravity.LEFT|Gravity.CENTER);
	TextView input 		= AndroidUtility.creativeTextView(context, value);
	input.setTextSize(valuesize);
	input.setGravity(Gravity.RIGHT|Gravity.CENTER);
	TextView sign_text=null;
	if(sign!=null){
		sign_text 	= AndroidUtility.creativeTextView(context,sign,signsize);
		sign_text.setGravity(Gravity.RIGHT|Gravity.CENTER);
	}
	sub_layout.addView(title_text,AndroidUtility.creativeLinearLayoutParam(titlewidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
	sub_layout.addView(input,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1F));
	if(sign_text!=null)
		sub_layout.addView(sign_text,AndroidUtility.creativeLinearLayoutParam(signwidth,ViewGroup.LayoutParams.FILL_PARENT,0F));
	
	input.setId(id);


	layout.addView(sub_layout,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,2,2,5,5));
	LinearLayout line = creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
	line.setBackgroundColor(linecolor);
	layout.addView(line,creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, 1,2,2,10,0));
	return layout;
}

public static InputStream openAssetsFile(Context context,String assetfilePath){
	InputStream is=null;
	try {
		is = context.getAssets().open(assetfilePath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return is;
	
}

public static List<WifiConfiguration> getWifiConfigrations(Context context){
	WifiManager mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	List<WifiConfiguration> list = mainWifi.getConfiguredNetworks();
	return list;
}

public static WifiInfo getWifiConnectionInfo(Context context){
	 WifiManager mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);;
	 WifiInfo wifiInfo = mainWifi.getConnectionInfo();
	 return wifiInfo;
}
public static WifiConfiguration creativeWifiConfiguration(String ssid,String mod,String passwd){
//	공통사항
	WifiConfiguration wifiConfig = new WifiConfiguration();
	wifiConfig.SSID = "\""+ssid+"\"";
//	wifiConfig.BSSID = result.BSSID;
	wifiConfig.status =WifiConfiguration.Status.ENABLED;
	wifiConfig.priority = 40;

	
	if(mod==null || mod=="" || mod.equals("")){
//		Capabilites -> Open
		wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wifiConfig.allowedAuthAlgorithms.clear();
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		 
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	}else if(Utilities.isMatches(mod, ".*WEP.*")){
		wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		 
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wifiConfig.wepKeys[0] ="\""+passwd+"\"";
		wifiConfig.wepTxKeyIndex = 0;
	}else if(Utilities.isMatches(mod, ".*WPA.*") || Utilities.isMatches(mod, ".*WPA2.*")){
		wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wifiConfig.preSharedKey ="\""+passwd+"\"";
//		wifiConfig.wepKeys[0] ="\""+passwd+"\"";
//		wifiConfig.wepTxKeyIndex = 0;
	}
	
	return wifiConfig;
}
public static WifiConfiguration creativeWifiConfiguration(ScanResult result,String passwd){
return creativeWifiConfiguration(result.SSID,result.capabilities,passwd);
}

public static int addNetwork(Context context,WifiConfiguration wifiConfig){
	setWifiEnable(context, true);
	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
	int netId = wifi.addNetwork(wifiConfig); 
	wifi.saveConfiguration();
	return netId;
}
public static void removeNetwork(Context context,int networkid){
	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	wifi.removeNetwork(networkid);
	wifi.saveConfiguration();
}
public static void removeNetwork(Context context,String ssid){
	removeNetwork(context,ssid,null);
}
public static void removeNetwork(Context context,String ssid,String bssid){
	if(ssid!=null)
	ssid=ssid.replaceAll("\"","");
	if(bssid!=null)
	bssid=bssid.replaceAll("\"","");
	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	List<WifiConfiguration> configrations = getWifiConfigrations(context);
	for(int  i = 0 ; i < configrations.size();i++){
		WifiConfiguration config = configrations.get(i);
		if((config.SSID!=null && config.SSID.replaceAll("\"","").equals(ssid)) || (config.BSSID!=null && config.BSSID.replaceAll("\"","").equals(bssid))){
			AndroidUtility.removeNetwork(context, config.networkId);
		}
	}
	wifi.saveConfiguration();
}

public static void connectionWifi(Context context,WifiConfiguration wifiConfig,int ntind,boolean wantConnection){
	WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
//	wifi.saveConfiguration();
	wifi.updateNetwork(wifiConfig);
	wifi.enableNetwork(ntind, wantConnection);
}	
public static void enableNetwork(Context context,int networkid){
	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//	wifi.updateNetwork(wifiConfig);
	wifi.enableNetwork(networkid, true);
	wifi.saveConfiguration();
}


public static float[] orientationSensorFromRatationMode(Activity context,float x_heading,float y_pitch, float z_roll){
	int rotation = AndroidUtility.getRotation(context);
	
	
	
	//total accept
	//방위각
	
	//위아래 경사
	float temp=0;
	if(y_pitch<=0){
		temp=y_pitch*-1;
	}else{
		temp=360-(y_pitch);
	}
	y_pitch=temp;
	
	
	//좌우경사
	if(y_pitch>100){
		if(z_roll<0){
			z_roll=-90-(z_roll+90);
		}else{
			z_roll=90+(90-z_roll);
		}
	}
	if(z_roll<=0){
		temp=z_roll*-1;
	}else{
		temp=360-(z_roll);
	}
	z_roll=temp;
	
	
	
	
	
	
	
	
	if (rotation == Surface.ROTATION_0) {
		
		//방위각
		//위아래 경사
		//좌우경사
		
		
	}else if(rotation == Surface.ROTATION_90){
		
		//방위각
		if(x_heading>270){
			x_heading = x_heading-270;
		}else{
			x_heading =360-270+x_heading;
		}
		
		temp = z_roll;
		z_roll = y_pitch;
		y_pitch=temp;
		y_pitch=360-y_pitch;
		
	}else if(rotation == Surface.ROTATION_180){

		
//		if(x_heading>180){
//			x_heading = x_heading-180;
//		}else{
//			x_heading =360-180+x_heading;
//		} 
		
	}else if(rotation == Surface.ROTATION_270){
		
		if(x_heading>90){
			x_heading = x_heading-90;
		}else{
			x_heading =360-90+x_heading;
		}
		
		temp = z_roll;
		z_roll = y_pitch;
		y_pitch=temp;
		z_roll=360-z_roll;
		
	}
	
	
	return new float[]{x_heading,y_pitch,z_roll}; 
}

public static void setOrientation(Activity context,int mode){
//	ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
	context.setRequestedOrientation(mode); 
}
public static int getRotation(Activity context){
return	context.getWindowManager().getDefaultDisplay().getRotation();
}
public static String getVersion(Context context){
	String version=null;
		try {
			PackageInfo i = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			version = i.versionName;
		} catch (Exception e) {
		}
		return version;
}
public static int getOSVersion(Context context){
	return  Integer.parseInt(Build.VERSION.SDK);
//	쓸때
//	if( sdkVersion < Build.VERSION_CODES.FROYO)
//	[출처] 안드로이드 OS 버전 체크|작성자 dadasi73
}
public static HorizontalScrollView creativeHorizontalScrollView(
		ActivityGroup context) {
	return creativeHorizontalScrollView(context, null);
}
public static HorizontalScrollView creativeHorizontalScrollView(
		ActivityGroup context, Drawable bgdrawable) {
	HorizontalScrollView h = new HorizontalScrollView(context);
	
	if(bgdrawable!=null){
		h.setBackgroundDrawable(bgdrawable);
	}
	
	return h;
}
public static Locale getLocale(Context context){
	Locale locale = context.getResources().getConfiguration().locale;
	return locale;
	
}









//public static LinearLayout creativeReport(Context context,ArrayList<StdView> data,OnTouchListener ontouchListener){
//	//Table Head!
////	String[]columnname	= 	new String[data.size()+1]; 	columnname[0]=axisTitle;
////	int[] 	color		=	new int [data.size()+1];	color[0]=0xFFCCCCCC;
//////	float[] weight		= 	new float[data.size()+1];	weight[0]=11;
////	for(int i = 0 ; i<data.size();i++){
////		columnname	[i+1] = data.get(i).getKey();
////		color		[i+1] = color[0];
//////		weight		[i+1] = 10;
////	}
////	
////	
//	LinearLayout container = creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
////	
////	
////	View tableHead   = creativeRow(context,columnname,15,color,Gravity.CENTER,null,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
////	tableHead.setBackgroundResource(titlebg_rsc);
//////	tableHead.setBackgroundResource(R.drawable.tablehead);
//
//	
//	//addRow Data
//	ScrollView scroll = creativeScrollView(context, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//	LinearLayout scrollDirectChild =creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER,ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT,7,7,0,0);
//	if(ontouchListener!=null)
//	scrollDirectChild.setOnTouchListener(ontouchListener);
//	scroll.addView(scrollDirectChild);
//	
//	
//	int rowLength = data.size();
//	for(int tablerow=0 ; tablerow < rowLength;tablerow++){
//		//row !
//		scrollDirectChild.addView(data.get(tablerow).getView(context));
//	}
//	
//	AndroidUtility.setChild_LinearLayoutparam(scrollDirectChild, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);	
//	
//////	//최대 최소
////	for(int  i = 0 ; i <2 ; i++){
////		//row !
////		String [] gb={"최대","최소"};
////		String[]info	= 	new String[data.size()+1]; 	info[0]=gb[i];
////		int[] 	rowcolor		=	new int [data.size()+1];	rowcolor[0]=0xFFCCCCCC;
////		
////		for(int itemrow = 0 ; itemrow<data.size();itemrow++){
////			List_DTO atitem = data.get(itemrow).getValue();
////			try{
////				info [itemrow+1] = atitem.getMaxValue_toString();
////			}catch(Exception e){
////				info [itemrow+1] = "-";
////				
////			}
////			if(i==0)
////				rowcolor		[itemrow+1] = Color.RED;
////			else if(i==1)
////				rowcolor		[itemrow+1] = 0xff72c2ff;
////			else
////				rowcolor		[itemrow+1] = rowcolor[0];
////		}
////		LinearLayout subrow = creativeRow(context, info, 15, rowcolor, Gravity.CENTER, weight,ViewGroup.LayoutParams.FILL_PARENT,45);
////		subrow.setBackgroundColor(0xFF2c2d3a);
////		subrow.setPadding(12, 0,9, 0);
////		LinearLayout line =  creativeLinearLayout(context, LinearLayout.HORIZONTAL, Gravity.CENTER,ViewGroup.LayoutParams.FILL_PARENT,1);
////		line.setBackgroundColor(0xff555555);
////
////		scrollDirectChild.addView(subrow);
////		scrollDirectChild.addView(line);
////	}
//	
//	
//	
////	container.addView(tableHead);
//	container.addView(scroll);
//	return container;
//	
//}













}