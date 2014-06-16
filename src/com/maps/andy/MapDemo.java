package com.maps.andy;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapDemo extends MapActivity {
MapView mapView;
LocationManager locationManager;
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		
		setContentView(R.layout.mappy);
		
		 mapView = (MapView) findViewById(R.id.mapview1);
	        
			mapView.setBuiltInZoomControls(true);
			//this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			// videoView = (VideoView) findViewById(R.id.videoView1);
			//videoView.setVisibility(View.GONE);
			//sate=(Button)findViewById(R.id.Button01);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
