package khh.android.graphics;

import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import khh.android.view.filter.ViewFilterUtil;
import khh.gui.util.GraphicsConfiguration;
import khh.gui.util.GraphicsDevice;
import khh.gui.util.GraphicsEnvironment;

public class GraphicsUtil {

	public static Bitmap createBitmap(int w, int h){
		return createBitmap(w,h,Bitmap.Config.ARGB_8888);
	}
	public static Bitmap createBitmap(int w, int h, Config type){
		Bitmap bitmap = Bitmap.createBitmap(w,h,type);
		return bitmap;
	}
	public static Bitmap getBitmap(byte[] data){
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bitmap;
	}
	public static Bitmap getBitmap(Context context, int imgres,BitmapFactory.Options option){
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inSampleSize=8;  //<-- 1/8로 크기를 줄여준다
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgres,option);
		return bitmap;
	}
	public static Bitmap getBitmap(Context context, int imgres){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgres);
		return bitmap;
	}
	public static void save(Bitmap mBitmap,OutputStream outstream){
		mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
	}
	public static Drawable getDrawable(Context context, int res){
		Resources resource =getResources(context);//.getResources();
		Drawable d = resource.getDrawable(res);
		return  ViewFilterUtil.setClearFilter(d);
	}
	

	
	
	
    
}
