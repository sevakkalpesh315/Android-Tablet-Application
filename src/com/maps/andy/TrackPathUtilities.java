package com.maps.andy;

import android.content.Context;
import android.graphics.Paint;

/**
 * Various utility functions for TrackPath painting.
 *
 * @author Vangelis S.
 */
public class TrackPathUtilities {
  
  public static Paint getPaint(int id, Context context) {
    Paint paint = new Paint();
    paint.setColor(context.getResources().getColor(id));
    paint.setStrokeWidth(3);
    paint.setStyle(Paint.Style.STROKE);
    paint.setAntiAlias(true);
    return paint;
  }
}
