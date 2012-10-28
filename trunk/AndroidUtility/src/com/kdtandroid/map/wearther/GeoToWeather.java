package com.kdtandroid.map.wearther;


	import java.io.IOException;
	import java.io.InputStream;
	import java.net.URL;
	import java.util.List;

	import org.xmlpull.v1.XmlPullParser;
	import org.xmlpull.v1.XmlPullParserFactory;

	import android.location.Address;
	import android.location.Geocoder;

	import com.google.android.maps.GeoPoint;

	public class GeoToWeather {

		private Geocoder geoCoder;
		private GeoPoint geoPoint;
		
		public GeoToWeather(Geocoder t_gc, GeoPoint t_gp)
		{
			geoCoder = t_gc;
			geoPoint = t_gp;
		}
		
		public Weather getWeather()
		{
			List<Address> tList=null;
	        try { 
				tList = geoCoder.getFromLocation((double)geoPoint.getLatitudeE6()/1000000, 
					      (double)geoPoint.getLongitudeE6()/1000000, 5);
			} catch (IOException e) {
				
			}
			// geocoder�� getFromLocation()�� �̿��Ͽ� Reverse Geocoding(Geopoint->�ּ�)�Ѵ�.
			// getFromLocation()�� ���ڷ� ���� latitude�� longitude�� ����ũ�� ���� �ƴϹǷ�
			// 10^6�� ������ ���ڷ� �־��ش�.

			Address tAddr = tList.get(0);
			
			Weather dataWeather = new Weather();
	        dataWeather.m_sRegion = tAddr.getLocality();
			// �������� ������ �´�. Geocoder �������� �� ��° ���ڸ� Ư���� Locale�� �־��� ��쿡��
	        // �ش� ���� �������� ���´�. ��� Default�̴�.
	        
	        
	        
	        // �Ʒ��� ���� �Ľ��ϴ� �κ��̴�.
	        XmlPullParserFactory factory = null;
			
			try{
				factory = XmlPullParserFactory.newInstance();

				factory.setNamespaceAware(true);
				XmlPullParser xpp = null;

				xpp = factory.newPullParser();

				String connectUrl = "http://www.google.co.kr/ig/api?weather="
										+ dataWeather.m_sRegion;
				// �ش� ������ url�� �����Ѵ�.
				URL UrlRecWeather = null;
				UrlRecWeather = new URL(connectUrl);

				InputStream in;
				in = UrlRecWeather.openStream();

				xpp.setInput(in, "euc-kr");

				ReceiveParsing getParse = new ReceiveParsing(dataWeather);

				getParse.proceed(xpp);
			}
			catch(Exception ex)
			{

			}
			
			return dataWeather;
		}
		
		public void chageGeoPoint(GeoPoint t_gp)
		{
			geoPoint = t_gp;
		}
		
		// �Ľ��ϴ� Ŭ����. ����Ŭ������ ����
		class ReceiveParsing {
			
			Weather dataWeather;
			
			public ReceiveParsing(Weather t_dW)
			{
				dataWeather = t_dW;
			}
			

			void proceed(XmlPullParser ReceiveStream) {

				boolean bcurrent_condition = false;

				try {

					String sTag;

					int eventType = ReceiveStream.getEventType();
					while (eventType != XmlPullParser.END_DOCUMENT) {

						// Wait(10);
						switch (eventType) {
						case XmlPullParser.START_DOCUMENT:
							break;

						case XmlPullParser.END_DOCUMENT:
							break;

						case XmlPullParser.START_TAG:
							// items.add(xpp.getAttributeValue(0));
							sTag = ReceiveStream.getName();

							if (sTag.equals("current_conditions")) {
								bcurrent_condition = true;
							} 

							if (bcurrent_condition == true) {
								if (sTag.equals("condition")) {
									String sValue = ReceiveStream.getAttributeValue(0);
									dataWeather.m_sCurrentState = sValue;
								} else if (sTag.equals("temp_f")) {
									String sValue = ReceiveStream.getAttributeValue(0);
									dataWeather.m_nTempF = Integer.parseInt(sValue);
								} else if (sTag.equals("temp_c")) {
									String sValue = ReceiveStream.getAttributeValue(0);
									dataWeather.m_nTempC = Integer.parseInt(sValue);
								} else if (sTag.equals("humidity")) {
									String sValue = ReceiveStream.getAttributeValue(0);
									dataWeather.m_sHumidity = sValue;
								} else if (sTag.equals("wind_condition")) {
									String sValue = ReceiveStream.getAttributeValue(0);
									dataWeather.m_sWindCondition = sValue;
								}
							}
							break;

						case XmlPullParser.END_TAG:
							sTag = ReceiveStream.getName();

							if (sTag.equals("current_conditions")) {
								bcurrent_condition = false;
							} 
							break;

						case XmlPullParser.TEXT:
							break;
						}

						eventType = ReceiveStream.next();
					}
				} catch (Exception e) {

				}		 
			}	
		}


	}


	
