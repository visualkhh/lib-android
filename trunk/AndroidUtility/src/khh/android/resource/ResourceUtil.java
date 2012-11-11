package khh.android.resource;

import java.io.InputStream;

import khh.android.view.filter.ViewFilterUtil;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class ResourceUtil {
	public static InputStream getRawResources(Context context,int ref){
		return context.getResources().openRawResource(ref);
	}
	public static Resources getResources(Context context){
		return context.getResources();
	}
	public static Drawable getDrawable(Context context, int res){
		Resources resource =getResources(context);//.getResources();
		Drawable d = resource.getDrawable(res);
		return  ViewFilterUtil.setClearFilter(d);
	}
	public static XmlResourceParser  getXml(Context context, int res){
		Resources resource =getResources(context);//.getResources();
		return  resource.getXml(res);
	}
	
	public static Bitmap getBitmap(Context context, int imgres){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgres);
		return bitmap;
	}
	public static String getString(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getString(res).toString();
	}
	public static String[] getStringArray(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getStringArray(res);
	}
	public static String getString(Context context, int res ,Object... arg){
		Resources resource =context.getResources();
		return  resource.getString(res,arg).toString();
	}
	public static int getColor(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getColor(res);
	}
	public static int getInteger(Context context, int res){
		Resources resource =context.getResources();
		return  resource.getInteger(res);
	}
}
