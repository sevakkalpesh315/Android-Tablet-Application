package com.maps.andy;

import static com.maps.andy.Constants.TAG;

import com.maps.andy.Constants;
//import com.maps.andy.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * A factory for TrackPathPainters.
 *
 * @author Vangelis S.
 */
public class TrackPathPainterFactory {

  private TrackPathPainterFactory() {
  }

  /**
   * Get a new TrackPathPainter.
   * @param context Context to fetch system preferences.
   * @return The TrackPathPainter that corresponds to the track color mode setting.
   */
  public static TrackPathPainter getTrackPathPainter(Context context) {
    SharedPreferences prefs =
        context.getSharedPreferences(Constants.SETTINGS_NAME, 0);
    if (prefs == null) {
      return new SingleColorTrackPathPainter(context);
    }
	return null;
    
   

   
  }
}