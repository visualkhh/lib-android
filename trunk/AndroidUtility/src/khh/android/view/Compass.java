package khh.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.view.*;
import android.util.AttributeSet;
   
public class Compass extends View {

	private Paint markerPaint;
	private Paint textPaint;
	private Paint circlePaint;

	private String northString;
	private String eastString;
	private String southString;
	private String westString;
	
	private int textHeight;
    int background_color = 0x77000000;
    int marker_color = 0xffffffff;
    int text_color = 0xffffffff;
    
    boolean isRollVisible=true;
    boolean isPitchVisible=true;
    
	public boolean isRollVisible() {
		return isRollVisible;
	}
	public void setRollVisible(boolean isRollVisible) {
		this.isRollVisible = isRollVisible;
	}
	public boolean isPitchVisible() {
		return isPitchVisible;
	}
	public void setPitchVisible(boolean isPitchVisible) {
		this.isPitchVisible = isPitchVisible;
	}
	/** Set the current bearing */
    
    public void setBackgroundColor(int color){
    	this.background_color = color;
    }
    public void setMarkerColor(int color){
    	this.marker_color = color;
    }
    public void setTextColor(int color){
    	this.text_color = color;
    }
    
    
  public void setBearing(float _bearing) {
    bearing = _bearing;
  }
  /** Get the current bearing */
  public float getBearing() {
    return bearing;
  }
  private float bearing;
	    
  /** Get the current pitch */
  public float getPitch() {
    return pitch;
  }
  /** Set the current pitch */
  public void setPitch(float pitch) {
    this.pitch = pitch;
  }
  float pitch;

  /** Get the current roll */
  public float getRoll() {
    return roll;
  }
  /** Set the current bearing */
  public void setRoll(float roll) {
    this.roll = roll;
  }
  float roll;
	
  public Compass(Context context) {
    super(context);
    initCompassView();
  }   

  public Compass(Context context, AttributeSet attrs) {
    super(context, attrs);
    initCompassView();
  }

  public Compass(Context context, AttributeSet attrs, int defaultStyle) {
    super(context, attrs, defaultStyle);
    initCompassView();
  }

  protected void initCompassView() {
    setFocusable(true);

    circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    circlePaint.setColor(background_color);
    circlePaint.setStrokeWidth(1);
    circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    Resources r = this.getResources();
    northString ="N";// r.getString(R.string.cardinal_north);
    eastString = "E";//r.getString(R.string.cardinal_east);
    southString = "S";//;r.getString(R.string.cardinal_south);
    westString = "W";//r.getString(R.string.cardinal_west);

    textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    textPaint.setColor(text_color);

    textHeight = (int)textPaint.measureText("yY");

    markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    markerPaint.setColor(marker_color);
  }
  
  @Override    
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
    // The compass is a circle that fills as much space as possible.
    // Set the measured dimensions by figuring out the shortest boundary,
    // height or width.
    int measuredWidth = measure(widthMeasureSpec);
    int measuredHeight = measure(heightMeasureSpec);
        
    int d = Math.min(measuredWidth, measuredHeight);
        
    setMeasuredDimension(d, d);    
  }
      
  private int measure(int measureSpec) {
    int result = 0; 

    // Decode the measurement specifications.
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec); 

    if (specMode == MeasureSpec.UNSPECIFIED) {
      // Return a default size of 200 if no bounds are specified. 
      result = 200;
    } else {
      // As you want to fill the available space
      // always return the full available bounds.
      result = specSize;
    } 
    return result;
  }

  @Override 
  protected void onDraw(Canvas canvas) {
	    int px = getMeasuredWidth() / 2;
	    int py = getMeasuredHeight() / 2 ;
	    int radius = Math.min(px, py);
	    
	    // Draw the background
	    canvas.drawCircle(px, py, radius, circlePaint);
	    // Rotate our perspective so that the 'top' is
	    // facing the current bearing.
	    canvas.save();
	    canvas.rotate(-bearing, px, py);
	    int textWidth = (int)textPaint.measureText("W");
	    int cardinalX = px-textWidth/2;
	    int cardinalY = py-radius+textHeight;
	        
	    // Draw the marker every 15 degrees and a text every 45.
	    for (int i = 0; i < 24; i++) {
	      // Draw a marker.
	      canvas.drawLine(px, py-radius, px, py-radius+10, markerPaint);
	      
	      canvas.save();
	      canvas.translate(0, textHeight);

	      // Draw the cardinal points
	      if (i % 6 == 0) {
	        String dirString = "";
	        switch (i) {
	          case(0)  : {
	                       dirString = northString;
	                       int arrowY = 2*textHeight;
	                       canvas.drawLine(px, arrowY, px-5, 3*textHeight, markerPaint);
	                       canvas.drawLine(px, arrowY, px+5, 3*textHeight, markerPaint);
	                       break;
	                     }
	          case(6)  : dirString = eastString; break;
	          case(12) : dirString = southString; break;
	          case(18) : dirString = westString; break;
	        }
	        canvas.drawText(dirString, cardinalX, cardinalY, textPaint);
	      } 
	      else if (i % 3 == 0) {
	        // Draw the text every alternate 45deg
	        String angle = String.valueOf(i*15);
	        float angleTextWidth = textPaint.measureText(angle);

	        int angleTextX = (int)(px-angleTextWidth/2);
	        int angleTextY = py-radius+textHeight;
	        canvas.drawText(angle, angleTextX, angleTextY, textPaint);
	      }
	      canvas.restore();

	      canvas.rotate(15, px, py);
	    }
	    canvas.restore();
	    
	    RectF rollOval = new RectF((getMeasuredWidth()/3)-getMeasuredWidth()/7, 
	                               (getMeasuredHeight()/2)-getMeasuredWidth()/7,
	                               (getMeasuredWidth()/3)+getMeasuredWidth()/7, 
	                               (getMeasuredHeight()/2)+getMeasuredWidth()/7);
	    markerPaint.setStyle(Paint.Style.STROKE);
	    canvas.drawOval(rollOval, markerPaint);
	    markerPaint.setStyle(Paint.Style.FILL);
	    canvas.save();
	    canvas.rotate(roll, getMeasuredWidth()/3, getMeasuredHeight()/2);
	    canvas.drawArc(rollOval, 0, 180, false, markerPaint);
	    markerPaint.setStyle(Paint.Style.STROKE);
	    canvas.restore();

	    RectF pitchOval = new RectF((2*getMeasuredWidth()/3)-getMeasuredWidth()/7, 
	                                (getMeasuredHeight()/2)-getMeasuredWidth()/7,
	                                (2*getMeasuredWidth()/3)+getMeasuredWidth()/7, 
	                                (getMeasuredHeight()/2)+getMeasuredWidth()/7);
	    canvas.drawOval(pitchOval, markerPaint);
	    markerPaint.setStyle(Paint.Style.FILL);
	    canvas.drawArc(pitchOval, 0-pitch/2, 180+(pitch), false, markerPaint);
	    markerPaint.setStyle(Paint.Style.STROKE);
    
  }
}