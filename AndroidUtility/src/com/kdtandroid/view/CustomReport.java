package com.kdtandroid.view;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AdapterView.OnItemClickListener;

import com.kdtandroid.std.adapter.StdAdapter;
import com.kdtandroid.std.item.StdView;
import com.kdtandroid.util.AndroidUtility;


public class CustomReport extends LinearLayout{
	private Activity context = null;
	private ArrayList<StdAdapter> columns = null; 
	
	private LinearLayout top_container		=null;
	private LinearLayout center_container	=null;
	private TableLayout top_tablelayout  	=null;
	private TableLayout center_tablelayout  =null;
	ScrollView	 center_scroll=null;
	OnItemClickListener listen = null;
	
	int borderColor=0xff8f8f8f;
	int columnColor=0xff000000;
	int headerColor=0xff292c39;
	int borderSize=1;
	
	
	
	
	
	public CustomReport(Activity context) {
		this(context, null);	
	}
	public CustomReport(Activity context,ArrayList<StdAdapter> columns) {
		super(context);
		this.context = context;
		this.columns = columns;
		init();
	}
	public void setAdapter (ArrayList<StdAdapter> columns){
		this.columns = columns;
		init();
	}

//	public int getBorderColor() {
//		return borderColor;
//	}
//	public void setBorderColor(int borderColor) {
//		this.borderColor = borderColor;
//	}
//	public int getColumnColor() {
//		return columnColor;
//	}
//	public void setColumnColor(int columnColor) {
//		this.columnColor = columnColor;
//	}
//	public int getHeaderColor() {
//		return headerColor;
//	}
//	public void setHeaderColor(int headerColor) {
//		this.headerColor = headerColor;
//	}
	
	
	private void init() {
		if(columns==null)
			return ;
		
		//setting
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER|Gravity.TOP);

		
		//큰 거
		top_container 					= AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
		center_scroll					= AndroidUtility.creativeScrollView(context);
		center_container 				= AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);

		center_scroll.addView(center_container,AndroidUtility.creativeFrameLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
//		AndroidUtility.setParent_LinearLayoutparam(center_scroll, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,1);
		
		addView(top_container,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		addView(center_scroll,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		
		
		top_container.setBackgroundColor(borderColor);
//		center_scroll.setBackgroundColor(Color.CYAN);
		center_container.setBackgroundColor(borderColor);
		
		top_tablelayout  	= AndroidUtility.creativeTableLayout(context);
		center_tablelayout  = AndroidUtility.creativeTableLayout(context);
		
		
		
		top_container.addView(top_tablelayout,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		center_container.addView(center_tablelayout,AndroidUtility.creativeLinearLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		
		
		///////top
		TableRow top_tablerow =  AndroidUtility.creativeTableRow(context);
		top_tablelayout.addView(top_tablerow,AndroidUtility.creativeTableLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		for(int  columnCnt = 0 ; columnCnt <  getColumnCount(); columnCnt ++){
			LinearLayout content_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL, Gravity.CENTER);
			content_container.setBackgroundColor(headerColor);
			try{
				content_container.addView(columns.get(columnCnt).getItem(0).getView(context,StdView.VIEWTYPE.LIST.getValue()));
			}catch(Exception e){
				
			}finally{
				top_tablerow.addView(content_container);
			}
			AndroidUtility.setChild_LinearLayoutparam(content_container, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,0);
		}
		AndroidUtility.setChild_TableRowLayoutparam(top_tablerow, ViewGroup.LayoutParams.FILL_PARENT,  ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,borderSize,borderSize,borderSize);
		
		
		
//		//center  헤더는위쪽에서 0했으니  1부터시작.
		for(int  rowCnt = 1 ; rowCnt <  getRowFullCount(); rowCnt ++){
		TableRow sub_tablerow =  AndroidUtility.creativeTableRow(context);
			center_tablelayout.addView(sub_tablerow);
			for(int  columnCnt = 0 ; columnCnt <  getColumnCount(); columnCnt ++){
				LinearLayout content_container = AndroidUtility.creativeLinearLayout(context, LinearLayout.VERTICAL,Gravity.CENTER);
				content_container.setBackgroundColor(columnColor);
				try{
					content_container.addView(columns.get(columnCnt).getItem(rowCnt).getView(context,StdView.VIEWTYPE.LIST.getValue()));
				}catch(Exception e){
					
				}finally{
					sub_tablerow.addView(content_container,AndroidUtility.creativeTableLayoutParam(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
				}
				AndroidUtility.setChild_LinearLayoutparam(content_container, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,0);
			}
			AndroidUtility.setChild_TableRowLayoutparam(sub_tablerow, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,borderSize,borderSize,borderSize);
		}
//		////////////
		
		
	}
	
	
	
	public int getRowFullCount(){//header까지..
		if(columns==null)
			return 0;
		int cnt = Integer.MIN_VALUE;
		for(int i = 0 ; i < columns.size();i++){
			cnt = Math.max(cnt,columns.get(i).getCount());
		}
		return cnt;
	}
	
	public int getRowCount(){//tablelayout 안쪽에.
		int cnt = getRowFullCount();
		return cnt-1;
	}
	
	public int getColumnCount(){
		if(columns==null)
			return 0;
		return columns.size();
	}
	
	
	public void setOnItemClickListener(OnItemClickListener listen){
		this.listen = listen;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(columns==null||top_tablelayout==null||center_tablelayout==null){
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			return;
		}
		
//		TableRow top_tablerow = (TableRow) top_tablelayout.getChildAt(0);
////		for(int i = 0  ;  i<top_tablerow.getChildCount();i++){
////			LinearLayout at= (LinearLayout) top_tablerow.getChildAt(i);
////			Log.d("onMeasure", "top_Size width "+ at.getWidth()+"  height"+at.getHeight());
////		}
//		
//		
//		TableRow center_tablerow = (TableRow) center_tablelayout.getChildAt(0);
//		for(int i = 0  ;  i<center_tablerow.getChildCount();i++){
//			LinearLayout center	= (LinearLayout) center_tablerow.getChildAt(i);
//			LinearLayout top	= (LinearLayout) top_tablerow.getChildAt(i);
//			Log.d("CustomReport", "  ");
//			Log.d("CustomReport", widthMeasureSpec+"  "+heightMeasureSpec);
//			Log.d("CustomReport", top_tablerow.getWidth()+"  _top_   "+top_tablerow.getHeight());
//			Log.d("CustomReport", center_tablerow.getWidth()+"  _center_   "+center_tablerow.getHeight());
//			
//			Log.d("CustomReport", "top after _Size width "+ top.getWidth()+"  height"+top.getHeight());
//			Log.d("CustomReport", "center after _Size width "+ center.getWidth()+"  height"+center.getHeight());
//			
//			AndroidUtility.setTableRowLayoutparam(top, center.getWidth(), center.getHeight(), 0,borderSize,borderSize,borderSize,borderSize);
//			
//			Log.d("CustomReport", "top before _Size width "+ top.getWidth()+"  height"+top.getHeight());
//		}
		
		int width = getMeasuredWidth() / getColumnCount();
		for(int i = 0  ;  i<top_tablelayout.getChildCount();i++){
			TableRow tablerow = (TableRow) top_tablelayout.getChildAt(i);
			for(int y=0;y<tablerow.getChildCount();y++){
				LinearLayout atView	= (LinearLayout) tablerow.getChildAt(y);
				
				if(y+1 ==tablerow.getChildCount()){
					AndroidUtility.setTableRowLayoutparam(atView, width, ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,borderSize,borderSize,borderSize*2);
				}else{
					AndroidUtility.setTableRowLayoutparam(atView, width, ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,0,borderSize,borderSize*2);
				}
			}
		}
		
		for(int i = 0  ;  i<center_tablelayout.getChildCount();i++){
			TableRow tablerow = (TableRow) center_tablelayout.getChildAt(i);
			for(int y=0;y<tablerow.getChildCount();y++){
				LinearLayout atView	= (LinearLayout) tablerow.getChildAt(y);
				if(y+1 ==tablerow.getChildCount()){
					AndroidUtility.setTableRowLayoutparam(atView, width,  ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,borderSize,0,borderSize);
				}else{
					AndroidUtility.setTableRowLayoutparam(atView, width, ViewGroup.LayoutParams.FILL_PARENT, 1,borderSize,0,0,borderSize);
				}
			}
		}
		
		
		invalidate();
		
		
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	
	
	OnTouchListener l=null;
	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l=l;
		super.setOnTouchListener(l);
	}
	
	
//	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("CUstomReport","onTouchEvent---"+event.getAction());
		this.l.onTouch(null, event);
		return super.onTouchEvent(event);
	}
	
	
	//자식 이벤트 검사
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		Log.d("CUstomReport","onInterceptTouchEvent---"+event.getAction());
		onTouchEvent(event);
		return super.onInterceptTouchEvent(event);
		
	}
	
	
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		boolean sw = true;
//		if(event!=null)
//		super.onTouchEvent(event);
//		
//		if(sw==true){
//			if (items != null) {
//				getSelectDialog().show();
//			}
//		}
//		return sw;
//	}

	
//	private Dialog getSelectDialog(){
//				ListView listview = AndroidUtility.creativeListView(context, 0);
//				listview.setAdapter(items);
//				final Dialog dialog = AndroidUtility.creativeDialog(context,listview,title);
//				listview.setOnItemClickListener(new OnItemClickListener() {
//					public void onItemClick(AdapterView<?> adapter, View view, int position,
//							long id) {
//						StdAdapter myAdapter = (StdAdapter)adapter.getAdapter();
//						accept();
//						if(listen!=null)
//							listen.onItemClick(adapter, view, position, id);
//						dialog.dismiss();
//					}
//					
//				});
//				return null;
//		}
//		
//	
//	public void setOnItemClickListener(OnItemClickListener listen){
//		this.listen = listen;
//	}
	
	
	
}
