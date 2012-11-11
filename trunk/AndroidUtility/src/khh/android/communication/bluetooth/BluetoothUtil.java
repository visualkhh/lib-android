package khh.android.communication.bluetooth;

import android.bluetooth.BluetoothAdapter;

public class BluetoothUtil {

	public static void setBluetoothEnable( boolean sw){
	    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
	    
	    //��� Bluetooth媛�耳�� ���吏� �뱀� 耳�� 以��吏���� ���. 
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
//	    //��� Bluetooth媛�耳�� ���吏� �뱀� 耳�� 以��吏���� ���. 
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
}
