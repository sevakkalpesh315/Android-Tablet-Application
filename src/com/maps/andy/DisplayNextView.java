package com.maps.andy;



import com.google.android.maps.MapView;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public class DisplayNextView implements AnimationListener {
	private boolean mCurrentView;
	ImageView idea,dominoz;
	MapView map;
	RelativeLayout main;
	public DisplayNextView(boolean currentView, RelativeLayout main, ImageView idea) {
	mCurrentView = currentView;
	this.main= main;
	this.idea = idea;
	}
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		//if(mCurrentView == true)
		   main.post(new SwapViews(mCurrentView, main, idea));        
		// com.maps.andy.AndyActivity.mapView.invalidate();
	//	else  idea.post(new SwapViews(mCurrentView, main, idea));
		//main.post(new SwapViews(mCurrentView, main, vf));*/
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		// com.maps.andy.AndyActivity.mapView.invalidate();
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		// com.maps.andy.AndyActivity.mapView.invalidate();
	}

}
