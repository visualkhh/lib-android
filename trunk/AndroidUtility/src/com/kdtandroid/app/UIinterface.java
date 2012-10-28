package com.kdtandroid.app;

import android.view.View;

public interface UIinterface {

	int ACTION_SUCCESS=1;
	int ACTION_FAIL=100;
	
	void onPretreatment();			//전처리.
	
	void onSettingComponent();		// 컴포넌트셋팅 setContentView(R.layout.layout_solarsimulation); 해라.ㅋ

	void onSyncComponentData();		// 컴포넌트 내용싱크

	void onAddComponentListener();	// 컴포넌트 리스너 등록
	
	View getComponent(int id); 		//컴포넌트 참조
	
	void setComponent(View view,int id); 		//컴포넌트 add
	
	void action(int requst);								//질문
	void action(int requst,Object obj);						//질문
	void actionForResult(int requst,int result);			//응답
	void actionForResult(int requst,int result,Object obj);	//응답
	
	void changeView(int gbtype);	//view Change!
	
	Object getData(int id);		//데이터 참조 DB..등...
	void setData(int id,Object object);		//데이터 참조 DB..등...
	
	
	
	
	
	
	
	
}
