package khh.android.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

public class LocationUtil {
	public LocationManager getLocationManager(Context context){
		return (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		//근접했을떄 처리할수 있는것
		//mLocationManager.addProximityAlert(...), mLocationManager.removeProximityAlert(curIntent);
	}
	
	
	public static String getAddressToString(Address adr){
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
	public static ArrayList<String> getAddressToString(List<Address> addrlist){
		
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
//
}
