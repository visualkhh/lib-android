package com.kdtandroid.std.item;

import android.os.Parcel;
import android.os.Parcelable;

public class StdIntentParameter  implements Parcelable{
	static Object obj=null;
	static StdIntentParameter thislcass=null;
	 
	public StdIntentParameter() {
		thislcass=this;
	}
	public StdIntentParameter(Object obj) {
		thislcass=this;
		this.obj=obj;
	}
	public void setData(Object obj) {
		this.obj=obj;
	}
	
	public Object getData() {
		return this.obj;
	}
	
	
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
	}

	public static final Parcelable.Creator<StdIntentParameter> CREATOR= new Parcelable.Creator<StdIntentParameter>() {
		public StdIntentParameter createFromParcel(Parcel source) {
			return thislcass;
		}
		public StdIntentParameter[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}

	};
}
