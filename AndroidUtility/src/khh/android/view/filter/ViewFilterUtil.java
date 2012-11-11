package khh.android.view.filter;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

public class ViewFilterUtil {
	public static Drawable setAlpha(Drawable draw,int alpha ){
		draw.setAlpha(alpha);
		draw.invalidateSelf();
		return draw;
	}
	public static void setFilter(Drawable draw,ColorFilter filter ){
		draw.setColorFilter( filter);
	}
	public static Drawable setClearFilter(Drawable draw){
		draw.clearColorFilter();
		draw.invalidateSelf();
		return draw;
	}
	public static void setGrayFilter(Drawable draw){
		ColorMatrix cm = new ColorMatrix(
				new float[] {
						0.299f, 0.587f, 0.114f, 0, 0,
						0.299f, 0.587f, 0.114f, 0, 0,
						0.299f, 0.587f, 0.114f, 0, 0,
						0, 0, 0, 1, 0 }
		);
		setMatrixColorFilter(cm,draw);
	}
	
	public static void setReverseFilter(Drawable draw){
		ColorMatrix cm = new ColorMatrix(
				new float[] {
						-1, 0, 0, 0, 255,
						0, -1, 0, 0, 255,
						0, 0, -1, 0, 255,
						0, 0, 0, 1, 0 }
		);
		setMatrixColorFilter(cm,draw);
	}
	
	public  static Drawable setMatrixColorFilter(ColorMatrix matrix,Drawable draw){
		draw.setColorFilter( new ColorMatrixColorFilter(matrix));
		draw.invalidateSelf();
		return draw;
	}
	public  static Drawable setColorFilter(Drawable draw,int color) {
//		Mode t = PorterDuff.Mode.MULTIPLY;
//        PorterDuff.Mode.SRC_ATOP,
//        PorterDuff.Mode.MULTIPLY,
		return setColorFilter(draw,color, PorterDuff.Mode.MULTIPLY);
	}
	public  static Drawable setColorFilter(Drawable draw,int color, PorterDuff.Mode mod) {
		draw.setColorFilter(new PorterDuffColorFilter(color, mod));
		draw.invalidateSelf();
		return draw;
	}
}
