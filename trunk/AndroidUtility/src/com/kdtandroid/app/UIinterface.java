package com.kdtandroid.app;

import android.view.View;

public interface UIinterface {

	int ACTION_SUCCESS=1;
	int ACTION_FAIL=100;
	
	void onPretreatment();			//��ó��.
	
	void onSettingComponent();		// ������Ʈ���� setContentView(R.layout.layout_solarsimulation); �ض�.��

	void onSyncComponentData();		// ������Ʈ �����ũ

	void onAddComponentListener();	// ������Ʈ ������ ���
	
	View getComponent(int id); 		//������Ʈ ����
	
	void setComponent(View view,int id); 		//������Ʈ add
	
	void action(int requst);								//����
	void action(int requst,Object obj);						//����
	void actionForResult(int requst,int result);			//����
	void actionForResult(int requst,int result,Object obj);	//����
	
	void changeView(int gbtype);	//view Change!
	
	Object getData(int id);		//������ ���� DB..��...
	void setData(int id,Object object);		//������ ���� DB..��...
	
	
	
	
	
	
	
	
}
