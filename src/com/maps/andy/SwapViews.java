package com.maps.andy;

import com.google.android.maps.MapView;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public final class SwapViews implements Runnable {
private boolean mIsFirstView;
ImageView idea,dominoz;
MapView map;
RelativeLayout main;
public SwapViews(boolean isFirstView, RelativeLayout main, ImageView idea) {
 mIsFirstView = isFirstView;
 this.main = main;
 this.idea = idea;
}

public void run() {
	Flip3dAnimation rotation;
// final float centerX = main.getWidth() / 2.0f;
 //final float centerY = main.getHeight() / 2.0f;
 final float centerX, centerY;
 if(mIsFirstView) {
     centerX = main.getWidth() / 2.0f;
     centerY = main.getHeight() / 2.0f;
    main.setVisibility(View.GONE);
     
     rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
     com.maps.andy.AndyActivity.idea.setVisibility(View.VISIBLE);
     com.maps.andy.AndyActivity.idea.requestFocus();
 } else {
     centerX = idea.getWidth() / 2.0f;
     centerY = idea.getHeight() / 2.0f;
     com.maps.andy.AndyActivity.idea.setVisibility(View.GONE);
     main.setVisibility(View.VISIBLE);
     main.requestFocus();
     rotation = new Flip3dAnimation(90, 0, centerX, centerY);
 }
 

 
 

    // rotation = new Flip3dAnimation(90, 0, centerX, centerY,200.0f,true);
 

 rotation.setDuration(800);
 rotation.setFillAfter(true);
 rotation.setInterpolator(new DecelerateInterpolator());

 if (mIsFirstView) {
	idea.startAnimation(rotation);
 // vf.showPrevious();
 } else {
  main.startAnimation(rotation);
 // map.setDisplayedChild(0);
 }
}
}