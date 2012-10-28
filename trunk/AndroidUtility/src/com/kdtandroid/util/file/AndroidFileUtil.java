package com.kdtandroid.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;

import com.kdtandroid.util.broadcast.AndroidBroadCastUtil;

public class AndroidFileUtil {
	public final static String TAG="AndroidFileUtil";

	//    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
	//<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	
	public static void writeFile(Context context,File file,Bitmap bm) throws IOException {
	      if(! file.getParentFile().exists()) {
	    	  file.getParentFile().mkdirs();
	      }
	      if(!file.exists()){
	    	  file.createNewFile();
	      }
//		String temp = "/디렉토리명/";
//		temp = temp + "파일명";
//		          temp = temp + ".jpg";
		FileOutputStream out = new FileOutputStream(file.getAbsoluteFile());
	    bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
	    out.close();      
	    AndroidBroadCastUtil.sendBroadcast_MediaMounted(context);
	}


	
}
