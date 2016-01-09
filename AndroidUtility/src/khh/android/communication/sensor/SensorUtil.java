package khh.android.communication.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

public class SensorUtil {

	
	public SensorManager getSensorManager(Context context){
		return (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		//등록
		//mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
		//해지
		//mSensorManager.unregisterListener(mListener);
	}
	public void registerOrientationListener(SensorManager sm, SensorEventListener l){
		sm.registerListener(l, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
	}
	public void unregisterListener(SensorManager sm, SensorEventListener l){
		sm.unregisterListener(l);
	}
}
