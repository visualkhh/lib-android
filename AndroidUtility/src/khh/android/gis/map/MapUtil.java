package khh.android.gis.map;

public class MapUtil {
	/*
	 googleMap.setMyLocationEnabled(true)  //내위치 깜빡깜빡하게처리 끌떄는 false
	 google.addMarker(marker) 마크 추가할수있음
	 
	 MarkerOPtion으로 마크를 만들수 있음
	 */

	public static void set(GoogleMap map, LatLng curPoint,int zoom/*15*/){
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, zoom));
        // 지도 유형 설정. 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는 GoogleMap.MAP_TYPE_SATELLITE
        //map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
	public static void getIcon(int drawble){
		BitmapDescriptorFactory.fromResource(drawble));
	}
}
