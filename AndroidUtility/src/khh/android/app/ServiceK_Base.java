package khh.android.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

abstract public class ServiceK_Base extends Service{
/*
client 호출방식에 따라서  
startService()가 호출되면 onStartCommand()가 bindService()가 호출되면 onBind()
가 실행되면서 서비스가 실행이 됩니다.
또한 startService()로 시작한 서비스를 다시 bindService()로 호출하여 onBind()를 실행할수 있습니다.
*/
	
	//처음실행
	@Override
	public void onCreate() {
		super.onCreate();
	}

	//처음 start
	@Override
	public ComponentName startService(Intent service) {
		return super.startService(service);
	}
	
	//두번째
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	//세번쨰
	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return super.bindService(service, conn, flags);
	}
	//네번째. 메소드 호출을 제공하지 않으면 null반환
	public IBinder onBind(Intent intent){
		return null;
	}
	
	
	@Override
	abstract public void onStart(Intent intent, int startId) ;
	
	
	
	//끝
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
