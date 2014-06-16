package com.maps.andy;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.maps.andy.AnimationFactory.FlipDirection;

public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {
	 private boolean isFirstImage = true;
	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;
MapView map;
ImageView idea;
	public MyItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
	}

	public void addOverlay(OverlayItem overlay) {
		m_overlays.add(overlay);
		
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}
	/* void applyRotation(float start, float end) {
			// Find the center of image
			final float centerX =  com.maps.andy.AndyActivity.mapView.getWidth() / 2.0f;
			final float centerY =  com.maps.andy.AndyActivity.mapView.getHeight() / 2.0f;

			// Create a new 3D rotation with the supplied parameter
			// The animation listener is used to trigger the next animation
			final Flip3dAnimation rotation =
			       new Flip3dAnimation(start, end, centerX, centerY,200.0f,false);
			rotation.setDuration(500);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new AccelerateInterpolator());
			rotation.setAnimationListener(new DisplayNextView(isFirstImage, mapView,  com.maps.andy.AndyActivity.idea));
			if (isFirstImage)
			{
				 com.maps.andy.AndyActivity.main.startAnimation(rotation);
			} else {
				 com.maps.andy.AndyActivity.vf.startAnimation(rotation);
			}

			}*/
	@Override
	protected boolean onBalloonTap(int index) {
		
			 
			  
		Toast.makeText(c, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();
		switch (index) {
		case 0: {
			AndyActivity an = new AndyActivity();
			 AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf, FlipDirection.RIGHT_LEFT);
			// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf, FlipDirection.RIGHT_LEFT);
			// Intent myintent = new Intent("com.maps.andy.DOMINOZ");
			//c.startActivity(myintent);
			//an.vf = (ViewFlipper) findViewById(R.id.details);
			//com.maps.andy.AndyActivity.vf.setAnimation(AnimationUtils.loadAnimation(
				//	mapView.getContext(), R.anim.hyper));
			/*if (isFirstImage) {   
				//AndyActivity an=new AndyActivity();
			     an.applyRotation(0, 90);
			     isFirstImage = !isFirstImage;
			   //com.maps.andy.AndyActivity.vf.setDisplayedChild(0);

			    } else {    
			     an.applyRotation(0, -90);
			     isFirstImage = !isFirstImage;
			    // com.maps.andy.AndyActivity.mapView.setVisibility(View.VISIBLE);
			     //com.maps.andy.AndyActivity.vf.setDisplayedChild(0);
			    }
			       */
			   /*  applyRotation(0, 90);
			    // isFirstImage = !isFirstImage;
			     com.maps.andy.AndyActivity.vf.setDisplayedChild(2);
			     com.maps.andy.AndyActivity.vf.setVisibility(View.VISIBLE);*/
			   
			   
			    
			//com.maps.andy.AndyActivity.vf.setDisplayedChild(2);
			// Set an animation from res/anim: I pick push left in
			// com.maps.andy.AndyActivity.vf.setVisibility(View.VISIBLE);
			 //com.maps.andy.AndyActivity.vf.requestFocus();
		
return true;
		}
		case 1:{
			AndyActivity an = new AndyActivity();
			 AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf, FlipDirection.RIGHT_LEFT);
			// Intent myintent = new Intent("com.maps.andy.DOMINOZ");
			//c.startActivity(myintent);
			//an.vf = (ViewFlipper) findViewById(R.id.details);
		//com.maps.andy.AndyActivity.vf.setAnimation(AnimationUtils.loadAnimation(
		/*	if (isFirstImage) {       
			     an.applyRotation(0, 90);
			     isFirstImage = !isFirstImage;
			     //com.maps.andy.AndyActivity.main.setVisibility(View.GONE);
			     com.maps.andy.AndyActivity.vf.setDisplayedChild(1);
			     com.maps.andy.AndyActivity.vf.setVisibility(View.VISIBLE);
			   //  com.maps.andy.AndyActivity.main.setVisibility(View.GONE);
			   //  com.maps.andy.AndyActivity.idea.setVisibility(View.VISIBLE);
			   //  com.maps.andy.AndyActivity.vf.setAnimation(AnimationUtils.loadAnimation(c, R.anim.hyper));
			    } else {    
			     an.applyRotation(0, -90);
			     isFirstImage = !isFirstImage;
			     com.maps.andy.AndyActivity.vf.setDisplayedChild(1);
			     com.maps.andy.AndyActivity.vf.setVisibility(View.VISIBLE);
			    }*/
			     //com.maps.andy.AndyActivity.vf.setVisibility(View.VISIBLE);
		//com.maps.andy.AndyActivity.vf.setDisplayedChild(3);
			//Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse("http://www.youtube.com/watch?v=v1bfV9SiRY0&feature=related") );

			 // c.startActivity(i);
	
			//c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=0bh3HP51rJs")));
		}
		}
		return true;
	}

	public void addOverlay(OverlayItem[] overlayItem) {
		// TODO Auto-generated method stub
		
	}

	

}
