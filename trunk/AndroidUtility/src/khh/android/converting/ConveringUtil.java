package khh.android.converting;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class ConveringUtil {
	public static GeoPoint getGeoPoint(double lat, double lon){
		return new GeoPoint((int)(lat * 1E6), (int)(lon*1E6));
	}
	public static GeoPoint getGeoPoint(Location location){
		return getGeoPoint(location.getLatitude(), location.getLongitude());
	}
}
