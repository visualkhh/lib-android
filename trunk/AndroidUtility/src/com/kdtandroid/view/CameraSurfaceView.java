package com.kdtandroid.view;

import com.kdtandroid.util.AndroidUtility;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//<uses-feature android:name="android.hardware.camera"/>
//<uses-permission android:name="android.permission.CAMERA"></uses-permission>
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera camera = null;
	private int cameraMode = 1;
	int width,height;
	Activity context;
	
	public CameraSurfaceView(Activity context, int width, int height,int cameraMode ) {
		super(context);
		this.context = context;
		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed
		this.cameraMode = cameraMode;
		this.width = width;
		this.height = height;
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Camera.Parameters params = camera.getParameters();
		params.set("camera-id", this.cameraMode); // 1번카메라 후카메라사용  0번카메라 전면부 카메라 /
		params.setPreviewSize(this.width, this.height);
//		params.setPreviewSize(width, height);
//		params.setRotation(0); 
//		params.set("orientation", "portrait");
		
//		int rotation = AndroidUtility.getRotation(context);
//		int degrees = 0;
//		switch (rotation) {
//		case Surface.ROTATION_0: degrees = 0; break;
//		case Surface.ROTATION_90: degrees = 90; break;
//		case Surface.ROTATION_180: degrees = 180; break;
//		case Surface.ROTATION_270: degrees = 270; break;
//		}
//		int result  = (90 - degrees + 360) % 360;
//		camera.setDisplayOrientation(result);
		
		camera.setParameters(params);
		camera.startPreview();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
		camera = Camera.open();
			camera.setPreviewDisplay(mHolder);
		} catch (Exception e) {
			camera.release();
			camera = null;
			Log.e("Camera", "Failed to set camera preview display", e);
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
		camera = null;
	}
	

	public boolean capture(Camera.PictureCallback jpegHandler) {
		if (camera != null) {
			camera.takePicture(null, null, jpegHandler);
			return true;
		} else {
			return false;
		}
	}

}
