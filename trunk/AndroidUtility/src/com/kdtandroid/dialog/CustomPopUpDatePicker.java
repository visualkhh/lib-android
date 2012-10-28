package com.kdtandroid.dialog;

import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.kdt.util.Utilities;
import com.kdt.util.date.DateUtil;
import com.kdtandroid.util.AndroidUtility;

public class CustomPopUpDatePicker  {
	private Context context = null;
	DatePickerDialog datepicker_dialog = null;
    private int mYear=0;
    private int mMonth=0;
    private int mDay=0;
    OnDateSetListener listen=null;
    boolean isGoTomorrow =  false;
    String dateFormat="yyyy-MM-dd";
    
	public CustomPopUpDatePicker(Context context) {
		this.context = context;
		this.mYear  =  Utilities.getDateY_toInt(); 
		this.mMonth =  Utilities.getDateM_toInt();
		this.mDay   =  Utilities.getDateD_toInt();  
		init();
	}
	public CustomPopUpDatePicker(Context context,int mYear, int mMonth, int mDay) {
		this.context = context;
		this.mYear  =  mYear; 
		this.mMonth =  mMonth;
		this.mDay   =  mDay;  
		init();
		// TODO Auto-generated constructor stub
	}
	
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		init();
	}
	
	public boolean isGoTomorrow(){
		return this.isGoTomorrow;
	}
	public void setGoTomorrow(boolean isGoTomorrow){
		this.isGoTomorrow = isGoTomorrow;
	}
	
	private void init() {
		if(datepicker_dialog==null){
			datepicker_dialog = new DatePickerDialog(this.context,
                    mDateSetListener,
                    mYear, mMonth-1, mDay);
		}
		
	}
	
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
            	Date applyDate = Utilities.setDate_toDate(year,monthOfYear+1,dayOfMonth, 0, 0,0);
            	if(applyDate.getTime()>new Date().getTime()){
            		AndroidUtility.showToast(context,"마지막 입니다."+mYear + "-" + mMonth +"-"+mDay);
            		return;
            	}
                mYear = year;
                mMonth = monthOfYear+1;
                mDay = dayOfMonth;
                if(listen!=null)
                listen.onDateSet(view, mYear, mMonth, mDay);
                
            }
        };


        
	
	
	
	public void dateSet(int mYear, int mMonth, int mDay){

		if (datepicker_dialog != null) {
			Log.d("DateSet",mYear+","+ (mMonth-1)+","+ mDay);
			
        	Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, 0, 0,0);
        	if(applyDate.getTime()>new Date().getTime()){
        		AndroidUtility.showToast(context,"마지막 입니다."+mYear + "-" + mMonth +"-"+mDay);
        		return;
        	}
            this.mYear = mYear;
            this.mMonth = mMonth;
            this.mDay = mDay;
            if(listen!=null)
            listen.onDateSet(null, this.mYear, this.mMonth, this.mDay);
            
			
			
//			mDateSetListener.onDateSet((DatePicker) datepicker_dialog.getCurrentFocus(),mYear,  mMonth-1,mDay);
			datepicker_dialog.updateDate(mYear, mMonth-1, mDay);
		}
	}
	
	public void showDialog() {
			if (datepicker_dialog != null) {
				datepicker_dialog.updateDate(mYear, mMonth-1, mDay);
				datepicker_dialog.show();
			}else{
				init();
			}
	}
	
	public void setOnDateSetListener(OnDateSetListener listen){
		this.listen = listen;
	}
	
	public int getYear(){
		return mYear;
	}
	public int getMonth(){
		return mMonth;
	}
	public int getDay(){
		return mDay;
	}
	public Date getDate(){
		Date date=null;
		try{
		date = DateUtil.getDate("yyyy-MM-dd", mYear + "-" + mMonth +"-"+mDay);
		}catch(Exception e ){
			e.printStackTrace();
		}
		return date;
	}
	public String getDate_toString(){
		Date date=null;
		try{
		date = DateUtil.getDate("yyyy-MM-dd", mYear + "-" + mMonth +"-"+mDay);
		}catch(Exception e ){
			e.printStackTrace();
		}
//		return DateUtil.getDate(format, mYear+"-");
		return DateUtil.dateFormat(date,getDateFormat());
	}
	
	public int getfulldate_toInt(){
		return 1;//Utilities.setDate_toString(mYear, mMonth,mDay);
	}
	public String getfulldate_toString(){
		return Utilities.setDate_toString(mYear, mMonth,mDay);
	}
	
	public void previousYear(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, -1, 0, 0);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}
	public void previousMonth(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, 0, -1, 0);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}
	public void previousDay(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, 0, 0, -1);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}
	public void nextYear(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, +1, 0, 0);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}
	public void nextMonth(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, 0, +1, 0);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}
	public void nextDay(){
		Date applyDate = Utilities.setDate_toDate(mYear,mMonth,mDay, 0, 0, +1);
		dateSet(applyDate.getYear()+1900,applyDate.getMonth()+1,applyDate.getDate());
	}

}
