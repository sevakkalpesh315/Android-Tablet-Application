package com.maps.andy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class PreBootUp extends Activity {

	ImageView welMaps,welSports,welentertainment,welshop,welsettings,welnews;
	ViewFlipper welFlipper;
	WebView welBrower;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.prebootup);
		
initializeWelcome();


		
	} 
	
public void initializeWelcome(){
		
		welMaps=(ImageView)findViewById(R.id.welMaps);
		welSports=(ImageView)findViewById(R.id.welSports);
		welentertainment=(ImageView)findViewById(R.id.welEntertainment);
		welshop=(ImageView)findViewById(R.id.welShopping);
	//PreBootUp.	welsettings=(ImageView)findViewById(R.id.welSettings);
		welnews=(ImageView)findViewById(R.id.welNews);
		
		welBrower=(WebView)findViewById(R.id.prebootup);
		//welFlipper=(ViewFlipper)findViewById(R.id.mainFlipper);
		
		//vf.setVisibility(View.GONE);
		
		
	}



}
