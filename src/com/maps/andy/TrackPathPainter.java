package com.maps.andy;

import com.maps.andy.MapOverlay.CachedLocation;
import com.google.android.maps.Projection;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

import java.util.List;

/**
 * An interface for classes which paint the track path. 
 *
 * @author Vangelis S.
 */
public interface TrackPathPainter {
        
  /**
   * Clears the related data.
   */
  void clear();
  
  /**
   * Draws the path to the canvas.
   * @param canvas The Canvas to draw upon
   */
  void drawTrack(Canvas canvas);
  
  /**
   * Updates the path.
   * @param projection The Canvas to draw upon.
   * @param viewRect The Path to be drawn.   
   * @param startLocationIdx The start point from where update the path.
   * @param alwaysVisible Flag for alwaysvisible.
   * @param points The list of points used to update the path.
   */
  void updatePath(Projection projection, Rect viewRect, int startLocationIdx,
      Boolean alwaysVisible, List<CachedLocation> points);
  
  /**
   * @return True if the path needs to be updated.
   */
  boolean needsRedraw();
  
  /**
   * @return The path being used currently.
   */
  Path getLastPath();
}


