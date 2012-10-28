package com.kdtandroid.app;

import java.util.HashMap;
import java.util.Timer;

import com.kdt.util.schedule.Scheduler;
import com.kdtandroid.util.AndroidUtility;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

abstract public class KActivity extends Activity implements UIinterface{
	public enum REQUEST {
		THEME			 			("THEME")
		;
		private final String value;   
		REQUEST(String value) {
			this.value = value;
		}
		public String getValue() { 
			return value; 
		}
	}
	Scheduler scheduler = null;
	HashMap<Integer, View> componet_container = null;
	HashMap<Integer, Object> data_container = null;
	protected Activity  context = this;
	public Handler handler = new Handler();
	boolean isfinish=false;
	
	
//	��Ƽ��Ƽ�� ��ȭ�Ѵ� �����ߴٰ� ������ϴ� ����� ��Ƽ��Ƽ�� ���� ���� ������ ��Ʋ�� ���޵ȴ� ��������� ���ʱ�ȭ�ȴ�
//	-	��Ƽ��Ƽ��  ó�� ���۵ɶ� ���ڷ�null ���� �Ѱܹ����鼭 ȣ��ȴ� (�ý�������۰��)
//	-	��Ƽ��Ƽ�� ����� ���� �ִµ� � ������ ����ƴٰ� �ٽý���Ǵ°�� onSaveInstanceState() �޼���� �����ص� Bundle �ν��Ͻ��� �Ѱܹ޴´�, �����Ҷ���  onRestoreInstanceState(Bundle)
//	-	��Ƽ��Ʈ�� �������ε� �ϵ���� ����(������� ����ü�� ���κ��� ��庯��)�� ���� �ٸ� �üҽ��� ������ ������ ��� �ϵ���� ���°� ����Ǹ� ��Ƽ��Ƽ�� ���� ������ ����Ǹ鼭  onCreate() �޼��� ȣ��ȴ�.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
		if(isfinish==false)
		onPretreatment();
		
		int themeget_style = AndroidUtility.requestGetParameter_toInt(context, REQUEST.THEME.getValue(), 0xffffffff);
		if(themeget_style !=0xffffffff)
			setTheme(themeget_style);
		super.onCreate(savedInstanceState);
		
		
		if(isfinish==false)
		onSettingComponent();
		if(isfinish==false)
		onSyncComponentData();
		if(isfinish==false)
		onAddComponentListener();
		if(isfinish==false)
		onPostProcessing();
	}

	
	public void onPretreatment(){}; // ��ó��.�����͵�..
	abstract public void onSettingComponent();  // ������Ʈ����
	abstract public void onSyncComponentData(); // ������Ʈ �����ũ
	abstract public void onAddComponentListener(); // ������Ʈ ������ ���
	public void onPostProcessing(){}; // ��ó��.�����͵�..
	
	final public void action(int request){action(request,null);}						//����
	abstract public void action(int request,Object obj);// {}						//����
	final public void actionForResult(int requst,int result) {actionForResult(requst, result, null);}	//����
	abstract public void actionForResult(int requst,int result,Object obj);// {}	//����
	
	
	
	
	
	
	
	public Scheduler getScheduler() {
		if(scheduler==null)
			scheduler=new Scheduler();
		return scheduler;
	}




	public void changeView(int requst) {}
	public View getComponent(int id) {
		if (componet_container == null) {
			componet_container = new HashMap<Integer, View>();
		}
		if (componet_container.get(new Integer(id)) == null) {
			componet_container.put(new Integer(id), context.findViewById(id));
		}
		return componet_container.get(new Integer(id));
	}
	public void setComponent(View view, int id) {
		view.setId(id);
		if (componet_container == null) {
			componet_container = new HashMap<Integer, View>();
		}
			componet_container.put(new Integer(id),view);
	}
	public Object getData(int id) {
		if (data_container == null) {
			data_container = new HashMap<Integer, Object>();
		}
		return data_container.get(new Integer(id));
	}
	public void setData(int id,Object object) {
		if (data_container == null) {
			data_container = new HashMap<Integer, Object>();
		}
			data_container.put(new Integer(id), object);
	
	}
	
	@Override
	public void finish() {
		isfinish=true;
		super.finish();
	}
	public void setfinish(boolean wantfinish){
		isfinish=wantfinish;
	}
	
	public void ok(View o){
		setResult(RESULT_OK);
		this.finish();
	}
	public void cancel(View o){
		setResult(RESULT_CANCELED);
		this.finish();
	}
	public void onClick(View o){
		action(o.getId(),o);
	}

	
	
	
//	��Ƽ��Ʈ�� ����۵� �� ȣ��ȴ� Ư���� ����������	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	
//	��Ƽ��Ʈ�� ����ڿ��� ���̱� ������ ȣ��ȴ�
	@Override
	protected void onStart() {
		super.onStart();
	}
//	����ڿ� ��ȣ�ۿ��� �ϱ� ������ ȣ��ȴ� �̴ܰ迡�� ������ ���� ���οö�´�
//	�������¿���->�ٽ� ���۵ɶ� ,
//	��Ƽ��Ʈ�� ó�� ����ɶ�.
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
//	�ٸ� ��Ƽ��Ʈ�� ����� �� ȣ��ȴ� �� �ܰ迡�� �������� �����Ͱ� ������ �����ϰ� �ִϸ��̼��� �����ؾ��Ѵ� �̸޽��ٰ� ���ϵǾ�� ���ֱ��Ʈ�� Ȱ��ȭ�ǹǷ� �ð��� �ʹ� ���� ��� �ȵȴ� 
//	���鿡�� �ڷ� ��������.
	@Override
	protected void onPause() {
		super.onPause();
	}
//	��Ƽ��Ʈ�� ����ڿ��� ������ �ʰ� �� �� ȣ���Ѵ�
//	��Ƽ��Ʈ�� �����ɶ�
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	
//	��Ƽ��Ƽ�� �ı��� �� ȣ��ȴ� �ý��ۿ� ���� ���� ����Ǵ°����� �ƴϸ� finish�޼��� ȣ�⿡ ���� ������ ����Ǵ°������� isFinishing �޼���� ���� �����ϴ�.
//	Finish() ȣ���ؼ� �״°Ŵ� �޸𸮰� �����ؼ� �״°Ŵ�.
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	//���ư�������� ���뿩�⿡ �ɾ ���μ���������.
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
