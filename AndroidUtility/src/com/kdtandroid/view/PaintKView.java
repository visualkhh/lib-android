package com.kdtandroid.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kdn.util.debug.LogK;
import com.khmandroid.hraphics.PointK;

public class PaintKView extends View {
	ArrayList<PointK> pointlist;
	Paint mPaint = null;
	int linecolor = 0xffffffff;
	int backgroundcolor = 0x00000000;

	public PaintKView(Context context) {
		super(context);
		init();
	}

	public PaintKView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PaintKView(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
		init();
	}

	private void init() {
		pointlist = new ArrayList<PointK>();
		mPaint = new Paint();
		mPaint.setColor(linecolor);
		mPaint.setStrokeWidth(3);
		mPaint.setAntiAlias(true);

	}

	public void setLineColor(int linecolor) {
		this.linecolor = linecolor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.draw(canvas);
		canvas.drawColor(backgroundcolor);
		mPaint.setColor(linecolor);

		for (int i = 0; i < pointlist.size(); i++) {
			if (pointlist.get(i).getDraw()) {
				canvas.drawLine(pointlist.get(i - 1).getX(), pointlist.get(
						i - 1).getY(), pointlist.get(i).getX(), pointlist
						.get(i).getY(), mPaint);
			}
		}
		
		
	}

	@Override
	public void setBackgroundColor(int backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			pointlist.add(new PointK(event.getX(), event.getY(), false));
			return true;
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			pointlist.add(new PointK(event.getX(), event.getY(), true));
			invalidate();
			return true;
		}
		return false;
	}
	public void clear(){
		pointlist.clear();
		invalidate();
	}
}
