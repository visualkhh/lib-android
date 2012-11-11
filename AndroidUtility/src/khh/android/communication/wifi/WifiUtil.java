package khh.android.communication.wifi;

import java.util.List;

import com.kdtandroid.util.AndroidUtility;

import khh.string.util.StringUtil;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {

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
//		공통사항
		WifiConfiguration wifiConfig = new WifiConfiguration();
		wifiConfig.SSID = "\""+ssid+"\"";
//		wifiConfig.BSSID = result.BSSID;
		wifiConfig.status =WifiConfiguration.Status.ENABLED;
		wifiConfig.priority = 40;

		
		if(mod==null || mod=="" || mod.equals("")){
//			Capabilites -> Open
			wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			wifiConfig.allowedAuthAlgorithms.clear();
			wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			 
			wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
			wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		}else if(StringUtil.isMatches(mod, ".*WEP.*")){
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
		}else if(StringUtil.isMatches(mod, ".*WPA.*") || StringUtil.isMatches(mod, ".*WPA2.*")){
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
//			wifiConfig.wepKeys[0] ="\""+passwd+"\"";
//			wifiConfig.wepTxKeyIndex = 0;
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
//		wifi.saveConfiguration();
		wifi.updateNetwork(wifiConfig);
		wifi.enableNetwork(ntind, wantConnection);
	}	
	public static void enableNetwork(Context context,int networkid){
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//		wifi.updateNetwork(wifiConfig);
		wifi.enableNetwork(networkid, true);
		wifi.saveConfiguration();
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
}
