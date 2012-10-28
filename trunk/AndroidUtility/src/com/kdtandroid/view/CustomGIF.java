package com.kdtandroid.view;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;


public class CustomGIF extends View{
Context context;
private Movie mMovie;
long mMovieStart=0;
	public CustomGIF(Context context) {
		super(context);
		this.context=context;
	}

	
	
	public void setImageResource(int res){
		InputStream is = context.getResources().openRawResource(res);
		mMovie = Movie.decodeStream(is);
	}
	
	
	@Override
		protected void onDraw(Canvas canvas) {
		
		  long now = android.os.SystemClock.uptimeMillis();
		if (mMovieStart == 0) {   // first time
              mMovieStart = now;
          }
		
		
		 if (mMovie != null) {
             int dur = mMovie.duration();
             if (dur == 0) {
                 dur = 1000;
             }
             int relTime = (int)((now - mMovieStart) % dur);
             mMovie.setTime(relTime);
//             mMovie.draw(canvas, getWidth() - mMovie.width(),
//                         getHeight() - mMovie.height());
             mMovie.draw(canvas, 0,
                        100);
             invalidate();
         }
		
		
		
		}
	
}
