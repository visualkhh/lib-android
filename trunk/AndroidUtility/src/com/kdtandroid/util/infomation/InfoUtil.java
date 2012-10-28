package com.kdtandroid.util.infomation;

import java.io.File;

import android.os.Environment;

public class InfoUtil {
	
	public static File getExternalStorageDirectory(){
		return Environment.getExternalStorageDirectory();
	}

}
